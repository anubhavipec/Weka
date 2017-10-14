package com.mpactly.vo;

public class GraphVO {
	
	private String crc;
	private String rqe;
	private String mean;
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public String getRqe() {
		return rqe;
	}
	public void setRqe(String rqe) {
		this.rqe = rqe;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	@Override
	public String toString() {
		return "GraphVO [crc=" + crc + ", rqe=" + rqe + ", mean=" + mean + "]";
	}
	
	
	
	

}
