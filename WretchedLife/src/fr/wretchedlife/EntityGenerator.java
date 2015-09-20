package fr.wretchedlife;

import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;

public class EntityGenerator {

	public static Entity createForest() {
		Entity forest = new Entity();
		
		forest.setTexture( Constants.getTexture( ".\\img\\map\\forest.png"));
		forest.setName("Arbre Mort");
		
		return forest;
	}
	
	
	public static Enemy createZombie( Player player ) {
		Enemy zombie = new Enemy();
		
		zombie.setTexture( Constants.getTexture( ".\\img\\entities\\zombie.gif"));
		zombie.setName("Mutant");
		zombie.setLevel( 1 );
		zombie.setLife( 20 );
		zombie.setLifeRemain( 20 );
		zombie.setItemMinDamage( 7 );
		zombie.setItemMaxDamage( 11 );
		zombie.setItemDefense( 1 );
		zombie.setExperienceToEarn( 5 );
		
		zombie.generateRandomInventory( player );
		
		return zombie;
	}
	
	public static Enemy createStrongerZombie( Player player ) {
		Enemy strongerZombie = new Enemy();
		
		strongerZombie.setTexture( Constants.getTexture( ".\\img\\entities\\undead.gif"));
		strongerZombie.setName("Mutant Purulent");
		strongerZombie.setLevel( 1 );
		strongerZombie.setLife( 25 );
		strongerZombie.setLifeRemain( 25 );
		strongerZombie.setItemMinDamage( 9 );
		strongerZombie.setItemMaxDamage( 13 );
		strongerZombie.setItemDefense( 2 );
		strongerZombie.setExperienceToEarn( 10 );
		
		strongerZombie.generateRandomInventory( player );
		
		return strongerZombie;
	}
	
	public static Enemy createSkeleton( Player player ) {
		Enemy skeleton = new Enemy();
		
		skeleton.setTexture( Constants.getTexture( ".\\img\\entities\\skeleton.gif"));
		skeleton.setName("Combattant d'Autrefois");
		skeleton.setLevel( 2 );
		skeleton.setLife( 30 );
		skeleton.setLifeRemain( 30 );
		skeleton.setItemMinDamage( 10 );
		skeleton.setItemMaxDamage( 15 );
		skeleton.setItemDefense( 3 );
		skeleton.setExperienceToEarn( 15 );
		
		skeleton.generateRandomInventory( player );
		
		return skeleton;
	}
	
	public static Enemy createMummy( Player player ) {
		Enemy mummy = new Enemy();
		
		mummy.setTexture( Constants.getTexture( ".\\img\\entities\\mummy.gif"));
		mummy.setName("Eternel Embaumé");
		mummy.setLevel( 2 );
		mummy.setLife( 35 );
		mummy.setLifeRemain( 35 );
		mummy.setItemMinDamage( 12 );
		mummy.setItemMaxDamage( 16 );
		mummy.setItemDefense( 3 );
		mummy.setExperienceToEarn( 20 );
		
		mummy.generateRandomInventory( player );
		
		return mummy;
	}
	
	public static Enemy createHorror( Player player ) {
		Enemy horror = new Enemy();
		
		horror.setTexture( Constants.getTexture( ".\\img\\entities\\horror.gif"));
		horror.setName("Mutant Evolué");
		horror.setLevel( 3 );
		horror.setLife( 42 );
		horror.setLifeRemain( 42 );
		horror.setItemMinDamage( 12 );
		horror.setItemMaxDamage( 16 );
		horror.setItemDefense( 4 );
		horror.setExperienceToEarn( 25 );
		
		horror.generateRandomInventory( player );
		
		return horror;
	}
	
	public static Enemy createScorpio( Player player ) {
		Enemy scorpio = new Enemy();
		
		scorpio.setTexture( Constants.getTexture( ".\\img\\entities\\scorpio.gif"));
		scorpio.setName("Scorpion Irradié");
		scorpio.setLevel( 4 );
		scorpio.setLife( 48 );
		scorpio.setLifeRemain( 48 );
		scorpio.setItemMinDamage( 15 );
		scorpio.setItemMaxDamage( 22 );
		scorpio.setItemDefense( 5 );
		scorpio.setExperienceToEarn( 35 );
		
		scorpio.generateRandomInventory( player );
		
		return scorpio;
	}
	
	public static Enemy createCerber( Player player ) {
		Enemy cerber = new Enemy();
		
		cerber.setTexture( Constants.getTexture( ".\\img\\entities\\cerber.gif"));
		cerber.setName("Hyène mutante");
		cerber.setLevel( 4 );
		cerber.setLife( 56 );
		cerber.setLifeRemain( 56 );
		cerber.setItemMinDamage( 19 );
		cerber.setItemMaxDamage( 27 );
		cerber.setItemDefense( 4 );
		cerber.setExperienceToEarn( 50 );
		
		cerber.generateRandomInventory( player );
		
		return cerber;
	}
	
	public static Enemy createSorcererBoss( Player player ) {
		Enemy sorcerer = new Enemy();
		
		sorcerer.setTexture( Constants.getTexture( ".\\img\\entities\\sorcerer.gif"));
		sorcerer.setName("Boss : Gourou de l'ère nouvelle");
		sorcerer.setLevel( 5 );
		sorcerer.setLife( 70 );
		sorcerer.setLifeRemain( 70 );
		sorcerer.setItemMinDamage( 26 );
		sorcerer.setItemMaxDamage( 38 );
		sorcerer.setItemDefense( 6 );
		sorcerer.setExperienceToEarn( 100 );
		
		sorcerer.generateRandomInventory( player );
		
		return sorcerer;
	}
	
	public static Enemy createSkeletonDragonBoss( Player player ) {
		Enemy dragon = new Enemy();
		
		dragon.setTexture( Constants.getTexture( ".\\img\\entities\\skeletondragon.gif"));
		dragon.setName("Boss : Charognard de l'éffroi");
		dragon.setLevel( 6 );
		dragon.setLife( 90 );
		dragon.setLifeRemain( 90 );
		dragon.setItemMinDamage( 40 );
		dragon.setItemMaxDamage( 55 );
		dragon.setItemDefense( 8 );
		dragon.setExperienceToEarn( 250 );
		
		dragon.generateRandomInventory( player );
		
		return dragon;
	}
}