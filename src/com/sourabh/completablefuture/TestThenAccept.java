package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;

public class TestThenAccept {

	public static void main(String[] args) throws Exception {
		
		/*
		 * CompletableFuture.thenAccept(Consumer<T> action)
		 * 
		 * It takes Consumer<T> as an argument so its purpose is same as Consumer interface.
		 * Its purpose is to process the result of CompletableFuture when it arrives and doesn't 
		 * return anything.
		 * 
		 */
		CompletableFuture<Void> supplyAsyncResult = CompletableFuture.supplyAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		}).thenAccept((str) -> {
			System.out.println("Run in thread : " +Thread.currentThread().getName());
			System.out.println("Length of " +str +" : " +str.length());
		});
		
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		System.out.println(supplyAsyncResult.get());
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		
	}
}
