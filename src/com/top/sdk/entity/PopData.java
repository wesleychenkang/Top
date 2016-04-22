package com.top.sdk.entity;

public class PopData {
	private int id; // 预留字段
	private int popType;
	private String popUrl;
	private String imgUrl;
	private String channelName;
	private String packageName;
	private int showCount;
	private String version;
	private String createTime;
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	// 展示的次数
	/**
	 * 显示的时间间隔
	 */
	private int showRate;
	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	/**
	 * 安装时间
	 */
	private int installTime;
	/**
	 * 下载时间
	 */
	private int downTime; // 下载时间

	public int getShowRate() {
		return showRate;
	}

	public void setShowRate(int showRate) {
		this.showRate = showRate;
	}

	public int getInstallTime() {
		return installTime;
	}

	public void setInstallTime(int installTime) {
		this.installTime = installTime;
	}

	public int getDownTime() {
		return downTime;
	}

	public void setDownTime(int downTime) {
		this.downTime = downTime;
	}

	private WhiteData whiteData; // 关联到指定的白名单系列

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPopType() {
		return popType;
	}

	public void setPopType(int popType) {
		this.popType = popType;
	}

	public String getPopUrl() {
		return popUrl;
	}

	public void setPopUrl(String popUrl) {
		this.popUrl = popUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public WhiteData getWhiteData() {
		return whiteData;
	}

	public void setWhiteData(WhiteData whiteData) {
		this.whiteData = whiteData;
	}

	@Override
	public String toString() {
		return "PopData [id=" + id + ", popType=" + popType + ", popUrl="
				+ popUrl + ", imgUrl=" + imgUrl + ", channelName="
				+ channelName + ", packageName=" + packageName + ", showCount="
				+ showCount + ", version=" + version + ", createTime="
				+ createTime + ", showRate=" + showRate + ", installTime="
				+ installTime + ", downTime=" + downTime + ", whiteData="
				+ whiteData + "]";
	}

	
	
  
}
