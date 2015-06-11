package fr.wretchedlife.map;

import java.util.ArrayList;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Enemy;

public class GameMap {

	private String id;
	private String name;
	private Type type;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int numberOfAreas;
	private int numberOfLines;
	private ArrayList<Area> areas;
	private String groundTexturePath;
	private String groundOverTexturePath;
	private String groundSelectedTexturePath;
	private boolean isLinkedToAnotherRegion;
	
	public static enum Type {
		OUTDOOR,
		BUILDING,
		UNDERGROUND
	}

	public GameMap() { 
		this.id = Constants.getRandomId( Constants.gameMapIdLength );
	}
	public GameMap(String name, Type type, ArrayList<Enemy> enemies,
			int numberOfAreas, int numberOfLines, ArrayList<Area> areas,
			String groundTexturePath, String groundOverTexturePath, 
			String groundSelectedTexturePath) {
		this.id = Constants.getRandomId( Constants.gameMapIdLength );
		this.name = name;
		this.type = type;
		this.enemies = enemies;
		this.numberOfAreas = numberOfAreas;
		this.numberOfLines = numberOfLines;
		this.areas = areas;
		this.groundTexturePath = groundTexturePath;
		this.groundOverTexturePath = groundOverTexturePath;
		this.groundSelectedTexturePath = groundSelectedTexturePath;
		this.isLinkedToAnotherRegion = false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	public int getNumberOfAreas() {
		return numberOfAreas;
	}
	public void setNumberOfAreas(int numberOfAreas) {
		this.numberOfAreas = numberOfAreas;
	}
	public int getNumberOfLines() {
		return numberOfLines;
	}
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}
	public ArrayList<Area> getAreas() {
		return areas;
	}
	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}
	public String getGroundTexturePath() {
		return groundTexturePath;
	}
	public void setGroundTexturePath(String groundTexturePath) {
		this.groundTexturePath = groundTexturePath;
	}
	public String getGroundOverTexturePath() {
		return groundOverTexturePath;
	}
	public void setGroundOverTexturePath(String groundOverTexturePath) {
		this.groundOverTexturePath = groundOverTexturePath;
	}
	public String getGroundSelectedTexturePath() {
		return groundSelectedTexturePath;
	}
	public void setGroundSelectedTexturePath(String groundSelectedTexturePath) {
		this.groundSelectedTexturePath = groundSelectedTexturePath;
	}
	public boolean isLinkedToAnotherRegion() {
		return isLinkedToAnotherRegion;
	}
	public void setLinkedToAnotherRegion(boolean isLinkedToAnotherRegion) {
		this.isLinkedToAnotherRegion = isLinkedToAnotherRegion;
	}
}
