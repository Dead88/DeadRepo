package fr.wretchedlife;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import javax.swing.ImageIcon;

//FIXME : coffre d'objets, loot des enemies, selection perso, nom.

public class Constants 
{
	public static final int multiplayerPort = 5000;
	
	public static final int itemIdLength = 25;
	public static final int gameMapIdLength = 20;
	public static final double waterRate = 0.05;
	public static final double doodadRate = 0.08;
	public static final int playerVisibilyRange = 3;
	public static final int enemyVisibilyRange = 3;
	
	public static final Color goldColor = new Color(201, 180, 87);
	public static final Color redColor = new Color(134, 1, 20);
	public static final Color itemButtonBkgColor = Color.LIGHT_GRAY;
	
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
	
	public static final double levelUpRate = 1.125;
	public static final double lifeUpRate = 1.02;
	public static final double hungerDownPercent = 0.25;
	public static final double thirstDownPercent = 0.5;
	public static final double inventorySizeUpRate = 1.05;
	public static final double transportableWeightUpRate = 1.10;
	
	public static final int startingExperienceToReach = 10;
	public static final int startingLife = 100;
	public static final int startingInventoryMaxSize = 10;
	public static final int startingTransportableWeight = 30;
	
	public static final String playerTexturePath = ".\\img\\entities\\player.gif";
	
	
//	public static Image getTexture(String imagePath) {
//		BufferedImage img = null;
//		
//		try {
//			img = ImageIO.read( new File( imagePath ) );
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//			
//		return (Image) img;
//	}
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
	
	public static String getStackTrace(Throwable t)
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
			sw = null;
			pw = null; 
		}
		return trace;
	}
	
}
