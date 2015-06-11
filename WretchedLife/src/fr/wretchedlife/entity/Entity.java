package fr.wretchedlife.entity;

import javax.swing.ImageIcon;

import fr.wretchedlife.Constants;

public class Entity {

	private String Id;
	private String Name;
	private ImageIcon Texture;
	
	public Entity() {
		Id = Constants.getRandomId( Constants.itemIdLength );
	}

	public Entity(String name, ImageIcon texture) {
		Id = Constants.getRandomId( Constants.itemIdLength );
		Name = name;
		Texture = texture;
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
}
