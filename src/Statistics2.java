package hometasks;

public class Statistics2 {

	public static void main(String[] args) {
		double[] array1 = {10.5, 10.8, 11.2, 10.9, 10.4, 10.6, 10.9, 
				   11.0, 10.3, 10.8, 10.6, 11.3, 10.5, 10.7, 
				   10.8, 10.9, 10.8, 10.7, 10.9, 11.0 };
		int n = array1.length;
		double m = 0.0;
		double d = 0.0;
		double sigma = 0.0;
		final double t_betha = 1.960;
		double[] I_betha = new double[2];
		
		for (double i : array1) {
			m += i;
		}

		m /= n;
		
		for (double i : array1) {
			d += (i - m)*(i - m);
		}
		d = d/(n - 1);
		
		sigma = Math.sqrt(d/n);
		
		I_betha[0] = m - t_betha*sigma;
		I_betha[1] = m + t_betha*sigma;
			
		System.out.println("m = " + m + "\nD = " + d + "\nSigma = " + sigma);
		
		for (double i : I_betha) {
			i = Math.round(i*100)/100;
		}
		
		//System.out.printf("I_betha = (%.2f, %.2f)", I_betha[0], I_betha[1]);
		System.out.println("I_betha = (" + I_betha[0] + ", " + I_betha[1] + ")");
		
	}

}
