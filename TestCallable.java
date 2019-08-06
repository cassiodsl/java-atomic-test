package com.javastudy.calculator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCallable {

	static volatile AtomicInteger x = new AtomicInteger(0);

	public static void main(String args[]) throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(5);

		Future tas = executor.submit(() -> {
			System.out.println("running runnable step A");
			System.out.println("1X = " + x.incrementAndGet()); //1
		});

		//What is the result offered via future task with Runnable that does not return.
		Object object = tas.get();
		System.out.println(object);// null

		Future<Integer> futureTask1 = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("running callable step B");
				System.out.println("B X = " + x.incrementAndGet()); // 2
				return x.incrementAndGet();
			}
		});

		Future<Integer> futureTask2 = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("running callable step C");
				System.out.println("C X = " + x.incrementAndGet()); // 5
				return x.incrementAndGet();
			}
		});

		otherTask("main thread step D");

		System.out.println("4X = " + x.incrementAndGet()); // 4

		Integer result = futureTask1.get(3, TimeUnit.SECONDS);
		System.out.println("Result of step B");
		System.out.println("B X = " + result); // 3
		
		Integer result1 = futureTask2.get(1, TimeUnit.SECONDS);
		System.out.println("Result of step C");
		System.out.println("C X = " + result1); // 6

		otherTask("step E");
		System.exit(0);
	}

	private static void otherTask(String string) {
		System.out.println(string);

	}

}
