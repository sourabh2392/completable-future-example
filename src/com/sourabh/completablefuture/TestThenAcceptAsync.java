package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestThenAcceptAsync {

	public static void main(String[] args) throws Exception {
		
		/*
		 * CompletableFuture.thenAcceptAsync(Consumer<T> action)
		 * 
		 * It is similar to CompletableFuture.thenAccept(Consumer<T> action) except that it always execute 
		 * the callback task in a separate thread.
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
		}).thenAcceptAsync((str) -> {
			System.out.println("Run in thread : " +Thread.currentThread().getName());
			System.out.println("Length of " +str +" : " +str.length());
		});
		
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		System.out.println(supplyAsyncResult.get());
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		
		System.out.println("------------------------------------------------------------------");
		
		/*
		 * It has one more variation,
		 * 
		 * CompletableFuture.thenAcceptAsync(Consumer<T> action, Executor executor)
		 * 
		 */
		Executor executor = Executors.newFixedThreadPool(3);
		CompletableFuture<Void> supplyAsyncExecutorResult = CompletableFuture.supplyAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		}).thenAcceptAsync((str) -> {
			System.out.println("Run in thread : " +Thread.currentThread().getName());
			System.out.println("Length of " +str +" : " +str.length());
		}, executor);
		
		System.out.println("Is Future completed : " +supplyAsyncExecutorResult.isDone());
		System.out.println(supplyAsyncExecutorResult.get());
		System.out.println("Is Future completed : " +supplyAsyncExecutorResult.isDone());
	}
}
