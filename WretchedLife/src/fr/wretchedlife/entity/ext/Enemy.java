package fr.wretchedlife.entity.ext;

import fr.wretchedlife.Constants;
import fr.wretchedlife.entity.Entity;
import fr.wretchedlife.factory.ItemFactory;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.generator.ItemGenerator;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.item.ContainerItem;
import fr.wretchedlife.ui.panel.GamePanel;
import fr.wretchedlife.ui.panel.InfoPanel;

public class Enemy extends Entity {
	
	private int level;
	
	private int life;
	private int lifeRemain;
	private int itemMinDamage;
	private int itemMaxDamage;
	private int itemDefense;
	private int experienceToEarn;
	
	private ContainerItem lootContainer;
	
	public Enemy() {
		super();
	}
	
	public void generateLootContainer( Player player ) {
		int inventorySize = Constants.getRandomBetween( Constants.minItemsPerEnemyChest, Constants.maxItemsPerEnemyChest );
		
		if( inventorySize == 0 ) {
			setLootContainer( null );
			return;
		}
		
		ContainerItem lootChest = ItemGenerator.createEmptyChest( player );
		
		for(int i = 0; i < inventorySize; i++) {
			Item item = ItemFactory.getRandomItem( player );
			lootChest.getInventory().add( item );
		}
		
		setLootContainer( lootChest );
	}
	
	public void move( Area currentArea, Area destinationArea ) {
		currentArea.setEntity( null );
		destinationArea.setEntity( this );
	}
	
	public void moveToRandomArea ( GamePanel gamePanel ) {
			
		Area enemyArea = gamePanel.getEnemyArea( getId() );
		Area destinationArea = null;
		
		int rand = Constants.getRandomBetween(1, 4);
		switch(rand) {
			case 1 : destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() - getTexture().getIconWidth(), enemyArea.getY() ); break;
			case 2 : destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() , enemyArea.getY() - getTexture().getIconHeight() ); break;
			case 3 : destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() + getTexture().getIconWidth(), enemyArea.getY() ); break;
			case 4 : destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() , enemyArea.getY() + getTexture().getIconHeight() ); break;
		}
			
		if(destinationArea == null) return;
		if(destinationArea.getType() == Area.Type.GROUND_AREA && destinationArea.getEntity() == null ) {
			move( enemyArea, destinationArea );
		}
	}
	
	public void moveToPlayer( GamePanel gamePanel ) {
		
		Area enemyArea = gamePanel.getEnemyArea( getId() );
		Area playerArea = gamePanel.getSinglePlayerGame().getPlayerArea();
		Area destinationArea = null;
		
		if( playerArea.getX() < enemyArea.getX() ) {
			
			destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() - getTexture().getIconWidth(), enemyArea.getY() );
			if(destinationArea != null && destinationArea.getType() == Area.Type.GROUND_AREA && destinationArea.getEntity() == null ) {
				move( enemyArea, destinationArea );
				return;
			}
		}
		if( playerArea.getX() > enemyArea.getX() ) {
			
			destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() + getTexture().getIconWidth(), enemyArea.getY() );
			if(destinationArea != null && destinationArea.getType() == Area.Type.GROUND_AREA && destinationArea.getEntity() == null ) {
				move( enemyArea, destinationArea );
				return;
			}
		}
		if( playerArea.getY() < enemyArea.getY() ) {
			
			destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() , enemyArea.getY() - getTexture().getIconHeight() );
			if(destinationArea != null && destinationArea.getType() == Area.Type.GROUND_AREA && destinationArea.getEntity() == null ) {
				move( enemyArea, destinationArea );
				return;
			}
		}
		if( playerArea.getY() > enemyArea.getY() ){
			
			destinationArea = gamePanel.getAreaByCoordinate( enemyArea.getX() , enemyArea.getY() + getTexture().getIconHeight() );
			if(destinationArea != null && destinationArea.getType() == Area.Type.GROUND_AREA && destinationArea.getEntity() == null ) {
				move( enemyArea, destinationArea );
				return;
			}
		}
	}
	
	public void attackPlayer( InfoPanel infoPanel, Player player ) {
		int playerLife = player.getLifeRemain();
		int damage = Constants.getRandomBetween( getItemMinDamage(), getItemMaxDamage() ) - player.getItemDefense();
		
		SoundFactory.playSound( SoundFactory.attackSoundFilePath );
		
		if(damage > 0) {
			infoPanel.log( this.getName() + " dealed " + damage +" dmg to you" );
			player.setLifeRemain( playerLife - damage );
			
			if( player.getLifeRemain() <= 0) {
				infoPanel.log( "Killed by " + this.getName() );
			}
		}
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLifeRemain() {
		return lifeRemain;
	}

	public void setLifeRemain(int lifeRemain) {
		this.lifeRemain = lifeRemain;
	}

	public int getItemMinDamage() {
		return itemMinDamage;
	}

	public void setItemMinDamage(int itemMinDamage) {
		this.itemMinDamage = itemMinDamage;
	}

	public int getItemMaxDamage() {
		return itemMaxDamage;
	}

	public void setItemMaxDamage(int itemMaxDamage) {
		this.itemMaxDamage = itemMaxDamage;
	}

	public int getItemDefense() {
		return itemDefense;
	}

	public void setItemDefense(int itemDefense) {
		this.itemDefense = itemDefense;
	}
	
	public int getExperienceToEarn() {
		return experienceToEarn;
	}

	public void setExperienceToEarn(int experienceToEarn) {
		this.experienceToEarn = experienceToEarn;
	}

	public ContainerItem getLootContainer() {
		return lootContainer;
	}

	public void setLootContainer(ContainerItem lootContainer) {
		this.lootContainer = lootContainer;
	}
}
