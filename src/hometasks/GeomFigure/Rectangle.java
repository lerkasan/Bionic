 package hometasks.GeomFigure;

import hometasks.Exceptions.*;

public class Rectangle extends Figure implements Areable {
	private double width;
	private double height;
	
	public Rectangle() {
		this.width = 0.0;
		this.height = 0.0;
	}
	
	public Rectangle(double side) throws WrongArgumentException {
		if (side >= 0) {
			this.width = side;
			this.height = side;
		} else {
			throw new WrongArgumentException("Side of square can't be a negative number.");
		}
	}

	public Rectangle(double width, double height) throws WrongArgumentException {
		if ( (width >= 0) && (height >= 0) ){
			this.width = width;
			this.height = height;
		} else {
			throw new WrongArgumentException("Width and height of square can't be a negative number.");
		}
	}
	
	public Rectangle(Rectangle rectangle1) {
		this.width = rectangle1.getWidth();
		this.height = rectangle1.getHeight();
	}

	public double getWidth() {
		return this.width;
	}

	public void setWidth(double width) throws WrongArgumentException {
		if (width >= 0){
			this.width = width;
		} else {
			throw new WrongArgumentException("Width of square can't be a negative number.");
		}
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) throws WrongArgumentException {
		if (height >= 0) {
			this.height = height;
		} else {
			throw new WrongArgumentException("Height of square can't be a negative number.");
		}
	}

	@Override
	public double getArea() {
		return this.width*this.height;
	}
}
