package hometasks.DepoBase;

import java.io.Serializable;

public interface Incomable extends Cloneable, Comparable<Incomable>, Serializable {
	public double getIncome();
	public Incomable cloneObj() throws CloneNotSupportedException;
	boolean equals(Object other);
	int compareTo(Incomable other);
}
