package PartII;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class QueuePipeline {
	
	static class Element implements Comparable<Element> {
		int val;
		LinkedList<Integer> queue; // The queue the integer came from

		Element(int val, LinkedList<Integer> queue) {
			this.val = val;
			this.queue = queue;
		}

		@Override
		public int compareTo(Element x) {
			return Integer.compare(this.val, x.val);
		}
	}

	public static void main(String[] args) {
		Random rand = new Random(3);
		LinkedList<Integer> queue1 = new LinkedList<>();
		LinkedList<Integer> queue2 = new LinkedList<>();
		LinkedList<Integer> queue3 = new LinkedList<>();
		
		for (int i=0;i<10;i++) {
			queue1.offer(rand.nextInt(100));
			queue2.offer(rand.nextInt(100));
			queue3.offer(rand.nextInt(100));
		}
		queue1.sort(null);
		queue2.sort(null);
		queue3.sort(null);
		
		PriorityQueue<Element> priorityQueue = new PriorityQueue<Element>();
		
		// Add the first element of each queue
		if (!queue1.isEmpty()) {
			priorityQueue.offer(new Element(queue1.removeFirst(), queue1));
		}
		if (!queue2.isEmpty()) {
			priorityQueue.offer(new Element(queue2.removeFirst(), queue2));
		}
		if (!queue3.isEmpty()) {
			priorityQueue.offer(new Element(queue3.removeFirst(), queue3));
		}
		
		// this should be sorted. You cannot actually
		// sort it directly
		List<Integer> sortedList = new ArrayList<Integer>();
		
		while (!priorityQueue.isEmpty()) {
			Element elem = priorityQueue.poll();
			sortedList.add(elem.val);
			if (!elem.queue.isEmpty()) {
				priorityQueue.offer(new Element(elem.queue.removeFirst(), elem.queue));
			}
		}
		
		System.out.println("contents of List:");
		for (Integer i : sortedList.subList(0, sortedList.size()-1)) {
			System.out.print(i +", ");
		}
		System.out.println(sortedList.getLast());
		
		// Answers to questions:
		// a. O(n log k) because n elements are added and removed from a heap/priority queue of size k. Each heap operation takes O(log k).
		// b. O(k) because the heap/priority queue will have a max of k elements at any given time, one for each queue.
	}
}
