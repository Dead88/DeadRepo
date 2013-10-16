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
import fr.paperciv.factories.MapFactory;
import fr.paperciv.factories.PlayerFactory;
import fr.paperciv.objs.Player;

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
			
			PaperSession.setGamePlayersSession(request, players);
			
			MapFactory.generateMap(request, gameMapId);
			
			response.sendRedirect(Constants.getUrlRedirect(request, "game.jsp"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendRedirect(Constants.getUrlRedirect(request, "index.jsp"));
		}
		return null;
	}
}
