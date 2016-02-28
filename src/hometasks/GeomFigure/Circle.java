package hometasks.GeomFigure;

import hometasks.Exceptions.*;

public class Circle extends Figure implements Areable {
	private double radius;
	
	public Circle() {
		this.radius = 0.0;
	}
	
	public Circle(double radius) throws WrongArgumentException {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new WrongArgumentException("Radius of circle can't be a negative number.");
		}
	}
	
	public Circle(Circle circle1) {
		this.radius = circle1.getRadius();
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) throws WrongArgumentException {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new WrongArgumentException("Radius of circle can't be a negative number.");
		}
	}

	@Override
	public double getArea() {
		return Math.PI*this.radius*this.radius;
	}

}
