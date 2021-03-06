package fr.wretchedlife.generator;

import java.util.ArrayList;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.factory.ItemFactory;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;
import fr.wretchedlife.obj.ext.ArmorItem;
import fr.wretchedlife.obj.ext.ConsumableItem;
import fr.wretchedlife.obj.ext.ContainerItem;
import fr.wretchedlife.obj.ext.WeaponItem;

public class ItemGenerator {
	
	public ItemGenerator() {
		
	}

	public static WeaponItem createClub( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Club" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\club.png" ) );
		weapon.setWeight( 5 );
		weapon.setProperties( null);
		weapon.setMinDamage( 4 );
		weapon.setMaxDamage( 7 );
		weapon.setDurability( 3 );
		weapon.setDurabilityRemain( 3 );
		weapon.setRequiredLevel( 1 );
		weapon.setRequiredStrengh( 1 );
		weapon.setRequiredAgility( 0 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createKnife( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Knife" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\knife.png" ) );
		weapon.setWeight( 2 );
		weapon.setProperties( null);
		weapon.setMinDamage( 5 );
		weapon.setMaxDamage( 6 );
		weapon.setDurability( 4 );
		weapon.setDurabilityRemain( 4 );
		weapon.setRequiredLevel( 1 );
		weapon.setRequiredStrengh( 0 );
		weapon.setRequiredAgility( 1 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createHammer( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Hammer" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\hammer.png" ) );
		weapon.setWeight( 4 );
		weapon.setProperties( null);
		weapon.setMinDamage( 4 );
		weapon.setMaxDamage( 7 );
		weapon.setDurability( 4 );
		weapon.setDurabilityRemain( 4 );
		weapon.setRequiredLevel( 1 );
		weapon.setRequiredStrengh( 1 );
		weapon.setRequiredAgility( 0 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createHatchet( final Player player ) {
		WeaponItem weapon = new WeaponItem();		
		weapon.setName( "Hatchet" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\hatchet.png" ) );
		weapon.setWeight( 4 );
		weapon.setProperties( null);
		weapon.setMinDamage( 5 );
		weapon.setMaxDamage( 8 );
		weapon.setDurability( 4 );
		weapon.setDurabilityRemain( 4 );
		weapon.setRequiredLevel( 1 );
		weapon.setRequiredStrengh( 1 );
		weapon.setRequiredAgility( 0 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createMace( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Mace" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\mace.png" ) );
		weapon.setWeight( 5 );
		weapon.setProperties( null);
		weapon.setMinDamage( 6 );
		weapon.setMaxDamage( 10 );
		weapon.setDurability( 5 );
		weapon.setDurabilityRemain( 5 );
		weapon.setRequiredLevel( 2 );
		weapon.setRequiredStrengh( 2 );
		weapon.setRequiredAgility( 0 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createSpear( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Iron spear" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\spear.png" ) );
		weapon.setWeight( 3 );
		weapon.setProperties( null);
		weapon.setMinDamage( 7 );
		weapon.setMaxDamage( 12 );
		weapon.setDurability( 3 );
		weapon.setDurabilityRemain( 3 );
		weapon.setRequiredLevel( 2 );
		weapon.setRequiredStrengh( 0 );
		weapon.setRequiredAgility( 2 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createCleaver( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Cleaver" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\cleaver.png" ) );
		weapon.setWeight( 6 );
		weapon.setProperties( null);
		weapon.setMinDamage( 8 );
		weapon.setMaxDamage( 13 );
		weapon.setDurability( 7 );
		weapon.setDurabilityRemain( 7 );
		weapon.setRequiredLevel( 3 );
		weapon.setRequiredStrengh( 2 );
		weapon.setRequiredAgility( 0 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static WeaponItem createGlavius( final Player player ) {
		WeaponItem weapon = new WeaponItem();
		weapon.setName( "Glavius" );
		weapon.setTexture( Constants.getTexture( ".\\img\\items\\glaive.png" ) );
		weapon.setWeight( 5 );
		weapon.setProperties( null);
		weapon.setMinDamage( 10 );
		weapon.setMaxDamage( 15 );
		weapon.setDurability( 6 );
		weapon.setDurabilityRemain( 6 );
		weapon.setRequiredLevel( 3 );
		weapon.setRequiredStrengh( 2 );
		weapon.setRequiredAgility( 1 );
		weapon.setRequiredKnowledge( 0 );
		
		return weapon;
	}
	
	public static ArmorItem createBasicVest( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Cloth Jacket" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_quiltedleather.png" ) );
		armor.setWeight( 5 );
		armor.setProperties( null );
		armor.setDefense( 4 );
		armor.setType( ArmorItem.Type.CHEST );
		armor.setDurability( 5 );
		armor.setDurabilityRemain( 5 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 1 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicTrousers( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Leather Pants" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_superbleathertrousers.png" ) );
		armor.setWeight( 3 );
		armor.setProperties( null );
		armor.setDefense( 3 );
		armor.setType( ArmorItem.Type.LEGS );
		armor.setDurability( 4 );
		armor.setDurabilityRemain( 4 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 1 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicBoots( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Leather Boots" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_soakedboots.png" ) );
		armor.setWeight( 4 );
		armor.setProperties( null );
		armor.setDefense( 2 );
		armor.setType( ArmorItem.Type.FEET );
		armor.setDurability( 3 );
		armor.setDurabilityRemain( 3 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 0 );
		armor.setRequiredAgility( 1 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicGloves( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Leather Gloves" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_wornleathergauntlets.png" ) );
		armor.setWeight( 1 );
		armor.setProperties( null );
		armor.setDefense( 1 );
		armor.setType( ArmorItem.Type.HANDS );
		armor.setDurability( 2 );
		armor.setDurabilityRemain( 2 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 0 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicHelmet( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Helm" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\heaume.png" ) );
		armor.setWeight( 4 );
		armor.setProperties( null );
		armor.setDefense( 4 );
		armor.setType( ArmorItem.Type.HEAD );
		armor.setDurability( 5 );
		armor.setDurabilityRemain( 5 );
		armor.setRequiredLevel( 2 );
		armor.setRequiredStrengh( 1 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicHood( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Cap" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\hood.png" ) );
		armor.setWeight( 2 );
		armor.setProperties( null );
		armor.setDefense( 2 );
		armor.setType( ArmorItem.Type.HEAD );
		armor.setDurability( 2 );
		armor.setDurabilityRemain( 2 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 0 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicBelt( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Leather Belt" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\leatherbelt.png" ) );
		armor.setWeight( 1 );
		armor.setProperties( null );
		armor.setDefense( 1 );
		armor.setType( ArmorItem.Type.BELT );
		armor.setDurability( 3 );
		armor.setDurabilityRemain( 3 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 0 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ArmorItem createBasicBracer( final Player player ) {
		ArmorItem armor = new ArmorItem();
		armor.setName( "Leather Armlet" );
		armor.setTexture( Constants.getTexture( ".\\img\\items\\leatherbracer.png" ) );
		armor.setWeight( 2 );
		armor.setProperties( null );
		armor.setDefense( 2 );
		armor.setType( ArmorItem.Type.ARM );
		armor.setDurability( 3 );
		armor.setDurabilityRemain( 3 );
		armor.setRequiredLevel( 1 );
		armor.setRequiredStrengh( 1 );
		armor.setRequiredAgility( 0 );
		armor.setRequiredKnowledge( 0 );
		
		return armor;
	}
	
	public static ConsumableItem createMeat( final Player player ) {
		ArrayList< ItemProperty > props = new ArrayList<ItemProperty>();
		ItemProperty hungerProp = new ItemProperty( ItemProperty.Code.Hunger, "+ 40%");
		ItemProperty thirstProp = new ItemProperty( ItemProperty.Code.Thirst, "+ 5%");
		props.add( hungerProp );
		props.add( thirstProp );
		
		ConsumableItem consumableItem = new ConsumableItem() {
			@Override
			public void use() {
				SoundFactory.playSound( SoundFactory.eatSoundFilePath );
				
				player.setHungerPercent( player.getHungerPercent() + 40 );
				player.setThirstPercent( player.getThirstPercent() + 5 );
				if(player.getHungerPercent() > 100) player.setHungerPercent(100);
				if(player.getThirstPercent() > 100) player.setThirstPercent(100);
			}
		};
		
		consumableItem.setName( "Meat" );
		consumableItem.setTexture( Constants.getTexture( ".\\img\\items\\meat.png" ) );
		consumableItem.setWeight( 2 );
		consumableItem.setProperties( props );
		
		return consumableItem;
	}
	
	public static ConsumableItem createWaterBottle( final Player player ) {
		ArrayList< ItemProperty > props = new ArrayList<ItemProperty>();
		ItemProperty thirstProp = new ItemProperty( ItemProperty.Code.Thirst, "+ 60%");
		props.add( thirstProp );
		
		ConsumableItem consumableItem = new ConsumableItem() {
			@Override
			public void use() {
				SoundFactory.playSound( SoundFactory.drinkSoundFilePath );
				
				player.setThirstPercent( player.getThirstPercent() + 60 );
				if(player.getThirstPercent() > 100) player.setThirstPercent(100);
			}
		};
		
		consumableItem.setName( "Water Bottle" );
		consumableItem.setTexture( Constants.getTexture( ".\\img\\items\\waterbottle.png" ) );
		consumableItem.setWeight( 1 );
		consumableItem.setProperties( props );
		
		return consumableItem;
	}
	
	public static ConsumableItem createTunaCan( final Player player ) {
		ArrayList< ItemProperty > props = new ArrayList<ItemProperty>();
		ItemProperty hungerProp = new ItemProperty( ItemProperty.Code.Hunger, "+ 25%");
		ItemProperty thristProp = new ItemProperty( ItemProperty.Code.Thirst, "+ 15%");
		props.add( hungerProp );
		props.add( thristProp );
		
		ConsumableItem consumableItem = new ConsumableItem() {
			@Override
			public void use() {
				SoundFactory.playSound( SoundFactory.eatSoundFilePath );
				SoundFactory.playSound( SoundFactory.drinkSoundFilePath );
				
				player.setHungerPercent( player.getHungerPercent() + 25 );
				player.setThirstPercent( player.getThirstPercent() + 15 );
				if(player.getHungerPercent() > 100) player.setHungerPercent(100);
				if(player.getThirstPercent() > 100) player.setThirstPercent(100);
			}
		};
		
		consumableItem.setName( "Tuna Can" );
		consumableItem.setTexture( Constants.getTexture( ".\\img\\items\\tunacan.png" ) );
		consumableItem.setWeight( 1 );
		consumableItem.setProperties( props );
		
		return consumableItem;
	}
	
	public static ConsumableItem createBandage( final Player player ) {
		ArrayList< ItemProperty > props = new ArrayList<ItemProperty>();
		ItemProperty lifeProp = new ItemProperty( ItemProperty.Code.Life, "+ 25");
		props.add( lifeProp );
		
		ConsumableItem consumableItem = new ConsumableItem() {
			@Override
			public void use() {
				player.setLifeRemain( player.getLifeRemain() + 25 );
				if(player.getLifeRemain() > player.getLife() ) player.setLifeRemain( player.getLife() );
			}
		};
		
		consumableItem.setName( "Bandages" );
		consumableItem.setTexture( Constants.getTexture( ".\\img\\items\\bandage.png" ) );
		consumableItem.setWeight( 1 );
		consumableItem.setProperties( props );
		
		return consumableItem;
	}
	
	public static ConsumableItem createMedikit( final Player player ) {
		ArrayList< ItemProperty > props = new ArrayList<ItemProperty>();
		ItemProperty lifeProp = new ItemProperty( ItemProperty.Code.Life, "+ 50");
		props.add( lifeProp );
		
		ConsumableItem consumableItem = new ConsumableItem() {
			@Override
			public void use() {
				player.setLifeRemain( player.getLifeRemain() + 50 );
				if(player.getLifeRemain() > player.getLife() ) player.setLifeRemain( player.getLife() );
			}
		};
		
		consumableItem.setName( "Medikit" );
		consumableItem.setTexture( Constants.getTexture( ".\\img\\items\\medikit.png" ) );
		consumableItem.setWeight( 1 );
		consumableItem.setProperties( props );
		
		return consumableItem;
	}
	
	public static ContainerItem createEmptyChest( final Player player) {
		ContainerItem containerItem = new ContainerItem();
		containerItem.setName( "Chest" );
		containerItem.setTexture( Constants.getTexture( ".\\img\\items\\chest.png" ) );
		containerItem.setWeight( 2 );
		containerItem.setProperties( null );
		containerItem.setInventory( new ArrayList<Item>() );
		containerItem.setLocked( false );
		
		return containerItem;
	}
	
	public static ContainerItem createChest( final Player player, int numberOfItems ) {
		ArrayList<Item> chestContent = new ArrayList<Item>();
		
		for (int i = 0; i < numberOfItems; i++) {
			Item randomItem = ItemFactory.getRandomItem(player);
			chestContent.add( randomItem );
		}
		
		ContainerItem containerItem = new ContainerItem();
		containerItem.setName( "Chest" );
		containerItem.setTexture( Constants.getTexture( ".\\img\\items\\chest.png" ) );
		containerItem.setWeight( 2 );
		containerItem.setProperties( null );
		containerItem.setInventory( chestContent );
		containerItem.setLocked( false );
		
		return containerItem;
	}
}
