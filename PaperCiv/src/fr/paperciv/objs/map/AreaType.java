package fr.paperciv.objs.map;

public class AreaType 
{
	public static final String GROUND_AREA = "groundArea";
	public static final String SEA_AREA = "seaArea";
	public static final String DOODAD_AREA = "doodadArea";
	
	private String Type;

	public String getType() {return Type;}
	public void setType(String type) {Type = type;}

	public AreaType(String type) 
	{
		this.Type = type;
	}
}
