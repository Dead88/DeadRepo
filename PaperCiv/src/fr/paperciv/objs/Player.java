package fr.paperciv.objs;

import java.util.ArrayList;

import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.units.Unit;

public class Player 
{
	private int Id;
	private int PaperAmount;
	private int FictiveRessourceAmount;
	private Race PlayerRace;
	private boolean isHumanControlled;
	private int BuildActionAmount;
	private int AttackActionAmount;
	private ArrayList<Unit> Units;
	private ArrayList<Building> Buildings;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public int getPaperAmount() {return PaperAmount;}
	public void setPaperAmount(int paperAmount) {PaperAmount = paperAmount;}
		
	public int getFictiveRessourceAmount() {return FictiveRessourceAmount;}
	public void setFictiveRessourceAmount(int fictiveRessourceAmount) {FictiveRessourceAmount = fictiveRessourceAmount;}
	
	public ArrayList<Unit> getUnits() {return Units;}
	public void setUnits(ArrayList<Unit> units) {Units = units;}
	
	public ArrayList<Building> getBuildings() {return Buildings;}
	public void setBuildings(ArrayList<Building> buildings) {Buildings = buildings;}
	
	public Race getPlayerRace() {return PlayerRace;}
	public void setPlayerRace(Race playerRace) {PlayerRace = playerRace;}
	
	public boolean isHumanControlled() {return isHumanControlled;}
	public void setHumanControlled(boolean isHumanControlled) {this.isHumanControlled = isHumanControlled;}
	
	public int getBuildActionAmount() {return BuildActionAmount;}
	public void setBuildActionAmount(int buildActionAmount) {BuildActionAmount = buildActionAmount;}
	
	public int getAttackActionAmount() {return AttackActionAmount;}
	public void setAttackActionAmount(int attackActionAmount) {AttackActionAmount = attackActionAmount;}
	
	public Player() {}
	public Player(int id, int paperAmount, int fictiveRessourceAmount,
			Race playerRace, boolean isHumanControlled, int buildActionAmount, int attackActionAmount,
			ArrayList<Unit> units, ArrayList<Building> buildings) {
		Id = id;
		PaperAmount = paperAmount;
		FictiveRessourceAmount = fictiveRessourceAmount;
		PlayerRace = playerRace;
		this.isHumanControlled = isHumanControlled;
		BuildActionAmount = buildActionAmount;
		AttackActionAmount = attackActionAmount;
		Units = units;
		Buildings = buildings;
	}
}
