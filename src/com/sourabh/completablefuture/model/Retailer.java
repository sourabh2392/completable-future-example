package com.sourabh.completablefuture.model;

public class Retailer {
	
	private String id;
	
	private String name;

	public Retailer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
