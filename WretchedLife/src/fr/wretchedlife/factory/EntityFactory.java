package fr.wretchedlife.factory;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.generator.EntityGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;

public class EntityFactory {

	public static void generateRandomEnemies( GameMap region, Player player, int number ) {
		
		Enemy zombie = null;
		Enemy strongerZombie = null;
		Enemy skeleton = null;
		Enemy mummy = null;
		Enemy horror = null;
		Enemy scorpio = null;
		Enemy cerber = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++){
			int entityNumber = Constants.getRandomBetween(1, 7);
			zombie = EntityGenerator.createZombie( player );
			strongerZombie = EntityGenerator.createStrongerZombie( player );
			skeleton = EntityGenerator.createSkeleton( player );
			mummy = EntityGenerator.createMummy( player );
			horror = EntityGenerator.createHorror( player );
			scorpio = EntityGenerator.createScorpio( player );
			cerber = EntityGenerator.createCerber( player );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if( randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					switch( entityNumber ){
						case 1 : {
							randomArea.setEntity( zombie );
							region.getEnemies().add( zombie );
						} break;
						case 2 : {
							randomArea.setEntity( strongerZombie );
							region.getEnemies().add( strongerZombie );
						} break;
						case 3 : {
							randomArea.setEntity( skeleton );
							region.getEnemies().add( skeleton );
						} break;
						case 4 : {
							randomArea.setEntity( mummy );
							region.getEnemies().add( mummy );
						} break;
						case 5 : {
							randomArea.setEntity( horror );
							region.getEnemies().add( horror );
						} break;
						case 6 : {
							randomArea.setEntity( scorpio );
							region.getEnemies().add( scorpio );
						} break;
						case 7 : {
							randomArea.setEntity( cerber );
							region.getEnemies().add( cerber );
						} break;
					}
					break;
				}
			}
		}
	}
	
	public static void generateRandomEnemyBoss( GameMap region, Player player, int number ) {
		
		Enemy sorcerer = null;
		Enemy dragon = null;
		
		Area randomArea = new Area(0, 0, null, null );
		
		for( int i = 0; i < number; i++){
			int entityNumber = Constants.getRandomBetween(1, 2);
			sorcerer = EntityGenerator.createSorcererBoss( player );
			dragon = EntityGenerator.createSkeletonDragonBoss( player );
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if( randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					switch( entityNumber ){
						case 1 : {
							randomArea.setEntity( sorcerer );
							region.getEnemies().add( sorcerer );
						} break;
						case 2 : {
							randomArea.setEntity( dragon );
							region.getEnemies().add( dragon );
						} break;
					}
					break;
				}
			}
		}
	}
}
