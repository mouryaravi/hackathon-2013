package com.ravimd.admon.models;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class VideoRequest {
  private String id;
  private String inputVideoPath;
  private String outputVideoPath;
  private String barcodeImagePath;
}
