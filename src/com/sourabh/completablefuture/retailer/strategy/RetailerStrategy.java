package com.sourabh.completablefuture.retailer.strategy;

import java.util.concurrent.CompletableFuture;

public interface RetailerStrategy {
	
	CompletableFuture<Double> fetchProductPrice(String productName); 

}
