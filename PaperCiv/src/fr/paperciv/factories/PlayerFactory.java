package fr.paperciv.factories;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import fr.paperciv.objs.Player;
import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.units.Unit;

public class PlayerFactory 
{
	public static Player buildPlayer(HttpServletRequest request, int id, int raceId, int startingPaper, boolean isHumanControlled) throws Exception
	{
		Race race = null;
		Player player = null;
		
		try
		{
			race = RaceFactory.buildRace(request, raceId);
			player = new Player(id, startingPaper, 100, race, isHumanControlled, 
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
