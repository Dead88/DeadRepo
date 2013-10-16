package fr.paperciv.objs.units;

import fr.paperciv.objs.map.Mesh;


public class Unit extends Mesh
{
	private int Id;
	private String Name;
	private String File;
	private int Level;
	private String Texture;
	private UnitType Type;
	private int UnitTypeId;
	private int PaperCost;
	private int FictiveCost;
	private int[] RequiredBuildingIds;
	
	private int Life;
	private int Power;
	private int Armor;
	private int Speed;
	private int Range;
	private int FireFrequency;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}
	
	public String getFile() {return File;}
	public void setFile(String file) {File = file;}
	
	public int getLevel() {return Level;}
	public void setLevel(int level) {Level = level;}
	
	public int[] getRequiredBuildingIds() {return RequiredBuildingIds;}
	public void setRequiredBuildingIds(int[] requiredBuildingIds) {RequiredBuildingIds = requiredBuildingIds;}
	
	public String getTexture() {return Texture;}
	public void setTexture(String texture) {Texture = texture;}
	
	public UnitType getType() {return Type;}
	public void setType(UnitType type) {Type = type;}
	
	public int getUnitTypeId() {return UnitTypeId;}
	public void setUnitTypeId(int unitTypeId) {UnitTypeId = unitTypeId;}
	
	public int getPaperCost() {return PaperCost;}
	public void setPaperCost(int paperCost) {PaperCost = paperCost;}
	
	public int getFictiveCost() {return FictiveCost;}
	public void setFictiveCost(int fictiveCost) {FictiveCost = fictiveCost;}
	
	public int getLife() {return Life;}
	public void setLife(int life) {Life = life;}
	
	public int getPower() {return Power;}
	public void setPower(int power) {Power = power;}
	
	public int getArmor() {return Armor;}
	public void setArmor(int armor) {Armor = armor;}
	
	public int getSpeed() {return Speed;}
	public void setSpeed(int speed) {Speed = speed;}
	
	public int getRange() {return Range;}
	public void setRange(int range) {Range = range;}
	
	public int getFireFrequency() {return FireFrequency;}
	public void setFireFrequency(int fireFrequency) {FireFrequency = fireFrequency;}
	
	public Unit() 
	{
		super("Unit", 0, 0.00, 0, null);
	}
	public Unit(int id, String name, String file, int level, String texture,
			UnitType type, int unitTypeId, int paperCost, int fictiveCost,
			int[] requiredBuildingIds, int life, int power, int armor,
			int speed, int range, int fireFrequency, int x, double y, int z) {
		super("Unit", x, y, z, null);
		Id = id;
		Name = name;
		File = file;
		Level = level;
		Texture = texture;
		Type = type;
		UnitTypeId = unitTypeId;
		PaperCost = paperCost;
		FictiveCost = fictiveCost;
		RequiredBuildingIds = requiredBuildingIds;
		Life = life;
		Power = power;
		Armor = armor;
		Speed = speed;
		Range = range;
		FireFrequency = fireFrequency;
	}
}
