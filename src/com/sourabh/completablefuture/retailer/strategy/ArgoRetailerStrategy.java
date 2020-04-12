package com.sourabh.completablefuture.retailer.strategy;

import java.util.concurrent.CompletableFuture;

public class ArgoRetailerStrategy implements RetailerStrategy {
	
	public ArgoRetailerStrategy() {
		super();
	}

	@Override
	public CompletableFuture<Double> fetchProductPrice(String productName) {
		return CompletableFuture.supplyAsync(() -> {
			// Assume fetching product price from argo remote api
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 370.00;
		});
	}
}
