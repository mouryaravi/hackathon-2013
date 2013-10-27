package com.ravimd.admon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravimd.admon.model.VideoResponse;
import com.ravimd.admon.models.VideoRequest;

@Service
public class CodeProcessorService {
  @Autowired
  CodeProcessor processor;
  
  @Autowired
  BarcodeService barcodeService;
  
  public static String VIDEO_OUT_DIRECTORY = "./videos/out/";
  public static String VIDEO_INPUT_DIRECTORY = "./videos/";
  public static String BARCODE_OUT_DIRECTORY = "./barcodes/";

  public VideoResponse processVideo(String id) {
    System.out.println("Will be searching for video: "
        + VIDEO_INPUT_DIRECTORY + id + ".mp4");
    String videoPath = VIDEO_INPUT_DIRECTORY + id + ".mp4";
    String barcodePath = BARCODE_OUT_DIRECTORY + id + ".png";
    String videoOutputPath = VIDEO_OUT_DIRECTORY + id + ".flv";
    
    VideoRequest request = new VideoRequest();
    request.setBarcodeImagePath(barcodePath);
    request.setId(id);
    request.setInputVideoPath(videoPath);
    request.setOutputVideoPath(videoOutputPath);
    
    barcodeService.processBarcodeImage(id, barcodePath);
    processor.processVideo(request);

    VideoResponse resp = new VideoResponse();
    resp.setId(id);
    resp.setUrl(videoOutputPath);
    return resp;
  }

}