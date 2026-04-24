import java.util.Scanner;

public class OscillatingSum {

	// This method takes in x, a number between 0 and 1, eps, a value
	// for epsilon representing the overshoot limit, and maxN, the
	// maximum number of iterations in the sum. It returns an int
	// representing the total number of iterations when |S_n - S_n-1| < eps 
	// ten times in a row or -1 if the condition is not met by the time
	// it reaches maxN
	public static int maxOvershoot(double x, double eps, int maxN) {
		
		// Verify x is between 0 and 1
		if (x <= 0.0 || x >= 1.0)
			return -1;
		
		int iter;
		double prevSum = 0.0;
		double currSum = 0.0;
		int consecutiveCount = 0;
		
		for (iter = 1; iter <= maxN; iter++) {
			
			currSum += (Math.pow(-1, iter+1)) * (Math.pow(x, iter)) / iter;
			double diff = Math.abs(currSum - prevSum);
			
			if (diff < eps) { // Condition met
				consecutiveCount++;
				if (consecutiveCount == 10) {
					return iter; // Return the number of iterations
				}
			} else { // Reset consecutiveCount
				consecutiveCount = 0;
			}
			
			prevSum = currSum;
		}
		
		// If we are here, the conditions have not been met within maxN iterations
		return -1;
	}
	
	public static void main(String[] args) {
		
		// Prompt the user for x, eps, and maxN
		Scanner in = new Scanner(System.in);
		System.out.println("Enter x: ");
		double x = in.nextDouble();
		System.out.println("Enter eps: ");
		double eps = in.nextDouble();
		System.out.println("Enter maxN: ");
		int maxN = in.nextInt();
		
		int iterations = maxOvershoot(x, eps, maxN);
		
		// Print the correct output depending on the result of maxOvershoot
		if (iterations > 0) {
			System.out.println("Converged after " + iterations + " iterations");
		} else {
			System.out.println("Did not converge within " + maxN + " iterations");
		}
		
	}
}