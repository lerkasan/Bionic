package hometasks;

import java.util.Arrays;
import java.util.Scanner;

public class Exponent {
	double x;
	double sum = 1.0;
	double arraySum = 0.0;
	double accuracy;
	int iterations;

	public Exponent(double x) {
		this.x = x;
		this.countRow();
	}

	public double getX() {
		return this.x;
	}

	public double getSum() {
		return this.sum;
	}

	public double getAccuracy() {
		return this.accuracy;
	}

	public int getIterations() {
		return this.iterations;
	}

	private double countRow() {
		int i = 1;
		double y = 1.0; // y is x_i/i!
		double sum_old;
		double sum_array[] = new double[500];
		System.out.println("Iterations: ");
		System.out.println("Iter # \t\t\t\t Sum \t\t\t\t Y \t\t\t\t Delta");
		
		sum_array[0] = 1;
		do {
			sum_old = this.sum;
			y *= this.x / i;
			this.sum += y;
			sum_array[i] = y;
			System.out.println("Iter " + i + "\t\t Sum " + this.sum + " \t\t Y " + y + "\t\t Delta " + (sum - sum_old));
			i++;
		} while (Math.abs(this.sum - sum_old) > 0);
		
		this.accuracy = this.sum - sum_old;
		this.iterations = i - 1;
		
		Arrays.sort(sum_array);
		for (double j: sum_array) {
			this.arraySum += j;
		}
		return this.arraySum;
	}

	public static void main(String[] args) {
		boolean letter = false;
		double xx;
		Scanner in = new Scanner(System.in);
		System.out.println("Finding exp(x). \nPress any letter to exit.");
		do {
			System.out.print("\nInput a number: ");
			if (in.hasNextDouble()) {
				xx = in.nextDouble();
				Exponent myExp = new Exponent(xx);
				System.out.println("\nResult: ");
				System.out.println("Sum of myExp(" + xx + ") is: " + myExp.getSum());
				System.out.println("Sum of ArrayExp(" + xx + ") is: " + myExp.arraySum);
				System.out.println("Accuracy of myExp(" + xx + ") is: " + myExp.getAccuracy());
				System.out.println("Math.exp(" + xx + ") is: " + Math.exp(xx));
				System.out.println("Delta between Math.exp(" + xx + ") and myExp(" + xx + ") is: "
						+ (Math.exp(xx) - myExp.getSum()));
				System.out.println("Amount of iterations is: " + myExp.getIterations());
			} else {
				System.out.println("You entered not a number.\nBye");
				letter = true;
				in.close();
			}
		} while (!letter);
	}

}
