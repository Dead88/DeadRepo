package fr.paperciv.objs.races;


public class RaceType 
{
	private int Id;
	private String Name;
	private String File;
//	public Period Period;
	
	public boolean Lvl1Enabled;
	public int NumberUnitsLvl1;
	public int NumberBuildingsLvl1;
//	
//	public static boolean Lvl2Enabled;
//	public static int NumberUnitsLvl2;
//	public static int NumberBuildingsLvl2;
//	
//	public static boolean Lvl3Enabled;
//	public static int NumberUnitsLvl3;
//	public static int NumberBuildingsLvl3;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}
	
	public String getFile() {return File;}
	public void setFile(String file) {File = file;}
	
//	public Period getPeriod() {return this.Period;}
//	public void setPeriod(Period period) {this.Period = period;}
	
	public boolean isLvl1Enabled() {return this.Lvl1Enabled;}
	public void setLvl1Enabled(boolean lvl1Enabled) {this.Lvl1Enabled = lvl1Enabled;}
	
	public int getNumberUnitsLvl1() {return this.NumberUnitsLvl1;}
	public void setNumberUnitsLvl1(int numberUnitsLvl1) {this.NumberUnitsLvl1 = numberUnitsLvl1;}
	
	public int getNumberBuildingsLvl1() {return this.NumberBuildingsLvl1;}
	public void setNumberBuildingsLvl1(int numberBuildingsLvl1) {this.NumberBuildingsLvl1 = numberBuildingsLvl1;}
	
	public RaceType(){}
	public RaceType(String name, boolean lvl1Enabled, int numberUnitsLvl1, int numberBuildingsLvl1)
	{	
		this.Name = name;
		this.Lvl1Enabled = lvl1Enabled;
		this.NumberUnitsLvl1 = numberUnitsLvl1;
		this.NumberBuildingsLvl1 = numberBuildingsLvl1;
	}
}
