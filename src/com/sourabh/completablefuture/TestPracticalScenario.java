package com.sourabh.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.sourabh.completablefuture.model.Retailer;
import com.sourabh.completablefuture.model.RetailerPrice;
import com.sourabh.completablefuture.retailer.strategy.factory.RetailerStrategyFactory;

/**
 * Let’s say that you want to fetch the product price from a remote API service of different-2 
 * retailers asynchronously and once the product price is available then you want to show it on 
 * your online price comparison website.
 *
 */
public class TestPracticalScenario {

	public static void main(String[] args) throws Exception {
		
		String productName = "PlayStation";
		
		Executor threadPool = Executors.newCachedThreadPool();
		
		CompletableFuture<List<RetailerPrice>> thenComposeResult = fetchRetailers().thenCompose(retailerList -> {
			
			// Fetch the product price of all retailers asynchronously.
			List<CompletableFuture<RetailerPrice>> retailerPriceFutures = retailerList.stream().map(retailer -> {
				CompletableFuture<Double> productPriceFuture = RetailerStrategyFactory.getRetailerStrategy(retailer.getName()).fetchProductPrice(productName);
				return productPriceFuture.thenApplyAsync(price -> new RetailerPrice(retailer.getName(), price), threadPool);
			}).collect(Collectors.toList());
			
			// create a combined future
			CompletableFuture<Void> allFutures = CompletableFuture.allOf(retailerPriceFutures.toArray(new CompletableFuture[retailerPriceFutures.size()]));
			
			// When all the Futures are completed, call `future.join()` to get their results and collect the results in a list
			CompletableFuture<List<RetailerPrice>> allRetailersPriceFuture = allFutures.thenApply(v -> retailerPriceFutures.stream().map(retailerPriceFuture -> retailerPriceFuture.join()).collect(Collectors.toList()));
			
			return allRetailersPriceFuture;
		});
		
		System.out.println("Retailer's Price : " +thenComposeResult.get());
	}
	
	private static CompletableFuture<List<Retailer>> fetchRetailers() {
		return CompletableFuture.supplyAsync(() -> {
			return Arrays.asList(new Retailer("1001", "Amazon"), new Retailer("1001", "Argo"));
		});
	}
}
