package com.javastudy.calculator;

public class Test003 {
	
	private static volatile boolean sayHello = false;
	
	public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
           while(!sayHello) {
           }

           System.out.println("3");

           while(sayHello) {
           }

           System.out.println("4");
        });

        thread.start();

        Thread.sleep(1000);
        System.out.println("1");
        sayHello = true;

        Thread.sleep(1000);
        System.out.println("2");
        sayHello = false;
    }

}
