package partI;

import java.text.DecimalFormat;

public class CountPrimesSingleThreaded {

	static boolean isPrime(long n) {
        if (n == 2) return true;
        if ((n < 2) || (n%2 == 0)) return false;
        for (long i = 3; i <= Math.sqrt(n); i+= 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
	public static void main(String[] args) {
		
		final int LIMIT = 100_000_000;
        DecimalFormat formatter = new DecimalFormat("#,###");
        System.out.println("Counting primes from 1 to " + formatter.format(LIMIT) + " (single-threaded)...");
        long start = System.currentTimeMillis();

        int count = 1; // 2 is a prime
        for (long i = 3; i <= LIMIT; i+=2) {
            if (isPrime(i)) count++;
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Prime count : " + formatter.format(count));
        System.out.println("Time elapsed: " + elapsed + " ms");
	}

}
