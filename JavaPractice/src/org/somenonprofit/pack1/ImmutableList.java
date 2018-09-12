package org.somenonprofit.pack1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * demonstrates that List can be passed as immutable list in a method argument to disallow that method
 * to modify that list, no add,remove,set operations allowed to that method on that list.
 * If It tries to do, will throw UnsupportedOperationException.
 * <br>
 * Output  <br>
list demo <br>
vishal <br>
done..2 <br>
calling second time print <br>
vishal <br>
error adding element in immutable list <br>
[vishal, patel-1, patel-2]  <br>

 * @author VISHAL
 *
 */
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
