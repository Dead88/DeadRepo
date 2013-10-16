package fr.paperciv.objs.races;

import java.util.ArrayList;

import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.deposits.DepositType;
import fr.paperciv.objs.units.Unit;

public class Race 
{
	private int Id;
	private String Name;
	private String File;
	
	private RaceType Type = null;
	private int RaceTypeId;
	
	private DepositType FictiveRessource = null;
	private int DepositTypeId;
	
	private ArrayList<Unit> Units = null;
	private int[] UnitsId = null;
	
	private ArrayList<Building> Buildings = null;
	private int[] BuildingsId = null;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}
	
	public String getFile() {return File;}
	public void setFile(String file) {File = file;}

	public RaceType getType() {return Type;}
	public void setType(RaceType type) {this.Type = type;}

	public DepositType getFictiveRessource() {return FictiveRessource;}
	public void setFictiveRessource(DepositType fictiveRessource) {this.FictiveRessource = fictiveRessource;}

	public int getRaceTypeId() {return RaceTypeId;}
	public void setRaceTypeId(int raceTypeId) {RaceTypeId = raceTypeId;}
	
	public int getDepositTypeId() {return DepositTypeId;}
	public void setDepositTypeId(int depositTypeId) {DepositTypeId = depositTypeId;}
	
	public int[] getUnitsId() {return UnitsId;}
	public void setUnitsId(int[] unitsId) {UnitsId = unitsId;}
	
	public int[] getBuildingsId() {return BuildingsId;}
	public void setBuildingsId(int[] buildingsId) {BuildingsId = buildingsId;}
	
	public ArrayList<Unit> getUnits() {return Units;}
	public void setUnits(ArrayList<Unit> units) {Units = units;}
	
	public ArrayList<Building> getBuildings() {return Buildings;}
	public void setBuildings(ArrayList<Building> buildings) {Buildings = buildings;}
	
	public Race() {}
	public Race(int id, String name, String file,
			int raceTypeId, int depositTypeId,
			int[] unitsId, int[] buildingsId) {
		this.Id = id;
		this.Name = name;
		this.File = file;
		this.RaceTypeId = raceTypeId;
		this.DepositTypeId = depositTypeId;
		this.UnitsId = unitsId;
		this.BuildingsId = buildingsId;
	}
	
}
