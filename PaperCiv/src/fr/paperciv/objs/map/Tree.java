package fr.paperciv.objs.map;

public class Tree extends Mesh
{
	private String TrunkTexture;
	private String FoliageTexture;
	
	public String getTrunkTexture() {return TrunkTexture;}
	public void setTrunkTexture(String trunkTexture) {TrunkTexture = trunkTexture;}
	
	public String getFoliageTexture() {return FoliageTexture;}
	public void setFoliageTexture(String foliageTexture) {FoliageTexture = foliageTexture;}
	
	public Tree(int x, double y, int z, Vertex[] vertices, String trunkTexture, String foliageTexture) 
	{
		super("Tree", x, y, z, vertices);
		TrunkTexture = trunkTexture;
		FoliageTexture = foliageTexture;
	}
}
