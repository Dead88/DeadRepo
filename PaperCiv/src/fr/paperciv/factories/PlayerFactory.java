package fr.paperciv.factories;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import fr.paperciv.common.PaperSession;
import fr.paperciv.objs.Player;
import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.units.Unit;

public class PlayerFactory 
{
	public static Player buildPlayer(HttpServletRequest request, int id, int raceId, int startingPaper, boolean isHumanControlled) throws Exception
	{
		Properties gameProps = null;
		Race race = null;
		Player player = null;
		
		try
		{
			gameProps = PaperSession.getGameProperties(request);
			race = RaceFactory.buildRace(request, raceId);
			player = new Player(id, startingPaper, 100, race, isHumanControlled, 
					Integer.parseInt(gameProps.get("BuildActionNumber")+"") ,  
					Integer.parseInt(gameProps.get("AttackActionNumber")+""), 
					new ArrayList<Unit>(), 
					new ArrayList<Building>());
		}
		finally
		{
			race = null;
		}
		return player;
	}
}
