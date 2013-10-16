package fr.paperciv.objs.map;

public class Vertex 
{
	private double X; 
	private double Y;
	private double Z;
	
	public double getX() {return X;}
	public void setX(double x) {X = x;}
	
	public double getY() {return Y;}
	public void setY(double y) {Y = y;}
	
	public double getZ() {return Z;}
	public void setZ(double z) {Z = z;}
	
	public Vertex(double x, double y, double z) 
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
	}
}
