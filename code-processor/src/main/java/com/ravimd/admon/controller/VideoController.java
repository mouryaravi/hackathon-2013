package com.ravimd.admon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ravimd.admon.model.VideoResponse;
import com.ravimd.admon.service.CodeProcessorService;

@Controller
public class VideoController {
  
  @Autowired
  CodeProcessorService codeService;

  @RequestMapping(value = "/{id}/video.json", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public VideoResponse processVideo(@PathVariable String id,
      HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    System.out.println("***** Video controller **** for id: " + id);
    VideoResponse resp =  codeService.processVideo(id);
    System.out.println("Reponse: " + resp.toString());
    return resp;
  }
}
