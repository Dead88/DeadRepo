package fr.wretchedlife;

import java.util.ArrayList;

import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.obj.ItemProperty;
import fr.wretchedlife.obj.item.ArmorItem;
import fr.wretchedlife.obj.item.ConsumableItem;
import fr.wretchedlife.obj.item.WeaponItem;

public class ItemGenerator {

	public static WeaponItem createGourdin( final Player player ) {
		WeaponItem gourdin = new WeaponItem(
			"Gourdin", 
			Constants.getTexture( ".\\img\\items\\club.png" ), 
			5, 
			null,
			4,
			7, 
			3, 
			3, 
			1, 
			1, 
			0, 
			0) {
			@Override
			public void wear() {
				player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
			}
			@Override
			public void unWear() {
				player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
			}
		};
		return gourdin;
	}
	
	public static WeaponItem createKnife( final Player player ) {
		WeaponItem knife = new WeaponItem(
			"Couteau usé", 
			Constants.getTexture( ".\\img\\items\\knife.png" ), 
			2, 
			null,
			5,
			6, 
			3, 
			3, 
			1, 
			0, 
			1, 
			0) {
			@Override
			public void wear() {
				player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
			}
			@Override
			public void unWear() {
				player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
			}
		};
		return knife;
	}
	
	public static WeaponItem createHammer( final Player player ) {
		WeaponItem hammer = new WeaponItem(
			"Marteau abîmé", 
			Constants.getTexture( ".\\img\\items\\hammer.png" ), 
			3, 
			null,
			5,
			8, 
			4, 
			4, 
			1, 
			1, 
			0, 
			0) {
			@Override
			public void wear() {
				player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
			}
			@Override
			public void unWear() {
				player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
			}
		};
		return hammer;
	}
	
	public static WeaponItem createHatchet( final Player player ) {
		WeaponItem hatchet = new WeaponItem("Hachette rouillé", 
			Constants.getTexture( ".\\img\\items\\hatchet.png" ), 
			4, 
			null, 
			6,
			9, 
			5, 
			5, 
			1, 
			1, 
			0, 
			0) {
			@Override
			public void wear() {
				player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
			}
			@Override
			public void unWear() {
				player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
			}
		};
		return hatchet;
	}
	
	public static WeaponItem createMagicAttributeHatchet( final Player player ) {
		
		ArrayList<ItemProperty> props = new ArrayList<ItemProperty>();
		ItemProperty prop = null;
		int randAttrNumber = Constants.getRandomBetween(1, 3);
		
		for(int i=0; i < randAttrNumber; i++ ) {
			int randProp = Constants.getRandomBetween(1, 3);
			int randPower = Constants.getRandomBetween(1, 2);
			
			switch(randProp) {
				case 1 : prop = new ItemProperty( ItemProperty.Code.Force, "+ "+randPower ); break;
				case 2 : prop = new ItemProperty( ItemProperty.Code.Agilité, "+ "+randPower ); break;
				case 3 : prop = new ItemProperty( ItemProperty.Code.Savoir, "+ "+randPower ); break;
			}
			
			props.add( prop );
		}
		
		WeaponItem magicalHatchet = new WeaponItem(
			"Hachette magique rouillé", 
			Constants.getTexture( ".\\img\\items\\hatchet.png" ), 
			4, 
			props, 
			7,
			10, 
			4, 
			4, 
			2, 
			1, 
			0, 
			1){
			@Override
			public void wear() {
				player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
				
				for(int i=0; i < this.getProperties().size() ; i++ ) {
					ItemProperty hatchetProp = this.getProperties().get(i);
					int hatchetPropPower = Integer.parseInt( hatchetProp.getValue().replace("+ ", "") );
					
					if(hatchetProp.getCode() == ItemProperty.Code.Force) {
						player.setStrengh( player.getStrengh() + hatchetPropPower );
					}
					else if(hatchetProp.getCode() == ItemProperty.Code.Agilité) {
						player.setAgility( player.getAgility() + hatchetPropPower );				
					}
					else if(hatchetProp.getCode() == ItemProperty.Code.Savoir) {
						player.setKnowledge( player.getKnowledge() + hatchetPropPower );
					}
				}
			}
			@Override
			public void unWear() {
				player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
				
				for(int i=0; i < this.getProperties().size() ; i++ ) {
					ItemProperty hatchetProp = this.getProperties().get(i);
					int hatchetPropPower = Integer.parseInt( hatchetProp.getValue().replace("+ ", "") );
					
					if(hatchetProp.getCode() == ItemProperty.Code.Force) {
						player.setStrengh( player.getStrengh() - hatchetPropPower );
					}
					else if(hatchetProp.getCode() == ItemProperty.Code.Agilité) {
						player.setAgility( player.getAgility() - hatchetPropPower );				
					}
					else if(hatchetProp.getCode() == ItemProperty.Code.Savoir) {
						player.setKnowledge( player.getKnowledge() - hatchetPropPower );
					}
				}
			}
		};
		return magicalHatchet;
	}
	
	public static WeaponItem createCleaver( final Player player ) {
		WeaponItem cleaver = new WeaponItem("Couperet rouillé", 
			Constants.getTexture( ".\\img\\items\\cleaver.png" ), 
			6, 
			null, 
			9,
			14, 
			8, 
			8, 
			3, 
			2, 
			0, 
			0) {
			@Override
			public void wear() {
				player.setItemMinDamage( player.getItemMinDamage() + this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() + this.getMaxDamage() );
			}
			@Override
			public void unWear() {
				player.setItemMinDamage( player.getItemMinDamage() - this.getMinDamage() );
				player.setItemMaxDamage( player.getItemMaxDamage() - this.getMaxDamage() );
			}
		};
		return cleaver;
	}
	
	public static ArmorItem createBasicVest( final Player player ) {
		ArmorItem a = new ArmorItem(
			"Veste de tissu", 
			Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_quiltedleather.png" ), 
			4, 
			null, 
			2, 
			ArmorItem.Type.CHEST,
			5, 
			5, 
			1, 
			0, 
			0, 
			0) {
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return a;
	}
	
	public static ArmorItem createBasicTrousers( final Player player ) {
		ArmorItem pantalon = new ArmorItem(
			"Pantalon de cuir léger", 
			Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_superbleathertrousers.png" ), 
			3, 
			null, 
			2, 
			ArmorItem.Type.LEGS,
			3, 
			3, 
			1, 
			0, 
			0, 
			0) {
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return pantalon;
	}
	
	public static ArmorItem createBasicBoots( final Player player ) {
		ArmorItem chausses = new ArmorItem(
			"Chausses en lambaux", 
			Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_soakedboots.png" ), 
			3, 
			null, 
			1, 
			ArmorItem.Type.FEET,
			2, 
			2, 
			1, 
			0, 
			0, 
			0){
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return chausses;
	}
	
	public static ArmorItem createBasicGloves( final Player player ) {
		ArmorItem mitaine = new ArmorItem(
			"Mitaine de cuir léger", 
			Constants.getTexture( ".\\img\\items\\assets\\Tw2_armor_wornleathergauntlets.png" ), 
			1, 
			null, 
			1, 
			ArmorItem.Type.HANDS,
			3, 
			3, 
			1, 
			0, 
			0, 
			0){
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return mitaine;
	}
	
	public static ArmorItem createBasicHelmet( final Player player ) {
		ArmorItem helmet = new ArmorItem(
			"Casque de chantier usé", 
			Constants.getTexture( ".\\img\\items\\buildinghelmet.png" ), 
			3, 
			null, 
			3, 
			ArmorItem.Type.HEAD,
			5, 
			5, 
			2, 
			0, 
			0, 
			0){
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return helmet;
	}
	
	public static ArmorItem createBasicBelt( final Player player ) {
		ArmorItem belt = new ArmorItem(
			"Ceinture usé de cuir", 
			Constants.getTexture( ".\\img\\items\\leatherbelt.png" ), 
			1, 
			null, 
			1, 
			ArmorItem.Type.BELT,
			3, 
			3, 
			1, 
			0, 
			0, 
			0){
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return belt;
	}
	
	public static ArmorItem createBasicBracer( final Player player ) {
		ArmorItem bracer = new ArmorItem(
			"Brassard de cuir léger", 
			Constants.getTexture( ".\\img\\items\\leatherbracer.png" ), 
			1, 
			null, 
			1, 
			ArmorItem.Type.ARM,
			3, 
			3, 
			1, 
			0, 
			0, 
			0){
			@Override
			public void wear() {
				player.setItemDefense( player.getItemDefense() + this.getDefense() );
			}
			@Override
			public void unWear() {
				player.setItemDefense( player.getItemDefense() - this.getDefense() );
			}
		};
		return bracer;
	}
	
	public static ConsumableItem createMeat( final Player player ) {
		ArrayList< ItemProperty > beanzProps = new ArrayList<ItemProperty>();
		ItemProperty beanzProp1 = new ItemProperty( ItemProperty.Code.Faim, "+ 40%");
		ItemProperty beanzProp2 = new ItemProperty( ItemProperty.Code.Soif, "+ 5%");
		beanzProps.add( beanzProp1 );
		beanzProps.add( beanzProp2 );
		
		ConsumableItem meat = new ConsumableItem(
			"Viande", 
			Constants.getTexture( ".\\img\\items\\meat.png" ), 
			2, 
			beanzProps 
		) {
			@Override
			public void use() {
				SoundFactory.playSound( SoundFactory.eatSoundFilePath );
				
				player.setHungerPercent( player.getHungerPercent() + 40 );
				player.setThirstPercent( player.getThirstPercent() + 5 );
				if(player.getHungerPercent() > 100) player.setHungerPercent(100);
				if(player.getThirstPercent() > 100) player.setThirstPercent(100);
			}
		};
		
		return meat;
	}
	
	public static ConsumableItem createWaterBottle( final Player player ) {
		ArrayList< ItemProperty > watterbottleProps = new ArrayList<ItemProperty>();
		ItemProperty watterbottleProp1 = new ItemProperty( ItemProperty.Code.Soif, "+ 60%");
		watterbottleProps.add( watterbottleProp1 );
		
		ConsumableItem waterbottle = new ConsumableItem(
			"Bouteille d'eau", 
			Constants.getTexture( ".\\img\\items\\waterbottle.png" ), 
			1, 
			watterbottleProps
		) {
			@Override
			public void use() {
				SoundFactory.playSound( SoundFactory.drinkSoundFilePath );
				
				player.setThirstPercent( player.getThirstPercent() + 60 );
				if(player.getThirstPercent() > 100) player.setThirstPercent(100);
			}
		};
		
		return waterbottle;
	}
	
	public static ConsumableItem createTunaCan( final Player player ) {
		ArrayList< ItemProperty > tunaCanProps = new ArrayList<ItemProperty>();
		ItemProperty tunaCanProp1 = new ItemProperty( ItemProperty.Code.Faim, "+ 25%");
		ItemProperty tunaCanProp2 = new ItemProperty( ItemProperty.Code.Soif, "+ 15%");
		tunaCanProps.add( tunaCanProp1 );
		tunaCanProps.add( tunaCanProp2 );
		
		ConsumableItem waterbottle = new ConsumableItem(
			"Thon en conserve", 
			Constants.getTexture( ".\\img\\items\\tunacan.png" ), 
			1, 
			tunaCanProps
		) {
			@Override
			public void use() {
				SoundFactory.playSound( SoundFactory.eatSoundFilePath );
				SoundFactory.playSound( SoundFactory.drinkSoundFilePath );
				
				player.setHungerPercent( player.getHungerPercent() + 25 );
				player.setThirstPercent( player.getThirstPercent() + 15 );
				if(player.getHungerPercent() > 100) player.setHungerPercent(100);
				if(player.getThirstPercent() > 100) player.setThirstPercent(100);
			}
		};
		
		return waterbottle;
	}
	
	public static ConsumableItem createBandage( final Player player ) {
		ArrayList< ItemProperty > bandageProps = new ArrayList<ItemProperty>();
		ItemProperty bandageProp = new ItemProperty( ItemProperty.Code.Vie, "+ 25");
		bandageProps.add( bandageProp );
		
		ConsumableItem waterbottle = new ConsumableItem(
			"Bandages", 
			Constants.getTexture( ".\\img\\items\\bandage.png" ), 
			1, 
			bandageProps
		) {
			@Override
			public void use() {
				player.setLifeRemain( player.getLifeRemain() + 25 );
				if(player.getLifeRemain() > player.getLife() ) player.setLifeRemain( player.getLife() );
			}
		};
		
		return waterbottle;
	}
	
	public static ConsumableItem createMedikit( final Player player ) {
		ArrayList< ItemProperty > medikitProps = new ArrayList<ItemProperty>();
		ItemProperty medikitProp = new ItemProperty( ItemProperty.Code.Vie, "+ 50");
		medikitProps.add( medikitProp );
		
		ConsumableItem waterbottle = new ConsumableItem(
			"Trousse de soin", 
			Constants.getTexture( ".\\img\\items\\medikit.png" ), 
			1, 
			medikitProps
		) {
			@Override
			public void use() {
				player.setLifeRemain( player.getLifeRemain() + 50 );
				if(player.getLifeRemain() > player.getLife() ) player.setLifeRemain( player.getLife() );
			}
		};
		
		return waterbottle;
	}
}
