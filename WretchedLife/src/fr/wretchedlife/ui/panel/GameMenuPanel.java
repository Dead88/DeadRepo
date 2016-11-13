package fr.wretchedlife.ui.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.Game;
import fr.wretchedlife.entity.ext.Enemy;
import fr.wretchedlife.entity.ext.Player;
import fr.wretchedlife.entity.ext.RegionEntrance;
import fr.wretchedlife.factory.SoundFactory;
import fr.wretchedlife.map.Area;
import fr.wretchedlife.obj.Item;
import fr.wretchedlife.obj.ItemProperty;
import fr.wretchedlife.obj.ext.ArmorItem;
import fr.wretchedlife.obj.ext.ConsumableItem;
import fr.wretchedlife.obj.ext.ContainerItem;
import fr.wretchedlife.obj.ext.WeaponItem;
import fr.wretchedlife.ui.Window;

public class GameMenuPanel extends JPanel {

	private static final long serialVersionUID = -1597401836787382841L;
	private Window window;
	private Game game;
	private Player player;
	private InfoPanel infoPanel;
	
	public InfoPanel getInfoPanel() {return infoPanel;}
	public void setInfoPanel(InfoPanel infoPanel) {this.infoPanel = infoPanel;}

	public GameMenuPanel( Window _window, Game _game ) {
		this.window = _window;
		this.game = _game;
		this.player = game.getPlayer();
		this.setLayout( new GridLayout(1,3) );
		this.setPreferredSize( new Dimension( window.getWidth(), 250 ));
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout( new GridLayout(2,1) );
		playerPanel.add( new PlayerInfoPanel( window, player ) );
		playerPanel.add( new PlayerInventoryPanel( this, player ) );
		
		this.add( playerPanel );
		this.add( new PlayerEquipementPanel( this, player ) );
		
		infoPanel = new InfoPanel();
		this.add( infoPanel );
	}
	
	public void clearPreviewInfoPanel() {
		infoPanel.getPreviewPanel().removeAll();
	}
	
	public void displayAreaInfos( final GamePanel gamePanel, final Area area ) {
		
		JLabel areaInfoLabel = new JLabel();
		areaInfoLabel.setForeground(Constants.goldColor);
		
		JButton attackButton = null;
		
		areaInfoLabel.setText( area.getType().name()+" at "+area.getX()+" "+area.getY() );
		
		if(area.getItem() != null) {
			areaInfoLabel.setIcon( area.getItem().getTexture() );
			areaInfoLabel.setText( getItemInfoHtml( area.getItem() ) );
		}
		if( area.getEntity() != null) {
			if(area.getEntity() instanceof Enemy) {
				final Enemy e = (Enemy) area.getEntity();
				areaInfoLabel.setIcon( e.getTexture() );
				areaInfoLabel.setText( getEnemyInfoHtml( e ) );
				
				Area playerArea = game.getPlayerArea();
				
				if( 
					area.getX() >= playerArea.getX() - player.getTexture().getIconWidth()
					&& area.getX() <= playerArea.getX() + player.getTexture().getIconWidth() 
					&& area.getY() >= playerArea.getY() - player.getTexture().getIconHeight()
					&& area.getY() <= playerArea.getY() + player.getTexture().getIconHeight() 
				) {
					attackButton = new JButton("Attaquer");
					attackButton.addActionListener( new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							game.getCurrentRegion().attackEnemy( area, gamePanel );
						}
					});
				}
			}
			else if(area.getEntity() instanceof RegionEntrance) {
				final RegionEntrance r = (RegionEntrance) area.getEntity();
				areaInfoLabel.setText( getEntranceInfoHtml( r ) );
			}
		}
		
		infoPanel.getPreviewPanel().add( areaInfoLabel );
		if( attackButton != null ) infoPanel.getPreviewPanel().add( attackButton );
	}
	
	public void displayInventoryItemInfos( final Item item ) {
		final GameMenuPanel _this = this;
		
		JLabel itemIcon = new JLabel( item.getTexture() );
		infoPanel.getPreviewPanel().add( itemIcon );
		
		JLabel itemInfoLabel = new JLabel( getItemInfoHtml( item ) );
		itemInfoLabel.setForeground(Constants.goldColor);
		infoPanel.getPreviewPanel().add( itemInfoLabel );
		
		if( item instanceof ConsumableItem ){
			JButton itemUseButton = new JButton("Utiliser");
			itemUseButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					((ConsumableItem) item).use();
					infoPanel.log( "Used " + item.getName() );
					player.setTransportedWeight( player.getTransportedWeight() - item.getWeight() );
					player.getInventory().remove( item );
					_this.clearPreviewInfoPanel();
				}
			});
			infoPanel.getPreviewPanel().add( itemUseButton );
		}
		else if( item instanceof ContainerItem ) {
			JButton itemUseButton = new JButton("Ouvrir");
			itemUseButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ContainerItem container = (ContainerItem) item;
					
					if( ( player.getInventory().size() + container.getInventory().size() ) - 1 > player.getInventoryMaxSize() ) {
						SoundFactory.playSound( SoundFactory.impossibleFilePath );
						return;
					}
					
					for (int i = 0; i < container.getInventory().size(); i++) {
						Item containerItem = container.getInventory().get(i);
						player.getInventory().add( containerItem );
					}

					container.setInventory( null );
					player.setTransportedWeight( player.getTransportedWeight() - container.getWeight() );
					player.getInventory().remove( container );
					_this.clearPreviewInfoPanel();
				}
			});
			infoPanel.getPreviewPanel().add( itemUseButton );
		}
		else if( item instanceof WeaponItem || item instanceof ArmorItem ){
			JButton itemUseButton = new JButton("Equiper");
			itemUseButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if( !player.hasEnoughSkillToEquipItem( item ) ) {
						SoundFactory.playSound( SoundFactory.impossibleFilePath );
						return;
					}
					
					SoundFactory.playSound( SoundFactory.equipSoundFilePath );
					
					if( item instanceof WeaponItem ) {
						
						if( player.getLeftHandWeaponItem() != null && player.getRightHandWeaponItem() != null) {
							SoundFactory.playSound( SoundFactory.impossibleFilePath );
							return;
						}
						if( player.getLeftHandWeaponItem() == null )
							player.setLeftHandWeaponItem( (WeaponItem) item );
						else if( player.getRightHandWeaponItem() == null ){
							player.setRightHandWeaponItem( (WeaponItem) item );
						}
						
						((WeaponItem) item).wear( player );
					}	
					else if( item instanceof ArmorItem ) {
						
						ArmorItem armorItem = (ArmorItem) item;
						if( armorItem.getType() == ArmorItem.Type.HEAD) {
							if( player.getHeadArmor() != null) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							player.setHeadArmor( armorItem );
						}
						else if( armorItem.getType() == ArmorItem.Type.SHOULDER) {
							if( player.getLeftShoulderArmor() != null &&  player.getRightShoulderArmor() != null ) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							if(player.getLeftShoulderArmor() == null)
								player.setLeftShoulderArmor( armorItem );
							if(player.getRightShoulderArmor() == null)
								player.setRightShoulderArmor( armorItem );
						}
						else if( armorItem.getType() == ArmorItem.Type.ARM) {
							if( player.getArmArmor() != null ) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							if(player.getArmArmor() == null)
								player.setArmArmor( armorItem );
						}
						else if( armorItem.getType() == ArmorItem.Type.HANDS) {
							if( player.getHandsArmor() != null) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							player.setHandsArmor( armorItem );
						}
						else if( armorItem.getType() == ArmorItem.Type.CHEST) {
							if( player.getChestArmor() != null) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							player.setChestArmor( armorItem );
						}
						else if( armorItem.getType() == ArmorItem.Type.BELT) {
							if( player.getBeltArmor() != null) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							player.setBeltArmor( armorItem );						
						}
						else if( armorItem.getType() == ArmorItem.Type.LEGS) {
							if( player.getLegsArmor() != null) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							player.setLegsArmor( armorItem );
						}
						else if( armorItem.getType() == ArmorItem.Type.FEET) {
							if( player.getFeetArmor() != null) {
								SoundFactory.playSound( SoundFactory.impossibleFilePath );
								return;
							}
							player.setFeetArmor( armorItem );
						}
						
						((ArmorItem) item).wear( player );
					}
					
					player.getInventory().remove( item );
					_this.clearPreviewInfoPanel();
				}
			});
			infoPanel.getPreviewPanel().add( itemUseButton );
		}
		
		if( window.getCurrentPanel() instanceof GamePanel) {
			final Area playerArea = game.getPlayerArea();
				
			JButton itemDropButton = new JButton("Jeter");
			itemDropButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(playerArea.getType() == Area.Type.GROUND_AREA && playerArea.getItem() == null ) {
						playerArea.setItem( item );
						player.setTransportedWeight( player.getTransportedWeight() - item.getWeight() );
						player.getInventory().remove( item );
						_this.clearPreviewInfoPanel();
					}
					else {
						SoundFactory.playSound( SoundFactory.impossibleFilePath );
						return;
					}
				}
			});
			infoPanel.getPreviewPanel().add( itemDropButton );
		}
	}
	
	public void displayEquipedItemInfos( final Item item ) {
		final GameMenuPanel _this = this;
		
		JLabel itemIcon = new JLabel( item.getTexture() );
		infoPanel.getPreviewPanel().add( itemIcon );
		
		JLabel itemInfoLabel = new JLabel( getItemInfoHtml( item ) );
		itemInfoLabel.setForeground(Constants.goldColor);
		infoPanel.getPreviewPanel().add( itemInfoLabel );
		
		JButton itemUseButton = new JButton("Enlever");
		itemUseButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( item instanceof WeaponItem ) {
					if( player.getLeftHandWeaponItem() != null && item.getId().equals( player.getLeftHandWeaponItem().getId() ) ){
						player.setLeftHandWeaponItem( null );
					}
					else if( player.getRightHandWeaponItem() != null && item.getId().equals( player.getRightHandWeaponItem().getId() ) ){
						player.setRightHandWeaponItem( null );
					}
					((WeaponItem) item).unWear( player );
				}
				else if( item instanceof ArmorItem ) {
					if( player.getLeftShoulderArmor() != null && item.getId().equals( player.getLeftShoulderArmor().getId() ) ) {
						player.setLeftShoulderArmor( null );
					}
					if( player.getHeadArmor() != null && item.getId().equals( player.getHeadArmor().getId() ) ) {
						player.setHeadArmor( null );
					}
					if( player.getRightShoulderArmor() != null && item.getId().equals( player.getRightShoulderArmor().getId() ) ) {
						player.setRightShoulderArmor( null );
					}
					if( player.getArmArmor() != null && item.getId().equals( player.getArmArmor().getId() ) ) {
						player.setArmArmor( null );
					}
					if( player.getChestArmor() != null && item.getId().equals( player.getChestArmor().getId() ) ) {
						player.setChestArmor( null );
					}
					if( player.getHandsArmor() != null && item.getId().equals( player.getHandsArmor().getId() ) ) {
						player.setHandsArmor( null );
					}
					if( player.getBeltArmor() != null && item.getId().equals( player.getBeltArmor().getId() ) ) {
						player.setBeltArmor( null );
					}
					if( player.getLegsArmor() != null && item.getId().equals( player.getLegsArmor().getId() ) ) {
						player.setLegsArmor( null );
					}
					if( player.getFeetArmor() != null && item.getId().equals( player.getFeetArmor().getId() ) ) {
						player.setFeetArmor( null); 
					}
					((ArmorItem) item).unWear( player );
				}
				
				SoundFactory.playSound( SoundFactory.storeItemSoundFilePath );
				
				player.getInventory().add( item );
				_this.clearPreviewInfoPanel();
			}
		});
		infoPanel.getPreviewPanel().add( itemUseButton );
	}
	
	public static String getItemInfoHtml( Item item ) {
		
		String itemInfo = "<html><u>"+ item.getName() + "</u><br />";
		itemInfo += "Poids : " + item.getWeight()+"<br />";
		
		if( item instanceof WeaponItem ) {
			itemInfo += "Durabilité : " + ((WeaponItem) item).getDurabilityRemain()+" / "+((WeaponItem) item).getDurability()+"<br />";
			itemInfo += "Dégâts : " + ((WeaponItem) item).getMinDamage() +" à "+((WeaponItem) item).getMaxDamage()+"<br />";
			itemInfo += "Niveau requis : " + ((WeaponItem) item).getRequiredLevel()+"<br />";
			itemInfo += "Force requise : " + ((WeaponItem) item).getRequiredStrengh()+"<br />";
			itemInfo += "Agilité requise : " + ((WeaponItem) item).getRequiredAgility()+"<br />";
			itemInfo += "Savoir requis : " + ((WeaponItem) item).getRequiredKnowledge()+"<br />";
		}
		else if( item instanceof ArmorItem ) {
			itemInfo += "Durabilité : " + ((ArmorItem) item).getDurabilityRemain()+" / "+((ArmorItem) item).getDurability()+"<br />";
			itemInfo += "Armure : " + ((ArmorItem) item).getDefense()+"<br />";
			itemInfo += "Niveau requis : " + ((ArmorItem) item).getRequiredLevel()+"<br />";
			itemInfo += "Force requise : " + ((ArmorItem) item).getRequiredStrengh()+"<br />";
			itemInfo += "Agilité requise : " + ((ArmorItem) item).getRequiredAgility()+"<br />";
			itemInfo += "Savoir requis : " + ((ArmorItem) item).getRequiredKnowledge()+"<br />";
		}
		else if( item instanceof ContainerItem ) {
			itemInfo += "Nombre d'objets : " + ((ContainerItem) item).getInventory().size()+"<br />";
			for (int i = 0; i < ((ContainerItem) item).getInventory().size(); i++) {
				itemInfo += "- "+ ((ContainerItem) item).getInventory().get(i).getName() + "<br />";
			}
		}
		
		if(item.getProperties() != null) {
			for(int i = 0; i < item.getProperties().size(); i++){
				ItemProperty prop = item.getProperties().get(i);
				itemInfo += prop.getValue() + " " + prop.getCode().name() + "<br />";
			}
		}
		itemInfo += "</html>";
		
		return itemInfo;
	}
	
	public static String getEnemyInfoHtml( Enemy enemy ) {
		String enemyInfo = "<html><u>"+ enemy.getName() + "</u><br />";
		enemyInfo += "Niveau " + enemy.getLevel() + "<br />";
		enemyInfo += "Vie " + enemy.getLifeRemain() + " / "+enemy.getLife()+"<br />";
		enemyInfo += "Dégâts : " + enemy.getItemMinDamage() +" à "+enemy.getItemMaxDamage()+"<br />";
		enemyInfo += "Armure : " + enemy.getItemDefense()+"<br />";
		
		enemyInfo += "</html>";
		
		return enemyInfo;
	}
	
	public static String getEntranceInfoHtml( RegionEntrance regionEntrance ) {
		String regionEntranceInfo = "<html><u>"+ regionEntrance.getName() +"</u><br /></html>";
		return regionEntranceInfo;
	}
}
