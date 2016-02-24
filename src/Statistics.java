package hometasks;

public class Statistics {

	public static void main(String[] args) {
		double[] array1 = {10.5, 10.8, 11.2, 10.9, 10.4, 10.6, 10.9, 
				   11.0, 10.3, 10.8, 10.6, 11.3, 10.5, 10.7, 
				   10.8, 10.9, 10.8, 10.7, 10.9, 11.0 };
		double m = 0.0;
		double d = 0.0;
		double sigma = 0.0;
		double sum = 0.0;
		final double t_betha = 1.960;
		double[] I_betha = new double[2];
		
		for (int i = 0; i < array1.length; i++) {
			sum += array1[i];
		}
		
		m = sum/array1.length;
		
		for (int i = 0; i < array1.length; i++) {
			d += (array1[i] - m)*(array1[i] - m);
		}
		d = d/(array1.length - 1);
		
		sigma = Math.sqrt(d/array1.length);
		
		I_betha[0] = m - t_betha*sigma;
		I_betha[1] = m + t_betha*sigma;
		
		
		System.out.println("m = " + m + "\nD = " + d + "\nSigma = " + sigma);
		
		System.out.println("I_betha = (" + I_betha[0] + ", " + I_betha[1] + ")"); 
	}

}
