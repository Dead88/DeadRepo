package fr.wretchedlife.generator;

import fr.wretchedlife.Constants;
import fr.wretchedlife.map.GameMap;

public class NameGenerator {

	public static String getRandomRegionName( GameMap.FloorType floorType ) {
		
		if( floorType == null ) {
			int rand = Constants.getRandomBetween(1, 10);
			switch( rand ) {
				case 1 : return "Desolated Lands";
				case 2 : return "Devastated Lands";
				case 3 : return "Calcined Lands";
				case 4 : return "Cursed Lands";
				case 5 : return "Steep Hills";
				case 6 : return "Radioactive Hills";
				case 7 : return "Irradiated Valley";
				case 8 : return "Winding Valley";
				case 9 : return "Unknown Plates";
				case 10 : return "High Plates";
			}
		}
		else if( floorType == GameMap.FloorType.HOUSE ) {
			int rand = Constants.getRandomBetween(1, 10);
			switch( rand ) {
				case 1 : return "Abandoned House";
				case 2 : return "House In Ruins";
				case 3 : return "Dilapidated House";
				case 4 : return "Devastated House";
				case 5 : return "Old House";
				case 6 : return "Well Conserved House";
				case 7 : return "Old Squatted House";
				case 8 : return "Poor House";
				case 9 : return "Damaged House";
				case 10 : return "Sacked House";
			}
		}
		else if( floorType == GameMap.FloorType.CAVE ) {
			int rand = Constants.getRandomBetween(1, 10);
			switch( rand ) {
				case 1 : return "Sordid Cave";
				case 2 : return "Dismal Cave";
				case 3 : return "Dark Cave";
				case 4 : return "Fearful Cave";
				case 5 : return "Odorous Undergrounds";
				case 6 : return "Complex Undergrounds";
				case 7 : return "Damaged Ruins";
				case 8 : return "Ancient Ruins";
				case 9 : return "Unknown Cavern";
				case 10 : return "Odorous Cavern";
			}
		}
		else if( floorType == GameMap.FloorType.DUNGEON ) {
			int rand = Constants.getRandomBetween(1, 4);
			switch( rand ) {
				case 1 : return "Evil's Lair";
				case 2 : return "Forgotten's Tower";
				case 3 : return "Dungeon Of Suffering";
				case 4 : return "Ancestral Keep";
			}
		}
		
		return null;
	}
}

