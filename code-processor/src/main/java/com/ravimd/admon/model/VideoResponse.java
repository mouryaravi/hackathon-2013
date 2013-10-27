package com.ravimd.admon.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.ToString;


@ToString
@Data
@JsonSerialize
public class VideoResponse {
  private String url;
  private String id;
}
