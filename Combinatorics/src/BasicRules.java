import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BasicRules {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		product_rule();
		permutation_using_product_rule();
		inclusion_exclusion_principle();

	}

	// Get All Words Of Length 4 That Can Be Created Using 2 Letters
	// Time Complexity: O(choices^length)
	static void product_rule() {
		char[] letters = { 'A', 'B' };
		char[] word = new char[4];

		/*
		 * Some Observations: We know the number of choices we have to make at each step
		 * Product Rule is about pure combinations
		 */

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						System.out.println(letters[i] + " " + letters[j] + " " + letters[k] + " " + letters[l]);
					}
				}
			}
		}
	}

	// Time Complexity = O(len^len)

	static void permutation_using_product_rule() {
		int[] permutation = { 1, 2, 3, 4 };

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < 4; k++) {
					if (k == j || k == i)
						continue;
					for (int l = 0; l < 4; l++) {
						if (l == k || l == j || l == i)
							continue;
						System.out.println(
								permutation[i] + " " + permutation[j] + " " + permutation[k] + " " + permutation[l]);
					}
				}
			}
		}
	}

	static int result;
	static int MAX = 100;
	static int[] items = { 2, 3, 5, 7 };

	static void inclusion_exclusion_principle() {
		// Number of subsets is 2^items - 1
		result = 0;

		enumerate_on_subsets(4);

		System.out.println(result);

	}

	// 4294967296

	static void enumerate_on_subsets(int size) {

		for (int bit = 1; bit < Math.pow(2, size); bit++) {
			long temp = 1;
			int parity = 0;
			for (int i = 1; i <= size; i++) {
				if (((bit >> (i - 1)) & 1) > 0) {
					if (overflow(temp, items[i - 1] / gcd(temp, items[i - 1]), Long.MAX_VALUE)) {
						temp = -1;
						break;
					}
					temp = lcm(temp, items[i - 1]);
					parity++;
				}
			}

			if (temp == -1)
				continue;

			if (parity % 2 == 0)
				result -= ((MAX / temp));
			else
				result += MAX / temp;
		}
	}

	static boolean overflow(long a, long b, long MAX) {
		// a * b > MAX
		if (a > MAX / b || b > MAX / a)
			return true;
		return false;
	}

	static long gcd(long a, long b) {
		if (a < b) {
			long tmp = a;
			a = b;
			b = tmp;
		}
		return b == 0 ? a : gcd(b, a % b);
	}

	static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	static class Scanner {
		public BufferedReader reader;
		public StringTokenizer st;

		public Scanner(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
			st = null;
		}

		public String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = reader.readLine();
					if (line == null)
						return null;
					st = new StringTokenizer(line);
				} catch (Exception e) {
					throw (new RuntimeException());
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}

}
