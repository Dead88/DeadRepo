package fr.paperciv.objs.map;

public class Mesh 
{	
	private String MeshType;
	private int X;
	private double Y;
	private int Z;
	private Vertex[] Vertices;

	public String getMeshType() {return MeshType;}
	public void setMeshType(String type) {MeshType = type;}
	
	public int getX() {return X;}
	public void setX(int x) {X = x;}
	
	public double getY() {return Y;}
	public void setY(double y) {Y = y;}
	
	public int getZ() {return Z;}
	public void setZ(int z) {Z = z;}
	
	public Vertex[] getVertices() {return Vertices;}
	public void setVertices(Vertex[] vertices) {Vertices = vertices;}
	
	public Mesh getMesh()
	{
		return this;
	}
	
	public Mesh(){}
	public Mesh(String meshType, int x, double y, int z, Vertex[] vertices) 
	{
		MeshType = meshType;
		X = x;
		Y = y;
		Z = z;
		Vertices = vertices;
	}
}
