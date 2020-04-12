package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestThenApplyAsync {

	public static void main(String[] args) throws Exception {
		
		/*
		 * CompletableFuture.thenApplyAsync(Function<T,R> fun)
		 * 
		 * It is similar to CompletableFuture.thenApply(Function<T,R> fun) except that it always execute 
		 * the callback task in a separate thread.
		 * 
		 */
		CompletableFuture<Integer> supplyAsyncResult = CompletableFuture.supplyAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		}).thenApplyAsync((str) -> {
			/*
			 * Executed in the separate thread from ForkJoinPool.commonPool()
			 */
			System.out.println("Run in thread : " +Thread.currentThread().getName());
			return str.length();
		});
		
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		System.out.println(supplyAsyncResult.get());
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		
		System.out.println("------------------------------------------------------------------");
		
		/*
		 * It has one more variation,
		 * 
		 * CompletableFuture.thenApplyAsync(Function<T,R> fun, Executor executor)
		 * 
		 */
		Executor executor = Executors.newFixedThreadPool(3);
		CompletableFuture<Integer> supplyAsyncExecutorResult = CompletableFuture.supplyAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		}).thenApplyAsync((str) -> {
			/*
			 * Executed in the separate thread from ForkJoinPool.commonPool()
			 */
			System.out.println("Run in thread : " +Thread.currentThread().getName());
			return str.length();
		}, executor);
		
		System.out.println("Is Future completed : " +supplyAsyncExecutorResult.isDone());
		System.out.println(supplyAsyncExecutorResult.get());
		System.out.println("Is Future completed : " +supplyAsyncExecutorResult.isDone());
		
	}
}
