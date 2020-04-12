package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestRunAsync {

	public static void main(String[] args) throws Exception {
		
		/*
		 * If you want to run task asynchronously and don’t want to return anything from
		 * the task, then you can use CompletableFuture.runAsync() method. It takes a
		 * Runnable object and returns CompletableFuture<Void>
		 * 
		 */
		CompletableFuture<Void> runAsyncResult = CompletableFuture.runAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println("Is Future completed : " +runAsyncResult.isDone());
		// Block and wait for the future to complete
		System.out.println(runAsyncResult.get());
		System.out.println("Is Future completed : " +runAsyncResult.isDone());
		
		System.out.println("------------------------------------------------------------------------------------------");
		
		/*
		 * In the above example, we never created a thread or ThreadPool executor. How Completablefuture.runAsync() method
		 * execute their tasks in a separate thread? 
		 * 
		 * By default, CompletableFuture.runAsync() executes their tasks in a thread obtained from the global ForkJoinPool.commonPool().
		 * 
		 */
		Executor executor = Executors.newFixedThreadPool(3);
		CompletableFuture<Void> runAsyncExecutorResult = CompletableFuture.runAsync(() -> {
			System.out.println("Run in a separate thread than main thread : " +Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, executor);
		
		System.out.println("Is Future completed : " +runAsyncExecutorResult.isDone());
		// Block and wait for the future to complete
		System.out.println(runAsyncExecutorResult.get());
		System.out.println("Is Future completed : " +runAsyncExecutorResult.isDone());
	}
}
