package fr.wretchedlife.obj.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;

public class ContainerItem extends Item {

	private ArrayList<Item> inventory;
	private boolean isLocked;
	
	public ContainerItem() {
		super();
	}
	
	public ContainerItem(String name, ImageIcon texture, int weight,
			ArrayList<ItemProperty> properties, ArrayList<Item> inventory,
			boolean isLocked) {
		super(name, texture, weight, properties);
		this.inventory = inventory;
		this.isLocked = isLocked;
	}

	@Override
	public int getWeight() {
		int inventoryWeight = super.getWeight();
		
		if(getInventory() == null ) return inventoryWeight;
		
		for( int i = 0; i < getInventory().size(); i++) {
			Item item = getInventory().get(i);
			inventoryWeight += item.getWeight();
		}
		return inventoryWeight;
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
}
