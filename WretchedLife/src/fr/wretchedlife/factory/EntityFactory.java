package fr.wretchedlife.factory;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.generator.EntityGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;

public class EntityFactory {

	public static void generateRandomEnemies( GameMap region, Player player, int number ) {
		
		for( int i = 0; i < number; i++){
			Area randomArea = null;
			Enemy enemy = null;
			
			while(true) {
				int entityNumber = Constants.getRandomBetween(1, 11);
				switch( entityNumber ){
					case 1 : enemy = EntityGenerator.createZombie( player ); break;
					case 2 : enemy = EntityGenerator.createGoblin( player ); break;
					case 3 : enemy = EntityGenerator.createStrongerZombie( player ); break;
					case 4 : enemy = EntityGenerator.createWolf( player ); break;
					case 5 : enemy = EntityGenerator.createLizard( player ); break;
					case 6 : enemy = EntityGenerator.createMummy( player ); break;
					case 7 : enemy = EntityGenerator.createSkeleton( player ); break;
					case 8 : enemy = EntityGenerator.createTroll( player ); break;
					case 9 : enemy = EntityGenerator.createHorror( player ); break;
					case 10 : enemy = EntityGenerator.createScorpio( player ); break;
					case 11 : enemy = EntityGenerator.createCerber( player ); break;
				}
				if( enemy.getLevel() >= region.getMinLevel() && enemy.getLevel() <= region.getMaxLevel() ) {
					break;
				}
			}
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if( randomArea.getType() == Area.Type.GROUND_AREA 
				&& randomArea.getItem() == null 
				&& randomArea.getEntity() == null ){
					randomArea.setEntity( enemy );
					region.getEnemies().add( enemy );
					break;
				}
			}
		}
	}
	
	public static void generateRandomEnemyBoss( GameMap region, Player player, int number ) {
		
		for( int i = 0; i < number; i++){
			Area randomArea = null;
			Enemy enemy = null;
			
			while(true) {
				int entityNumber = Constants.getRandomBetween(1, 5);
				switch( entityNumber ){
					case 1 : enemy = EntityGenerator.createLizardWariorBoss( player ); break;
					case 2 : enemy = EntityGenerator.createOgreBoss( player ); break;
					case 3 : enemy = EntityGenerator.createSorcererBoss( player ); break;
					case 4 : enemy = EntityGenerator.createSkeletonDragonBoss( player ); break;
					case 5 : enemy = EntityGenerator.createBlueDragonBoss( player ); break;
				}
				if( enemy.getLevel() >= region.getMinLevel() && enemy.getLevel() <= region.getMaxLevel() ) {
					break;
				}
			}
			
			while(true){
				randomArea = region.getAreas().get( Constants.getRandomBetween(0, region.getAreas().size() - 1) );
				if( randomArea.getType() == Area.Type.GROUND_AREA && randomArea.getItem() == null && randomArea.getEntity() == null ){
					randomArea.setEntity( enemy );
					region.getEnemies().add( enemy );
					break;
				}
			}
		}
	}
}
