package com.casinogod.pojo;


public class UserAuthResponse {
	
	private User userinfo;
	private String token = "";
	private String gameServerUrl ="";
	private String snsId;
	private String gameDownloadUrl;
	private String bannerImageUrl;
	private String bannerJumpUrl; 
	
	public User getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getGameServerUrl() {
		return gameServerUrl;
	}
	public void setGameServerUrl(String gameServerUrl) {
		this.gameServerUrl = gameServerUrl;
	}
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public String getGameDownloadUrl() {
		return gameDownloadUrl;
	}
	public void setGameDownloadUrl(String gameDownloadUrl) {
		this.gameDownloadUrl = gameDownloadUrl;
	}
	public String getBannerImageUrl() {
		return bannerImageUrl;
	}
	public void setBannerImageUrl(String bannerImageUrl) {
		this.bannerImageUrl = bannerImageUrl;
	}
	public String getBannerJumpUrl() {
		return bannerJumpUrl;
	}
	public void setBannerJumpUrl(String bannerJumpUrl) {
		this.bannerJumpUrl = bannerJumpUrl;
	}
	
	
	
	
}
