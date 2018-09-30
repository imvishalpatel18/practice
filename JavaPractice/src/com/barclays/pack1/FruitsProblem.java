package com.barclays.pack1;

/**
 * 
 * Three types of fruits f1, f2, f3 each gives 2, 3, 5 points energy respectively.
 * To get the expected energy (first argument S in method) with some combination of different
 * fruits, we have to find the minimum cost we can spend so that we get desired energy
 * with constraint given maximum number of fruits count given by considering provided
 * costs for each of them.
 * 
 * @author VISHAL
 *
 */
public class FruitsProblem {

	public static void main(String[] args) {
		int sum = findCheapestCombination(10, 2, 6, 8, 5, 5, 10);
		System.out.println(sum);
	}
	static int t1 = 2;
	static int t2 = 3;
	static int t3 = 5;
	static int min = Integer.MAX_VALUE;
	static int findCheapestCombination(int S, int count1, int count2, int count3, int cost1, int cost2, int cost3) {
		
		int f1 = count1;
		int f2 = count2;
		int f3 = count3;
		
		int counter = 3;
		for (; f1 >= 0 && f2 >= 0 && f3 >= 0 ;) {
			
			int energy = calculateEnergy(f1, f2, f3);
			int cost = calculateCost(f1, f2, f3, cost1, cost2, cost3);
			System.out.println("energy="+energy);
			System.out.println("cost="+cost);
			if (min > cost) {
				min = cost;
			}
			System.out.println("min=" + min);
			if (energy == S) {
				System.out.println(String.format("a=%d b=%d c=%d", f1, f2, f3));
				break;
			}
			// TODO: condition for deciding which counter to decrement
			if (counter % 3 == 0) {
				f3--;
			} else if (counter % 2 == 0) {
				f2--;
			} else {
				f1--;
			}
			counter++;
		}
		
		return min;
	}
	static int calculateEnergy(int count1, int count2, int count3) {
		return (t1*count1) + (t2*count2) + (t3*count3);
	}
	static int calculateCost(int count1, int count2, int count3, int cost1, int cost2, int cost3) {
		return (count1*cost1) + (count2*cost2) + (count3*cost3);
	}
}
