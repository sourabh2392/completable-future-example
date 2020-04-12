package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestSupplyAsync {

	public static void main(String[] args) throws Exception {
		
		/*
		 * If you want to run task asynchronously and also wants to return anything from
		 * the task, then you can use CompletableFuture.supplyAsync() method. It takes a
		 * Supplier object and returns CompletableFuture<R>
		 */
		CompletableFuture<String> supplyAsyncResult = CompletableFuture.supplyAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		});
		
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		// Block and wait for the future to complete
		System.out.println(supplyAsyncResult.get());
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		
		System.out.println("------------------------------------------------------------------------------------------");
		
		/*
		 * Similarly, CompletableFuture.supplyAsync() executes their tasks in a thread obtained from the global ForkJoinPool.commonPool().
		 * 
		 */
		Executor executor = Executors.newFixedThreadPool(3);
		CompletableFuture<String> supplyAsyncExecutorResult = CompletableFuture.supplyAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		}, executor);
		
		System.out.println("Is Future completed : " +supplyAsyncExecutorResult.isDone());
		// Block and wait for the future to complete
		System.out.println(supplyAsyncExecutorResult.get());
		System.out.println("Is Future completed : " +supplyAsyncExecutorResult.isDone());
	}
}
