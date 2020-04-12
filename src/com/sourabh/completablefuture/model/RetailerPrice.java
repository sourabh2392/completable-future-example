package com.sourabh.completablefuture.model;

public class RetailerPrice {
	
	private String api;
	
	private Double price;

	public RetailerPrice(String api, Double price) {
		super();
		this.api = api;
		this.price = price;
	}

	@Override
	public String toString() {
		return "RetailerPrice [api=" + api + ", price=" + price + "]";
	}
}
