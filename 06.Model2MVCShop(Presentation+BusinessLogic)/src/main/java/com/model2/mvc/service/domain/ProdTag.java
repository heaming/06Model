package com.model2.mvc.service.domain;

public class ProdTag {

	// field
	private int tagNo;
	private String tagName;
	
	
	// ctor
	public ProdTag() {
	}


	// method :: GETTER & SETTER
	public int getTagNo() {
		return tagNo;
	}


	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	@Override
	public String toString() {
		return "ProdTag [tagNo=" + tagNo + ", tagName=" + tagName + "]";
	}

}
