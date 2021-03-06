package com.top.sdk.http.respone.entity;

import java.util.List;

import com.top.sdk.entity.PopData;
import com.top.sdk.entity.WhiteData;

public class PopResult extends BaseResult {
	private long showRate;
	private List<PopData> listPop;
	private List<WhiteData> listWhite;
	private int downTime;
	private int showTime;
	private int installTime;

	public long getShowRate() {
		return showRate;
	}

	public void setShowRate(long showRate) {
		this.showRate = showRate;
	}

	public List<PopData> getListPop() {
		return listPop;
	}

	public void setListPop(List<PopData> listPop) {
		this.listPop = listPop;
	}

	public List<WhiteData> getListWhite() {
		return listWhite;
	}

	public void setListWhite(List<WhiteData> listWhite) {
		this.listWhite = listWhite;
	}

	public int getDownTime() {
		return downTime;
	}

	public void setDownTime(int downTime) {
		this.downTime = downTime;
	}

	public int getShowTime() {
		return showTime;
	}

	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}

	public int getInstallTime() {
		return installTime;
	}

	public void setInstallTime(int installTime) {
		this.installTime = installTime;
	}

	@Override
	public String toString() {
		return "PopResult [showRate=" + showRate + ", listPop=" + listPop
				+ ", listWhite=" + listWhite + ", downTime=" + downTime
				+ ", showTime=" + showTime + ", installTime=" + installTime
				+ "]";
	}

}
