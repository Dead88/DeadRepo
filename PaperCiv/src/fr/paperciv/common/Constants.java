package fr.paperciv.common;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.paperciv.objs.Player;

public class Constants 
{
	public static final String PARAM_DIR 				= "H:\\PARAM_SITE\\PaperCiv\\";
	public static final String GAME_PROPERTIES			= PARAM_DIR+"game.properties";
	public static final String USERS_FILE 				= PARAM_DIR+"users.xml";
	public static final String MAPPING_FILE 			= PARAM_DIR+"mapping.xml";
	
	public static final String GAMEMAPS_DIR				= PARAM_DIR + "Maps\\";
	
	public static final String RACES_DIR 				= PARAM_DIR + "Races\\";
	public static final String RACE_TYPES_DIR 			= PARAM_DIR + "RaceTypes\\";
	
	public static final String DEPOSIT_TYPES_DIR 		= PARAM_DIR + "DepositTypes\\";
	
	public static final String UNITS_DIR 				= PARAM_DIR + "Units\\";
	public static final String UNIT_TYPES_DIR 			= PARAM_DIR + "UnitTypes\\";
	
	public static final String BUILDINGS_DIR 			= PARAM_DIR + "Buildings\\";
	public static final String BUILDING_TYPES_DIR 		= PARAM_DIR + "BuildingTypes\\";
	
	public static void initGameProperties(HttpServletRequest request) throws Exception
	{
		Properties props = null;
		FileInputStream in = null;
		
		try
		{
			in = new FileInputStream(GAME_PROPERTIES);
			
			props = new Properties();
			props.load(in);
			
			PaperSession.setGameProperties(request, props);
		}
		finally
		{
			if(in!=null) in.close();
			in = null;
		}
	}
	
	public static String getUrlRedirect(HttpServletRequest request, String page)
	{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		return basePath+page; 
	}
	
	public static String getParameter(HttpServletRequest request, String paramName)
	{
		if(request.getParameter(paramName) != null && !"".equals(request.getParameter(paramName)))
			return request.getParameter(paramName);
		else return "";
	}
	
	public static void sendResponse(HttpServletResponse response, String message)
	{
		try
		{		
			response.setContentType("text/html;charset=iso-8859-1");
			response.getOutputStream().print(message);
			response.getOutputStream().flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public static int getRandomBetween(int from, int to)
	{ 
		return (from + (int) ( Math.random()*(to - from + 1)));
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
	
	public static Player getPlayerById(HttpServletRequest request, int id) throws Exception
	{
		ArrayList<Player> players = null;
		Player player = null;
		
		try
		{
			players = PaperSession.getGamePlayersSession(request);
			
			if(players != null && players.size() > 0)
			{
				for(int i=0;i<players.size();i++)
				{
					if(players.get(i).getId() == id)
					{
						player = players.get(i);
						break;
					}
				}
			}
		}
		finally
		{
			players = null;
		}
		return player;
	}
}
