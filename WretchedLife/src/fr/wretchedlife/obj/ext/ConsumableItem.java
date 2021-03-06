package fr.wretchedlife.obj.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;

public abstract class ConsumableItem extends Item {
	
	public ConsumableItem() {
		super();
	}
	
	public ConsumableItem(String name, ImageIcon texture, int weight,
			ArrayList<ItemProperty> properties) {
		super(name, texture, weight, properties);
	}

	public abstract void use();
}
