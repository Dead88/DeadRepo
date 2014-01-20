package fr.paperciv.factories;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import fr.paperciv.common.Constants;
import fr.paperciv.common.PaperSession;
import fr.paperciv.objs.Player;
import fr.paperciv.objs.deposits.Deposit;
import fr.paperciv.objs.map.Area;
import fr.paperciv.objs.map.AreaType;
import fr.paperciv.objs.map.GameMap;
import fr.paperciv.objs.map.Tree;
import fr.paperciv.objs.map.Vertex;
import fr.paperciv.objs.units.Unit;

public class MapFactory 
{	
	public static final int minQuantityOfDeposit = 50;
	public static final int maxQuantityOfDeposit = 100;
	
	public static void generateMap(HttpServletRequest request, int gameMapId) throws Exception
	{
		GameMap gameMap = null;
		
		ArrayList<Area> areas = null;
		int tempX = 0;
		int tempZ = 0;
		
		try
		{
			gameMap = (GameMap)XmlFactory.getObjectFromXmlFile(request, GameMap.class, gameMapId);
			areas = new ArrayList<Area>();
			
			for(int i=0;i < (gameMap.getLength()*gameMap.getLength());i++)
			{			
				if(i==0)
				{
					tempX = -(gameMap.getLength()/2);
					tempZ = gameMap.getLength()/2;
				}
	
				if(tempX == (gameMap.getLength()/2))
				{
					tempX = -(gameMap.getLength()/2);
					tempZ -= 1;
				}
				
				Vertex[] vertices = { new Vertex(0, 0, 0), new Vertex(0, 0, 0),
									new Vertex(0, 0, 0), new Vertex(0, 0, 0)};
				
				Area area = new Area(tempX, 0, tempZ, vertices, i, gameMap.getTexture(), new AreaType(AreaType.GROUND_AREA), null, null);
				areas.add(i, area);
				
				tempX += 1;
			}
			
			generateWater(gameMap, areas);
			generateTrees(gameMap, areas);
			generatePaperDeposits(gameMap, areas);
			generateFictiveDeposits(request, gameMap, areas);
			
			//TODO: Use server to height map
			//MapFactory.generateHeightOn9(areas, gameMap.getLength(), gameMap.getHeightsQuantity(), gameMap.getHeight(), 0);
			gameMap.setAreas(areas);
			
			PaperSession.setGameMapSession(request, gameMap);
		}
		catch(Exception e)
		{
			throw new Exception("Error while generating the map :\n"+Constants.getStackTrace(e));
		}
		finally
		{
			gameMap = null;	
			areas = null;
		}
	}
	
	public static void generateHeightOn9(ArrayList<Area> areas, int mapLength, int numberOfHeight, double height, int predefinedIndex)
	{
		Area centerArea = null;
		Area previousArea = null;
		Area nextArea  = null;
		
		Area previousRightArea = null;
		Area previousRearRightArea = null;
		Area previousTopRightArea = null;
		
		Area nextLeftArea = null;
		Area nextRearLeftArea = null;
		Area nextTopLeftArea = null;
		
		try
		{
			for(int i=0;i<numberOfHeight;i++)
			{
				int randomIndex = 0;
				
				if(predefinedIndex != 0)
				{
					randomIndex = predefinedIndex;
				}
				else randomIndex = Constants.getRandomBetween( 0, areas.size() - 1);
	
				try
				{
					centerArea = areas.get(randomIndex);
					previousArea = areas.get(randomIndex - 1);
					nextArea  = areas.get(randomIndex + 1);
					
					previousRightArea = areas.get(randomIndex - mapLength);
					previousRearRightArea = areas.get(randomIndex - 1 - mapLength);
					previousTopRightArea = areas.get(randomIndex + 1 - mapLength);
					
					nextLeftArea = areas.get(randomIndex + mapLength);
					nextRearLeftArea = areas.get(randomIndex - 1 + mapLength);
					nextTopLeftArea = areas.get(randomIndex + 1 + mapLength);
				}
				catch(IndexOutOfBoundsException Ex){};
				
				centerArea.getVertices()[0].setY(centerArea.getVertices()[0].getY() + height);
				centerArea.getVertices()[1].setY(centerArea.getVertices()[1].getY() + height);
				centerArea.getVertices()[2].setY(centerArea.getVertices()[2].getY() + height);
				centerArea.getVertices()[3].setY(centerArea.getVertices()[3].getY() + height);
				
				if(previousArea != null)
				{
					previousArea.getVertices()[1].setY(previousArea.getVertices()[1].getY() + height);
					previousArea.getVertices()[3].setY(previousArea.getVertices()[3].getY() + height);
				}
				if(nextArea != null)
				{
					nextArea.getVertices()[0].setY(nextArea.getVertices()[0].getY() + height);
					nextArea.getVertices()[2].setY(nextArea.getVertices()[2].getY() + height);
				}
				
				if(previousRightArea != null)
				{
					previousRightArea.getVertices()[0].setY(previousRightArea.getVertices()[0].getY() + height);
					previousRightArea.getVertices()[1].setY(previousRightArea.getVertices()[1].getY() + height);
				}
				if(previousRearRightArea != null)
					previousRearRightArea.getVertices()[1].setY(previousRearRightArea.getVertices()[1].getY() + height);
				if(previousTopRightArea != null)
					previousTopRightArea.getVertices()[0].setY(previousTopRightArea.getVertices()[0].getY() + height);
				
				if(nextLeftArea != null)
				{
					nextLeftArea.getVertices()[2].setY(nextLeftArea.getVertices()[2].getY() + height);
					nextLeftArea.getVertices()[3].setY(nextLeftArea.getVertices()[3].getY() + height);
				}
				if(nextRearLeftArea != null)
					nextRearLeftArea.getVertices()[3].setY(nextRearLeftArea.getVertices()[3].getY() + height);
				if(nextTopLeftArea != null)
					nextTopLeftArea.getVertices()[2].setY(nextTopLeftArea.getVertices()[2].getY() + height);
			}
			
			for(int l=0;l<areas.size();l++)
			{
				Area area = areas.get(l);
				double yCount = 0.00;
				
				for(int j=0;j<area.getVertices().length;j++)
				{
					yCount += area.getVertices()[j].getY();
				}
				
				area.setY( yCount / area.getVertices().length );
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void generateWater(GameMap gameMap, ArrayList<Area> areas) throws Exception
	{
		int randLakeMode = 0;
		
		for(int j=0;j<gameMap.getWaterQuantity();j++)
		{				
			randLakeMode = Constants.getRandomBetween(1, 4);
			
			if(randLakeMode == 1)
			{
				int rand = Constants.getRandomBetween(0, areas.size()-1 - (5 * gameMap.getLength()));
				areas.get(rand).setTexture("img/water2.jpg");
				areas.get(rand).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand - 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 2 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + 2 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 1 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 2 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 2 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 3 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 3 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 2 + (3 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 2 + (3 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
			}
			else if(randLakeMode == 2)
			{
				int rand = Constants.getRandomBetween(0, areas.size()-1 - (5 * gameMap.getLength()));
				areas.get(rand).setTexture("img/water2.jpg");
				areas.get(rand).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand - 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 2 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand - 2 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 1 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 2 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 2 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 3 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 3 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 2 + (3 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 2 + (3 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
			}
			else if(randLakeMode == 3)
			{
				int rand = Constants.getRandomBetween(0, areas.size()-1 - (5 * gameMap.getLength()));
				areas.get(rand).setTexture("img/water2.jpg");
				areas.get(rand).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1).setTexture("img/water2.jpg");
				areas.get(rand - 1).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 2).setTexture("img/water2.jpg");
				areas.get(rand - 2).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand - 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 2 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + 2 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 1 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 2 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 2 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 3 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + 3 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
			}
			else if(randLakeMode == 4)
			{
				int rand = Constants.getRandomBetween(0, areas.size()-1 - (5 * gameMap.getLength()));
				areas.get(rand).setTexture("img/water2.jpg");
				areas.get(rand).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1).setTexture("img/water2.jpg");
				areas.get(rand + 1).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 2).setTexture("img/water2.jpg");
				areas.get(rand + 2).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand - 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + 1 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand + 1 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 2 + gameMap.getLength()).setTexture("img/water2.jpg");
				areas.get(rand - 2 + gameMap.getLength()).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 1 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 1 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 2 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 2 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
				
				areas.get(rand - 3 + (2 * gameMap.getLength())).setTexture("img/water2.jpg");
				areas.get(rand - 3 + (2 * gameMap.getLength())).getAreaType().setType(AreaType.SEA_AREA);
			}
		}
	}
	
	public static void generateTrees(GameMap gameMap, ArrayList<Area> areas) throws Exception
	{
		for(int k=0;k<gameMap.getTreesQuantity();k++)
		{
			int rand = Constants.getRandomBetween(0, areas.size()-1);
			
			while(AreaType.SEA_AREA.equals(areas.get(rand).getAreaType().getType()) || areas.get(rand).getDoodad()!=null)
			{
				rand = Constants.getRandomBetween(0, areas.size()-1);
			}
			
			String trunkTexture = gameMap.getTexture().replaceAll("\\.", "_trunk.");
			String foliageTexture = gameMap.getTexture().replaceAll("\\.", "_foliage.");
			
			Tree tree = new Tree(areas.get(rand).getX(), areas.get(rand).getY(), areas.get(rand).getZ(), null, trunkTexture, foliageTexture);
			areas.get(rand).setDoodad(tree);
		}
	}
	
	public static void generatePaperDeposits(GameMap gameMap, ArrayList<Area> areas) throws Exception 
	{
		int paperDepositQuantity = gameMap.getPaperDepositsQuantity();
		
		for(int l=0;l<paperDepositQuantity;l++)
		{
			int rand = Constants.getRandomBetween(0, areas.size()-1);
			
			while(AreaType.SEA_AREA.equals(areas.get(rand).getAreaType().getType()) || areas.get(rand).getDoodad()!=null)
			{
				rand = Constants.getRandomBetween(0, areas.size()-1);
			}
			
			Deposit deposit = new Deposit(areas.get(rand).getX(), areas.get(rand).getY(), areas.get(rand).getZ(), null, 
								Constants.getRandomBetween(minQuantityOfDeposit, maxQuantityOfDeposit), null);
			areas.get(rand).setDoodad(deposit);
		}
	}
	
	public static void generateFictiveDeposits(HttpServletRequest request, GameMap gameMap, ArrayList<Area> areas) throws Exception
	{
		ArrayList<Player> players = PaperSession.getGamePlayersSession(request);
		int fictiveDepositQuantity = gameMap.getFictiveDepositsQuantity();
		
		for(int m=0;m<players.size();m++)
		{
			Player player = players.get(m);
			
			for(int n=0;n<(fictiveDepositQuantity / players.size());n++)
			{
				int rand = Constants.getRandomBetween(0, areas.size()-1);
				
				while(AreaType.SEA_AREA.equals(areas.get(rand).getAreaType().getType()) || areas.get(rand).getDoodad()!=null)
				{
					rand = Constants.getRandomBetween(0, areas.size()-1);
				}
					
				Deposit deposit = new Deposit(areas.get(rand).getX(), areas.get(rand).getY(), areas.get(rand).getZ(), null, 
						Constants.getRandomBetween(minQuantityOfDeposit, maxQuantityOfDeposit), player.getPlayerRace().getFictiveRessource());
				areas.get(rand).setDoodad(deposit);
			}
		}
	}
	
	public static HashMap<Integer, Area> getNeighboursAreasFromRange(HttpServletRequest request, Area originArea, int range) throws Exception
	{
		GameMap gameMap = PaperSession.getGameMapSession(request);
		HashMap<Integer, Area> neighboursAreas = new HashMap<Integer, Area>();

		neighboursAreas.put( (originArea.getId() - range) , gameMap.getAreas().get( originArea.getId() - range ) );
		neighboursAreas.put( (originArea.getId() + range) , gameMap.getAreas().get( originArea.getId() + range ) );
		neighboursAreas.put( (originArea.getId() + gameMap.getLength()), gameMap.getAreas().get( originArea.getId() + gameMap.getLength() ) );
		neighboursAreas.put( (originArea.getId() + gameMap.getLength() - range), gameMap.getAreas().get( originArea.getId() + gameMap.getLength() - range ) );
		neighboursAreas.put( (originArea.getId() + gameMap.getLength() + range), gameMap.getAreas().get( originArea.getId() + gameMap.getLength() + range ) );
		neighboursAreas.put( (originArea.getId() - gameMap.getLength()), gameMap.getAreas().get( originArea.getId() - gameMap.getLength() ) );
		neighboursAreas.put( (originArea.getId() - gameMap.getLength() - range), gameMap.getAreas().get( originArea.getId() - gameMap.getLength() - range ) );
		neighboursAreas.put( (originArea.getId() - gameMap.getLength() + range), gameMap.getAreas().get( originArea.getId() - gameMap.getLength() + range ) );

		return neighboursAreas;
	}
	
	public static boolean isAreaInArrayList(Area area, ArrayList<Area> areas) throws Exception
	{
		boolean isAreaInArrayList = false;
		
		for(int i=0;i<areas.size();i++)
		{
			if(area.getId() == areas.get(i).getId())
			{			
				isAreaInArrayList = true;
				break;
			}
		}
		
		return isAreaInArrayList;
	}
	
	public static ArrayList<Area> getReachableAreas(HttpServletRequest request, int playerId, Area unitArea, Unit unit) throws Exception
	{
		ArrayList <Area> reachableAreas = new ArrayList <Area>();
		int weight = 0;
		
		unitArea.setDistance( weight );
		
		HashMap<Integer, Area> weightedNeighbours = new HashMap<Integer, Area>();
		weightedNeighbours.put( unitArea.getId(), unitArea );
		
		getWeightedNeighbours( request, unitArea, weightedNeighbours, weight, unit.getSpeedRemaining() );
		
		for (Area reachableArea : weightedNeighbours.values()) 
		{
			reachableAreas.add( reachableArea );
		}
		
		return reachableAreas;
	}
	
	public static void getWeightedNeighbours( HttpServletRequest request, Area firstArea, HashMap<Integer, Area> weightedNeighbours, int weight, int speedRemaining) throws Exception
	{	
		weight++;
		
		if( weight > speedRemaining )
		{
			return;
		}
		
		HashMap<Integer, Area> neighbours = getNeighboursAreasFromRange( request, firstArea, 1 );
		
		for ( Area area : neighbours.values() ) 
		{
			if(weightedNeighbours.get( area.getId() ) != null && weightedNeighbours.get( area.getId() ).getDistance() > weight)
			{
				weightedNeighbours.get( area.getId() ).setDistance( weight );
				
				getWeightedNeighbours( request, area, weightedNeighbours, weight, speedRemaining );
			}
			else
			{
				area.setDistance( weight );
				weightedNeighbours.put( area.getId(),  area );
				
				getWeightedNeighbours( request, area, weightedNeighbours, weight, speedRemaining );
			}
		}
	}
}
