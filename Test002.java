package com.javastudy.calculator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
//	int count = 0;
	AtomicInteger count = new AtomicInteger(0);

	public void increment() {
			count.incrementAndGet();
//		count++;
	}

	public int getCount() {
		return count.get();
//		return count;
	}
}

public class Test002 {

	public static void main(String[] args) throws InterruptedException {
		Test002 test = new Test002();
		Integer result = test.getFinalResultFromThreads();
		System.out.println("Final count is : " + result);
	}

	public Integer getFinalResultFromThreads() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Counter counter = new Counter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> counter.increment());
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);
		return counter.getCount();

	}

}
