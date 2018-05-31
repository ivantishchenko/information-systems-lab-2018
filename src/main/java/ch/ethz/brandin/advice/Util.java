package ch.ethz.brandin.advice;

public class Util {
	public static int sum(int[] is) {
		int sum = 0;
		for (int i : is) {
			sum += i;
		}
		return sum;
	}
	
	public static double proportion(int[] is, int[] indices) {
		int sum = sum(is);
		
		double sum2 = 0;
		for (int index : indices) {
			sum2 += is[index];
		}
		
		return sum2 / sum;
	}
	
	public static boolean hasZeros(int[] is) {
		boolean res = false;
		
		for (int i : is) {
			res |= i == 0;
		}
		return res;
	}
	
	public static int getMax(int[] is) {
		int max = 0;
		for (int i : is) {
			max = max > i ? max : i;
		}
		return max;
	}
	
	public static int getMax2(int[][] is) {
		int max = 0;
		for (int[] iss : is) {
			max = max > iss[1] ? max : iss[1];
		}
		return max;
	}
}

