package com.sourabh.completablefuture.retailer.strategy.factory;

import com.sourabh.completablefuture.retailer.strategy.AmazonRetailerStrategy;
import com.sourabh.completablefuture.retailer.strategy.ArgoRetailerStrategy;
import com.sourabh.completablefuture.retailer.strategy.RetailerStrategy;

public class RetailerStrategyFactory {

	private RetailerStrategyFactory() {
		super();
	}

	public static RetailerStrategy getRetailerStrategy(String retailer) {
		RetailerStrategy retailerStrategy;
		switch (retailer) {
		case "Amazon":
			retailerStrategy = new AmazonRetailerStrategy();
			break;

		case "Argo":
			retailerStrategy = new ArgoRetailerStrategy();
			break;

		default:
			retailerStrategy = new AmazonRetailerStrategy();
		}
		return retailerStrategy;
	}
}
