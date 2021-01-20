import java.util.ArrayList;
import java.util.Collections;

public class FactorsAndDivisors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Time Complexity O(sqrt(x))
	static ArrayList<Long> divisors(long x) {
		ArrayList<Long> div = new ArrayList<>();
		long i = 0;
		for (i = 1; i * i < x; ++i) {
			if (x % i == 0) {
				div.add(i);
				div.add(x / i);
			}
		}
		if (i * i == x)
			div.add(i);
		return div;
	}

	// Time Complexity O(sqrt(x)) so x is max 1e12
	static ArrayList<Long> factors(long x) {
		/*
		 * This function doesn't handle 0 and 1
		 */
		ArrayList<Long> factors = new ArrayList<>();
		for (long i = 2; i * i <= x; i++) {
			while (x % i == 0) {
				factors.add(i);
				x /= i;
			}
		}
		if (x > 1)
			factors.add(x);
		return factors;
	}

	/*
	 * Let's assume we have the prime factorization of a given number: 2^x * 3^y *
	 * 5^z - - - x(0 - 1 - 2 - .. - x)(x + 1 options) y(0 - 1 - 2 - .. - y)(y + 1
	 * options) z(0 - 1 - 2 - .. - z)(z + 1 options)
	 * 
	 * So we have (x + 1) * (y + 1) * (z + 1) possible divisors
	 */

	/*
	 * Attempting a recursive solution to generate divisors using our current
	 * prime_factors
	 */

	static class Point {
		int factor, pow;

		Point(int factor, int pow) {
			this.factor = factor;
			this.pow = pow;
		}
	}

	static ArrayList<Point> pows = new ArrayList<>();
	static int[] temp_pows = new int[101];

	static void factors(int x) {
		for (int i = 2; i * i <= x; i++) {
			while (x % i == 0) {
				temp_pows[i]++;
				x /= i;
			}
		}

		if (x > 1)
			temp_pows[x]++;
		for (int i = 0; i < 101; i++)
			if (temp_pows[i] != 0)
				pows.add(new Point(i, temp_pows[i]));
	}

	static ArrayList<Integer> divisors = new ArrayList<>();

	static void generate(int factor_idx, int div) {
		if (factor_idx == pows.size()) {
			divisors.add(div);
			return;
		}

		for (int pow = 0; pow <= pows.get(factor_idx).pow; pow++)
			generate(factor_idx + 1, (int) (div * Math.pow(pows.get(factor_idx).factor, pow)));
	}

	static int rangeFactorization(int n) {
		int[] numFactors = new int[n + 1];
		for (int d = 1; d <= n; d++)
			for (int k = d; k <= n; k += d)
				numFactors[k]++;
		int res = 0;
		for (int i : numFactors)
			res += i;
		return res;
	}
}

