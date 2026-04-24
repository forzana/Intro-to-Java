package grades;

public class Student {
	private String name;
	private double[] grades;
	private int numGrades; // Number of grades put into the grades array
	
	// Takes a name and creates a student
	public Student(String name) {
		setName(name);
		this.grades = new double[10];
		for (int i = 0; i < grades.length; i++) {
			grades[i] = -1;
		}
		this.numGrades = 0;
	}

	// Name getter
	public String getName() {
		return name;
	}

	// Name setter
	public void setName(String name) {
		this.name = name;
	}
	
	// Adds a grade to the grade array in the first spot that is -1
	// If array is full, does nothing
	public void addGrade(double grade) {
		if (numGrades < 10) {
			grades[numGrades++] = grade;
		}
	}
	
	// Returns a copy of the grades array
	public double[] getAllGrades() {
		double[] copy = new double[grades.length];
		for (int i = 0; i < grades.length; i++) {
			copy[i] = grades[i];
		}
		return copy;
	}
	
	// Returns an average of the grades in the grades array, ignoring -1's
	public double getAverage() {
		if (numGrades == 0) return 0.0;
		
		double sum = 0.0;
		for (int i = 0; i < numGrades; i++) {
			sum += grades[i];
		}
		return sum / numGrades;
	}
	
	// Returns a character of A, B, C, D, or F depending on the average
	public char getGrade() {
		double avg = getAverage();
		if (avg >= 90) {
			return 'A';
		} else if (avg >= 80) {
			return 'B';
		} else if (avg >= 70) {
			return 'C';
		} else if (avg >= 60) {
			return 'D';
		} else {
			return 'F';
		}
	}
}
