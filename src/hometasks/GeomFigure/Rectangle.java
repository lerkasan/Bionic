package hometasks.GeomFigure;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Rectangle extends Figure implements Areable {
	private double width;
	private double height;
	
	public Rectangle() {
		this.width = 0.0;
		this.height = 0.0;
	}
	
	public Rectangle(double side) {
		if (side >= 0) {
			this.width = side;
			this.height = side;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Side of square can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public Rectangle(double width, double height) {
		if ( (width >= 0) && (height >= 0) ){
			this.width = width;
			this.height = height;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Width and height of square can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}
	
	public Rectangle(Rectangle rectangle1) {
		this.width = rectangle1.getWidth();
		this.height = rectangle1.getHeight();
	}

	public double getWidth() {
		return this.width;
	}

	public void setWidth(double width) {
		if (width >= 0){
			this.width = width;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Width of square can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		if (height >= 0) {
			this.height = height;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Height of square can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	@Override
	public double getArea() {
		return this.width*this.height;
	}
}
