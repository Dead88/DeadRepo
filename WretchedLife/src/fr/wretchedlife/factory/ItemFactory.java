package fr.wretchedlife.factory;

import java.util.ArrayList;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.generator.ItemGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;
import fr.wretchedlife.obj.ext.ArmorItem;
import fr.wretchedlife.obj.ext.ConsumableItem;
import fr.wretchedlife.obj.ext.ContainerItem;
import fr.wretchedlife.obj.ext.WeaponItem;

public class ItemFactory {
	
	public static void generateRandomItems( GameMap region, Player player, int number ) {
		
		Area randomArea = null;
		Item item = null;
		
		for( int i = 0; i < number; i++){
			while(true) {
				int itemNumber = Constants.getRandomBetween(1, 16);
				switch( itemNumber ){
					case 1 : item = ItemGenerator.createClub( player ); break;
					case 2 : item = ItemGenerator.createKnife( player ); break;
					case 3 : item = ItemGenerator.createHammer( player ); break;
					case 4 : item = ItemGenerator.createMace( player ); break;
					case 5 : item = ItemGenerator.createHatchet( player ); break;
					case 6 : item = ItemGenerator.createSpear( player ); break;
					case 7 : item = ItemGenerator.createCleaver( player ); break;
					case 8 : item = ItemGenerator.createGlavius( player ); break;
					case 9 : item = ItemGenerator.createBasicVest( player ); break;
					case 10 : item = ItemGenerator.createBasicTrousers( player ); break;
					case 11 : item = ItemGenerator.createBasicBoots( player ); break;
					case 12 : item = ItemGenerator.createBasicGloves( player ); break;
					case 13 : item = ItemGenerator.createBasicHelmet (player ); break;
					case 14 : item = ItemGenerator.createBasicHood( player ); break;
					case 15 : item = ItemGenerator.createBasicBelt( player ); break;
					case 16 : item = ItemGenerator.createBasicBracer( player ); break;
				}
				
				if( item instanceof WeaponItem ) {
					WeaponItem weapon = (WeaponItem) item;
					
					int rand = Constants.getRandomBetween(1, 100);
					if(rand < Constants.magicalDropPercent ) {
						weapon.setName( "Magical " + weapon.getName() );
						weapon.setProperties( getRamdomMagicalAttributes() );
						weapon.setRequiredKnowledge( weapon.getProperties().size() );
					}
					
					if( weapon.getRequiredLevel() <= region.getMaxLevel() ) {
						break;
					}
				}
				else if( item instanceof ArmorItem ) {
					ArmorItem armor = (ArmorItem) item;
					
					int rand = Constants.getRandomBetween(1, 100);
					if(rand < Constants.magicalDropPercent ) {
						armor.setName( "Magical " + armor.getName() );
						armor.setProperties( getRamdomMagicalAttributes() );
						armor.setRequiredKnowledge( armor.getProperties().size() );
					}
					
					if( armor.getRequiredLevel() <= region.getMaxLevel() ) {
						break;
					}
				}
			}
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null  ){
					randomArea.setItem(item);
					break;
				}
			}
		}
	}
	
	public static void generateRandomConsumableItems( GameMap region, Player player, int number ) {
		
		Area randomArea = null;
		ConsumableItem consumableItem = null;
		
		for( int i = 0; i < number; i++){
			int itemNumber = Constants.getRandomBetween(1, 3);
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					switch( itemNumber ){
						case 1 : consumableItem = ItemGenerator.createMeat( player ); break;
						case 2 : consumableItem = ItemGenerator.createWaterBottle( player ); break;
						case 3 : consumableItem = ItemGenerator.createTunaCan( player ); break;
					}
					randomArea.setItem(consumableItem);
					break;
				}
			}
		}
	}
	
	public static void generateRandomHealingConsumableItems( GameMap region, Player player, int number ) {
		
		Area randomArea = null;
		ConsumableItem consumableItem = null;
		
		for( int i = 0; i < number; i++){
			int itemNumber = Constants.getRandomBetween(1, 2);
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					switch( itemNumber ){
						case 1 : consumableItem = ItemGenerator.createBandage( player ); break;
						case 2 : consumableItem = ItemGenerator.createMedikit( player ); break;
					}
					randomArea.setItem(consumableItem);
					break;
				}
			}
		}
	}
	
	public static void generateRandomContainerItems( GameMap region, Player player, int number ) {
		
		ContainerItem chest = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++) {
			chest = ItemGenerator.createChest( player, 
				Constants.getRandomBetween( Constants.minItemsPerRandomChest, Constants.maxItemsPerRandomChest) );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					randomArea.setItem( chest ); 
					break;
				}
			}
		}
	}
	
	public static Item getRandomItem( Player player ) {
		//no chest
		int itemNumber = Constants.getRandomBetween(1, 21);
		Item item = null;
		
		while(true) {
			switch( itemNumber ){
				case 1 : item = ItemGenerator.createClub( player );
				case 2 : item = ItemGenerator.createKnife( player );
				case 3 : item = ItemGenerator.createHammer( player );
				case 4 : item = ItemGenerator.createMace( player );
				case 5 : item = ItemGenerator.createHatchet( player );
				case 6 : item = ItemGenerator.createSpear( player );
				case 7 : item = ItemGenerator.createCleaver( player );
				case 8 : item = ItemGenerator.createGlavius( player );
				case 9 : item = ItemGenerator.createBasicVest( player );
				case 10 : item = ItemGenerator.createBasicTrousers( player );
				case 11 : item = ItemGenerator.createBasicBoots( player );
				case 12 : item = ItemGenerator.createBasicGloves( player );
				case 13 : item = ItemGenerator.createBasicHelmet (player );
				case 14 : item = ItemGenerator.createBasicHood( player );
				case 15 : item = ItemGenerator.createBasicBelt( player );
				case 16 : item = ItemGenerator.createBasicBracer( player );
				case 17 : item = ItemGenerator.createMeat( player );
				case 18 : item = ItemGenerator.createWaterBottle( player );
				case 19 : item = ItemGenerator.createTunaCan( player );
				case 20 : item = ItemGenerator.createBandage( player );
				case 21 : item = ItemGenerator.createMedikit( player );
			}
		
			if( item instanceof WeaponItem ) {
				WeaponItem weapon = (WeaponItem) item;
				
				int rand = Constants.getRandomBetween(1, 100);
				if(rand < Constants.magicalDropPercent ) {
					weapon.setName( "Magical " + weapon.getName() );
					weapon.setProperties( getRamdomMagicalAttributes() );
					weapon.setRequiredKnowledge( weapon.getProperties().size() );
				}
				return weapon;
			}
			else if( item instanceof ArmorItem ) {
				ArmorItem armor = (ArmorItem) item;
				
				int rand = Constants.getRandomBetween(1, 100);
				if(rand < Constants.magicalDropPercent ) {
					armor.setName( "Magical " + armor.getName() );
					armor.setProperties( getRamdomMagicalAttributes() );
					armor.setRequiredKnowledge( armor.getProperties().size() );
				}
				return armor;
			}
			else return item;
		}	
	}
	
	public static ArrayList<ItemProperty> getRamdomMagicalAttributes() {
		ArrayList<ItemProperty> props = new ArrayList<ItemProperty>();
		ItemProperty randomProp = null;
		int number = Constants.getRandomBetween(1, 3);
		
		for(int i=0; i < number; i++ ) {
			int randStatsBonus = Constants.getRandomBetween(1, 3);
			
			while(true) {
				int randPropNumber = Constants.getRandomBetween(1, 3);
				
				switch(randPropNumber) {
					case 1 : randomProp = new ItemProperty( ItemProperty.Code.Strengh, "+ "+randStatsBonus ); break;
					case 2 : randomProp = new ItemProperty( ItemProperty.Code.Agility, "+ "+randStatsBonus ); break;
					case 3 : randomProp = new ItemProperty( ItemProperty.Code.Knowledge, "+ "+randStatsBonus ); break;
				}
				
				boolean isRandomPropAlreadyAdded = false;
				for(ItemProperty itemProp : props) {
					if(itemProp.getCode() == randomProp.getCode()){
						isRandomPropAlreadyAdded = true;
						break;
					}
				}
				
				if(!isRandomPropAlreadyAdded) {
					props.add( randomProp );
					break;
				}
			}
		}
		
		return props;
	}
	
	public static void onHandleMagicalWeapon( Player player, WeaponItem weapon, boolean unwear ) {
		for(int i=0; i < weapon.getProperties().size() ; i++ ) {
			ItemProperty hatchetProp = weapon.getProperties().get(i);
			int hatchetPropPower = Integer.parseInt( hatchetProp.getValue().replace("+ ", "") );
			
			if(unwear) {
				if(hatchetProp.getCode() == ItemProperty.Code.Strengh) {
					player.setStrenghBonus( player.getStrenghBonus() - hatchetPropPower );
				}
				else if(hatchetProp.getCode() == ItemProperty.Code.Agility) {
					player.setAgilityBonus( player.getAgilityBonus() - hatchetPropPower );				
				}
				else if(hatchetProp.getCode() == ItemProperty.Code.Knowledge) {
					player.setKnowledgeBonus( player.getKnowledgeBonus() - hatchetPropPower );
				}
			}
			else {
				if(hatchetProp.getCode() == ItemProperty.Code.Strengh) {
					player.setStrenghBonus( player.getStrenghBonus() + hatchetPropPower );
				}
				else if(hatchetProp.getCode() == ItemProperty.Code.Agility) {
					player.setAgilityBonus( player.getAgilityBonus() + hatchetPropPower );				
				}
				else if(hatchetProp.getCode() == ItemProperty.Code.Knowledge) {
					player.setKnowledgeBonus( player.getKnowledgeBonus() + hatchetPropPower );
				}
			}
		}
	}
}
