package fr.paperciv.objs.map;

import java.util.ArrayList;

public class GameMap 
{
	private int Id;
	private String Name;
	private String File;
	private String Texture;
	private int Length;
	private int PaperDepositsQuantity;
	private int FictiveDepositsQuantity;
	private int TreesQuantity;
	private int WaterQuantity;
	private double Height;
	private int HeightsQuantity;

	private ArrayList<Area> Areas;
	private int X;
	private int Z;
	
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	
	public String getName() {return Name;}
	public void setName(String name) {Name = name;}
	
	public String getFile() {return File;}
	public void setFile(String file) {File = file;}
	
	public String getTexture() {return Texture;}
	public void setTexture(String texture) {Texture = texture;}
	
	public int getLength() {return Length;}
	public void setLength(int length) {Length = length;}
	
	public int getPaperDepositsQuantity() {return PaperDepositsQuantity;}
	public void setPaperDepositsQuantity(int paperDepositsQuantity) {PaperDepositsQuantity = paperDepositsQuantity;}
	
	public int getFictiveDepositsQuantity() {return FictiveDepositsQuantity;}
	public void setFictiveDepositsQuantity(int fictiveDepositsQuantity) {FictiveDepositsQuantity = fictiveDepositsQuantity;}
	
	public int getTreesQuantity() {return TreesQuantity;}
	public void setTreesQuantity(int treesQuantity) {TreesQuantity = treesQuantity;}
	
	public int getWaterQuantity() {return WaterQuantity;}
	public void setWaterQuantity(int waterQuantity) {WaterQuantity = waterQuantity;}
	
	public double getHeight() {return Height;}
	public void setHeight(double height) {Height = height;}
	
	public int getHeightsQuantity() {return HeightsQuantity;}
	public void setHeightsQuantity(int heightsQuantity) {HeightsQuantity = heightsQuantity;}
	
	public ArrayList<Area> getAreas() {return Areas;}
	public void setAreas(ArrayList<Area> areas) {Areas = areas;}
	
	public int getX() {return X;}
	public void setX(int x) {X = x;}

	public int getZ() {return Z;}
	public void setZ(int z) {Z = z;}
	
	public GameMap(){}
	public GameMap(int id, String name, String file, String texture,
			int length, int paperDepositsQuantity, int fictiveDepositsQuantity,
			int treesQuantity, int waterQuantity, double height,
			int heightsQuantity, ArrayList<Area> areas, int x, int z) {
		Id = id;
		Name = name;
		File = file;
		Texture = texture;
		Length = length;
		PaperDepositsQuantity = paperDepositsQuantity;
		FictiveDepositsQuantity = fictiveDepositsQuantity;
		TreesQuantity = treesQuantity;
		WaterQuantity = waterQuantity;
		Height = height;
		HeightsQuantity = heightsQuantity;
		Areas = areas;
		X = x;
		Z = z;
	}
}
