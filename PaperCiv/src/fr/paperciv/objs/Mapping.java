package fr.paperciv.objs;

import java.util.ArrayList;

import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.buildings.BuildingType;
import fr.paperciv.objs.deposits.DepositType;
import fr.paperciv.objs.map.GameMap;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.races.RaceType;
import fr.paperciv.objs.units.Unit;
import fr.paperciv.objs.units.UnitType;

public class Mapping 
{
	private ArrayList<GameMap> GameMaps;
	private ArrayList<Race> Races;
	private ArrayList<RaceType> RaceTypes;
	private ArrayList<Unit> Units;
	private ArrayList<UnitType> UnitTypes;
	private ArrayList<Building> Buildings;
	private ArrayList<BuildingType> BuildingTypes;
	private ArrayList<DepositType> DepositTypes;
	
	public ArrayList<GameMap> getGameMaps() {return GameMaps;}
	public void setGameMaps(ArrayList<GameMap> gameMaps) {GameMaps = gameMaps;}
	
	public ArrayList<Race> getRaces() {return Races;}
	public void setRaces(ArrayList<Race> races) {Races = races;}
	
	public ArrayList<RaceType> getRaceTypes() {return RaceTypes;}
	public void setRaceTypes(ArrayList<RaceType> raceType) {RaceTypes = raceType;}
	
	public ArrayList<Unit> getUnits() {return Units;}
	public void setUnits(ArrayList<Unit> units) {Units = units;}
	
	public ArrayList<UnitType> getUnitTypes() {return UnitTypes;}
	public void setUnitTypes(ArrayList<UnitType> unitTypes) {UnitTypes = unitTypes;}
	
	public ArrayList<Building> getBuildings() {return Buildings;}
	public void setBuildings(ArrayList<Building> buildings) {Buildings = buildings;}
	
	public ArrayList<BuildingType> getBuildingTypes() {return BuildingTypes;	}
	public void setBuildingTypes(ArrayList<BuildingType> buildingTypes) {BuildingTypes = buildingTypes;}
	
	public ArrayList<DepositType> getDepositTypes() {return DepositTypes;}
	public void setDepositTypes(ArrayList<DepositType> depositTypes) {DepositTypes = depositTypes;}
	
	public Mapping() {}
	public Mapping(ArrayList<GameMap> gameMaps, ArrayList<Race> races,
			ArrayList<RaceType> raceTypes, ArrayList<Unit> units,
			ArrayList<UnitType> unitTypes, ArrayList<Building> buildings,
			ArrayList<BuildingType> buildingTypes,
			ArrayList<DepositType> depositTypes) {
		GameMaps = gameMaps;
		Races = races;
		RaceTypes = raceTypes;
		Units = units;
		UnitTypes = unitTypes;
		Buildings = buildings;
		BuildingTypes = buildingTypes;
		DepositTypes = depositTypes;
	}
}
