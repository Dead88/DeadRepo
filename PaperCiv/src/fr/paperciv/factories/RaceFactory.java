package fr.paperciv.factories;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import fr.paperciv.objs.buildings.Building;
import fr.paperciv.objs.buildings.BuildingType;
import fr.paperciv.objs.deposits.DepositType;
import fr.paperciv.objs.races.Race;
import fr.paperciv.objs.races.RaceType;
import fr.paperciv.objs.units.Unit;
import fr.paperciv.objs.units.UnitType;

public class RaceFactory 
{
	public static Race buildRace(HttpServletRequest request, int raceId) throws Exception
	{
		Race race = null;
		RaceType raceType = null;
		DepositType depositType = null;
		
		Unit unit = null;
		UnitType unitType = null;
		Building building = null;
		BuildingType buildingType = null;
		
		try
		{
			race = (Race)XmlFactory.getObjectFromXmlFile(request, Race.class, raceId);
			raceType = (RaceType)XmlFactory.getObjectFromXmlFile(request, RaceType.class, race.getRaceTypeId());
			depositType = (DepositType)XmlFactory.getObjectFromXmlFile(request, DepositType.class, race.getDepositTypeId());
			
			race.setType(raceType);
			race.setFictiveRessource(depositType);
			
			race.setUnits(new ArrayList<Unit>());
			race.setBuildings(new ArrayList<Building>());
			
			for(int i=0;i<race.getUnitsId().length;i++)
			{
				int currentUnitId = race.getUnitsId()[i];
				unit = (Unit)XmlFactory.getObjectFromXmlFile(request, Unit.class, currentUnitId);
				unitType = (UnitType)XmlFactory.getObjectFromXmlFile(request, UnitType.class, unit.getUnitTypeId());
			
				unit.setType(unitType);
				race.getUnits().add(unit);
			}
			
			for(int i=0;i<race.getBuildingsId().length;i++)
			{
				int currentBuildingId = race.getBuildingsId()[i];
				building = (Building)XmlFactory.getObjectFromXmlFile(request, Building.class, currentBuildingId);
				buildingType = (BuildingType)XmlFactory.getObjectFromXmlFile(request, BuildingType.class, building.getBuildingTypeId());
				
				building.setType(buildingType);
				race.getBuildings().add(building);
			}
		}
		finally
		{
			raceType = null;
			depositType = null;		
			unit = null;
			unitType = null;
			building = null;
			buildingType = null;
		}
		return race;
	}
}
