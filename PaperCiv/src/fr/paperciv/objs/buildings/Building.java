package fr.paperciv.objs.buildings;

import fr.paperciv.objs.map.Mesh;

public class Building extends Mesh
{
	private int Id;
	private String Name;
	private String File;
	private int Level;
	private String Texture;
	private BuildingType Type;
	private int BuildingTypeId;
	private int PaperCost;
	private int FictiveCost;
	private int[] RequiredBuildingIds;
	
	private int Life;
	private int LifeRemaining;
	private int Armor;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}
	
	public String getFile() {return File;}
	public void setFile(String file) {File = file;}
	
	public int getLevel() {return Level;}
	public void setLevel(int level) {Level = level;}
	
	public String getTexture() {return Texture;}
	public void setTexture(String texture) {Texture = texture;}
	
	public BuildingType getType() {return Type;}
	public void setType(BuildingType type) {Type = type;}
	
	public int getBuildingTypeId() {return BuildingTypeId;}
	public void setBuildingTypeId(int buildingTypeId) {BuildingTypeId = buildingTypeId;}
	
	public int getPaperCost() {return PaperCost;}
	public void setPaperCost(int paperCost) {PaperCost = paperCost;}
	
	public int getFictiveCost() {return FictiveCost;}
	public void setFictiveCost(int fictiveCost) {FictiveCost = fictiveCost;}
	
	public int[] getRequiredBuildingIds() {return RequiredBuildingIds;}
	public void setRequiredBuildingIds(int[] requiredBuildingIds) {RequiredBuildingIds = requiredBuildingIds;}
	
	public int getLife() {return Life;}
	public void setLife(int life) {Life = life;}
	
	public int getLifeRemaining() {return LifeRemaining;}
	public void setLifeRemaining(int lifeRemaining) {LifeRemaining = lifeRemaining;}
	
	public int getArmor() {return Armor;}
	public void setArmor(int armor) {Armor = armor;}
	
	public Building() {
		super("Building", 0, 0.00, 0, null);
	}
	public Building(int id, String name, String file, int level,
			String texture, BuildingType type, int buildingTypeId,
			int paperCost, int fictiveCost, int[] requiredBuildingIds,
			int life, int armor, int x, double y, int z) {
		super("Building", x, y, z, null);
		Id = id;
		Name = name;
		File = file;
		Level = level;
		Texture = texture;
		Type = type;
		BuildingTypeId = buildingTypeId;
		PaperCost = paperCost;
		FictiveCost = fictiveCost;
		RequiredBuildingIds = requiredBuildingIds;
		Life = life;
		LifeRemaining = life;
		Armor = armor;
	}
}
