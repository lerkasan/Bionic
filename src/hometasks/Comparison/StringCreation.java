package hometasks.Comparison;

public class StringCreation {

	public static String generateString(int n) {
		String numbers = "";
		for (int i = 1; i <= n; i++) {
			numbers = numbers + (i + " ");
		}
		return numbers;
	}

	public static StringBuilder generateStringBuilder(int n) {
		StringBuilder numbers = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			numbers = numbers.append(i + " ");
		}
		return numbers;
	}

	public static double comparePerformance(int n) {
		double times = 0.0;
		long before = System.nanoTime();
		String text1 = StringCreation.generateString(n);
		long after = System.nanoTime();
		long deltaConcat = after - before;
		// System.out.println(text1);
		System.out.println("\n" + n + " numbers:");
		System.out.println("Concatenanion execution time: "
				+ (double) ((int) Math.round(1000000 * (deltaConcat * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ deltaConcat + " nanoseconds");
 
		before = System.nanoTime();
		StringBuilder text2 = StringCreation.generateStringBuilder(n);
		after = System.nanoTime();
		long deltaBuilder = after - before;
		// System.out.println(text2);
		System.out.println("StringBuilder execution time: "
				+ (double) ((int) Math.round(1000000 * (deltaBuilder * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ deltaBuilder + " nanoseconds");
		if (deltaConcat == deltaBuilder) {
			System.out.println("Performance is equal.");
		}
		if (deltaConcat > deltaBuilder) {
			double temp1 = (deltaConcat * 1.0) / deltaBuilder;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("StringBuilder is quicker in " + times + " times.");
		} else {
			double temp1 = (deltaBuilder * 1.0) / deltaConcat;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("Concatenation is quicker in " + times + " times.");
		}

		return times;
	}

	public static void main(String[] args) {
		StringCreation.comparePerformance(10);
		StringCreation.comparePerformance(100);
		StringCreation.comparePerformance(1000);
		StringCreation.comparePerformance(10000);
		StringCreation.comparePerformance(100000);
	}
}
