package fr.paperciv.factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fr.paperciv.common.Constants;
import fr.paperciv.common.PaperSession;
import fr.paperciv.objs.Mapping;
import fr.paperciv.objs.User;
import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.buildings.BuildingType;
import fr.paperciv.objs.deposits.DepositType;
import fr.paperciv.objs.map.GameMap;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.races.RaceType;
import fr.paperciv.objs.units.Unit;
import fr.paperciv.objs.units.UnitType;

public class XmlFactory 
{
	public static String getXmlFileContent(String filePath) throws Exception
	{
		String fileContent = "";
		String tmp = "";
		
		BufferedReader b = null;
		FileReader reader= null;
		
		try
		{
			reader = new FileReader(filePath);
			b = new BufferedReader(reader);
			
			while((tmp = b.readLine()) != null)
			{
				fileContent += tmp;
			}
		}
		finally
		{
			if(b != null) b.close();
			if(reader != null) reader.close();
			b = null;
			reader = null;
		}
		return fileContent;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<User> getUsers() throws Exception
	{
		ArrayList<User> al = null;
		XStream xstream = null;
		FileReader r = null;
		
		try
		{
			xstream = new XStream(new DomDriver());
			xstream.alias("User", User.class);
			
			r = new FileReader(Constants.USERS_FILE);
			al = (ArrayList<User>) xstream.fromXML(r);
		}
		finally
		{
			if(r!=null)r.close();
			r=null;
			xstream=null;		
		}
		return al;
	}
	
	public static Mapping getMappingObject(HttpServletRequest request) throws Exception
	{
		XStream xstream = null;
		FileReader r = null;
		Mapping mappingObj = null;
		try
		{	
			xstream = new XStream(new DomDriver());
			xstream.alias("Mapping", Mapping.class);
			xstream.alias("GameMap", GameMap.class);
			xstream.alias("Race", Race.class);
			xstream.alias("RaceType", RaceType.class);
			xstream.alias("Unit", Unit.class);
			xstream.alias("UnitType", UnitType.class);
			xstream.alias("Building", Building.class);
			xstream.alias("BuildingType", BuildingType.class);
			xstream.alias("DepositType", DepositType.class);
			r = new FileReader(Constants.MAPPING_FILE);
			mappingObj = (Mapping) xstream.fromXML(r);
			
			PaperSession.setMappingSession(request, mappingObj);
		}
		finally
		{
			if(r!=null)r.close();
			r=null;
			xstream=null;
		}
		return mappingObj;
	}
	
	@SuppressWarnings("rawtypes") 
	public static Object getObjectFromXmlFile(HttpServletRequest request, Class desiredClass, int mappingId) throws Exception
	{
		Mapping mapping = null;
		XStream xstream = null;
		FileReader r = null;
	
		Object obj = null;
		String objectFilePath = "";
		
		try
		{		
			mapping = PaperSession.getMappingSession(request);
			xstream = new XStream(new DomDriver());
			
			if(desiredClass == GameMap.class)
			{
				obj = new GameMap();
				ArrayList<GameMap> al = mapping.getGameMaps();
				
				for(int i=0;i<al.size();i++)
				{
					GameMap tmpGameMap = (GameMap)al.get(i);
					if(tmpGameMap.getId() == mappingId)
					{
						objectFilePath = Constants.GAMEMAPS_DIR + tmpGameMap.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("GameMap", GameMap.class);
				obj = (GameMap)xstream.fromXML(r);
			}
			else if(desiredClass == Race.class)
			{
				obj = new Race();
				ArrayList<Race> al = mapping.getRaces();
				
				for(int i=0;i<al.size();i++)
				{
					Race tmpRace = (Race)al.get(i);
					if(tmpRace.getId() == mappingId)
					{
						objectFilePath = Constants.RACES_DIR + tmpRace.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("Race", Race.class);
				xstream.alias("UnitId", Integer.class);
				xstream.alias("BuildingId", Integer.class);
				obj = (Race)xstream.fromXML(r);
			}
			else if(desiredClass == RaceType.class)
			{
				obj = new RaceType();
				ArrayList<RaceType> al = mapping.getRaceTypes();
				
				for(int i=0;i<al.size();i++)
				{
					RaceType tmpRaceType = (RaceType)al.get(i);
					if(tmpRaceType.getId() == mappingId)
					{
						objectFilePath = Constants.RACE_TYPES_DIR + tmpRaceType.getFile();
						break;
					}	
				}
				
				r = new FileReader(objectFilePath);
				xstream.alias("RaceType", RaceType.class);
				obj = (RaceType)xstream.fromXML(r);
			}
			else if(desiredClass == Unit.class)
			{
				obj = new Unit();
				ArrayList<Unit> al = mapping.getUnits();
				
				for(int i=0;i<al.size();i++)
				{
					Unit tmpUnit = (Unit)al.get(i);
					if(tmpUnit.getId() == mappingId)
					{
						objectFilePath = Constants.UNITS_DIR + tmpUnit.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("Unit", Unit.class);
				obj = (Unit)xstream.fromXML(r);
			}
			else if(desiredClass == UnitType.class)
			{
				obj = new UnitType();
				ArrayList<UnitType> al = mapping.getUnitTypes();
				
				for(int i=0;i<al.size();i++)
				{
					UnitType tmpUnitType = (UnitType)al.get(i);
					if(tmpUnitType.getId() == mappingId)
					{
						objectFilePath = Constants.UNIT_TYPES_DIR + tmpUnitType.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("UnitType", UnitType.class);
				obj = (UnitType)xstream.fromXML(r);
			}
			else if(desiredClass == Building.class)
			{
				obj = new Building();
				ArrayList<Building> al = mapping.getBuildings();
				
				for(int i=0;i<al.size();i++)
				{
					Building tmpBuilding = (Building)al.get(i);
					if(tmpBuilding.getId() == mappingId)
					{
						objectFilePath = Constants.BUILDINGS_DIR + tmpBuilding.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("Building", Building.class);
				obj = (Building)xstream.fromXML(r);
			}
			else if(desiredClass == BuildingType.class)
			{
				obj = new UnitType();
				ArrayList<BuildingType> al = mapping.getBuildingTypes();
				
				for(int i=0;i<al.size();i++)
				{
					BuildingType tmpBuildingType = (BuildingType)al.get(i);
					if(tmpBuildingType.getId() == mappingId)
					{
						objectFilePath = Constants.BUILDING_TYPES_DIR + tmpBuildingType.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("BuildingType", BuildingType.class);
				obj = (BuildingType)xstream.fromXML(r);
			}
			else if(desiredClass == DepositType.class)
			{
				obj = new DepositType();
				ArrayList<DepositType> al = mapping.getDepositTypes();
				
				for(int i=0;i<al.size();i++)
				{
					DepositType tmpDepositType = (DepositType)al.get(i);
					if(tmpDepositType.getId() == mappingId)
					{
						objectFilePath = Constants.DEPOSIT_TYPES_DIR + tmpDepositType.getFile();
						break;
					}		
				}	
				
				r = new FileReader(objectFilePath);
				xstream.alias("DepositType", DepositType.class);
				obj = (DepositType)xstream.fromXML(r);
			}
		}
		finally
		{
			if(r!=null)r.close();
			r=null;
			xstream = null;
		}
		return obj;
	}
	
	public static String getJSONStringFromObject(Object o)
	{
		Gson gson = new Gson();
		String jsonString = "";
		
		jsonString = gson.toJson(o);
		
		return jsonString;
	}
}
