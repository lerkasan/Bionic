package hometasks.DepoBase;

public interface Incomable extends Cloneable, Comparable<Incomable> {
	public double getIncome();
	public Incomable cloneObj() throws CloneNotSupportedException;
	int compareTo(Incomable other);
}
