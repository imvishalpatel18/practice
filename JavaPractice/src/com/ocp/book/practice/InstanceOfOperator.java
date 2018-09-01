package com.ocp.book.practice;

public class InstanceOfOperator {

	public static void main(String[] args) {
		
		// use of instance of operator with abstract class
		HeavyAnimal hippo = new Hippo();
		System.out.println(hippo instanceof HeavyAnimal); // true
		HeavyAnimal elephant = new Elephant();
		System.out.println(elephant instanceof HeavyAnimal); // true
		System.out.println(hippo instanceof Elephant); // false
		
		// now create specific object reference
		Hippo hippo2 = new Hippo();
		// below code does not compile ::: Incompatible conditional operand types Hippo and Elephant
//		System.out.println(hippo2 instanceof Elephant);
		
		// now in the case of interface it does not give compile time error, as it might be implemented later
		System.out.println(hippo2 instanceof Animal);  // but it will give false only
	}
	
	public static abstract class HeavyAnimal {	
	}
	public static class Hippo extends HeavyAnimal {
	}
	public static class Elephant extends HeavyAnimal {
	}
	public static interface Animal {
		
	}
}
