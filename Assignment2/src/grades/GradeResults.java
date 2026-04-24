package grades;

public class GradeResults {

	public static void main(String[] args) {
		// create a student here
		Student stud = new Student("Forzana");
		
		// give the student some sample grades
		stud.addGrade(98.3);
		stud.addGrade(65.2);
		stud.addGrade(100.0);
		stud.addGrade(94.5);
		stud.addGrade(95.6);
		stud.addGrade(25.9);

		// print out the list of grades
		double[] allGrades = stud.getAllGrades();
		System.out.print("Grades: ");
		for (int i = 0; i < allGrades.length; i++) {
			System.out.print(allGrades[i] + " ");
		}
		
		// print out the average score
		System.out.println("\nAverage score: " + stud.getAverage());
		
		// print out the grade
		System.out.println("Grade: " + stud.getGrade());

	}

}
