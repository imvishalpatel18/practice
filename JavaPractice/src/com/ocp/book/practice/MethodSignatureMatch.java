package com.ocp.book.practice;

import java.io.IOException;

/**
 * primitives are given priority for method signature match. // LINE 1
 * <br> LINE 2
 * Demonstrates the method signature Ambiguity when we use dynamic number of arguments in method signature.
 * @author VISHAL
 *
 */
public class MethodSignatureMatch {

	public static void main(String[] args) {
		m1(1); // LINE 1
		m1(new Integer(2));
		// to call Long type argumented method, we have to explicitly define long argument while calling
		m1(1L);
		
		m1("p");
		// LINE 2
		// m1(1, "a"); // this will give compile time error, due to method signature ambiguity
		// to solve this problem, we have to put our arguments in an array like below code
		String[] q = {"a"};
		m1(10, q);
		m1(10, "", q);
		
		// dynamic instance
		A a = new A();
		m1(a);  // will call with argument type A
		B b = new B();
		m1(b);  // will call with argument type B
		
		A ab = new B();		
		// this will match type reference (will call method with argument type A)
		m1(ab);
		
		// we have to call A's m2() method in try block as it is throwing checked type of Exception (IOException)
		try {
			a.m2();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// B's m2() method is not throwing any type of checked exception so we don't need try-catch block
		b.m2();
		
		// as we already know that class B's method m2() doesn't throw any exception
		// but as dynamic binding happens run time, but compiler knows only type reference A
		// and in class A, method m2() is throwing IOException, so we have to add below try-catch block
		try {
			ab.m2();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void m1(long x) {
		System.out.print("long ");
		System.out.println(x);
	}
	public static void m1(int x) {
		System.out.print("int primitive ");
		System.out.println(x);
	}
	public static void m1(Integer x) {
		System.out.print("integer wrapper ");
		System.out.println(x);
	}
	public static void m1(String x) {
		System.out.print("String single arg ");
		System.out.println(x);
	}
	public static void m1(int x, String...args) {
		System.out.println("second last method");
	}
	public static void m1(int x, String p, String...q) {
		System.out.println("last method");
	}
	
	public static void m1(A a) {
		System.out.println("with argument A");
		a.m();
	}
	public static void m1(B b) {
		System.out.println("with argument B");
		b.m();
	}
	
	public static class A {
		public void m() {
			System.out.println("A's m()");
		}
		// this method is not throwing any exception, so overriding sub class method can not throw any checked exceptions
		public void m1() {
			System.out.println("method m1 without exception");
		}
		public void m2() throws IOException {
			System.out.println("method m2 with exception");
		}
	}
	public static class B extends A {
		public void m() {
			System.out.println("B's m()");
		}
		// this m1 method here can throw IllegalArgumentException even if it is not present in super class A
		// as it is runtime exception type (unchecked exception)
		// if we try to throw IOExcetion which is checked exception, then compiler will not allow that
		public void m1() throws IllegalArgumentException {
			System.out.println("method m1 with exception");
		}
		// it is not throwing any exception, even if super class method is throwing IOException
		// but if this class wants to throw, then it can throw only IOException or any subclass exception(s) only.
		public void m2() {
			System.out.println("method without exception");
		}
	}
}
