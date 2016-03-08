package hometasks.Comparison;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hometasks.Exceptions.NullArgumentException;

public class ListInsertion {
	private List<Integer> list;
	
	public ListInsertion() {
	}
	
	public ArrayList<Integer> generateArrayList(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0, 1);
		list.add(1, 2);
		for (int i = 3; i <= n; i++) {
			int index = list.size()/2;
			list.add(index, i);
			//System.out.println("i = " + i + list.toString());
		}
		this.list = list;
		//System.out.println("n = " + n + list.toString());
		return list;
	}
	
	public LinkedList<Integer> generateLinkedList(int n) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0, 1);
		list.add(1, 2);
		for (int i = 3; i <= n; i++) {
			int index = list.size()/2;
			list.add(index, i);
			//System.out.println("i = " + i + list.toString());
		}
		this.list = list;
		//System.out.println("n = " + n + list.toString());
		return list;
	}
	
	public void removeFromList() {
		if (this.list == null) {
			throw new NullArgumentException("List is null.");
		}
		while (!this.list.isEmpty()) {
			int index = this.list.size()/2;
			this.list.remove(index);
			//System.out.println(this.list);
		}
	}
	
	public static double compareInsertPerformance(int n) {
		double times = 0.0;
		long before = System.nanoTime();
		ListInsertion list1 = new ListInsertion();
		list1.generateArrayList(n);
		long after = System.nanoTime();
		long deltaArrayList = after - before;
		// System.out.println(text1);
		System.out.println("\n" + n + " numbers:");
		System.out.println("ArrayList add() execution time: "
				+ (double) ((int) Math.round(1000000 * (deltaArrayList * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ deltaArrayList + " nanoseconds");
 
		before = System.nanoTime();
		list1.generateLinkedList(n);
		after = System.nanoTime();
		long deltaLinkedList = after - before;
		// System.out.println(text2);
		System.out.println("LinkedList add() execution time: "
				+ (double) ((int) Math.round(1000000 * (deltaLinkedList * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ deltaLinkedList + " nanoseconds");
		if (deltaArrayList == deltaLinkedList) {
			System.out.println("add() performance is equal.");
		}
		if (deltaArrayList > deltaLinkedList) {
			double temp1 = (deltaArrayList * 1.0) / deltaLinkedList;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("LinkedList add() is quicker in " + times + " times.");
		} else {
			double temp1 = (deltaLinkedList * 1.0) / deltaArrayList;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("ArrayList add() is quicker in " + times + " times.");
		}

		return times;
	}
	
	public static double compareRemovePerformance(int n) {
		double times = 0.0;
		ListInsertion list1 = new ListInsertion();
		list1.generateArrayList(n);
		long before = System.nanoTime();
		list1.removeFromList();
		long after = System.nanoTime();
		long deltaArrayList = after - before;
		// System.out.println(text1);
		System.out.println("\n" + n + " numbers:");
		System.out.println("ArrayList remove() execution time: "
				+ (double) ((int) Math.round(1000000 * (deltaArrayList * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ deltaArrayList + " nanoseconds");
 
		list1.generateLinkedList(n);
		before = System.nanoTime();
		list1.removeFromList();
		after = System.nanoTime();
		long deltaLinkedList = after - before;
		// System.out.println(text2);
		System.out.println("LinkedList remove() execution time: "
				+ (double) ((int) Math.round(1000000 * (deltaLinkedList * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ deltaLinkedList + " nanoseconds");
		if (deltaArrayList == deltaLinkedList) {
			System.out.println("Remove() performance is equal.");
		}
		if (deltaArrayList > deltaLinkedList) {
			double temp1 = (deltaArrayList * 1.0) / deltaLinkedList;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("LinkedList remove() is quicker in " + times + " times.");
		} else {
			double temp1 = (deltaLinkedList * 1.0) / deltaArrayList;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("ArrayList remove() is quicker in " + times + " times.");
		}

		return times;
	}

	public static void main(String[] args) {
		for (int i = 1; i <=10000; i *= 10) {
			ListInsertion.compareInsertPerformance(i);
		}
		for (int i = 1; i <=10000; i *= 10) {
			ListInsertion.compareRemovePerformance(i);
		}
	}
	

}
