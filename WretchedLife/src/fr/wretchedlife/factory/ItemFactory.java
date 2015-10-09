package fr.wretchedlife.factory;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.generator.ItemGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ext.ArmorItem;
import fr.wretchedlife.obj.ext.ConsumableItem;
import fr.wretchedlife.obj.ext.ContainerItem;
import fr.wretchedlife.obj.ext.WeaponItem;

public class ItemFactory {
	
	public static void generateRandomItems( GameMap region, Player player, int number ) {
		
		WeaponItem club = null;
		WeaponItem knife = null;
		WeaponItem hammer = null;
		WeaponItem mace = null;
		WeaponItem hatchet = null;
		WeaponItem magicalHatchet = null;
		WeaponItem spear = null;
		WeaponItem cleaver = null;
		WeaponItem glavius = null;
		ArmorItem veste = null;
		ArmorItem pantalon = null;
		ArmorItem chausses = null;
		ArmorItem mitaine = null;
		ArmorItem helmet = null;
		ArmorItem hood = null;
		ArmorItem belt = null;
		ArmorItem bracer = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++){
			int itemNumber = Constants.getRandomBetween(1, 17);
			club = ItemGenerator.createGourdin( player );
			knife = ItemGenerator.createKnife( player );
			hammer = ItemGenerator.createHammer( player );
			mace = ItemGenerator.createMace( player );
			hatchet = ItemGenerator.createHatchet( player );
			magicalHatchet = ItemGenerator.createMagicAttributeHatchet( player );
			spear = ItemGenerator.createSpear( player );
			cleaver = ItemGenerator.createCleaver( player );
			glavius = ItemGenerator.createGlavius( player );
			veste = ItemGenerator.createBasicVest( player );
			pantalon = ItemGenerator.createBasicTrousers( player );
			chausses = ItemGenerator.createBasicBoots( player );
			mitaine = ItemGenerator.createBasicGloves( player );
			helmet = ItemGenerator.createBasicHelmet (player );
			hood = ItemGenerator.createBasicHood( player );
			belt = ItemGenerator.createBasicBelt( player );
			bracer = ItemGenerator.createBasicBracer( player );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if (randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null  ){
					switch( itemNumber ){
						case 1 : randomArea.setItem( club ); break;
						case 2 : randomArea.setItem( knife ); break;
						case 3 : randomArea.setItem( hammer ); break;
						case 4 : randomArea.setItem( mace ); break;
						case 5 : randomArea.setItem( hatchet ); break;
						case 6 : randomArea.setItem( magicalHatchet ); break;
						case 7 : randomArea.setItem( spear ); break;
						case 8 : randomArea.setItem( cleaver ); break;
						case 9 : randomArea.setItem( glavius ); break;
						case 10 : randomArea.setItem( veste ); break;
						case 11 : randomArea.setItem( pantalon );break;
						case 12 : randomArea.setItem( chausses );break;
						case 13 : randomArea.setItem( mitaine );break;
						case 14 : randomArea.setItem( helmet );break;
						case 15 : randomArea.setItem( hood ); break;
						case 16 : randomArea.setItem( belt );break;
						case 17 : randomArea.setItem( bracer );break;
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
		int itemNumber = Constants.getRandomBetween(1, 22);
		
		switch( itemNumber ){
			case 1 : return ItemGenerator.createGourdin( player );
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
}
