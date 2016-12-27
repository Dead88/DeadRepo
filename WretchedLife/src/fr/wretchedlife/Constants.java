package fr.wretchedlife;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import javax.swing.ImageIcon;

public class Constants 
{
	// TECHNICAL FIELDS / SETTINGS
	
	public static final int playerVisibilyRange = 3;
	public static final int enemyVisibilyRange = 3;
	
	public static final int multiplayerPort = 5000;
	public static final int itemIdLength = 36;
	public static final int gameMapIdLength = 20;
	
	public static final int minItemsPerRandomChest = 1;
	public static final int maxItemsPerRandomChest = 3;
	public static final int minItemsPerEnemyChest = 1;
	public static final int maxItemsPerEnemyChest = 2;
	
	// STYLES 
	
	public static final Color goldColor = new Color(201, 180, 87);
	public static final Color redColor = new Color(134, 1, 20);
	public static final Color itemButtonBkgColor = Color.LIGHT_GRAY;
	
	// OUTDOOR REGION SETTINGS
		
	public static int numberOfOutdoorRegions = 10;
	
	public static int minAreasPerOutdoorRegion = 8000;
	public static int maxAreasPerOutdoorRegion = 12000;
	public static int minLinesPerOutdoorRegion = 100;
	public static int maxLinesPerOutdoorRegion = 200;
	public static int minRegionEntrancesPerOutdoorRegion = 20;
	public static int maxRegionEntrancesPerOutdoorRegion = 60;
	
	public static int minItemsPerOutdoorRegion = 10;
	public static int maxItemsPerOutdoorRegion = 25;
	public static int minConsumableItemsPerOutdoorRegion = 15;
	public static int maxConsumableItemsPerOutdoorRegion = 25;
	public static int minHealingConsumableItemsPerOutdoorRegion = 5;
	public static int maxHealingConsumableItemsPerOutdoorRegion = 15;
	public static int minContainerItemsPerOutdoorRegion = 5;
	public static int maxContainerItemsPerOutdoorRegion = 10;
	public static int minEnemiesPerOutdoorRegion = 15;
	public static int maxEnemiesPerOutdoorRegion = 55;
	public static int minBossPerOutdoorRegion = 1;
	public static int maxBossPerOutdoorRegion = 2;
	
	// BUILDING REGION SETTINGS
	
	public static int numberOfBuildingRegions = 250;
	
	public static int minAreasPerBuildingRegion = 36;
	public static int maxAreasPerBuildingRegion = 60;
	public static int minLinesPerBuildingRegion = 4;
	public static int maxLinesPerBuildingRegion = 6;
	
	public static int minItemsPerBuildingRegion = 0;
	public static int maxItemsPerBuildingRegion = 2;
	public static int minConsumableItemsPerBuildingRegion = 0;
	public static int maxConsumableItemsPerBuildingRegion = 2;
	public static int minHealingConsumableItemsPerBuildingRegion = 0;
	public static int maxHealingConsumableItemsPerBuildingRegion = 2;
	public static int minContainerItemsPerBuildingRegion = 0;
	public static int maxContainerItemsPerBuildingRegion = 1;
	public static int minEnemiesPerBuildingRegion = 0;
	public static int maxEnemiesPerBuildingRegion = 1;
	
	// UNDERGROUND REGION SETTINGS
	
	public static int numberOfUndergroundRegions = 250;
	
	public static int minAreasPerUndergroundRegion = 100;
	public static int maxAreasPerUndergroundRegion = 300;
	public static int minLinesPerUndergroundRegion = 10;
	public static int maxLinesPerUndergroundRegion = 20;
	
	public static int minItemsPerUndergroundRegion = 0;
	public static int maxItemsPerUndergroundRegion = 4;
	public static int minConsumableItemsPerUndergroundRegion = 0;
	public static int maxConsumableItemsPerUndergroundRegion = 4;
	public static int minHealingConsumableItemsPerUndergroundRegion = 0;
	public static int maxHealingConsumableItemsPerUndergroundRegion = 2;
	public static int minContainerItemsPerUndergroundRegion = 0;
	public static int maxContainerItemsPerUndergroundRegion = 1;
	public static int minEnemiesPerUndergroundRegion = 1;
	public static int maxEnemiesPerUndergroundRegion = 4;
	public static int minBossPerUndergroundRegion = 0;
	public static int maxBossPerUndergroundRegion = 1;
	
	// DUNGEON REGION SETTINGS
	
	public static int numberOfDungeonRegions = 100;
	
	public static int minAreasPerDungeonRegion = 200;
	public static int maxAreasPerDungeonRegion = 400;
	public static int minLinesPerDungeonRegion = 20;
	public static int maxLinesPerDungeonRegion = 30;
	
	public static int minItemsPerDungeonRegion = 1;
	public static int maxItemsPerDungeonRegion = 5;
	public static int minConsumableItemsPerDungeonRegion = 1;
	public static int maxConsumableItemsPerDungeonRegion = 5;
	public static int minHealingConsumableItemsPerDungeonRegion = 1;
	public static int maxHealingConsumableItemsPerDungeonRegion = 5;
	public static int minContainerItemsPerDungeonRegion = 1;
	public static int maxContainerItemsPerDungeonRegion = 2;
	public static int minEnemiesPerDungeonRegion = 2;
	public static int maxEnemiesPerDungeonRegion = 6;
	public static int minBossPerDungeonRegion = 1;
	public static int maxBossPerDungeonRegion = 3;
	
	// GAME RATES
	public static final double magicalDropPercent = 20;
	public static final double enemyDropPercent = 40;
	
	public static final double lowLevelUpRate = 2.25;
	public static final double mediumLevelUpRate = 1.60;
	public static final double highLevelUpRate = 1.40;
	
	public static final double lowLifeUpRate = 1.02;
	public static final double mediumLifeUpRate = 1.06;
	public static final double highLifeUpRate = 1.15;
	
	public static final double inventorySizeUpRate = 1.10;
	public static final double transportableWeightUpRate = 1.20;
	
	public static final double hungerDownPercent = 0.25;
	public static final double thirstDownPercent = 0.5;
	
	public static final double waterRate = 0.05;
	public static final double doodadRate = 0.08;
	
	// STARTING PLAYER CONFIG
	
	public static final int startingExperienceToReach = 50;
	public static final int startingLife = 100;
	public static final int startingInventoryMaxSize = 10;
	public static final int startingTransportableWeight = 30;
	
	// TEXTURES PATHS
	
	public static final String playerTexturePath = ".\\img\\entities\\player.gif";
	
	public static final String seaAreaTexturePath = ".\\img\\map\\water.gif";
	
	public static final String grassTexturePath = ".\\img\\map\\grass.jpg";
	public static final String grassSelectedTexturePath = ".\\img\\map\\grass_selected.jpg";
	public static final String grassOverTexturePath = ".\\img\\map\\grass_over.jpg";
	
	public static final String dirtTexturePath = ".\\img\\map\\dirt.jpg";
	public static final String dirtSelectedTexturePath = ".\\img\\map\\dirt_selected.jpg";
	public static final String dirtOverTexturePath = ".\\img\\map\\dirt_over.jpg";
	
	public static final String parquetTexturePath = ".\\img\\map\\parquet.jpg";
	public static final String parquetSelectedTexturePath = ".\\img\\map\\parquet_selected.jpg";
	public static final String parquetOverTexturePath = ".\\img\\map\\parquet_over.jpg";
	
	public static final String rockTexturePath = ".\\img\\map\\rock.jpg";
	public static final String rockSelectedTexturePath = ".\\img\\map\\rock_selected.jpg";
	public static final String rockOverTexturePath = ".\\img\\map\\rock_over.jpg";
	
	public static final String sandTexturePath = ".\\img\\map\\sand.jpg";
	public static final String sandSelectedTexturePath = ".\\img\\map\\sand_selected.jpg";
	public static final String sandOverTexturePath = ".\\img\\map\\sand_over.jpg";
	
	public static final String snowTexturePath = ".\\img\\map\\snow.jpg";
	public static final String snowSelectedTexturePath = ".\\img\\map\\snow_selected.jpg";
	public static final String snowOverTexturePath = ".\\img\\map\\snow_over.jpg";
	
	public static final String dungeonTexturePath = ".\\img\\map\\dale.jpg";
	public static final String dungeonSelectedTexturePath = ".\\img\\map\\dale_selected.jpg";
	public static final String dungeonOverTexturePath = ".\\img\\map\\dale_over.jpg";
	
	// COMMON METHODS
	
	public static ImageIcon getTexture(String url) {
		ImageIcon icon = new ImageIcon( url );
		return icon;
	}
	
	public static int getRandomBetween(int from, int to)
	{ 
		return (from + (int) ( Math.random() * (to - from + 1) ) );
	}
	
	public static String getRandomId( int len ) {
		Random rnd = new Random();

		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
		StringBuilder sb = new StringBuilder( len );

		for ( int i = 0; i < len; i++ ) {
			sb.append( chars.charAt( rnd.nextInt( chars.length() ) ) );
		}

		return sb.toString();
	}
	
	public static String getStackTrace(Throwable t) throws Exception
	{
		String trace = "";
		StringWriter sw = null;
		PrintWriter pw = null; 
				
		try
		{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			trace = sw.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null) pw.close();
			if(sw != null) sw.close();
		}
		return trace;
	}
	
}
