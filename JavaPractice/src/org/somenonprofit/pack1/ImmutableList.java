package org.somenonprofit.pack1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImmutableList {

	public static void main(String[] args) {
		System.out.println("list demo");
		List<String> listOriginal = new ArrayList<>();
		listOriginal.add("vishal");
		print(listOriginal);
		java.util.List<String> list = Collections.unmodifiableList(listOriginal);
		System.out.println("calling second time print");
		print(list);
		System.out.println(Arrays.toString(list.toArray()));
	}
	private static void print(java.util.List<String> list) {
		System.out.println(list.get(0));
		try {
			list.add("patel-1");
			list.add("patel-2");
			System.out.println("done..2");
		} catch (UnsupportedOperationException ex) {
			System.out.println("error adding element in immutable list");
		}
		list = new ArrayList<>();
		list.add("patel");
	}
}
