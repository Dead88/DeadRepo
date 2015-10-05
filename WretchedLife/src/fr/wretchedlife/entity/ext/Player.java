package fr.wretchedlife.entity.ext;

import java.util.ArrayList;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.item.ArmorItem;
import fr.wretchedlife.obj.item.WeaponItem;

public class Player extends Entity {
	private int level;
	private long experience;
	private long experienceToReach;
	private int life;
	private int lifeRemain;
	private int itemMinDamage;
	private int itemMaxDamage;
	private int itemDefense;
	
	private double hungerPercent;
	private double thirstPercent;
	
	private int strengh;
	private int agility;
	private int knowledge;
	private int skillPointsLeft;
	private int strenghBonus;
	private int agilityBonus;
	private int knowledgeBonus;
	
	private ArrayList<Item> inventory;
	private int inventoryMaxSize;
	
	private int transportableWeight;
	private int transportedWeight;
	
	private WeaponItem leftHandWeaponItem;
	private WeaponItem rightHandWeaponItem;
	
	private ArmorItem headArmor;
	private ArmorItem leftShoulderArmor;
	private ArmorItem rightShoulderArmor;
	private ArmorItem armArmor;
	private ArmorItem handsArmor;
	private ArmorItem chestArmor;
	private ArmorItem beltArmor;
	private ArmorItem legsArmor;
	private ArmorItem feetArmor;
	
	private boolean isMoving;

	public Player() {
		super("Player", Constants.getTexture( Constants.playerTexturePath ) );
		this.level = 1;
		this.experience = 0;
		this.experienceToReach = Constants.startingExperienceToReach;
		this.life = Constants.startingLife;
		this.lifeRemain = Constants.startingLife;
		this.itemMinDamage = 0;
		this.itemMaxDamage = 0;
		this.itemDefense = 0;
		
		this.hungerPercent = 100;
		this.thirstPercent = 100;
		
		this.strengh = 0;
		this.agility = 0;
		this.knowledge = 0;
		this.skillPointsLeft = 1;
		this.strenghBonus = 0;
		this.agilityBonus = 0;
		this.knowledgeBonus = 0;
		
		this.inventory = new ArrayList<Item>();
		this.inventoryMaxSize = Constants.startingInventoryMaxSize;
		
		this.transportableWeight = Constants.startingTransportableWeight;
		this.transportedWeight = 0;
		
		this.isMoving = false;
	}
	
	public void levelUp() {
		setLevel( getLevel() + 1 );
		setExperienceToReach( getExperienceToReach() + Math.round( getExperienceToReach() * Constants.levelUpRate ) );
		setLife( (int) Math.round( getLife() * Constants.lifeUpRate ) );
		setLifeRemain( (int) Math.round( getLifeRemain() * Constants.lifeUpRate ) );
		setSkillPointsLeft( getSkillPointsLeft() + 1 );
		setInventoryMaxSize( (int) Math.round( getInventoryMaxSize() * Constants.inventorySizeUpRate ) );
		setTransportableWeight( (int) Math.round( getTransportableWeight() * Constants.transportableWeightUpRate ) );
	}

	public void move( SinglePlayerGame playerGame, Area currentArea, Area destinationArea ) {
		setMoving( true );
		SoundFactory.playSound( SoundFactory.walkSoundFilePath );
		
		playerGame.setPlayerArea( destinationArea );
		
		consumeEnergy();
		currentArea.setEntity( null );
		destinationArea.setEntity( this );
		setMoving( false );
	}
	
	public boolean hasEnoughSkillToEquipItem( Item item ) {
		if( item instanceof WeaponItem ) {
			WeaponItem w = (WeaponItem) item;
			if( getLevel() >= w.getRequiredLevel() 
			&& ( getStrengh() + getStrenghBonus() ) >= w.getRequiredStrengh()
			&& ( getAgility() + getAgilityBonus() ) >= w.getRequiredAgility()
			&& ( getKnowledge() + getKnowledgeBonus() ) >= w.getRequiredKnowledge() ) {
				return true;
			}
		}
		else {
			ArmorItem a = (ArmorItem) item;
			if( getLevel() >= a.getRequiredLevel() 
			&& ( getStrengh() + getStrenghBonus() ) >= a.getRequiredStrengh()
			&& ( getAgility() + getAgilityBonus() ) >= a.getRequiredAgility()
			&& ( getKnowledge() + getKnowledgeBonus() ) >= a.getRequiredKnowledge() ) {
				return true;
			}
		}
		return false;
	}
	
	public void consumeEnergy() {
		
		if( getHungerPercent() - Constants.hungerDownPercent <= 0) {
			setLifeRemain( getLifeRemain() - 1 );
			setHungerPercent( 0 );
		}
		else setHungerPercent( getHungerPercent() - Constants.hungerDownPercent );
		
		if( getThirstPercent() - Constants.thirstDownPercent <= 0) {
			setLifeRemain( getLifeRemain() - 1 );
			setThirstPercent( 0 );
		}
		else setThirstPercent( getThirstPercent() - Constants.thirstDownPercent );
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExperience() {
		return experience;
	}

	public void setExperience(long experience) {
		this.experience = experience;
	}

	public long getExperienceToReach() {
		return experienceToReach;
	}

	public void setExperienceToReach(long experienceToReach) {
		this.experienceToReach = experienceToReach;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLifeRemain() {
		return lifeRemain;
	}

	public void setLifeRemain(int lifeRemain) {
		this.lifeRemain = lifeRemain;
	}
	
	public int getItemMinDamage() {
		return itemMinDamage;
	}

	public void setItemMinDamage(int itemMinDamage) {
		this.itemMinDamage = itemMinDamage;
	}

	public int getItemMaxDamage() {
		return itemMaxDamage;
	}

	public void setItemMaxDamage(int itemMaxDamage) {
		this.itemMaxDamage = itemMaxDamage;
	}

	public int getItemDefense() {
		return itemDefense;
	}

	public void setItemDefense(int itemDefense) {
		this.itemDefense = itemDefense;
	}

	public double getHungerPercent() {
		return hungerPercent;
	}

	public void setHungerPercent(double hungerPercent) {
		this.hungerPercent = hungerPercent;
	}

	public double getThirstPercent() {
		return thirstPercent;
	}

	public void setThirstPercent(double thirstPercent) {
		this.thirstPercent = thirstPercent;
	}

	public int getStrengh() {
		return strengh;
	}

	public void setStrengh(int strengh) {
		this.strengh = strengh;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public int getSkillPointsLeft() {
		return skillPointsLeft;
	}

	public void setSkillPointsLeft(int skillPointsLeft) {
		this.skillPointsLeft = skillPointsLeft;
	}

	public int getStrenghBonus() {
		return strenghBonus;
	}

	public void setStrenghBonus(int strenghBonus) {
		this.strenghBonus = strenghBonus;
	}

	public int getAgilityBonus() {
		return agilityBonus;
	}

	public void setAgilityBonus(int agilityBonus) {
		this.agilityBonus = agilityBonus;
	}

	public int getKnowledgeBonus() {
		return knowledgeBonus;
	}

	public void setKnowledgeBonus(int knowledgeBonus) {
		this.knowledgeBonus = knowledgeBonus;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public int getInventoryMaxSize() {
		return inventoryMaxSize;
	}

	public void setInventoryMaxSize(int inventoryMaxSize) {
		this.inventoryMaxSize = inventoryMaxSize;
	}

	public int getTransportableWeight() {
		return transportableWeight;
	}

	public void setTransportableWeight(int transportableWeight) {
		this.transportableWeight = transportableWeight;
	}

	public int getTransportedWeight() {
		return transportedWeight;
	}

	public void setTransportedWeight(int transportedWeight) {
		this.transportedWeight = transportedWeight;
	}

	public WeaponItem getLeftHandWeaponItem() {
		return leftHandWeaponItem;
	}

	public void setLeftHandWeaponItem(WeaponItem leftHandWeaponItem) {
		this.leftHandWeaponItem = leftHandWeaponItem;
	}

	public WeaponItem getRightHandWeaponItem() {
		return rightHandWeaponItem;
	}

	public void setRightHandWeaponItem(WeaponItem rightHandWeaponItem) {
		this.rightHandWeaponItem = rightHandWeaponItem;
	}

	public ArmorItem getHeadArmor() {
		return headArmor;
	}

	public void setHeadArmor(ArmorItem headArmor) {
		this.headArmor = headArmor;
	}

	public ArmorItem getLeftShoulderArmor() {
		return leftShoulderArmor;
	}

	public void setLeftShoulderArmor(ArmorItem leftShoulderArmor) {
		this.leftShoulderArmor = leftShoulderArmor;
	}

	public ArmorItem getRightShoulderArmor() {
		return rightShoulderArmor;
	}

	public void setRightShoulderArmor(ArmorItem rightShoulderArmor) {
		this.rightShoulderArmor = rightShoulderArmor;
	}

	public ArmorItem getArmArmor() {
		return armArmor;
	}

	public void setArmArmor(ArmorItem armArmor) {
		this.armArmor = armArmor;
	}

	public ArmorItem getHandsArmor() {
		return handsArmor;
	}

	public void setHandsArmor(ArmorItem handsArmor) {
		this.handsArmor = handsArmor;
	}

	public ArmorItem getChestArmor() {
		return chestArmor;
	}

	public void setChestArmor(ArmorItem chestArmor) {
		this.chestArmor = chestArmor;
	}

	public ArmorItem getBeltArmor() {
		return beltArmor;
	}

	public void setBeltArmor(ArmorItem beltArmor) {
		this.beltArmor = beltArmor;
	}

	public ArmorItem getLegsArmor() {
		return legsArmor;
	}

	public void setLegsArmor(ArmorItem legsArmor) {
		this.legsArmor = legsArmor;
	}

	public ArmorItem getFeetArmor() {
		return feetArmor;
	}

	public void setFeetArmor(ArmorItem feetArmor) {
		this.feetArmor = feetArmor;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
}
