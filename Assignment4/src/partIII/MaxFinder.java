package partIII;

import java.util.ArrayList;

public class MaxFinder<T extends Comparable<T>> {
	private T currentMax;
	private ArrayList<T> values;
	
	public MaxFinder() {
		values = new ArrayList<T>();
	}
	
	// Returns string representation of all values added to MaxFinder
	public String getValuesStringified() {
		return values.toString();
	}

	// Adds val to the array of values and updated the currentMax if necessary
	public void add(T val) {
		values.add(val);
		if (currentMax == null || val.compareTo(currentMax) > 0) 
			currentMax = val;
	}
	
	// Returns the currentMax of all added values
	public T getMax() {
		return currentMax;
	}
	
	public static void main(String[] args) {
		 MaxFinder<Integer> intMaxFinder = new MaxFinder<Integer>();
		 for (int i=0;i<10;i++) {
			 intMaxFinder.add((int)(100*Math.random()));
			 
		 }
		 System.out.println("Values of intMaxFinder: " + intMaxFinder.getValuesStringified());
		 System.out.println("Max of intMaxFinder is: " + intMaxFinder.getMax());
		 
		 MaxFinder<String> stringMaxFinder = new MaxFinder<String>();
		 stringMaxFinder.add("Hello");
		 stringMaxFinder.add("Goodbye");
		 stringMaxFinder.add("New York");
		 stringMaxFinder.add("Paris");
		 stringMaxFinder.add("Java");
		 stringMaxFinder.add("Athens");
		 System.out.println("Values of stringMaxFinder: " + stringMaxFinder.getValuesStringified());
		 System.out.println("Max of stringMaxFinder is: " + stringMaxFinder.getMax());
	}

}

