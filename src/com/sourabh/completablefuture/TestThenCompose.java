package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;

import com.sourabh.completablefuture.model.User;

/*
 * Combine two dependent Futures using thenCompose()
 * 
 * It is used to combine two Futures where one future is dependent on the other.
 * 
 * If your callback function returns a CompletableFuture, and you want a flattened result 
 * from the CompletableFuture chain, then use thenCompose() method.
 * 
 * e.g., 
 * 
 * Let’s say that you want to fetch the details of a user from a remote API service and 
 * once the user’s detail is available, you want to fetch his Credit rating from another 
 * service.
 * 
 */
public class TestThenCompose {

	public static void main(String[] args) throws Exception {
		
		CompletableFuture<Double> composedFuture = getUserDetails("1230").thenCompose(user -> {
			return getCreditRating(user);
		});
		
		System.out.println(composedFuture.get());
	}
	
	private static CompletableFuture<User> getUserDetails(String userId) {
		return CompletableFuture.supplyAsync(() -> {
			// calling remote service to fetch user details
			//return UserService.getUserDetails(userId);
			return new User(userId, "Ram");
		});
	}
	
	private static CompletableFuture<Double> getCreditRating(User user) {
		return CompletableFuture.supplyAsync(() -> {
			// calling another service to fetch user's credit rating
			//return CreditRatingService.getCreditRating(user);
			return 7.5;
		});
	}
}
