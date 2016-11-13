package fr.wretchedlife.map;

import javax.swing.ImageIcon;

import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.obj.Item;

public class Area {

	private int X;
	private int Y;
	private ImageIcon texture;
	private Type type;
	
	private boolean visible;
	private Entity entity;
	private Item item;
	
	public Area() {}
	public Area(int x, int y, ImageIcon texture, Type type) {
		super();
		X = x;
		Y = y;
		this.texture = texture;
		this.type = type;
		this.visible = false;
		this.item = null;
	}

	public static enum Type {
		GROUND_AREA,
		SEA_AREA,
	}
	
	public boolean isCoordinateOfArea(int x, int y) {
		if(
			(
				x > getX()
				&& x < getX() + ( getTexture().getIconWidth() )
			) 
			&&
			(
				y > getY()
				&& y < getY() + ( getTexture().getIconHeight() ) 
			) 
		) {
			return true;
		}
		
		return false;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public ImageIcon getTexture() {
		return texture;
	}

	public void setTexture(ImageIcon texture) {
		this.texture = texture;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
