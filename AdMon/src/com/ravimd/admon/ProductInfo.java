package com.ravimd.admon;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductInfo {
	
	private String id;  // Unique id for the produc 
	private String campaignName;  // Name of the add project 
	private String price;  // Price of the product 
	
	// Link for the meta search engine of the prices 
	private String buyUrl;
	private boolean following; 
	private String videoUrl;
	private String picUrl;
	
	
	public ProductInfo(String id, String campaignName, String price, String buyUrl, boolean following, String videoUrl, String picURL) {
		
		this.id = id; 
		this.campaignName = campaignName; 
		this.price = price; 
		this.buyUrl = buyUrl;
		this.following = following; 
		this.videoUrl = videoUrl;
		this.picUrl = picURL;
		
	}
	
	
	public ProductInfo(JSONObject prodInfo) throws JSONException {
	  JSONObject basicInfo = prodInfo.getJSONObject("basic");
    this.campaignName = basicInfo.getString("name");
    this.buyUrl = basicInfo.getString("buyUrl");
    this.price = basicInfo.getString("price");
    this.picUrl = basicInfo.getString("thumbnail");
    this.videoUrl = basicInfo.getString("videoUrl");
  }


  public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBuyUrl() {
		return buyUrl;
	}
	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}
	public boolean isFollowing() {
		return following;
	}
	public void setFollowing(boolean following) {
		this.following = following;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getPicURL() {
		return picUrl;
	}
	public void setPicURL(String picURL) {
		this.picUrl = picURL;
	}

	
}
