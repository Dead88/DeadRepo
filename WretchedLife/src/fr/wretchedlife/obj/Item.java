package fr.wretchedlife.obj;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.wretchedlife.Constants;

public class Item {

	private String Id;
	private String Name;
	private ImageIcon Texture;
	private int Weight;
	
	private ArrayList< ItemProperty > Properties;
	
	public Item() {
		Id = Constants.getRandomId( Constants.itemIdLength );
	}

	public Item(String name, ImageIcon texture, int weight, ArrayList< ItemProperty > properties) {
		super();
		Id = Constants.getRandomId( Constants.itemIdLength );
		Name = name;
		Texture = texture;
		Weight = weight;
		Properties = properties;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ImageIcon getTexture() {
		return Texture;
	}

	public void setTexture(ImageIcon texture) {
		Texture = texture;
	}
	
	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	public ArrayList<ItemProperty> getProperties() {
		return Properties;
	}

	public void setProperties(ArrayList<ItemProperty> properties) {
		Properties = properties;
	}
}
