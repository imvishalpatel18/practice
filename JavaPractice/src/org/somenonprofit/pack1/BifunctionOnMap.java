package org.somenonprofit.pack1;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Demonstrate use of bi-function on map object using compute methods available.
 * @author VISHAL
 * Output :: <br>

x4 <br>
H <br>
 */
public class BifunctionOnMap {
	
	public static void main(String[] args) {
		Map<String, Data> map = new HashMap<String, Data>();
		map.put("1", new Data(1, "V"));
		map.put("2", new Data(2, "I"));
		map.put("3", new Data(3, "S"));
		map.put("4", new Data(4, "H"));
		// String is key in our map object, Data is value in our map object and last Data is return type of bifunction
		BiFunction<String, Data, Data> mapper = (x,y) -> {
			System.out.println("x" + x);
			if (y.id==4) { 
				return y; 
			} else {
				return null;
			}
		};
		Data data = map.computeIfPresent("4", mapper);
		System.out.println(data.name);
	}

	private static class Data {
		int id;
		String name;
		public Data(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}
}
