package org.somenonprofit.pack1;

public abstract class AbstractWithMain {
	public String recipient;
	// this line gives compile time error :: 
	// public abstract final void sendMessage();
	public static void main(String[] args) {
		System.out.println("main method can be contained in an abstract class and program will run normally");
	}
}