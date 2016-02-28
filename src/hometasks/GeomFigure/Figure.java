package hometasks.GeomFigure;

abstract class Figure {
	abstract double getArea();
	
	public static double getTotalArea(Figure[] figures) {
		double totalArea = 0.0;
		for (Figure i: figures) {
			if (i != null) {
				totalArea+=i.getArea();
			}
		}
		return totalArea;
	}
	
	public static void main(String[] args) {
		Rectangle square1 = new Rectangle(5);
		Rectangle square2 = new Rectangle(8.5);
		Rectangle square3 = new Rectangle(45.78);
		Rectangle rectangle1 = new Rectangle(5.9, 6.3);
		Rectangle rectangle2 = new Rectangle(14.36, 12.8);
		Rectangle rectangle3 = new Rectangle(0.97, 5.12);
		Rectangle rectangle4 = new Rectangle(2.7, 68.9);
		Rectangle rectangle5 = new Rectangle(0.5, 30);
		Circle circle1 = new Circle(5.35);
		Circle circle2 = new Circle(9.4);
		Circle circle3 = new Circle(2.67);
		Circle circle4 = new Circle(10.1);
		Circle circle5 = new Circle(14.8);
		Circle circle6 = new Circle(6.38);
		Figure[] figures = {square1, rectangle1, circle1, square2, rectangle2, circle2, square3, rectangle3, circle3, rectangle4, rectangle5, circle4, circle5, circle6}; 
		System.out.println("Total area is " + Figure.getTotalArea(figures));
		
		Areable[] areables = new Areable[14];
		areables[0] = new Rectangle(5);
		areables[1]= new Rectangle(8.5);
		areables[2] = new Rectangle(45.78);
		areables[3] = new Rectangle(5.9, 6.3);
		areables[4] = new Rectangle(14.36, 12.8);
		areables[5] = new Rectangle(0.97, 5.12);
		areables[6] = new Rectangle(2.7, 68.9);
		areables[7] = new Rectangle(0.5, 30);
		areables[8] = new Circle(5.35);
		areables[9] = new Circle(9.4);
		areables[10] = new Circle(2.67);
		areables[11] = new Circle(10.1);
		areables[12] = new Circle(14.8);
		areables[13] = new Circle(6.38);
		System.out.println("Total area is " + Areable.getTotalAreable(areables));
	}
	
}
