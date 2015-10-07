package fr.wretchedlife.factory;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.generator.ItemGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.item.ArmorItem;
import fr.wretchedlife.obj.item.ConsumableItem;
import fr.wretchedlife.obj.item.ContainerItem;
import fr.wretchedlife.obj.item.WeaponItem;

public class ItemFactory {
	
	public static void generateRandomItems( GameMap region, Player player, int number ) {
		
		WeaponItem club = null;
		WeaponItem knife = null;
		WeaponItem hammer = null;
		WeaponItem hatchet = null;
		WeaponItem magicalHatchet = null;
		WeaponItem cleaver = null;
		ArmorItem veste = null;
		ArmorItem pantalon = null;
		ArmorItem chausses = null;
		ArmorItem mitaine = null;
		ArmorItem helmet = null;
		ArmorItem belt = null;
		ArmorItem bracer = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++){
			int itemNumber = Constants.getRandomBetween(1, 13);
			club = ItemGenerator.createGourdin( player );
			knife = ItemGenerator.createKnife( player );
			hammer = ItemGenerator.createHammer( player );
			hatchet = ItemGenerator.createHatchet( player );
			magicalHatchet = ItemGenerator.createMagicAttributeHatchet( player );
			cleaver = ItemGenerator.createCleaver( player );
			veste = ItemGenerator.createBasicVest( player );
			pantalon = ItemGenerator.createBasicTrousers( player );
			chausses = ItemGenerator.createBasicBoots( player );
			mitaine = ItemGenerator.createBasicGloves( player );
			helmet = ItemGenerator.createBasicHelmet (player );
			belt = ItemGenerator.createBasicBelt( player );
			bracer = ItemGenerator.createBasicBracer( player );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null  ){
					switch( itemNumber ){
						case 1 : randomArea.setItem( club ); break;
						case 2 : randomArea.setItem( knife ); break;
						case 3 : randomArea.setItem( hammer ); break;
						case 4 : randomArea.setItem( hatchet ); break;
						case 5 : randomArea.setItem( magicalHatchet ); break;
						case 6 : randomArea.setItem( cleaver ); break;
						case 7 : randomArea.setItem( veste ); break;
						case 8 : randomArea.setItem( pantalon );break;
						case 9 : randomArea.setItem( chausses );break;
						case 10 : randomArea.setItem( mitaine );break;
						case 11 : randomArea.setItem( helmet );break;
						case 12 : randomArea.setItem( belt );break;
						case 13 : randomArea.setItem( bracer );break;
					}
					break;
				}
			}
		}
	}
	
	public static void generateRandomConsumableItems( GameMap region, Player player, int number ) {
		
		ConsumableItem meat = null;
		ConsumableItem waterbottle = null;
		ConsumableItem tunaCan = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++){
			int itemNumber = Constants.getRandomBetween(1, 3);
			meat = ItemGenerator.createMeat( player );
			waterbottle = ItemGenerator.createWaterBottle( player );
			tunaCan = ItemGenerator.createTunaCan( player );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					switch( itemNumber ){
						case 1 : randomArea.setItem( meat ); break;
						case 2 : randomArea.setItem( waterbottle ); break;
						case 3 : randomArea.setItem( tunaCan ); break;
					}
					break;
				}
			}
		}
	}
	
	public static void generateRandomHealingConsumableItems( GameMap region, Player player, int number ) {
		
		ConsumableItem bandage = null;
		ConsumableItem medikit = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++){
			int itemNumber = Constants.getRandomBetween(1, 2);
			bandage = ItemGenerator.createBandage( player );
			medikit = ItemGenerator.createMedikit( player );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					switch( itemNumber ){
						case 1 : randomArea.setItem( bandage ); break;
						case 2 : randomArea.setItem( medikit ); break;
					}
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
		int itemNumber = Constants.getRandomBetween(1, 18);
		WeaponItem club = ItemGenerator.createGourdin( player );
		WeaponItem knife = ItemGenerator.createKnife( player );
		WeaponItem hammer = ItemGenerator.createHammer( player );
		WeaponItem hatchet = ItemGenerator.createHatchet( player );
		WeaponItem magicalHatchet = ItemGenerator.createMagicAttributeHatchet( player );
		WeaponItem cleaver = ItemGenerator.createCleaver( player );
		ArmorItem veste = ItemGenerator.createBasicVest( player );
		ArmorItem pantalon = ItemGenerator.createBasicTrousers( player );
		ArmorItem chausses = ItemGenerator.createBasicBoots( player );
		ArmorItem mitaine = ItemGenerator.createBasicGloves( player );
		ArmorItem helmet = ItemGenerator.createBasicHelmet (player );
		ArmorItem belt = ItemGenerator.createBasicBelt( player );
		ArmorItem bracer = ItemGenerator.createBasicBracer( player );
		ConsumableItem meat = ItemGenerator.createMeat( player );
		ConsumableItem waterbottle = ItemGenerator.createWaterBottle( player );
		ConsumableItem tunaCan = ItemGenerator.createTunaCan( player );
		ConsumableItem bandage = ItemGenerator.createBandage( player );
		ConsumableItem medikit = ItemGenerator.createMedikit( player );
		
		switch( itemNumber ){
			case 1 : return club;
			case 2 : return knife;
			case 3 : return hammer;
			case 4 : return hatchet;
			case 5 : return magicalHatchet;
			case 6 : return cleaver;
			case 7 : return veste;
			case 8 : return pantalon;
			case 9 : return chausses;
			case 10 : return mitaine;
			case 11 : return helmet;
			case 12 : return belt;
			case 13 : return bracer;
			case 14 : return meat;
			case 15 : return waterbottle;
			case 16 : return tunaCan;
			case 17 : return bandage;
			case 18 : return medikit;
		}
		
		return null;
	}
}
