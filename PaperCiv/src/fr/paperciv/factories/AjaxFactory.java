package fr.paperciv.factories;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.paperciv.common.Constants;
import fr.paperciv.common.PaperSession;
import fr.paperciv.objs.Order;
import fr.paperciv.objs.Player;
import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.deposits.Deposit;
import fr.paperciv.objs.map.Area;
import fr.paperciv.objs.map.AreaType;
import fr.paperciv.objs.map.GameMap;
import fr.paperciv.objs.map.Vertex;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.units.Unit;

public class AjaxFactory extends Action
{
	//FIXME : handle add unit and building, and move unit errors text
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
				String entityIdentifier = Constants.getParameter(request, "entityIdentifier");
				String entityType = Constants.getParameter(request, "entityType");
				String xzCoord = Constants.getParameter(request, "xzCoord");
				
				boolean canBuildEntityOnSelectedArea = canBuildEntityOnSelectedArea(request, playerId, entityIdentifier, entityType, xzCoord);
				
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
												PaperSession.getGameMapSession(request).getAreas().get(areaArrayId).getBuilding())
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
			else if("movePlayerUnitToArea".equals(method))
			{
				int unitX = Integer.parseInt(Constants.getParameter(request, "unitX"));
				int unitZ = Integer.parseInt(Constants.getParameter(request, "unitZ"));
				int destinationAreaId = Integer.parseInt(Constants.getParameter(request, "destinationAreaId"));
				
				String unitAreaId = movePlayerUnitToArea(request, playerId, unitX, unitZ, destinationAreaId);
				
				if(!"".equals(unitAreaId))
				{
					String returnObjects = unitAreaId
										+";"+XmlFactory.getJSONStringFromObject(
												PaperSession.getGameMapSession(request).getAreas().get(destinationAreaId).getDoodad())
										+";"+XmlFactory.getJSONStringFromObject(
												Constants.getPlayerById(request, playerId));
					
					Constants.sendResponse(response, returnObjects);
				}
				else Constants.sendResponse(response, "KO");	
			}
			else if("getBuildableAreasFromBuildingsRange".equals(method))
			{
				String areaIds = getBuildableAreasFromBuildingsRange(request, playerId);
				
				if(!"".equals(areaIds))
				{			
					Constants.sendResponse(response, areaIds);
				}
				else Constants.sendResponse(response, "KO");
			}
			else if("getBuildableAreasFromOriginRange".equals(method))
			{
				int centerAreaId = Integer.parseInt(Constants.getParameter(request, "centerAreaId"));
				int range = Integer.parseInt(Constants.getParameter(request, "range"));
				
				String areaIds = getBuildableAreasFromOriginRange(request, playerId, centerAreaId, range);
		
				if(!"".equals(areaIds))
				{			
					Constants.sendResponse(response, areaIds);
				}
				else Constants.sendResponse(response, "KO");
			}
			else if("getReachableAreasFromUnitRange".equals(method))
			{
				int centerAreaId = Integer.parseInt(Constants.getParameter(request, "centerAreaId"));
				
				String areaIds = getReachableAreasFromUnitRange(request, playerId, centerAreaId);
				
				if(!"".equals(areaIds))
				{			
					Constants.sendResponse(response, areaIds);
				}
				else Constants.sendResponse(response, "KO");
			}
			else if("getAttackableAreasFromUnitRange".equals(method))
			{
				int centerAreaId = Integer.parseInt(Constants.getParameter(request, "centerAreaId"));
				
				String areaIds = getAttackableAreasFromUnitRange(request, playerId, centerAreaId);
				
				if(!"".equals(areaIds))
				{			
					Constants.sendResponse(response, areaIds);
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
	
	public static boolean canBuildEntityOnSelectedArea(HttpServletRequest request, int playerId, String entityIdentifier, 
														String entityType, String xzCoord) throws Exception
	{
		boolean b = false;
		Race playerRace = Constants.getPlayerById(request, playerId).getPlayerRace();
		GameMap gameMap = PaperSession.getGameMapSession(request);
		Area area = null;
		Deposit deposit = null;
		int x = Integer.parseInt(xzCoord.split(";")[0]);
		int z = Integer.parseInt(xzCoord.split(";")[1]);
		
		for(int i=0;i<gameMap.getAreas().size();i++)
		{
			if(gameMap.getAreas().get(i).getX() == x && gameMap.getAreas().get(i).getZ() == z)
			{
				area = gameMap.getAreas().get(i);
				if(area.getDoodad()!=null)
					deposit = (Deposit) area.getDoodad();
				break;
			}
		}
		
		if(area==null) return false;
		
		if("U".equals(entityIdentifier))
		{
			if("Infantry".equals(entityType) && AreaType.GROUND_AREA.equals(area.getAreaType().getType())
			&& area.getDoodad() == null && area.getBuilding() == null)
				b = true;
		}
		else if("B".equals(entityIdentifier))
		{		
			if("GroundBuilding".equals(entityType) && AreaType.GROUND_AREA.equals(area.getAreaType().getType()) && area.getDoodad() == null && area.getBuilding() == null)
				b = true;
			else if("DepositBuilding".equals(entityType) && (deposit!=null && "Deposit".equals(deposit.getMeshType())) && area.getBuilding() == null
				&& ((deposit.getType() == null) || (deposit.getType()!=null && deposit.getType().getId() == playerRace.getFictiveRessource().getId())))
				b = true;
		}
		return b;
	}
	
	public static boolean canBuildEntityOnSelectedArea(HttpServletRequest request, int playerId, String entityIdentifier, Object entity, Area area) throws Exception
	{
		boolean b = false;
		Race playerRace = Constants.getPlayerById(request, playerId).getPlayerRace();
		
		if("U".equals(entityIdentifier))
		{
			Unit unit = (Unit) entity;
			
			if("Infantry".equals(unit.getType().getName()) && AreaType.GROUND_AREA.equals(area.getAreaType().getType())
			&& area.getDoodad() == null && area.getBuilding() == null)
				b = true;
		}
		else if("B".equals(entityIdentifier))
		{
			Building building = (Building) entity;
			Deposit deposit = null;
			
			if(area.getDoodad()!=null && area.getDoodad() instanceof Deposit)
				deposit = (Deposit) area.getDoodad();
			
			if("GroundBuilding".equals(building.getType().getName()) 
			&& AreaType.GROUND_AREA.equals(area.getAreaType().getType()) 
			&& area.getDoodad() == null
			&& area.getBuilding() == null)
				b = true;
			else if("DepositBuilding".equals(building.getType().getName())
			&& area.getDoodad()!=null 
			&& area.getDoodad() instanceof Deposit 
			&& area.getBuilding() == null
			&& ((deposit.getType() == null) || (deposit.getType()!=null && deposit.getType().getId() == playerRace.getFictiveRessource().getId())))
				b = true;
		}
		return b;
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
			&& canBuildEntityOnSelectedArea(request, playerId, "B", referenceBuilding, area))
			{	
				Building buildingToAdd = new Building(referenceBuilding.getId(), referenceBuilding.getName(), referenceBuilding.getFile(), referenceBuilding.getLevel(), 
												referenceBuilding.getTexture(), referenceBuilding.getType(), referenceBuilding.getBuildingTypeId(), 
												referenceBuilding.getPaperCost(), referenceBuilding.getFictiveCost(), referenceBuilding.getRequiredBuildingIds(), 
												referenceBuilding.getLife(), referenceBuilding.getArmor(), x, y, z);
				
				player.getBuildings().add(buildingToAdd);
				player.setPaperAmount(player.getPaperAmount() - referenceBuilding.getPaperCost());
				player.setFictiveRessourceAmount(player.getFictiveRessourceAmount() - referenceBuilding.getFictiveCost());
				players.set(playerArrayId, player);
				
				area.setBuilding(buildingToAdd);
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
				
				if(gameMap.getAreas().get(baseAreaArrayId - 1).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId - 1))){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId - 1);
					nearestAvailableAreaArrayId = baseAreaArrayId - 1;
				}
				else if(gameMap.getAreas().get(baseAreaArrayId + 1).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId + 1))){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId + 1);
					nearestAvailableAreaArrayId = baseAreaArrayId + 1;
				}
				else if(gameMap.getAreas().get(baseAreaArrayId - gameMap.getLength()).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId - gameMap.getLength()))){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId - gameMap.getLength());
					nearestAvailableAreaArrayId = baseAreaArrayId - gameMap.getLength();
				}
				else if(gameMap.getAreas().get(baseAreaArrayId + gameMap.getLength()).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId + gameMap.getLength()))){
					nearestAvailableArea = gameMap.getAreas().get(baseAreaArrayId + gameMap.getLength());
					nearestAvailableAreaArrayId = baseAreaArrayId + gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId - 1 - gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId - 1 - gameMap.getLength()))){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId - 1 - gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId - 1 - gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId - 1 + gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId - 1 + gameMap.getLength()))){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId - 1 + gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId - 1 + gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId + 1 - gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId + 1 - gameMap.getLength()))){
					nearestAvailableArea = gameMap.getAreas().get((baseAreaArrayId + 1 - gameMap.getLength()));
					nearestAvailableAreaArrayId = baseAreaArrayId + 1 - gameMap.getLength();
				}
				else if(gameMap.getAreas().get((baseAreaArrayId + 1 + gameMap.getLength())).getDoodad()==null && canBuildEntityOnSelectedArea(request, playerId, "U", referenceUnit, gameMap.getAreas().get(baseAreaArrayId + 1 + gameMap.getLength()))){
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
										nearestAvailableArea.getX(), nearestAvailableArea.getY(), nearestAvailableArea.getZ(), playerId, null);
				
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
	
	@SuppressWarnings({ "unchecked" })
	public static String movePlayerUnitToArea(HttpServletRequest request, int playerId, int unitX, int unitZ, int destinationAreaId) throws Exception
	{
		ArrayList<Player> players = null;
		GameMap gameMap = null;
		
		Player player = null;
		int playerArrayId = 0;
		Unit selectedUnit = null;
		int selectedUnitArrayId = 0;
		Area destinationArea = null;
		Area selectedUnitArea = null;
		boolean uMoved = false;
		
		try
		{
			players = PaperSession.getGamePlayersSession(request);
			gameMap = PaperSession.getGameMapSession(request);
			
			player = Constants.getPlayerById(request, playerId);
			
			if(player == null) return "";
			
			for(int i=0;i<player.getUnits().size();i++)
			{
				if(player.getUnits().get(i).getX() == unitX
				&& player.getUnits().get(i).getZ() == unitZ)
				{
					selectedUnit = player.getUnits().get(i);
					selectedUnitArrayId = i;
					break;
				}
			}
			
			if(selectedUnit == null) return "";
			
			destinationArea = gameMap.getAreas().get(destinationAreaId);
			
			for(int j=0;j<gameMap.getAreas().size();j++)
			{
				if(gameMap.getAreas().get(j).getX() == selectedUnit.getX() && gameMap.getAreas().get(j).getZ() == selectedUnit.getZ())
				{
					selectedUnitArea = gameMap.getAreas().get(j);
				}
			}
			
			if(destinationArea == null || selectedUnitArea == null) return "";
			
			if(canBuildEntityOnSelectedArea(request, playerId, "U", selectedUnit, destinationArea)
			&& selectedUnit.getSpeedRemaining() > 0)
			{
				//TODO : use papersession
				ArrayList<Area> reachableAreas = (ArrayList<Area>)request.getSession().getAttribute("reachableAreas");
				int distance = 0;
				
				for(int a=0;a<reachableAreas.size();a++)
				{
					if(reachableAreas.get(a).getX() == destinationArea.getX()
					&& reachableAreas.get(a).getZ() == destinationArea.getZ())
					{
						distance = reachableAreas.get(a).getDistance();
						break;
					}
				}
				
				selectedUnit.setOrder(
					new Order(playerId, 
							selectedUnit.getId(), 
							Order.OrderType.Move, 
							new Vertex(selectedUnit.getX(), selectedUnit.getY(), selectedUnit.getZ()), 
							new Vertex(destinationArea.getX(), destinationArea.getY(), destinationArea.getZ()))
				);
				
				selectedUnit.setX(destinationArea.getX());
				selectedUnit.setY(destinationArea.getY());
				selectedUnit.setZ(destinationArea.getZ());
				
				selectedUnit.setSpeedRemaining( selectedUnit.getSpeedRemaining() - distance );
				
				player.getUnits().set(selectedUnitArrayId, selectedUnit);	
				players.set(playerArrayId, player);
		
				selectedUnitArea.setDoodad(null);
				gameMap.getAreas().set(selectedUnitArea.getId(), selectedUnitArea);
				
				destinationArea.setDoodad(selectedUnit);
				gameMap.getAreas().set(destinationAreaId, destinationArea);
				
				PaperSession.setGamePlayersSession(request, players);
				PaperSession.setGameMapSession(request, gameMap);
				
				uMoved = true;
			}
		}
		finally
		{
			players = null;
			gameMap = null;
		}
		if(uMoved) return selectedUnitArea.getId()+"";
		else return "";
	}
	
	public static String getBuildableAreasFromBuildingsRange(HttpServletRequest request, int playerId) throws Exception
	{
		Properties gameProperties = null;
		Player player = null;
		GameMap gameMap = null;
		
		String areaIds = "";
		int width = 0;
		
		ArrayList<Area> buildableAreas = null;
		
		try
		{
			gameProperties = PaperSession.getGameProperties(request);
			player = Constants.getPlayerById(request, playerId);
			gameMap = PaperSession.getGameMapSession(request);
			
			try{
				width = Integer.parseInt(gameProperties.get("FogDistanceFromBuilding")+"");
			}catch(NumberFormatException n){
				throw new NumberFormatException("FogDistanceFromBuilding unparsable");
			}
			
			buildableAreas = new ArrayList<Area>();
			
			for(int i=0;i<gameMap.getAreas().size();i++)
			{
				Area area = gameMap.getAreas().get(i);
				
				if(MapFactory.isAreaInArrayList(area, buildableAreas))
					continue;
				
				for(int j=0;j<player.getBuildings().size();j++)
				{
					Building b = player.getBuildings().get(j);
					
					if(		
						(area.getX() >= (b.getX() - width) 
						&& area.getX() <= (b.getX() + width))
					&&
						(area.getZ() >= (b.getZ() - width) 
						&& area.getZ() <= (b.getZ() + width))
					)
					{
						buildableAreas.add(area);
						break;
					}
				}
			}
			
			for(int k=0;k<buildableAreas.size();k++)
			{
				if("".equals(areaIds))
					areaIds += buildableAreas.get(k).getId();
				else areaIds += ";"+buildableAreas.get(k).getId();
			}
		}
		finally
		{
			gameProperties = null;
			player = null;
			gameMap = null;
		}
		return areaIds;
	}
	
	public static String getBuildableAreasFromOriginRange(HttpServletRequest request, int playerId, int centerAreaId, int width) throws Exception
	{
		String areaIds = "";
		GameMap gameMap = null;
		Area centerArea = null;
		
		try
		{
			gameMap = PaperSession.getGameMapSession(request);
			centerArea = gameMap.getAreas().get(centerAreaId);
			
			for(int i=0;i<gameMap.getAreas().size();i++)
			{
				Area area = gameMap.getAreas().get(i);
				
				if(		
					(area.getX() >= (centerArea.getX() - width) 
					&& area.getX() <= (centerArea.getX() + width))
				&&
					(area.getZ() >= (centerArea.getZ() - width) 
					&& area.getZ() <= (centerArea.getZ() + width))
				&& !area.getAreaType().getType().equals(AreaType.SEA_AREA)
				&& area.getDoodad() == null
				)
				{
					if("".equals(areaIds))
						areaIds += area.getId();
					else areaIds += ";"+area.getId();
				}
			}
		}
		finally
		{
			gameMap = null;
		}
		return areaIds;
	}
	
	public static String getReachableAreasFromUnitRange(HttpServletRequest request, int playerId, int centerAreaId) throws Exception
	{
		String areaIds = "";
		GameMap gameMap = null;
		Area unitArea = null;
		Unit unit = null;
		
		ArrayList<Area> reachableAreas = null;
		
		try
		{
			gameMap = PaperSession.getGameMapSession(request);
			unitArea = gameMap.getAreas().get(centerAreaId);
			unit = (Unit) unitArea.getDoodad();
		
			reachableAreas = MapFactory.getReachableAreas(request, playerId, unitArea, unit);
			
			request.getSession().setAttribute("reachableAreas", reachableAreas);
			
			for(int j=0;j<reachableAreas.size();j++)
			{			
				if("".equals(areaIds))
					areaIds += reachableAreas.get(j).getId();
				else areaIds += ";"+reachableAreas.get(j).getId();
			}
		}
		finally
		{
			gameMap = null;
		}
		return areaIds;
	}
	
	public static String getAttackableAreasFromUnitRange(HttpServletRequest request, int playerId, int centerAreaId) throws Exception 
	{
		String areaIds = "";
		GameMap gameMap = null;
		Area centerArea = null;
		Unit unit = null;
		
		try
		{
			gameMap = PaperSession.getGameMapSession(request);
			centerArea = gameMap.getAreas().get(centerAreaId);
			unit = (Unit) centerArea.getDoodad();
			
			for(int i=0;i<gameMap.getAreas().size();i++)
			{
				Area area = gameMap.getAreas().get(i);
				
				if(		
					(area.getX() >= (centerArea.getX() - unit.getRange()) 
					&& area.getX() <= (centerArea.getX() + unit.getRange()))
				&&
					(area.getZ() >= (centerArea.getZ() - unit.getRange()) 
					&& area.getZ() <= (centerArea.getZ() + unit.getRange()))
				&& !area.getAreaType().getType().equals(AreaType.SEA_AREA)
				&& area.getDoodad() == null
				)
				{
					if("".equals(areaIds))
						areaIds += area.getId();
					else areaIds += ";"+area.getId();
				}
			}
		}
		finally
		{
			gameMap = null;
		}
		return areaIds;
	}
}
