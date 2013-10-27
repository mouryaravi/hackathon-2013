package com.ravimd.admon.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ShortBuffer;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.ravimd.admon.models.VideoRequest;
import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaTool;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.MediaToolAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IAudioSamplesEvent;
import com.xuggle.mediatool.event.IVideoPictureEvent;

@Service
public class CodeProcessor {

  public void processVideo(VideoRequest request) {

    // create a media reader
    IMediaReader mediaReader = ToolFactory.makeReader(request
        .getInputVideoPath());

    // configure it to generate BufferImages
    mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

    IMediaWriter mediaWriter = ToolFactory.makeWriter(
        request.getOutputVideoPath(), mediaReader);

    IMediaTool imageMediaTool = new StaticImageMediaTool(
        request.getBarcodeImagePath());
    IMediaTool audioVolumeMediaTool = new VolumeAdjustMediaTool(0.1);

    // create a tool chain:
    // reader -> addStaticImage -> reduceVolume -> writer
    mediaReader.addListener(imageMediaTool);
    imageMediaTool.addListener(audioVolumeMediaTool);
    audioVolumeMediaTool.addListener(mediaWriter);

    while (mediaReader.readPacket() == null)
      ;

  }

  private class StaticImageMediaTool extends MediaToolAdapter {

    private BufferedImage logoImage;

    public StaticImageMediaTool(String imageFile) {

      try {
        logoImage = ImageIO.read(new File(imageFile));
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Could not open file");
      }

    }

    @Override
    public void onVideoPicture(IVideoPictureEvent event) {

      BufferedImage image = event.getImage();

      // get the graphics for the image
      Graphics2D g = image.createGraphics();

      Rectangle2D bounds = new Rectangle2D.Float(0, 0, logoImage.getWidth(),
          logoImage.getHeight());

      // compute the amount to inset the time stamp and
      // translate the image to that position
      double inset = bounds.getHeight();
      g.translate(inset, event.getImage().getHeight() - inset);

      g.setColor(Color.WHITE);
      g.fill(bounds);
      g.setColor(Color.BLACK);
      g.drawImage(logoImage, 0, 0, null);

      // call parent which will pass the video onto next tool in chain
      super.onVideoPicture(event);

    }

  }

  private class VolumeAdjustMediaTool extends MediaToolAdapter {

    // the amount to adjust the volume by
    private double mVolume;

    public VolumeAdjustMediaTool(double volume) {
      mVolume = volume;
    }

    @Override
    public void onAudioSamples(IAudioSamplesEvent event) {

      // get the raw audio bytes and adjust it's value
      ShortBuffer buffer = event.getAudioSamples().getByteBuffer()
          .asShortBuffer();

      for (int i = 0; i < buffer.limit(); ++i) {
        buffer.put(i, (short) (buffer.get(i) * mVolume));
      }

      // call parent which will pass the audio onto next tool in chain
      super.onAudioSamples(event);

    }

  }

}