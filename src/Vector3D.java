package hometasks;

/** Describes 3D vectors with coordinates X, Y, Z and basic operations with them: 
 *  addition, multiplication by a number, calculation of scalar, vector production and module of vector.*/ 
public class Vector3D {
	private double x;
	private double y;
	private double z;
	
	/** default constructor that creates 3D vector with zero coordinates (0,0,0)
	 * @return new 3D vector with coordinates (0,0,0) */
	public Vector3D() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	/** constructor that creates 3D vector with coordinates (xx,yy,zz) 
	 * @param xx - vector coordinate on X axis
	 * @param yy - vector coordinate on Y axis
	 * @param zz - vector coordinate on Z axis
	 * @return new 3D vector with coordinates (xx,yy,zz) */
	public Vector3D(double xx, double yy, double zz) {
		this.setXYZ(xx, yy, zz);
	}
	
	/** constructor that creates 3D vector as a separate copy of a given 3D Vector argument vect.
	 * @return new separate 3D vector as a copy of argument vector*/
	public Vector3D(Vector3D vect) {
		this.x = vect.getX();
		this.y = vect.getY();
		this.z = vect.getZ();
	}
	
	/** creates 3D vector as a separate copy of a given 3D vector argument vect.
	 * @param vect - 3D vector to be copied*/
	public static Vector3D copy(Vector3D vect) {
		return new Vector3D(vect); 
	}
	
	/** creates 3D vector as a separate copy of current 3D vector.*/
	public Vector3D copy() {
		return new Vector3D(this);
	}
	
	/** checks if 3D vector equals argument vector.
	 * @param vect - 3D vector to check equality with
	 * @return true if vectors are equal else false*/
	@Override
	public boolean equals(Object vect) {  // TO DO - override hashCode() also!
        if (this == vect) {  //How to implement? Equality tests should not be made with floating point values
        	return true;
        }
        if (vect == null) {
        	return false;
        }
        if (this.getClass() != vect.getClass()) {
        	return false;
        }
        Vector3D vectObj = (Vector3D)vect;
		boolean equal = (this.getX() == vectObj.getX()) && (this.getY() == vectObj.getY()) && (this.getZ() == vectObj.getZ());
		return equal;
	}
	
	/** checks if two vectors are equal.
	 * @return true if vectors are equal else false*/
	public static boolean areEqual(Object vect1, Object vect2) {   //How to implement? Equality tests should not be made with floating point values
        if (vect1 == vect2) { 

        	return true;
        }
        if ((vect1 == null) || (vect2 == null)) {
        	return false;
        }
        if (vect1.getClass() != vect2.getClass()) {
        	return false;
        }
        Vector3D vectObj1 = (Vector3D)vect1;
        Vector3D vectObj2 = (Vector3D)vect2;
		boolean equal = (vectObj1.getX() == vectObj2.getX()) && (vectObj1.getY() == vectObj2.getY()) && (vectObj1.getZ() == vectObj2.getZ());
		return equal;
	}
	
	/** represents current vector in a format of (x; y; z) String like "(6.78; -4.51; 0.0)".
	 * @return current vector in a format of (x; y; z)*/
	@Override
	public String toString() {
		return "("+this.getX()+"; "+this.getY()+"; "+this.getZ()+")";
	}
	
	/** return vector coordinate on X axis*/
	public double getX() {
		return this.x;
	}
	
	/** return vector coordinate on Y axis*/
	public double getY() {
		return this.y;
	}
	
	/** return vector coordinate on Z axis*/
	public double getZ() {
		return this.z;
	}
	
	/** changes vector coordinate on X axis with given argument
	 * @param xx - vector coordinate on X axis*/
	public void setX(double xx) {
		this.x = xx;
	}
	
	/** changes vector coordinate on Y axis with given argument
	 * @param yy - vector coordinate on Y axis*/
	public void setY(double yy) {
		this.y = yy;
	}
	
	/** changes vector coordinate on Z axis with given argument
	 * @param zz - vector coordinate on Z axis*/
	public void setZ(double zz) {
		this.z = zz;
	}
	
	/** changes vector coordinates on X, Y, Z axis with given arguments
	 * @param xx - vector coordinate on X axis
	 * @param yy - vector coordinate on Y axis
	 * @param zz - vector coordinate on Z axis*/
	public void setXYZ(double xx, double yy, double zz) {
		this.x = xx;
		this.y = yy;
		this.z = zz;
	}
	
	/** creates 3D vector as a sum of a current 3D vector and given argument vector.
	 * @param vect - 3D vector to add with current vector*/
	public Vector3D add(Vector3D vect) { // округлять или нет? 
		double xx = this.getX() + vect.getX();
		int temp = (int)Math.round(xx*1000);
		xx = (double)temp/1000;
		double yy = this.getY() + vect.getY();
		temp = (int)Math.round(yy*1000);
		yy = (double)temp/1000;
		double zz = this.getZ() + vect.getZ();
		temp = (int)Math.round(zz*1000);
		zz = (double)temp/1000;
		return new Vector3D(xx,yy,zz);
	}
	
	/** creates 3D vector as a multiplication of a current 3D vector by given double argument.
	 * @param n - number to multiply current vector with*/
	public Vector3D multiply(double n) { // округлять или нет? 
		double xx = n*this.getX() ;
		int temp = (int)Math.round(xx*1000);
		xx = (double)temp/1000;
		double yy = n*this.getY();
		temp = (int)Math.round(yy*1000);
		yy = (double)temp/1000;
		double zz = n*this.getZ();
		temp = (int)Math.round(zz*1000);
		zz = (double)temp/1000;
		return new Vector3D(xx,yy,zz);
	}
	
	/** creates 3D vector as a sum of two given argument vectors.
	 * @param vect1 - first 3D vector
	 * @param vect2 - second 3D vector*/
	public static Vector3D add(Vector3D vect1, Vector3D vect2) {
		double xx = vect1.getX() + vect2.getX();
		int temp = (int)Math.round(xx*1000);
		xx = (double)temp/1000;
		double yy = vect1.getY() + vect2.getY();
		temp = (int)Math.round(yy*1000);
		yy = (double)temp/1000;
		double zz = vect1.getZ() + vect2.getZ();
		temp = (int)Math.round(zz*1000);
		zz = (double)temp/1000;
		return new Vector3D(xx,yy,zz);
	}

	/** sets current 3D vector as a sum of current vector and given argument vector.
	 * @param vect - 3D vector*/
	public void addTo(Vector3D vect) {
		double xx = this.getX() + vect.getX();
		int temp = (int)Math.round(xx*1000);
		xx = (double)temp/1000;
		double yy = this.getY() + vect.getY();
		temp = (int)Math.round(yy*1000);
		yy = (double)temp/1000;
		double zz = this.getZ() + vect.getZ();
		temp = (int)Math.round(zz*1000);
		zz = (double)temp/1000;
		this.setX(xx);
		this.setY(yy);
		this.setZ(zz);
	}
	
	/**calculates scalar product of current vector and argument vector.
	 * @param vect - 3D vector*/
	public double getScalarProduct(Vector3D vect) { // округлять или нет? 
		double scalarProduct = this.getX()*vect.getX()+this.getY()*vect.getY()+this.getZ()*vect.getZ();
		int temp = (int)Math.round(scalarProduct*1000);
		scalarProduct = (double)temp/1000;
		return scalarProduct;
	}
	
	/**calculates scalar product of two given vectors.
	 * @param vect1 - first 3D vector
	 * @param vect2 - second 3D vector*/
	public static double getScalarProduct(Vector3D vect1, Vector3D vect2) { 
		double scalarProduct = vect1.getX()*vect2.getX()+vect1.getY()*vect2.getY()+vect1.getZ()*vect2.getZ();
		int temp = (int)Math.round(scalarProduct*1000);
		scalarProduct = (double)temp/1000;
		return scalarProduct;
	}
	
	/**calculates vector product of current vector and argument vector.
	 * @param vect - 3D vector*/
	public Vector3D getVectorProduct(Vector3D vect) { // округлять или нет? 
		double xx = this.getY()*vect.getZ()-this.getZ()*vect.getY();
		int temp = (int)Math.round(xx*1000);
		xx = (double)temp/1000;
		double yy = this.getZ()*vect.getX()-this.getX()*vect.getZ();
		temp = (int)Math.round(yy*1000);
		yy = (double)temp/1000;
		double zz = this.getX()*vect.getY()-this.getY()*vect.getX();
		temp = (int)Math.round(zz*1000);
		zz = (double)temp/1000;
		return new Vector3D(xx,yy,zz);
	}
	
	/**calculates vector product of two given vectors.
	 * @param vect1 - first 3D vector
	 * @param vect2 - second 3D vector*/
	public static Vector3D getVectorProduct(Vector3D vect1, Vector3D vect2) { 
		double xx = vect1.getY()*vect2.getZ()-vect1.getZ()*vect2.getY();
		int temp = (int)Math.round(xx*1000);
		xx = (double)temp/1000;
		double yy = vect1.getZ()*vect2.getX()-vect1.getX()*vect2.getZ();
		temp = (int)Math.round(yy*1000);
		yy = (double)temp/1000;
		double zz = vect1.getX()*vect2.getY()-vect1.getY()*vect2.getX();
		temp = (int)Math.round(zz*1000);
		zz = (double)temp/1000;
		return new Vector3D(xx,yy,zz);
	}
	
	/**calculates current vector module.*/
	public double getModule() { // округлять или нет? 
		double module = Math.sqrt(this.getScalarProduct(this));
		/* int temp = (int)Math.round(module*1000000);
		module = (double)temp/1000000; */
		return module;
	}
	
	/**calculates given argument vector module.*/
	public static double getModule(Vector3D vect) {
		double module = Math.sqrt(getScalarProduct(vect,vect));
		return module;
	}
	
	public static void main(String[] args) {
		Vector3D vect1 = new Vector3D(8.74,5.82,-4.9);
		Vector3D vect2 = new Vector3D(0.0,-5.17,12.0);
		Vector3D vect3 = new Vector3D();
		Vector3D vect4 = new Vector3D(vect1);
		System.out.println("Module of " + vect1 + ": " + vect1.getModule());
		System.out.println("Module of " + vect3 + ": " + vect3.getModule());
		System.out.println("Module of " + vect4 + ": " + vect4.getModule());
		System.out.println("Sum of " + vect1 + " and " + vect2 +": " + vect1.add(vect2));
		System.out.println("Scalar product of " + vect2 + " and " + vect4 +": " + vect2.getScalarProduct(vect4));
		System.out.println("Vector product of " + vect4 + " and " + vect2 +": " + vect4.getVectorProduct(vect2));
	}
}