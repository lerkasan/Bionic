package hometasks.Generics;

import java.util.ArrayList;
import java.util.Iterator;

public class PrintList<T> {
	
	private ArrayList<T> list;
	
	
	
	public PrintList() {
		this.list = new ArrayList<>();
	}

	public void add(T element) {
		this.list.add(element);
	}
	
	public void printList(boolean isOdd) {
		int counter = 0;
		if (isOdd) {
			counter = 1;
		}
		for (Iterator<T> iter = this.list.iterator(); iter.hasNext(); ) {
			if (counter % 2 == 0) {
				System.out.print(iter.next()+ " ");
			} 
			counter++;
		}
	}
	
	public void printList2(boolean isOdd) {
		if (isOdd) {
			for (int i=1; i < this.list.size(); i+=2) {
				System.out.print(this.list.get(i).toString()+ " ");
			}
		} else {
			for (int i=0; i < this.list.size(); i+=2) {
				System.out.print(this.list.get(i).toString()+ " ");
			}
		}
	}

	public static void main(String[] args) {
		PrintList<String> plS = new PrintList<String>();
		for (int i = 0; i < 10; i++){
			plS.add("" + i);
		}
		plS.printList2(true);

	}

}
