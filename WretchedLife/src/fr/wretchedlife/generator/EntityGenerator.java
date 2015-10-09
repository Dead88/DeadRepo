package fr.wretchedlife.generator;

import fr.wretchedlife.Constants;
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
		
		zombie.generateLootContainer( player );
		
		return zombie;
	}
	
	public static Enemy createWolf( Player player ) {
		Enemy wolf = new Enemy();
		
		wolf.setTexture( Constants.getTexture( ".\\img\\entities\\wolf.gif"));
		wolf.setName("Loup nocture");
		wolf.setLevel( 1 );
		wolf.setLife( 15 );
		wolf.setLifeRemain( 15 );
		wolf.setItemMinDamage( 7 );
		wolf.setItemMaxDamage( 14 );
		wolf.setItemDefense( 1 );
		wolf.setExperienceToEarn( 6 );
		
		wolf.generateLootContainer( player );
		
		return wolf;
	}
	
	public static Enemy createStrongerZombie( Player player ) {
		Enemy strongerZombie = new Enemy();
		
		strongerZombie.setTexture( Constants.getTexture( ".\\img\\entities\\undead.gif"));
		strongerZombie.setName("Mutant Purulent");
		strongerZombie.setLevel( 2 );
		strongerZombie.setLife( 25 );
		strongerZombie.setLifeRemain( 25 );
		strongerZombie.setItemMinDamage( 9 );
		strongerZombie.setItemMaxDamage( 13 );
		strongerZombie.setItemDefense( 2 );
		strongerZombie.setExperienceToEarn( 10 );
		
		strongerZombie.generateLootContainer( player );
		
		return strongerZombie;
	}
	
	public static Enemy createLizard( Player player ) {
		Enemy lizard = new Enemy();
		
		lizard.setTexture( Constants.getTexture( ".\\img\\entities\\lizard.gif"));
		lizard.setName("Lézard démesuré");
		lizard.setLevel( 2 );
		lizard.setLife( 30 );
		lizard.setLifeRemain( 30 );
		lizard.setItemMinDamage( 12 );
		lizard.setItemMaxDamage( 14 );
		lizard.setItemDefense( 2 );
		lizard.setExperienceToEarn( 12 );
		
		lizard.generateLootContainer( player );
		
		return lizard;
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
		
		skeleton.generateLootContainer( player );
		
		return skeleton;
	}
	
	public static Enemy createMummy( Player player ) {
		Enemy mummy = new Enemy();
		
		mummy.setTexture( Constants.getTexture( ".\\img\\entities\\mummy.gif"));
		mummy.setName("Eternel Embaumé");
		mummy.setLevel( 2 );
		mummy.setLife( 35 );
		mummy.setLifeRemain( 35 );
		mummy.setItemMinDamage( 13 );
		mummy.setItemMaxDamage( 16 );
		mummy.setItemDefense( 3 );
		mummy.setExperienceToEarn( 20 );
		
		mummy.generateLootContainer( player );
		
		return mummy;
	}
	
	public static Enemy createTroll( Player player ) {
		Enemy troll = new Enemy();
		
		troll.setTexture( Constants.getTexture( ".\\img\\entities\\troll.gif"));
		troll.setName("Troll inconnu");
		troll.setLevel( 3 );
		troll.setLife( 30 );
		troll.setLifeRemain( 30 );
		troll.setItemMinDamage( 14);
		troll.setItemMaxDamage( 16 );
		troll.setItemDefense( 3 );
		troll.setExperienceToEarn( 22 );
		
		troll.generateLootContainer( player );
		
		return troll;
	}
	
	public static Enemy createHorror( Player player ) {
		Enemy horror = new Enemy();
		
		horror.setTexture( Constants.getTexture( ".\\img\\entities\\horror.gif"));
		horror.setName("Mutant Evolué");
		horror.setLevel( 3 );
		horror.setLife( 42 );
		horror.setLifeRemain( 42 );
		horror.setItemMinDamage( 13 );
		horror.setItemMaxDamage( 18 );
		horror.setItemDefense( 4 );
		horror.setExperienceToEarn( 25 );
		
		horror.generateLootContainer( player );
		
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
		scorpio.setItemMaxDamage( 20 );
		scorpio.setItemDefense( 5 );
		scorpio.setExperienceToEarn( 35 );
		
		scorpio.generateLootContainer( player );
		
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
		
		cerber.generateLootContainer( player );
		
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
		
		sorcerer.generateLootContainer( player );
		
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
		
		dragon.generateLootContainer( player );
		
		return dragon;
	}
}
