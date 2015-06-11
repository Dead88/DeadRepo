package fr.wretchedlife.factory;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.wretchedlife.Constants;
import fr.wretchedlife.EntityGenerator;
import fr.wretchedlife.NameGenerator;
import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;

public class MapFactory {

	private static int numberOfOutdoorRegions = 8;
	private static int minRegionEntrancesPerOutdoorRegion = 20;
	private static int maxRegionEntrancesPerOutdoorRegion = 60;
	
	private static int numberOfBuildingRegions = 640;
	private static int numberOfUndergroundRegions = 640;

	public static ArrayList<GameMap> generateRegions( Player player ) {
		ArrayList<GameMap> regions = new ArrayList<GameMap>();
		
		for(int i = 0; i < numberOfOutdoorRegions; i++ ) {
			
			String groundTexturePath = "";
			String groundOverTexturePath = "";
			String groundSelectedTexturePath = "";
			
			int rand = Constants.getRandomBetween(1, 4);
			switch(rand) {
				case 1 : {
					groundTexturePath = Constants.dirtTexturePath;
					groundOverTexturePath = Constants.dirtOverTexturePath;
					groundSelectedTexturePath = Constants.dirtSelectedTexturePath;
				} break;
				case 2 : {
					groundTexturePath = Constants.grassTexturePath;
					groundOverTexturePath = Constants.grassOverTexturePath;
					groundSelectedTexturePath = Constants.grassSelectedTexturePath;			
				} break;
				case 3 : {
					groundTexturePath = Constants.sandTexturePath;
					groundOverTexturePath = Constants.sandOverTexturePath;
					groundSelectedTexturePath = Constants.sandSelectedTexturePath;
				} break;
				case 4 : {
					groundTexturePath = Constants.snowTexturePath;
					groundOverTexturePath = Constants.snowOverTexturePath;
					groundSelectedTexturePath = Constants.snowSelectedTexturePath;
				} break;
			}
			
			GameMap region = new GameMap( NameGenerator.getRandomRegionName( GameMap.Type.OUTDOOR )+" "+i, 
				GameMap.Type.OUTDOOR, 
				new ArrayList<Enemy>(), 
				Constants.getRandomBetween( 8000, 12000), 
				Constants.getRandomBetween( 100, 200), 
				null, 
				groundTexturePath,
				groundOverTexturePath,
				groundSelectedTexturePath
			);
			
			buildGameMapAreas( region );
			
			MapFactory.generateWater( region );
			MapFactory.generateDoodad( region );
			ItemFactory.generateRandomItems( region, player, Constants.getRandomBetween( 10, 50) );
			ItemFactory.generateRandomConsumableItems( region, player, Constants.getRandomBetween( 10, 50) );
			ItemFactory.generateRandomHealingConsumableItems( region, player, Constants.getRandomBetween( 10, 20) );
			EntityFactory.generateRandomEnemies( region, player, Constants.getRandomBetween( 15, 54) );
			EntityFactory.generateRandomEnemyBoss(region, player, Constants.getRandomBetween( 1, 2) );
			
			regions.add( region );
		}
		
		for(int i = 0; i < numberOfBuildingRegions; i++ ) {
			GameMap region = new GameMap( NameGenerator.getRandomRegionName( GameMap.Type.BUILDING )+" "+i,
				GameMap.Type.BUILDING, 
				new ArrayList<Enemy>(), 
				Constants.getRandomBetween( 36, 60), 
				Constants.getRandomBetween( 4, 6), 
				null, 
				Constants.parquetTexturePath,
				Constants.parquetOverTexturePath,
				Constants.parquetSelectedTexturePath
			);
			buildGameMapAreas( region );
			
			ItemFactory.generateRandomItems( region, player, Constants.getRandomBetween( 0, 2) );
			ItemFactory.generateRandomConsumableItems( region, player, Constants.getRandomBetween( 0, 2) );
			ItemFactory.generateRandomHealingConsumableItems( region, player, Constants.getRandomBetween( 0, 2) );
			EntityFactory.generateRandomEnemies( region, player, Constants.getRandomBetween( 0, 1) );
			
			regions.add( region );
		}
		
		for(int i = 0; i < numberOfUndergroundRegions; i++ ) {
			GameMap region = new GameMap( NameGenerator.getRandomRegionName( GameMap.Type.UNDERGROUND )+" "+i,
				GameMap.Type.UNDERGROUND, 
				new ArrayList<Enemy>(), 
				Constants.getRandomBetween( 200, 400), 
				Constants.getRandomBetween( 10, 20), 
				null, 
				Constants.rockTexturePath,
				Constants.rockOverTexturePath,
				Constants.rockSelectedTexturePath
			);
			buildGameMapAreas( region );
			
			ItemFactory.generateRandomItems( region, player, Constants.getRandomBetween( 0, 6) );
			ItemFactory.generateRandomConsumableItems( region, player, Constants.getRandomBetween( 0, 6) );
			ItemFactory.generateRandomHealingConsumableItems( region, player, Constants.getRandomBetween( 0, 6 ) );
			EntityFactory.generateRandomEnemies( region, player, Constants.getRandomBetween( 0, 6) );
			EntityFactory.generateRandomEnemyBoss(region, player, Constants.getRandomBetween( 0, 1) );
			
			regions.add( region );
		}
		
		MapFactory.generateRegionsEntrances( regions );
		
		return regions;
	}
	
	public static void buildGameMapAreas( GameMap gameMap ) {
		
		int Xcount = 0;
		int baseX = 0;
		int baseY = 0;
		
		gameMap.setAreas( new ArrayList<Area>() );
		ImageIcon areaTexture = Constants.getTexture( gameMap.getGroundTexturePath() );
		
		for(int i=0; i < gameMap.getNumberOfAreas(); i++) {
			
			if( Xcount == ( gameMap.getNumberOfAreas() / gameMap.getNumberOfLines() ) ){
				Xcount = 0;
				baseX = 0;
				baseY++;
			}
				
			gameMap.getAreas().add( 
				new Area( 
					baseX * areaTexture.getIconWidth(), 
					baseY * areaTexture.getIconHeight(),
					areaTexture, 
					Area.Type.GROUND_AREA 
				) 
			);
			
			Xcount++;
			baseX++;
		}
	}
	
	public static void generateDoodad( GameMap gameMap ) {
		
		Entity forest = null;
		
		ArrayList<Area> areas = gameMap.getAreas();
		
		for( int i = 0; i < (gameMap.getAreas().size() * Constants.doodadRate ); i++) {
			
			int rand = Constants.getRandomBetween( 0, areas.size() - 1 );
			
			while( areas.get(rand).getType() != Area.Type.GROUND_AREA || areas.get(rand).getEntity() !=null){
				rand = Constants.getRandomBetween( 0, areas.size() - 1 );
			}
			
			forest = EntityGenerator.createForest();
			areas.get(rand).setEntity(forest);
		}
	}
	
	public static void generateWater( GameMap gameMap ) {
		
		ArrayList<Area> areas = gameMap.getAreas();
		
		for( int i = 0; i < (gameMap.getAreas().size() * Constants.waterRate ); i++) {
			int randLakeMode = Constants.getRandomBetween(1, 2);
			int rand = Constants.getRandomBetween( 0, areas.size() - 1 );
			
			try {
				if(randLakeMode == 1)
				{
					areas.get(rand).setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
					areas.get(rand).setType( Area.Type.SEA_AREA );
						
					areas.get(rand + (gameMap.getNumberOfAreas() / gameMap.getNumberOfLines() ) ).setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
					areas.get(rand + (gameMap.getNumberOfAreas() / gameMap.getNumberOfLines() ) ).setType( Area.Type.SEA_AREA );
				}
				else if(randLakeMode == 2)
				{
					areas.get(rand).setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
					areas.get(rand).setType( Area.Type.SEA_AREA );
					
					areas.get(rand + 1).setTexture( Constants.getTexture( Constants.seaAreaTexturePath ) );
					areas.get(rand + 1).setType( Area.Type.SEA_AREA );
				}
			}
			catch(Exception e) {
				continue;
			}
		}
	}
	
	public static void generateRegionsEntrances( ArrayList<GameMap> regions ) {
		Area regionRandomArea = null;
		RegionEntrance regionEntrance = null;
		RegionEntrance regionBackEntrance = null;
		
		int outdoorRegionArrayId = 1;
		int maxRegionEntrances = 0;
		int regionEntranceCount = 0;
		
		//LINKING OUTDOOR REGIONS
		for(int i = 0; i < numberOfOutdoorRegions - 1; i++ ) {
			GameMap region = regions.get(i);
			Area outdoorDestinationRegionRandomArea = null;
			
			if(region.getType() == GameMap.Type.OUTDOOR) {
				
				final GameMap outdoorDestinationRegion = regions.get( outdoorRegionArrayId );
				
				while(true){
					regionRandomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
					if (regionRandomArea.getType() == Area.Type.GROUND_AREA 
					&& regionRandomArea.getItem() == null 
					&& regionRandomArea.getEntity() == null ){
						break;
					}
				}
				
				regionEntrance = new RegionEntrance() {
					@Override
					public void use() {}
				};	
				regionEntrance.setTexture( Constants.getTexture(".//img//entities//tunnel.gif") );
				regionEntrance.setName( "Entrée : "+outdoorDestinationRegion.getName() );
				regionEntrance.setRegionId( outdoorDestinationRegion.getId() );
				regionRandomArea.setEntity( regionEntrance );
				
				while(true){
					outdoorDestinationRegionRandomArea = outdoorDestinationRegion.getAreas().get( Constants.getRandomBetween(0, outdoorDestinationRegion.getAreas().size() - 1) );
					if (outdoorDestinationRegionRandomArea.getType() == Area.Type.GROUND_AREA 
					&& outdoorDestinationRegionRandomArea.getItem() == null 
					&& outdoorDestinationRegionRandomArea.getEntity() == null ){
						break;
					}
				}
				
				regionBackEntrance = new RegionEntrance() {
					@Override
					public void use() {	}
				};
				regionBackEntrance.setTexture( Constants.getTexture(".//img//entities//tunnel.gif") );
				regionBackEntrance.setName( "Sortie vers : "+region.getName() );
				regionBackEntrance.setRegionId( region.getId() );
				outdoorDestinationRegionRandomArea.setEntity( regionBackEntrance );
				
				region.setLinkedToAnotherRegion(true);
				outdoorRegionArrayId++;
			}
		}
		
		//LINKING OTHER REGIONS TO OUTDOOR REGIONS
		for(int i = 0; i < numberOfOutdoorRegions; i++ ) {
			GameMap region = regions.get(i);
			int randomArrayId = 0;
			Area destinationRegionRandomArea = null;
			
			maxRegionEntrances = Constants.getRandomBetween(minRegionEntrancesPerOutdoorRegion, maxRegionEntrancesPerOutdoorRegion);
			regionEntranceCount = 0;
			
			if(region.getType() == GameMap.Type.OUTDOOR) {
				
				while(regionEntranceCount < maxRegionEntrances){
					
					while(true) {
						randomArrayId = Constants.getRandomBetween(numberOfOutdoorRegions, regions.size() - 1);
						
						if( !regions.get( randomArrayId ).getId().equals( region.getId() ) 
						&& !regions.get( randomArrayId ).isLinkedToAnotherRegion() 
						&& regions.get( randomArrayId ).getType() != GameMap.Type.OUTDOOR ) {
							break;
						}
					}
					
					final GameMap destinationRegion = regions.get( randomArrayId );
					
					while(true){
						regionRandomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
						if (regionRandomArea.getType() == Area.Type.GROUND_AREA 
						&& regionRandomArea.getItem() == null 
						&& regionRandomArea.getEntity() == null ){
							break;
						}
					}
					
					regionEntrance = new RegionEntrance() {
						@Override
						public void use() {
							if(destinationRegion.getType() == GameMap.Type.BUILDING)
								SoundFactory.playSound( SoundFactory.doorFilePath );
						}
					};
					
					if(destinationRegion.getType() == GameMap.Type.BUILDING)
						regionEntrance.setTexture( Constants.getTexture(".//img//entities//house.png") );
					else if(destinationRegion.getType() == GameMap.Type.UNDERGROUND)
						regionEntrance.setTexture( Constants.getTexture(".//img//entities//cave.png") );
					
					regionEntrance.setName( "Entrée : "+destinationRegion.getName() );
					regionEntrance.setRegionId( destinationRegion.getId() );
					regionRandomArea.setEntity( regionEntrance );
					
					while(true){
						destinationRegionRandomArea = destinationRegion.getAreas().get( Constants.getRandomBetween(0, destinationRegion.getAreas().size() - 1) );
						if (destinationRegionRandomArea.getType() == Area.Type.GROUND_AREA 
						&& destinationRegionRandomArea.getItem() == null 
						&& destinationRegionRandomArea.getEntity() == null ){
							break;
						}
					}
					
					regionBackEntrance = new RegionEntrance() {
						@Override
						public void use() {
							if(destinationRegion.getType() == GameMap.Type.BUILDING)
								SoundFactory.playSound( SoundFactory.doorFilePath );
						}
					};
					
					if(destinationRegion.getType() == GameMap.Type.BUILDING)
						regionBackEntrance.setTexture( Constants.getTexture(".//img//entities//door.png") );
					else if(destinationRegion.getType() == GameMap.Type.UNDERGROUND)
						regionBackEntrance.setTexture( Constants.getTexture(".//img//entities//cave.png") );
					
					regionBackEntrance.setName( "Sortie vers : "+region.getName() );
					regionBackEntrance.setRegionId( region.getId() );
					destinationRegionRandomArea.setEntity( regionBackEntrance );
					
					destinationRegion.setLinkedToAnotherRegion(true);
					regionEntranceCount++;
				}
			}
		}
	}
}
