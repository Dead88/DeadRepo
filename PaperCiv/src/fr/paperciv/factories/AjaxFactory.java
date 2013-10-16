package fr.paperciv.factories;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.paperciv.common.Constants;
import fr.paperciv.common.PaperSession;
import fr.paperciv.objs.Player;
import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.map.Area;
import fr.paperciv.objs.map.AreaType;
import fr.paperciv.objs.map.GameMap;
import fr.paperciv.objs.units.Unit;

public class AjaxFactory extends Action
{
	//FIXME : handle add unit and building errors text
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		int playerId = 0;
		String method = "";
		
		try
		{
			playerId = Integer.parseInt(Constants.getParameter(request, "playerId"));
			method = Constants.getParameter(request, "method");
			
			if("haveRequiredBuildings".equals(method))
			{
				String idsTofind = Constants.getParameter(request, "idsTofind");
				
				if(idsTofind == null || (idsTofind != null && "".equals(idsTofind)))
					Constants.sendResponse(response, "OK");
				else 
				{			
					boolean haveRequiredBuildings = haveRequiredBuildings(request, playerId, idsTofind);
					
					if(haveRequiredBuildings)
						Constants.sendResponse(response, "OK");
					else Constants.sendResponse(response, "KO");
				}
			}
			else if("haveEnoughRessource".equals(method))
			{
				int paperCost = Integer.parseInt(Constants.getParameter(request, "paperCost"));
				int fictiveCost = Integer.parseInt(Constants.getParameter(request, "fictiveCost"));
				
				boolean haveEnoughRessource = haveEnoughRessource(request, playerId, paperCost, fictiveCost);
				
				if(haveEnoughRessource)
					Constants.sendResponse(response, "OK");
				else Constants.sendResponse(response, "KO");
			}
			else if("canBuildEntityOnSelectedArea".equals(method))
			{
				String entityType = Constants.getParameter(request, "entityType");
				String areaType = Constants.getParameter(request, "areaType");
				
				boolean canBuildEntityOnSelectedArea = canBuildEntityOnSelectedArea(entityType, areaType);
				
				if(canBuildEntityOnSelectedArea)
					Constants.sendResponse(response, "OK");
				else Constants.sendResponse(response, "KO");
			}
			else if("addPlayerBuildingAtCoordinate".equals(method))
			{
				int buildingId = Integer.parseInt(Constants.getParameter(request, "buildingId"));
				int X = Integer.parseInt(Constants.getParameter(request, "X"));
				double Y = Double.parseDouble(Constants.getParameter(request, "Y"));
				int Z = Integer.parseInt(Constants.getParameter(request, "Z"));
	
				int areaArrayId = addPlayerBuildingAtCoordinate(request, playerId, buildingId, X, Y, Z);
				
				if(areaArrayId > 0)
				{
					String returnObjects = XmlFactory.getJSONStringFromObject(
												PaperSession.getGameMapSession(request).getAreas().get(areaArrayId).getDoodad())
										+";"+XmlFactory.getJSONStringFromObject(
												Constants.getPlayerById(request, playerId));
					
					Constants.sendResponse(response, returnObjects);
				}
				else Constants.sendResponse(response, "KO");				
			}
			else if("addPlayerUnitAtCoordinate".equals(method))
			{
				int unitId = Integer.parseInt(Constants.getParameter(request, "unitId"));
	
				int areaArrayId = addPlayerUnitAtCoordinate(request, playerId, unitId);
				
				if(areaArrayId > 0)
				{
					String returnObjects = areaArrayId
										+";"+XmlFactory.getJSONStringFromObject(
												PaperSession.getGameMapSession(request).getAreas().get(areaArrayId).getDoodad())
										+";"+XmlFactory.getJSONStringFromObject(
												Constants.getPlayerById(request, playerId));
					
					Constants.sendResponse(response, returnObjects);
				}
				else Constants.sendResponse(response, "KO");	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Constants.sendResponse(response, "KO");
		}
		return null;
	}
	
	public static boolean haveRequiredBuildings(HttpServletRequest request, int playerId, String idsTofind) throws Exception
	{
		Player player = null;
		String[] cuttedIds = null;
		ArrayList<String> requiredIds = null;
		boolean oneNeeded = false;
		
		try
		{
			cuttedIds = idsTofind.split(";");
			requiredIds = new ArrayList<String>();
			
			for (int i=0;i<cuttedIds.length;i++) 
			{
			    requiredIds.add(cuttedIds[i]);
			}
			
			player = Constants.getPlayerById(request, playerId);
			
			if(player.getBuildings() != null && player.getBuildings().size() > 0)
			{
				if(requiredIds == null || (requiredIds != null && requiredIds.size() == 0))
					return true;	
						

				for(int i=0;i<requiredIds.size();i++)
				{
					for(int j=0;j<player.getBuildings().size();j++)
					{
						Building b = player.getBuildings().get(j);
						
						if(requiredIds.get(i).equals(b.getId()+""))
						{
							if(requiredIds.size() == 1)
								oneNeeded = true;
							
							requiredIds.set(i, "0");
							break;
						}
					}
				}		
				
				if(oneNeeded) return true;
				else 
				{
					int compteur = 0;
					for(int j=0;j<requiredIds.size();j++)
					{
						compteur += Integer.parseInt(requiredIds.get(j));
					}
					if(compteur == 0) return true;
				}
			}
		}
		finally
		{
			player = null;
		}
		return false;
	}
	
	public static boolean haveEnoughRessource(HttpServletRequest request, int playerId, int paperCost, int fictiveCost) throws Exception
	{
		Player player = null;
		
		try
		{
			player = Constants.getPlayerById(request, playerId);
			
			if(player.getPaperAmount() >= paperCost && player.getFictiveRessourceAmount() >= fictiveCost)
				return true;
			else return false;
		}
		finally
		{
			player = null;
		}
	}
	
	public static boolean canBuildEntityOnSelectedArea(String entityType, String areaType)
	{
		if("Ground Building".equals(entityType) && AreaType.GROUND_AREA.equals(areaType))
			return true;
		else if("Infantry".equals(entityType) && AreaType.GROUND_AREA.equals(areaType))
			return true;
		else return false;
	}
	
	public static int addPlayerBuildingAtCoordinate(HttpServletRequest request, int playerId, int buildingId, int x, double y, int z) throws Exception
	{
		ArrayList<Player> players = null;
		GameMap gameMap = null;
		
		Player player = null;
		int playerArrayId = 0;
		Building referenceBuilding = null;
		Area area = null;
		int areaArrayId = 0;
		
		boolean bAdded = false;
		
		try
		{
			players = PaperSession.getGamePlayersSession(request);
			gameMap = PaperSession.getGameMapSession(request);
			
			for(int i=0;i<players.size();i++)
			{
				if(players.get(i).getId() == playerId)
				{
					player = players.get(i);
					playerArrayId = i;
					break;
				}
			}
			
			if(player == null) return 0;
			
			for(int j=0;j<player.getPlayerRace().getBuildings().size();j++)
			{
				if(player.getPlayerRace().getBuildings().get(j).getId() == buildingId)
				{
					referenceBuilding = player.getPlayerRace().getBuildings().get(j);
					break;
				}
			}
			
			if(referenceBuilding == null) return 0;
			
			for(int k=0;k<gameMap.getAreas().size();k++)
			{
				if(gameMap.getAreas().get(k).getX() == x && gameMap.getAreas().get(k).getZ() == z)
				{
					area = gameMap.getAreas().get(k);
					areaArrayId = k;
					break;
				}	
			}
			
			if(area == null) return 0;
			
			if(haveEnoughRessource(request, playerId, referenceBuilding.getPaperCost(), referenceBuilding.getFictiveCost())
			&& canBuildEntityOnSelectedArea(referenceBuilding.getType().getName(), area.getAreaType().getType()))
			{	
				Building buildingToAdd = new Building(referenceBuilding.getId(), referenceBuilding.getName(), referenceBuilding.getFile(), referenceBuilding.getLevel(), 
												referenceBuilding.getTexture(), referenceBuilding.getType(), referenceBuilding.getBuildingTypeId(), 
												referenceBuilding.getPaperCost(), referenceBuilding.getFictiveCost(), referenceBuilding.getRequiredBuildingIds(), 
												referenceBuilding.getLife(), referenceBuilding.getArmor(), x, y, z);
				
				player.getBuildings().add(buildingToAdd);
				player.setPaperAmount(player.getPaperAmount() - referenceBuilding.getPaperCost());
				player.setFictiveRessourceAmount(player.getFictiveRessourceAmount() - referenceBuilding.getFictiveCost());
				players.set(playerArrayId, player);
				
				area.setDoodad(buildingToAdd);
				gameMap.getAreas().set(areaArrayId, area);
				
				PaperSession.setGamePlayersSession(request, players);
				PaperSession.setGameMapSession(request, gameMap);
						
				bAdded = true;
			}
		}
		finally
		{
			players = null;
			gameMap = null;
		}
		if(bAdded)return areaArrayId;
		else return 0;
	}
	
	public static int addPlayerUnitAtCoordinate(HttpServletRequest request, int playerId, int unitId) throws Exception
	{
		ArrayList<Player> players = null;
		GameMap gameMap = null;
		
		Player player = null;
		int playerArrayId = 0;
		Unit referenceUnit = null;
		ArrayList<Building> parentBuildings = null;
		ArrayList<Integer> baseAreaArrayIds = null;
		int baseAreaArrayId = 0;
		Area nearestAvailableArea = null;
		int nearestAvailableAreaArrayId = 0;
		
		boolean uAdded = false;
		
		try
		{
			players = PaperSession.getGamePlayersSession(request);
			gameMap = PaperSession.getGameMapSession(request);
					
			parentBuildings = new ArrayList<Building>();		
			baseAreaArrayIds = new ArrayList<Integer>();
			
			for(int i=0;i<players.size();i++)
			{
				if(players.get(i).getId() == playerId)
				{
					player = players.get(i);
					playerArrayId = i;
					break;
				}
			}
			
			if(player == null) return 0;
			
			for(int j=0;j<player.getPlayerRace().getUnits().size();j++)
			{
				if(player.getPlayerRace().getUnits().get(j).getId() == unitId)
				{
					referenceUnit = player.getPlayerRace().getUnits().get(j);
					break;
				}
			}
			
			if(referenceUnit == null) return 0;
					
			for(int k=player.getBuildings().size() - 1;k>=0;k--)
			{
				if(player.getBuildings().get(k).getId() == referenceUnit.getRequiredBuildingIds()[ referenceUnit.getRequiredBuildingIds().length - 1 ])
				{
					parentBuildings.add(player.getBuildings().get(k));
					
					for(int l=0;l<gameMap.getAreas().size();l++)
					{
						if(gameMap.getAreas().get(l).getX() == player.getBuildings().get(k).getX()
						&& gameMap.getAreas().get(l).getZ() == player.getBuildings().get(k).getZ())
						{
							baseAreaArrayIds.add(l);
						}
					}
				}
			}
			
			if(parentBuildings.size() == 0 || (parentBuildings.size() > 0 && parentBuildings.size() != baseAreaArrayIds.size())) 
				return 0;
			
			for(int m=0;m<baseAreaArrayIds.size();m++)
			{
				if(baseAreaArrayIds.get(m) == 0) continue;
				baseAreaArrayId = baseAreaArrayIds.get(m);
				
				if(gameMap.getAreas().get(baseAreaArrayId - 1).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId - 1).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId - 1);
					nearestAvailableAreaArrayId = baseAreaArrayId - 1;
				}
				else if(gameMap.getAreas().get(baseAreaArrayId + 1).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId + 1).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId + 1);
					nearestAvailableAreaArrayId = baseAreaArrayId + 1;
				}
				else if(gameMap.getAreas().get(baseAreaArrayId - gameMap.getLength()).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId - gameMap.getLength()).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId - gameMap.getLength());
					nearestAvailableAreaArrayId = baseAreaArrayId - gameMap.getLength();
				}
				else if(gameMap.getAreas().get(baseAreaArrayId + gameMap.getLength()).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId + gameMap.getLength()).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId + gameMap.getLength());
					nearestAvailableAreaArrayId = baseAreaArrayId + gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId - 1 - gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId - 1 - gameMap.getLength()).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId - 1 - gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId - 1 - gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId - 1 + gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId - 1 + gameMap.getLength()).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId - 1 + gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId - 1 + gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId + 1 - gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId + 1 - gameMap.getLength()).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId + 1 - gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId + 1 - gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId + 1 + gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(referenceUnit.getType().getName(), gameMap.getAreas().get(baseAreaArrayId + 1 + gameMap.getLength()).getAreaType().getType())){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId + 1 + gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId + 1 + gameMap.getLength();
				}
				
				if(nearestAvailableArea != null) break;
			}		
					
			if(nearestAvailableArea != null)
			{
				//FIXME : Y here isnt set;
				Unit unitToAdd = new Unit(referenceUnit.getId(), referenceUnit.getName(), referenceUnit.getFile(), 
										referenceUnit.getLevel(), referenceUnit.getTexture(), referenceUnit.getType(), 
										referenceUnit.getUnitTypeId(), referenceUnit.getPaperCost(), referenceUnit.getFictiveCost(), 
										referenceUnit.getRequiredBuildingIds(), referenceUnit.getLife(), referenceUnit.getPower(), 
										referenceUnit.getArmor(), referenceUnit.getSpeed(), referenceUnit.getRange(), referenceUnit.getFireFrequency(), 
										nearestAvailableArea.getX(), nearestAvailableArea.getY(), nearestAvailableArea.getZ());
				
				player.getUnits().add(unitToAdd);
				player.setPaperAmount(player.getPaperAmount() - referenceUnit.getPaperCost());
				player.setFictiveRessourceAmount(player.getFictiveRessourceAmount() - referenceUnit.getFictiveCost());
				players.set(playerArrayId, player);
							
				nearestAvailableArea.setDoodad(unitToAdd);
				gameMap.getAreas().set(nearestAvailableAreaArrayId, nearestAvailableArea);

				PaperSession.setGamePlayersSession(request, players);
				PaperSession.setGameMapSession(request, gameMap);
				
				uAdded = true;
			}
		}
		finally
		{
			players = null;
			gameMap = null;
		}
		if(uAdded) return nearestAvailableAreaArrayId;
		else return 0;
	}
}
