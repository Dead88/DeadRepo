package fr.paperciv.objs.map;

public class Area extends Mesh
{
	private String Texture;
	private AreaType AreaType; 
	private Mesh Doodad;
	
	public String getTexture() {return Texture;}
	public void setTexture(String texture) {Texture = texture;}
	
	public AreaType getAreaType() {return AreaType;}
	public void setAreaType(AreaType areaType) {AreaType = areaType;}
	
	public Mesh getDoodad() {return Doodad;}	
	public void setDoodad(Mesh doodad) {Doodad = doodad;}
	
	public Area(int x, double y, int z, Vertex[] vertices, String texture, AreaType areaType, Mesh doodad) 
	{
		super("Area", x, y, z, vertices);
		Texture = texture;
		AreaType = areaType;
		Doodad = doodad;
	}
}
