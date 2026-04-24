package partI;

public class RollingWindow {

	public static WindowStats getWindow(double[] arr, int windowSize) {
		int resLength = arr.length - windowSize + 1;
		double[] avgArr = new double[resLength];
		double[] minArr = new double[resLength];
		double[] maxArr = new double[resLength];
		
		for (int i = 0; i < resLength; i++) {
			double min = arr[i];
			double max = arr[i];
			double sum = arr[i];
			
			for (int j = i + 1; j < i + windowSize; j++) {
				if (arr[j] < min) {
					min = arr[j];
				}
				if (arr[j] > max) {
					max = arr[j];
				}
				sum += arr[j];
			}
			
			avgArr[i] = sum / windowSize;
			minArr[i] = min;
			maxArr[i] = max;
		}
		
		return new WindowStats(avgArr, minArr, maxArr);
	}
	
	public static void printArray(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		double[] readings = {
			    12.5, 11.8, 13.2, 14.0, 12.9,
			    13.5, 15.1, 14.8, 13.9, 14.2
			};
		int windowSize = 3;
	
		/* print out results for min, max, and mean
		 * in the window
		 */
		WindowStats result = getWindow(readings, windowSize);
		
		System.out.println("Min Array:");
		printArray(result.min);
		
		System.out.println("\nMax Array:");
		printArray(result.max);
		
		System.out.println("\nMean Array:");
		printArray(result.avg);
		
	}
}
