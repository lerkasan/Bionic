package hometasks.GeomFigure;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Circle extends Figure implements Areable {
	private double radius;
	
	public Circle() {
		this.radius = 0.0;
	}
	
	public Circle(double radius) throws IllegalArgumentException {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Radius of circle can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}
	
	public Circle(Circle circle1) {
		this.radius = circle1.getRadius();
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) throws IllegalArgumentException {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Radius of circle can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	@Override
	public double getArea() {
		return Math.PI*this.radius*this.radius;
	}

}
