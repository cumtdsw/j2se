package container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) {
			list.add(i * i);
		}
		
		Iterator<Integer> it = list.iterator();
		
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i);
		}
		
		List<Object> list2 = new LinkedList<Object>();
	}
}
