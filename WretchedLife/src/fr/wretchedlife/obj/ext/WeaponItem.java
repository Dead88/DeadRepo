package fr.wretchedlife.obj.ext;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.factory.ItemFactory;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;

public class WeaponItem extends Item {

	private int MinDamage;
	private int MaxDamage;
	private int Durability;
	private int DurabilityRemain;
	private int RequiredLevel;
	private int RequiredStrengh;
	private int RequiredAgility;
	private int RequiredKnowledge;
	
	public WeaponItem() {
		super();
	}
	
	public WeaponItem(String name, ImageIcon texture, int weight,
			ArrayList<ItemProperty> properties, int minDamage, int maxDamage,
			int durability, int durabilityRemain, int requiredLevel,
			int requiredStrengh, int requiredAgility, int requiredKnowledge) {
		super(name, texture, weight, properties);
		MinDamage = minDamage;
		MaxDamage = maxDamage;
		Durability = durability;
		DurabilityRemain = durabilityRemain;
		RequiredLevel = requiredLevel;
		RequiredStrengh = requiredStrengh;
		RequiredAgility = requiredAgility;
		RequiredKnowledge = requiredKnowledge;
	}
	
	public void wear( Player player ) {
		player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
		player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
		
		if(this.getProperties() != null) {
			ItemFactory.onHandleMagicalWeapon(player, this, false);
		}
	}
	public void unWear( Player player ) {
		player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
		player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
		
		if(this.getProperties() != null) {
			ItemFactory.onHandleMagicalWeapon(player, this, true);
		}
	}

	public int getMinDamage() {
		return MinDamage;
	}

	public void setMinDamage(int minDamage) {
		MinDamage = minDamage;
	}

	public int getMaxDamage() {
		return MaxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		MaxDamage = maxDamage;
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
