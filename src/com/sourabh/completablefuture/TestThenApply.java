package com.sourabh.completablefuture;

import java.util.concurrent.CompletableFuture;

/*
 * The CompletableFuture.get() method is blocking. It waits until the Future is completed and returns the result after its completion.
 * 
 * But, that’s not what we want right? 
 * 
 * For building asynchronous systems, we want to attach a callback to the CompletableFuture which should automatically get called when the Future completes.
 * so that we won’t need to wait for the result, and we can write the logic that needs to be executed after the completion of the Future inside our callback function.
 * 
 * How can you attach callback to the CompletableFuture?
 * 
 * We can attach a callback to the CompletableFuture using thenApply(), thenAccept() and thenRun() methods.
 * 
 */

public class TestThenApply {

	public static void main(String[] args) throws Exception {
		
		/*
		 * CompletableFuture.thenApply(Function<T,R> fun)
		 * 
		 * It takes Function<T, R> as an argument so its purpose is same as Function interface.
		 * Its purpose is to process and transform the result of CompletableFuture when it arrives.
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
		}).thenApply((str) -> {
			/*
			 * Executed in the same thread where the supplyAsync() task is executed
			 * or in the main thread If the supplyAsync() task completes immediately
			 */
			System.out.println("Run in thread : " +Thread.currentThread().getName());
			return str.length();
		});
		
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		System.out.println(supplyAsyncResult.get());
		System.out.println("Is Future completed : " +supplyAsyncResult.isDone());
		
	}
}
