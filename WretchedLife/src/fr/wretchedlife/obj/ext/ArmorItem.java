package fr.wretchedlife.obj.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;

public abstract class ArmorItem extends Item {

	private int Defense;
	private Enum Type;
	private int Durability;
	private int DurabilityRemain;
	private int RequiredLevel;
	private int RequiredStrengh;
	private int RequiredAgility;
	private int RequiredKnowledge;
	
	public static enum Type {
		HEAD,
		SHOULDER,
		ARM,
		HANDS,
		CHEST,
		BELT,
		LEGS,
		FEET
	}
	
	public ArmorItem() {
		super();
	}

	public ArmorItem(String name, ImageIcon texture, int weight,
			ArrayList<ItemProperty> properties, int defense,
			Type type, int durability,
			int durabilityRemain, int requiredLevel, int requiredStrengh,
			int requiredAgility, int requiredKnowledge) {
		super(name, texture, weight, properties);
		Defense = defense;
		Type = type;
		Durability = durability;
		DurabilityRemain = durabilityRemain;
		RequiredLevel = requiredLevel;
		RequiredStrengh = requiredStrengh;
		RequiredAgility = requiredAgility;
		RequiredKnowledge = requiredKnowledge;
	}

	public abstract void wear();
	public abstract void unWear();
	
	public int getDefense() {
		return Defense;
	}

	public void setDefense(int damage) {
		Defense = damage;
	}
	
	public Enum getType() {
		return Type;
	}

	public void setType(Enum type) {
		Type = type;
	}

	public int getDurability() {
		return Durability;
	}

	public void setDurability(int durability) {
		Durability = durability;
	}

	public int getDurabilityRemain() {
		return DurabilityRemain;
	}

	public void setDurabilityRemain(int durabilityRemain) {
		DurabilityRemain = durabilityRemain;
	}

	public int getRequiredLevel() {
		return RequiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		RequiredLevel = requiredLevel;
	}

	public int getRequiredStrengh() {
		return RequiredStrengh;
	}

	public void setRequiredStrengh(int requiredStrengh) {
		RequiredStrengh = requiredStrengh;
	}

	public int getRequiredAgility() {
		return RequiredAgility;
	}

	public void setRequiredAgility(int requiredAgility) {
		RequiredAgility = requiredAgility;
	}

	public int getRequiredKnowledge() {
		return RequiredKnowledge;
	}

	public void setRequiredKnowledge(int requiredKnowledge) {
		RequiredKnowledge = requiredKnowledge;
	}
}
