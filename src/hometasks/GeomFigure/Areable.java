package hometasks.GeomFigure;

@FunctionalInterface
public interface Areable {
	public double getArea();
	
	public static double getTotalArea(Areable[] areables) {
		double totalArea = 0.0;
		for (Areable i: areables) {
			if (i != null) {
				totalArea+=i.getArea();
			}
		}
		return totalArea;
	}
}
