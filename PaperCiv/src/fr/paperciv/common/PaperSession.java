package fr.paperciv.common;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import fr.paperciv.factories.XmlFactory;
import fr.paperciv.objs.Mapping;
import fr.paperciv.objs.Player;
import fr.paperciv.objs.User;
import fr.paperciv.objs.map.GameMap;

public class PaperSession 
{
	public static final String GAME_PROPERTIES_SESSION 		= "gamePropertiesSession";
	public static final String USER_SESSION 				= "userSession";
	public static final String GAMEMAP_SESSION 				= "gamemapSession";
	public static final String GAME_PLAYERS_SESSION 		= "gamePlayersSession";
	public static final String MAPPING_SESSION 				= "mappingSession";
	
	public static Properties getGameProperties(HttpServletRequest request) throws Exception
	{
		if(request.getSession().getAttribute(GAME_PROPERTIES_SESSION)==null)
			Constants.initGameProperties(request);
		return (Properties)request.getSession().getAttribute(GAME_PROPERTIES_SESSION);
	}
	public static void setGameProperties(HttpServletRequest request, Properties props)
	{
		request.getSession().setAttribute(GAME_PROPERTIES_SESSION, props);
	}
	
	public static User getUserSession(HttpServletRequest request)
	{
		return (User)request.getSession().getAttribute(USER_SESSION);
	}
	public static void setUserSession(HttpServletRequest request, User user)
	{
		request.getSession().setAttribute(USER_SESSION, user);
	}
	
	public static GameMap getGameMapSession(HttpServletRequest request)
	{
		return (GameMap)request.getSession().getAttribute(GAMEMAP_SESSION);
	}
	public static void setGameMapSession(HttpServletRequest request, GameMap gameMap)
	{
		request.getSession().setAttribute(GAMEMAP_SESSION, gameMap);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Player> getGamePlayersSession(HttpServletRequest request)
	{
		return (ArrayList<Player>)request.getSession().getAttribute(GAME_PLAYERS_SESSION);
	}
	public static void setGamePlayersSession(HttpServletRequest request, ArrayList<Player> gamePlayers)
	{
		request.getSession().setAttribute(GAME_PLAYERS_SESSION, gamePlayers);
	}
	
	public static Mapping getMappingSession(HttpServletRequest request) throws Exception
	{	
		if(request.getSession().getAttribute(MAPPING_SESSION)==null)
			return XmlFactory.getMappingObject(request);
		else return (Mapping) request.getSession().getAttribute(MAPPING_SESSION);
	}
	public static void setMappingSession(HttpServletRequest request, Mapping mapping)
	{
		request.getSession().setAttribute(MAPPING_SESSION, mapping);
	}
}
