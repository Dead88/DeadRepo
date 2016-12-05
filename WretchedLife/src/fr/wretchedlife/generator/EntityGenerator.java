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
		zombie.setLife( 15 );
		zombie.setLifeRemain( 15 );
		zombie.setItemMinDamage( 6 );
		zombie.setItemMaxDamage( 10);
		zombie.setItemDefense( 1 );
		zombie.setExperienceToEarn( 5 );
		
		zombie.generateLootContainer( player );
		
		return zombie;
	}
	
	public static Enemy createGoblin( Player player ) {
		Enemy goblin = new Enemy();
		
		goblin.setTexture( Constants.getTexture( ".\\img\\entities\\goblin.gif"));
		goblin.setName("Stinking Goblin");
		goblin.setLevel( 1 );
		goblin.setLife( 20 );
		goblin.setLifeRemain( 20 );
		goblin.setItemMinDamage( 7 );
		goblin.setItemMaxDamage( 12);
		goblin.setItemDefense( 1 );
		goblin.setExperienceToEarn( 6 );
		
		goblin.generateLootContainer( player );
		
		return goblin;
	}
	
	
	public static Enemy createStrongerZombie( Player player ) {
		Enemy strongerZombie = new Enemy();
		
		strongerZombie.setTexture( Constants.getTexture( ".\\img\\entities\\undead.gif"));
		strongerZombie.setName("Purulent Mutant");
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
		wolf.setName("Nocturnal Wolf");
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
		lizard.setName("Shapeless Lizard");
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
		mummy.setName("Cursed Embalmed");
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
		skeleton.setName("Ancient Fighter");
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
		troll.setName("Sneaky Troll");
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
		horror.setName("Evolved Mutant");
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
		scorpio.setName("Irradiated Scorpion");
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
		cerber.setName("Misshappen Hyena");
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
	
	public static Enemy createLizardWariorBoss( Player player ) {
		Enemy lizard = new Enemy();
		
		lizard.setTexture( Constants.getTexture( ".\\img\\entities\\Lizard_man.gif"));
		lizard.setName("Boss : Lizard Warrior");
		lizard.setLevel( 2 );
		lizard.setLife( 45 );
		lizard.setLifeRemain( 45 );
		lizard.setItemMinDamage( 10 );
		lizard.setItemMaxDamage( 16 );
		lizard.setItemDefense( 4 );
		lizard.setExperienceToEarn( 40 );
		
		lizard.generateLootContainer( player );
		
		return lizard;
	}
	
	public static Enemy createOgreBoss( Player player ) {
		Enemy ogre = new Enemy();
		
		ogre.setTexture( Constants.getTexture( ".\\img\\entities\\Ogre.gif"));
		ogre.setName("Boss : Gargantua Monster");
		ogre.setLevel( 3 );
		ogre.setLife( 60 );
		ogre.setLifeRemain( 60 );
		ogre.setItemMinDamage( 12 );
		ogre.setItemMaxDamage( 32 );
		ogre.setItemDefense( 6 );
		ogre.setExperienceToEarn( 54 );
		
		ogre.generateLootContainer( player );
		
		return ogre;
	}
	
	public static Enemy createSorcererBoss( Player player ) {
		Enemy sorcerer = new Enemy();
		
		sorcerer.setTexture( Constants.getTexture( ".\\img\\entities\\sorcerer.gif"));
		sorcerer.setName("Boss : Guru Of The New Era");
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
		dragon.setName("Boss : Scavenger Of Fear");
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
	
	public static Enemy createBlueDragonBoss( Player player ) {
		Enemy dragon = new Enemy();
		
		dragon.setTexture( Constants.getTexture( ".\\img\\entities\\blue_dragon.gif"));
		dragon.setName("Boss : Dragon Of The Nuclear Winter");
		dragon.setLevel( 6 );
		dragon.setLife( 150 );
		dragon.setLifeRemain( 150 );
		dragon.setItemMinDamage( 70 );
		dragon.setItemMaxDamage( 120 );
		dragon.setItemDefense( 18 );
		dragon.setExperienceToEarn( 500 );
		
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
						forest.setName("Dead Tree");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree4.gif"));
						forest.setName("Damaged Tree");
					} break;
				}
			} break;
			case GRASS : {
				rand = Constants.getRandomBetween( 1, 3);
				switch( rand ) {
					case 1 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree.gif"));
						forest.setName("Tree");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree2.gif"));
						forest.setName("Tree");
					} break;
					case 3 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\tree3.gif"));
						forest.setName("Tree");
					} break;
				}	
			} break;
			case SAND : {
				rand = Constants.getRandomBetween( 1, 2);
				switch( rand ) {
					case 1 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\palm.gif"));
						forest.setName("Palm");
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
						forest.setName("Snowy Dead Tree");
					} break;
					case 2 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\snowfir.gif"));
						forest.setName("Snowy Fir");
					} break;
					case 3 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\snowtree.gif"));
						forest.setName("Snowy Tree");
					} break;
					case 4 : {
						forest.setTexture( Constants.getTexture( ".\\img\\map\\trees\\fir.gif"));
						forest.setName("Fir");
					} break;
				}	
			} break;
		}
		return forest;
	}
}
