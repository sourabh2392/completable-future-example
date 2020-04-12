package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;

/*
 * Combine two independent Futures using thenCombine()
 * 
 * It is used when you want two Futures to run independently and do something after both are complete.
 * 
 * e.g.,
 * 
 * Calculate BMI of user
 * 
 */
public class TestThenCombine {

	public static void main(String[] args) throws Exception {
		
		CompletableFuture<Double> combinedFuture = getWeightInKg().thenCombine(getHeightInCm(), (weightInKg, heightInCm) -> {
			Double heightInMeter = heightInCm/100;
		    return weightInKg / (heightInMeter * heightInMeter);
		});
		
		System.out.println(combinedFuture.get());
	}
	
	private static CompletableFuture<Double> getWeightInKg() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 65.0;
		});
	}
	
	private static CompletableFuture<Double> getHeightInCm() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 177.8;
		});
	}
}
