package partI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.*;

public class CountPrimesMultithreaded {
	
	static boolean isPrime(long n) {
        if (n == 2) return true;
        if ((n < 2) || (n%2 == 0)) return false;
        for (long i = 3; i <= Math.sqrt(n); i+= 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
	
	static class Counter implements Runnable {
        private final int start;
        private final int end;
        private final int[] results;
        private final int index;

        public Counter(int start, int end, int[] results, int index) {
            this.start = start;
            this.end = end;
            this.results = results;
            this.index = index;
        }

        public void run() {
            int count = 0;
            
            // Make sure the starting number is odd
            int startNum = start;
            if (startNum % 2 == 0) startNum++;
            
            for (int i = startNum; i <= end; i += 2) {
                if (isPrime(i)) {
                    count++;
                }
            }

            results[index] = count;
        }
    }
	
    public static int countPrimes(String description, int numberOfTasks, int poolSize, int LIMIT, DecimalFormat formatter) throws InterruptedException {

		System.out.println("\nCounting primes from 1 to " + formatter.format(LIMIT) + " (" + description + ")...");
		long startTime = System.currentTimeMillis();
		
		ExecutorService es = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		int[] results = new int[numberOfTasks];
		ArrayList<Runnable> tasks = new ArrayList<>();
		
		int chunkSize = LIMIT / numberOfTasks;
		int start = 1;
		
		for (int i = 0; i < numberOfTasks; i++) {
			int end;
			if (i == numberOfTasks - 1) {
				end = LIMIT;
			} else {
				end = start + chunkSize - 1;
			}
		
			Runnable task = new Counter(start, end, results, i);
			tasks.add(task);
			es.execute(task);
			
			start = end + 1;
		}
		
		es.shutdown();
		while (!es.isTerminated()) {
		}
		
		int totalCount = 1; // 2 is a prime
		for (int result : results) {
			totalCount += result;
		}
		
		long elapsed = System.currentTimeMillis() - startTime;
		System.out.println("Prime count : " + formatter.format(totalCount));
		System.out.println("Time elapsed: " + elapsed + " ms");
		
		return totalCount;
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("number of cores: " + Runtime.getRuntime().availableProcessors());
		int numberOfCores = Runtime.getRuntime().availableProcessors();
		
		final int LIMIT = 100_000_000;
        DecimalFormat formatter = new DecimalFormat("#,###");
        
        // Single threaded
        System.out.println("\nCounting primes from 1 to " + formatter.format(LIMIT) + " (single-threaded)...");
        long start = System.currentTimeMillis();

        int count = 1; // 2 is a prime
        for (long i = 3; i <= LIMIT; i+=2) {
            if (isPrime(i)) count++;
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Prime count : " + formatter.format(count));
        System.out.println("Time elapsed: " + elapsed + " ms");
        
        
        // Begin multi-threading
        
        // 2 cores
        int numberOfThreads = 2;
        countPrimes("2 cores", numberOfThreads, numberOfThreads, LIMIT, formatter);
        
        // (Processors number of cores) / 2 threads
        numberOfThreads = numberOfCores / 2;
        countPrimes("(your processor's number of cores) / 2 threads", numberOfThreads, numberOfThreads, LIMIT, formatter);
        
        // threads equal to the number of cores
        numberOfThreads = numberOfCores;
        countPrimes("threads equal to the number of cores", numberOfThreads, numberOfThreads, LIMIT, formatter);
        
        // double the number of threads as cores
        numberOfThreads = numberOfCores * 2;
        countPrimes("double the number of threads as cores", numberOfThreads, numberOfThreads, LIMIT, formatter);
        
        // 100 Runnable objects, pool size = number of cores
        countPrimes("100 Runnable objects with a thread pool of threads equal to the number of cores", 100, numberOfCores, LIMIT, formatter);
        
        /* Output:

		number of cores: 10

		Counting primes from 1 to 100,000,000 (single-threaded)...
		Prime count : 5,761,455
		Time elapsed: 13938 ms
		
		Counting primes from 1 to 100,000,000 (2 cores)...
		Prime count : 5,761,455
		Time elapsed: 8969 ms
		
		Counting primes from 1 to 100,000,000 ((your processor's number of cores) / 2 threads)...
		Prime count : 5,761,455
		Time elapsed: 3993 ms
		
		Counting primes from 1 to 100,000,000 (threads equal to the number of cores)...
		Prime count : 5,761,455
		Time elapsed: 2570 ms
		
		Counting primes from 1 to 100,000,000 (double the number of threads as cores)...
		Prime count : 5,761,455
		Time elapsed: 2368 ms
		
		Counting primes from 1 to 100,000,000 (100 Runnable objects with a thread pool of threads equal to the number of cores)...
		Prime count : 5,761,455
		Time elapsed: 2328 ms
         */
        
	}

}
