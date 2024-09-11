package com.example.demo.response;

public class RecommendedProduct {

	private String type;
	private String safra;
	
	public RecommendedProduct(String type, String safra) {
		super();
		this.type = type;
		this.safra = safra;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSafra() {
		return safra;
	}

	public void setSafra(String safra) {
		this.safra = safra;
	}
	
}
