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
				int itemNumber = Constants.getRandomBetween(1, 17);
				switch( itemNumber ){
					case 1 : item = ItemGenerator.createClub( player ); break;
					case 2 : item = ItemGenerator.createKnife( player ); break;
					case 3 : item = ItemGenerator.createHammer( player ); break;
					case 4 : item = ItemGenerator.createMace( player ); break;
					case 5 : item = ItemGenerator.createHatchet( player ); break;
					case 6 : item = ItemGenerator.createMagicAttributeHatchet( player ); break;
					case 7 : item = ItemGenerator.createSpear( player ); break;
					case 8 : item = ItemGenerator.createCleaver( player ); break;
					case 9 : item = ItemGenerator.createGlavius( player ); break;
					case 10 : item = ItemGenerator.createBasicVest( player ); break;
					case 11 : item = ItemGenerator.createBasicTrousers( player ); break;
					case 12 : item = ItemGenerator.createBasicBoots( player ); break;
					case 13 : item = ItemGenerator.createBasicGloves( player ); break;
					case 14 : item = ItemGenerator.createBasicHelmet (player ); break;
					case 15 : item = ItemGenerator.createBasicHood( player ); break;
					case 16 : item = ItemGenerator.createBasicBelt( player ); break;
					case 17 : item = ItemGenerator.createBasicBracer( player ); break;
				}
				
				if( item instanceof WeaponItem ) {
					WeaponItem weapon = (WeaponItem) item;
					if( weapon.getRequiredLevel() <= region.getMaxLevel() ) {
						break;
					}
				}
				else if( item instanceof ArmorItem ) {
					ArmorItem armor = (ArmorItem) item;
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
		int itemNumber = Constants.getRandomBetween(1, 22);
		
		switch( itemNumber ){
			case 1 : return ItemGenerator.createClub( player );
			case 2 : return ItemGenerator.createKnife( player );
			case 3 : return ItemGenerator.createHammer( player );
			case 4 : return ItemGenerator.createMace( player );
			case 5 : return ItemGenerator.createHatchet( player );
			case 6 : return ItemGenerator.createMagicAttributeHatchet( player );
			case 7 : return ItemGenerator.createSpear( player );
			case 8 : return ItemGenerator.createCleaver( player );
			case 9 : return ItemGenerator.createGlavius( player );
			case 10 : return ItemGenerator.createBasicVest( player );
			case 11 : return ItemGenerator.createBasicTrousers( player );
			case 12 : return ItemGenerator.createBasicBoots( player );
			case 13 : return ItemGenerator.createBasicGloves( player );
			case 14 : return ItemGenerator.createBasicHelmet (player );
			case 15 : return ItemGenerator.createBasicHood( player );
			case 16 : return ItemGenerator.createBasicBelt( player );
			case 17 : return ItemGenerator.createBasicBracer( player );
			case 18 : return ItemGenerator.createMeat( player );
			case 19 : return ItemGenerator.createWaterBottle( player );
			case 20 : return ItemGenerator.createTunaCan( player );
			case 21 : return ItemGenerator.createBandage( player );
			case 22 : return ItemGenerator.createMedikit( player );
		}
	
		return null;
	}
	
	public static ArrayList<ItemProperty> getRamdomMagicalAttributes() {
		ArrayList<ItemProperty> props = new ArrayList<ItemProperty>();
		ItemProperty randomProp = null;
		int number = Constants.getRandomBetween(1, 3);
		
		for(int i=0; i < number; i++ ) {
			int randStatsBonus = Constants.getRandomBetween(1, 3);
			int randLifeBonus = Constants.getRandomBetween(5, 25);
			
			while(true) {
				int n = Constants.getRandomBetween(1, 3);
				
				switch(n) {
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
