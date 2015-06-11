package fr.wretchedlife.entity.ext;

import javax.swing.ImageIcon;

import fr.wretchedlife.entity.Entity;

public abstract class RegionEntrance extends Entity {

	private String regionId;

	public RegionEntrance() {
		super();
	}
	public RegionEntrance(String name, ImageIcon texture, String regionId) {
		super(name, texture);
		this.regionId = regionId;
	}
	
	public abstract void use(); 
	
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
}
