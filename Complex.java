package hometasks;

public class Complex {
	private double re;
	private double im;
	
	public Complex() {
		this.re = 0;
		this.im = 0;
	}
	
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	public double getRe() {
		return this.re;
	}
	
	public double getIm() {
		return this.im;
	}
	
	public void setRe(double re) {
		this.re = re;
	}
	
	public void setIm(double im) {
		this.im = im;
	}
	
	public Complex add(Complex x) {
		double re, im;
		re = this.re + x.re;
		im = this.im + x.im;
		return new Complex(re,im);
		//return new Complex(this.re + x.re, this.im + x.im); 
	}
	
	public Complex multiply(Complex x) {
		double re, im;
		re = this.re*x.re - this.im*x.im;
		im = this.re*x.im + x.re*this.im;
		return new Complex(re,im);
	}
	
	public double getModule() {
		return Math.sqrt(this.re*this.re + this.im*this.im);
	}
	
	public String toString() {
		if (this.im > 0) {
			return this.re+"+"+this.im+"i";
		} else if (this.im < 0) {
			return this.re+""+this.im+"i";
		} else {
			return this.re+"";
		}
	}

	public static void main(String[] args) {
		Complex x = new Complex(8,3);
		Complex y = new Complex(2,-5);
		System.out.println("x+y = " + x.add(y));
		System.out.println("x*y = " + x.multiply(y));
		System.out.println("r = " + x.getModule());
	}

}
