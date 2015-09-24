package fr.wretchedlife.generator;

import fr.wretchedlife.Constants;
import fr.wretchedlife.map.GameMap;

public class NameGenerator {

	public static String getRandomRegionName( GameMap.Type regionType ) {
		
		if( regionType == GameMap.Type.OUTDOOR ) {
			int rand = Constants.getRandomBetween(1, 10);
			switch( rand ) {
				case 1 : return "Terres désolées";
				case 2 : return "Terres dévastée";
				case 3 : return "Landes calcinées";
				case 4 : return "Landes mortes";
				case 5 : return "Collines escarpées";
				case 6 : return "Collines radioactives";
				case 7 : return "Vallées irradiées";
				case 8 : return "Vallées sineuses";
				case 9 : return "Plateaux inconnus";
				case 10 : return "Plateaux du monde";
			}
		}
		else if( regionType == GameMap.Type.BUILDING ) {
			int rand = Constants.getRandomBetween(1, 10);
			switch( rand ) {
				case 1 : return "Maison abandonnée";
				case 2 : return "Maison en ruines";
				case 3 : return "Maison délabrée";
				case 4 : return "Maison dévastée";
				case 5 : return "Maison ancienne";
				case 6 : return "Maison bien conservée";
				case 7 : return "Maison anciennement squatée";
				case 8 : return "Maison de pauvre";
				case 9 : return "Maison endomagée";
				case 10 : return "Maison sacagée";
			}
		}
		else if( regionType == GameMap.Type.UNDERGROUND ) {
			int rand = Constants.getRandomBetween(1, 10);
			switch( rand ) {
				case 1 : return "Cave sordide";
				case 2 : return "Cave lugubre";
				case 3 : return "Grotte sombre";
				case 4 : return "Grotte éffroyable";
				case 5 : return "Souterrains odorants";
				case 6 : return "Souterrains complexes";
				case 7 : return "Ruines endomagée";
				case 8 : return "Ruines anciennes";
				case 9 : return "Caverne inconnue";
				case 10 : return "Caverne odorante";
			}
		}
		
		return null;
	}
}
