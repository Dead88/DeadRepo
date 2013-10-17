package fr.paperciv.objs.map;

import fr.paperciv.objs.buildings.Building;

public class Area extends Mesh
{
	private String Texture;
	private AreaType AreaType; 
	private Mesh Doodad;
	private Building Building;
	
	public String getTexture() {return Texture;}
	public void setTexture(String texture) {Texture = texture;}
	
	public AreaType getAreaType() {return AreaType;}
	public void setAreaType(AreaType areaType) {AreaType = areaType;}
	
	public Mesh getDoodad() {return Doodad;}	
	public void setDoodad(Mesh doodad) {Doodad = doodad;}
	
	public Building getBuilding() {return Building;}
	public void setBuilding(Building building) {Building = building;}
	
	public Area(int x, double y, int z, Vertex[] vertices, String texture, AreaType areaType, Mesh doodad, Building building) 
	{
		super("Area", x, y, z, vertices);
		Texture = texture;
		AreaType = areaType;
		Doodad = doodad;
		Building = building;
	}
}
