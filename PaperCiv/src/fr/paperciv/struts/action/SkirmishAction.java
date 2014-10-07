package fr.paperciv.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.paperciv.common.Constants;
import fr.paperciv.common.PaperSession;
import fr.paperciv.factories.AjaxFactory;
import fr.paperciv.factories.MapFactory;
import fr.paperciv.factories.PlayerFactory;
import fr.paperciv.objs.Player;
import fr.paperciv.objs.map.Area;
import fr.paperciv.objs.map.GameMap;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.units.Unit;

public class SkirmishAction extends Action
{	
	static int startingPaper = 200;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		int playerRaceId = 0;
		int enemyRaceId = 0;
		int gameMapId = 0;
		
		ArrayList<Player> players = null;
		Player human = null;
		Player enemy = null;
		
		try
		{	
//TODO : logged user only
//			if(PaperSession.getUserSession(request)==null)
//			{
//				response.sendRedirect(Constants.getUrlRedirect(request, "index.jsp"));
//				return null;
//			}
			
			playerRaceId = Integer.parseInt(Constants.getParameter(request, "playerRaceId"));
			enemyRaceId = Integer.parseInt(Constants.getParameter(request, "enemyRaceId"));
			gameMapId = Integer.parseInt(Constants.getParameter(request, "gameMapId"));
			
			human = PlayerFactory.buildPlayer(request, 1, playerRaceId, startingPaper, true);
			enemy = PlayerFactory.buildPlayer(request, 2, enemyRaceId, startingPaper, false);
			
			players = new ArrayList<Player>();
			players.add(human);
			players.add(enemy);
			
			MapFactory.generateMap(request, players, gameMapId);
			
			PaperSession.setGamePlayersSession(request, players);
			
			addRandomTroopsToPlayer(request, enemy.getId(), 5);	
			
			response.sendRedirect(Constants.getUrlRedirect(request, "game.jsp"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendRedirect(Constants.getUrlRedirect(request, "index.jsp"));
		}
		return null;
	}
	
	public static void addRandomTroopsToPlayer(HttpServletRequest request, int playerId, int numberOfTroops) throws Exception
	{
		Player player = Constants.getPlayerById(request, playerId);
		GameMap gamemap = PaperSession.getGameMapSession(request);
		Race enemyRace = player.getPlayerRace();
		int numberAdded = 0;
		
		while(numberAdded < numberOfTroops)
		{
			Area randomArea = gamemap.getAreas().get(Constants.getRandomBetween(0, (gamemap.getLength() * gamemap.getLength())));
		
			Unit randomUnit = enemyRace.getUnits().get(Constants.getRandomBetween(0, enemyRace.getUnits().size()-1));
		
			if(AjaxFactory.canEntityUseSelectedArea(request, player.getId(), "U", randomUnit, randomArea))
			{
				Unit unitToAdd = new Unit(randomUnit.getId(), randomUnit.getName(), randomUnit.getFile(), 
						randomUnit.getLevel(), randomUnit.getTexture(), randomUnit.getType(), 
						randomUnit.getUnitTypeId(), randomUnit.getPaperCost(), randomUnit.getFictiveCost(), 
						randomUnit.getRequiredBuildingIds(), randomUnit.getLife(), randomUnit.getPower(), 
						randomUnit.getArmor(), randomUnit.getSpeed(), randomUnit.getRange(), randomUnit.getAmmo(), 
						randomArea.getX(), randomArea.getY(), randomArea.getZ(), playerId, null);
				
				player.getUnits().add( unitToAdd );
				randomArea.setDoodad( unitToAdd );
				numberAdded++;
			}
		}
		
		PaperSession.setGameMapSession(request, gamemap);
	}
}
