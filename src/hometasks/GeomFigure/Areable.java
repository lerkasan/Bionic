package hometasks.GeomFigure;

public interface Areable {
	public default double getArea() {
		return 0.0;
	}
	
	public static double getTotalAreable(Areable[] areables) {
		double totalArea = 0.0;
		for (Areable i: areables) {
			if (i != null) {
				totalArea+=i.getArea();
			}
		}
		return totalArea;
	}
}
