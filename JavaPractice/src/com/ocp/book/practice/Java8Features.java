package com.ocp.book.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * @author VISHAL
 *
 */
public class Java8Features {

	public static class Data {
		public Data(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int id;
		public String name;
		@Override
		public String toString() {
			return "Data [id=" + id + ", name=" + name + "]";
		}
	}
	
	public static void main(String[] args) {
		
		List<Data> allData = new ArrayList<Java8Features.Data>();
		allData.addAll(Arrays.asList(
				new Data(1, "a"), new Data(2, "b"), new Data(3, "c"),
				new Data(4, "d"), new Data(5, "e"), new Data(6, "f"),
				new Data(7, "x"), new Data(8, "y"), new Data(9, "z")
		));
		
		Java8Features app = new Java8Features();
		app.filter(allData);
		app.map(allData);
		app.reduce();
	}
	public void filter(List<Data> list) {
		System.out.println("Demo .filter method");
		// filter items and get new list
		List<Data> filtered = list.stream().filter(item -> item.id %3 == 0).collect(Collectors.toList());
		System.out.println("Data Object first result where filter id%3==0" + filtered.get(0));
		// get only count of filtered items
		long count = list.stream().filter(item -> item.id %3 == 0).count();
		System.out.println("No. of result with id%3==0"+count);
	}
	public void map(List<Data> list) {
		// collect specific fields of a class of list
		Set<Integer> ids = list.stream().map(item -> { return item.id ; }).collect(Collectors.toSet());
		System.out.println(ids.iterator().next());
		// get count only of id which is %2 is equals to 0
		long count = list.stream().map(item -> { return item.id ; }).filter(x -> x % 2 == 0).count();
		System.out.println(count);
		// convert List to Map where id would be key and name would be value
		// here in map function Data::getName can work if we want to return name only
		Map<Integer, String> map = list.stream().map(item -> { return item; } )
			.collect(Collectors.toMap(
					item -> { return item.id ; },  // works as KEY
					item -> { return item.name; } // works as VALUE
					));
		System.out.println(map.get(new Integer(1)));
	}
	public void reduce() {
		// Start range is inclusive, end range in exclusive
		OptionalInt reduced =
				  IntStream.range(1, 6).reduce((a, b) -> a + b);
		System.out.println("Sum of 1,2,3,4...5 " + reduced);
	}
}
