package fr.wretchedlife.core.utils;

import javax.swing.ImageIcon;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.map.GameMap;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;
import fr.wretchedlife.obj.item.ArmorItem;
import fr.wretchedlife.obj.item.ConsumableItem;
import fr.wretchedlife.obj.item.WeaponItem;

public class XmlTools {
	
	private static XStream x;
	
	private static XStream getInstance() {
		if( x == null ) {
			x = new XStream( new DomDriver() );
			x.alias("GameMap", GameMap.class);
			x.alias("Area", Area.class);
			x.alias("Entity", Entity.class);
			x.alias("Enemy", Enemy.class);
			x.alias("Player", Player.class);
			x.alias("Item", Item.class);
			x.alias("ItemProperty", ItemProperty.class);
			x.alias("ArmorItem", ArmorItem.class);
			x.alias("WeaponItem", WeaponItem.class);
			x.alias("ConsumableItem", ConsumableItem.class);
			x.alias("RegionEntrance", RegionEntrance.class);
			x.alias("ImageIcon", ImageIcon.class);
		}
		return x;
	}
	
	public static String getXMLStringFromObject( Object o ) {
		return getInstance().toXML( o );
	}
	
	public static <T> T getObjectFromXMLString( String s ) {
		return ( T ) getInstance().fromXML( s );
	}
}
