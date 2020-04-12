package com.sourabh.completablefuture.retailer.strategy;

import java.util.concurrent.CompletableFuture;

public class AmazonRetailerStrategy implements RetailerStrategy {

	public AmazonRetailerStrategy() {
		super();
	}

	@Override
	public CompletableFuture<Double> fetchProductPrice(String productName) {
		return CompletableFuture.supplyAsync(() -> {
			// Assume fetching product price from amazon remote api
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 390.00;
		});
	}
}
