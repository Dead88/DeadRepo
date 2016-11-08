package fr.wretchedlife.generator;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.map.GameMap;

public class EntityGenerator {

	public static Enemy createZombie( Player player ) {
		Enemy zombie = new Enemy();
		
		zombie.setTexture( Constants.getTexture( ".\\img\\entities\\zombie.gif"));
		zombie.setName("Mutant");
		zombie.setLevel( 1 );
		zombie.setLife( 20 );
		zombie.setLifeRemain( 20 );
		zombie.setItemMinDamage( 6 );
		zombie.setItemMaxDamage( 12);
		zombie.setItemDefense( 1 );
		zombie.setExperienceToEarn( 5 );
		
		zombie.generateLootContainer( player );
		
		return zombie;
	}
	
	public static Enemy createStrongerZombie( Player player ) {
		Enemy strongerZombie = new Enemy();
		
		strongerZombie.setTexture( Constants.getTexture( ".\\img\\entities\\undead.gif"));
		strongerZombie.setName("Mutant Purulent");
		strongerZombie.setLevel( 2 );
		strongerZombie.setLife( 20 );
		strongerZombie.setLifeRemain( 20 );
		strongerZombie.setItemMinDamage( 9 );
		strongerZombie.setItemMaxDamage( 13 );
		strongerZombie.setItemDefense( 2 );
		strongerZombie.setExperienceToEarn( 8 );
		
		strongerZombie.generateLootContainer( player );
		
		return strongerZombie;
	}
	
	public static Enemy createWolf( Player player ) {
		Enemy wolf = new Enemy();
		
		wolf.setTexture( Constants.getTexture( ".\\img\\entities\\wolf.gif"));
		wolf.setName("Loup nocture");
		wolf.setLevel( 2 );
		wolf.setLife( 25 );
		wolf.setLifeRemain( 25 );
		wolf.setItemMinDamage( 7 );
		wolf.setItemMaxDamage( 15 );
		wolf.setItemDefense( 3 );
		wolf.setExperienceToEarn( 10 );
		
		wolf.generateLootContainer( player );
		
		return wolf;
	}
	
	public static Enemy createLizard( Player player ) {
		Enemy lizard = new Enemy();
		
		lizard.setTexture( Constants.getTexture( ".\\img\\entities\\lizard.gif"));
		lizard.setName("Lézard démesuré");
		lizard.setLevel( 2 );
		lizard.setLife( 30 );
		lizard.setLifeRemain( 30 );
		lizard.setItemMinDamage( 12 );
		lizard.setItemMaxDamage( 16 );
		lizard.setItemDefense( 4 );
		lizard.setExperienceToEarn( 14 );
		
		lizard.generateLootContainer( player );
		
		return lizard;
	}
	
	public static Enemy createMummy( Player player ) {
		Enemy mummy = new Enemy();
		
		mummy.setTexture( Constants.getTexture( ".\\img\\entities\\mummy.gif"));
		mummy.setName("Eternel Embaumé");
		mummy.setLevel( 2 );
		mummy.setLife( 35 );
		mummy.setLifeRemain( 35 );
		mummy.setItemMinDamage( 11 );
		mummy.setItemMaxDamage( 17 );
		mummy.setItemDefense( 3 );
		mummy.setExperienceToEarn( 18 );
		
		mummy.generateLootContainer( player );
		
		return mummy;
	}
	
	public static Enemy createSkeleton( Player player ) {
		Enemy skeleton = new Enemy();
		
		skeleton.setTexture( Constants.getTexture( ".\\img\\entities\\skeleton.gif"));
		skeleton.setName("Combattant d'Autrefois");
		skeleton.setLevel( 3 );
		skeleton.setLife( 40 );
		skeleton.setLifeRemain( 40 );
		skeleton.setItemMinDamage( 14 );
		skeleton.setItemMaxDamage( 19 );
		skeleton.setItemDefense( 4 );
		skeleton.setExperienceToEarn( 25 );
		
		skeleton.generateLootContainer( player );
		
		return skeleton;
	}
	
	public static Enemy createTroll( Player player ) {
		Enemy troll = new Enemy();
		
		troll.setTexture( Constants.getTexture( ".\\img\\entities\\troll.gif"));
		troll.setName("Troll sournois");
		troll.setLevel( 3 );
		troll.setLife( 42 );
		troll.setLifeRemain( 42 );
		troll.setItemMinDamage( 17 );
		troll.setItemMaxDamage( 22 );
		troll.setItemDefense( 5 );
		troll.setExperienceToEarn( 28 );
		
		troll.generateLootContainer( player );
		
		return troll;
	}
	
	public static Enemy createHorror( Player player ) {
		Enemy horror = new Enemy();
		
		horror.setTexture( Constants.getTexture( ".\\img\\entities\\horror.gif"));
		horror.setName("Mutant Evolué");
		horror.setLevel( 3 );
		horror.setLife( 45 );
		horror.setLifeRemain( 45 );
		horror.setItemMinDamage( 20 );
		horror.setItemMaxDamage( 25 );
		horror.setItemDefense( 4 );
		horror.setExperienceToEarn( 32 );
		
		horror.generateLootContainer( player );
		
		return horror;
	}
	
	public static Enemy createScorpio( Player player ) {
		Enemy scorpio = new Enemy();
		
		scorpio.setTexture( Constants.getTexture( ".\\img\\entities\\scorpio.gif"));
		scorpio.setName("Scorpion Irradié");
		scorpio.setLevel( 4 );
		scorpio.setLife( 50 );
		scorpio.setLifeRemain( 50 );
		scorpio.setItemMinDamage( 18 );
		scorpio.setItemMaxDamage( 28 );
		scorpio.setItemDefense( 7 );
		scorpio.setExperienceToEarn( 38 );
		
		scorpio.generateLootContainer( player );
		
		return scorpio;
	}
	
	public static Enemy createCerber( Player player ) {
		Enemy cerber = new Enemy();
		
		cerber.setTexture( Constants.getTexture( ".\\img\\entities\\cerber.gif"));
		cerber.setName("Hyène mutante");
		cerber.setLevel( 4 );
		cerber.setLife( 55 );
		cerber.setLifeRemain( 55 );
		cerber.setItemMinDamage( 22 );
		cerber.setItemMaxDamage( 28 );
		cerber.setItemDefense( 6 );
		cerber.setExperienceToEarn( 45 );
		
		cerber.generateLootContainer( player );
		
		return cerber;
	}
	
	public static Enemy createSorcererBoss( Player player ) {
		Enemy sorcerer = new Enemy();
		
		sorcerer.setTexture( Constants.getTexture( ".\\img\\entities\\sorcerer.gif"));
		sorcerer.setName("Boss : Gourou de l'ère nouvelle");
		sorcerer.setLevel( 4 );
		sorcerer.setLife( 70 );
		sorcerer.setLifeRemain( 70 );
		sorcerer.setItemMinDamage( 32 );
		sorcerer.setItemMaxDamage( 45 );
		sorcerer.setItemDefense( 8 );
		sorcerer.setExperienceToEarn( 75 );
		
		sorcerer.generateLootContainer( player );
		
		return sorcerer;
	}
	
	public static Enemy createSkeletonDragonBoss( Player player ) {
		Enemy dragon = new Enemy();
		
		dragon.setTexture( Constants.getTexture( ".\\img\\entities\\skeletondragon.gif"));
		dragon.setName("Boss : Charognard de l'éffroi");
		dragon.setLevel( 5 );
		dragon.setLife( 90 );
		dragon.setLifeRemain( 90 );
		dragon.setItemMinDamage( 50 );
		dragon.setItemMaxDamage( 70 );
		dragon.setItemDefense( 12 );
		dragon.setExperienceToEarn( 100 );
		
		dragon.generateLootContainer( player );
		
		return dragon;
	}
	
	public static Entity createTree( GameMap gameMap ) {
		Entity forest = new Entity();
		int rand = 0;
		
		switch( gameMap.getFloorType() ) {
			case DIRT : {
				rand = Constants.getRandomBetween( 1, 2);
				switch( rand ) {
					case 1 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\deadtree.gif"));
						forest.setName("Arbre Mort");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree4.gif"));
						forest.setName("Arbre âbimé");
					} break;
				}
			} break;
			case GRASS : {
				rand = Constants.getRandomBetween( 1, 3);
				switch( rand ) {
					case 1 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree.gif"));
						forest.setName("Arbre");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree2.gif"));
						forest.setName("Arbre");
					} break;
					case 3 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree3.gif"));
						forest.setName("Arbre");
					} break;
				}	
			} break;
			case SAND : {
				rand = Constants.getRandomBetween( 1, 2);
				switch( rand ) {
					case 1 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\palm.gif"));
						forest.setName("Palmier");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\palm2.gif"));
						forest.setName("Bananier");
					} break;
				}
			} break;
			case SNOW : {
				rand = Constants.getRandomBetween( 1, 4);
				switch( rand ) {
					case 1 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\snowdeadtree.gif"));
						forest.setName("Arbre mort enneigé");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\snowfir.gif"));
						forest.setName("Sapin enneigé");
					} break;
					case 3 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\snowtree.gif"));
						forest.setName("Arbre enneigé");
					} break;
					case 4 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\fir.gif"));
						forest.setName("Sapin");
					} break;
				}	
			} break;
		}
		return forest;
	}
}
