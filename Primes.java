import java.util.Arrays;

public class Primes {
	public static void main(String[] args) {

	}

	static int[] primes;
	static int sz;
	static boolean isPrime[];

	static void sieve(int n) {
		isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		sz = 0;
		for (int i = 2; i * i <= n; i++) {
			if (isPrime[i])
				for (int j = 2 * i; j <= n; j += i)
					isPrime[j] = false;
		}

		primes = new int[n + 1];

		for (int i = 0; i <= n; i++)
			if (isPrime[i]) {
				primes[sz] = i;
				sz++;
			}
	}
}
