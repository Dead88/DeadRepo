import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlateauJeu extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Vaisseau joueur;
	private FlotteEnnemis flotteEnnemis;
	private Soucoupe soucoupe;
	
	public PlateauJeu(Vaisseau Joueur, FlotteEnnemis flotteEnnemis, Soucoupe soucoupe)
	{
		this.joueur = Joueur;
		this.flotteEnnemis = flotteEnnemis;
		this.soucoupe = soucoupe;
	}
	
	public Image getTexturePlateau()
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(".\\img\\plateau.jpg"));
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}
		return (Image)img;
	}
	
	public Vaisseau getJoueur()
	{
		return this.joueur;
	}
	
	public void paintComponent(Graphics G)
	{	
		// Plateau
		G.drawImage(getTexturePlateau(),0,0,this.getWidth(),this.getHeight(),null);
		// Joueur
		G.drawImage(joueur.getTexture(),
				joueur.getJoueurObject().getX(),
				joueur.getJoueurObject().getY(),
				joueur.getJoueurObject().getW(),
				joueur.getJoueurObject().getH(),
				null);
		
		if(joueur.getProjectilesJoueur().size() > 0)
		{
			for(int h=0;h<joueur.getProjectilesJoueur().size();h++)
			{
				Projectile p = joueur.getProjectilesJoueur().get(h);
				
				G.drawImage(p.getTexture(false),
						p.getProjectileObject().getX(),
						p.getProjectileObject().getY(),
						p.getProjectileObject().getW(),
						p.getProjectileObject().getH(),
						null);
			}
		}
		
		if(soucoupe!=null)
		{		
			if(soucoupe.getProjectileSoucoupe()!=null && soucoupe.getProjectileSoucoupe().getProjectileObject()!=null)
			{
				G.drawImage(soucoupe.getProjectileSoucoupe().getTexture(true),
						soucoupe.getProjectileSoucoupe().getProjectileObject().getX(),
						soucoupe.getProjectileSoucoupe().getProjectileObject().getY(),
						soucoupe.getProjectileSoucoupe().getProjectileObject().getW(),
						soucoupe.getProjectileSoucoupe().getProjectileObject().getH(),
						null);
			}
			
			if(!soucoupe.isMort() && soucoupe.getSoucoupeObject()!=null)
			{						
				G.drawImage(soucoupe.getTexture(),
						soucoupe.getSoucoupeObject().getX(),
						soucoupe.getSoucoupeObject().getY(),
						soucoupe.getSoucoupeObject().getW(),
						soucoupe.getSoucoupeObject().getH(),
						null);
				
				G.drawImage(soucoupe.getTexturePV(soucoupe.getPointVie()),
						soucoupe.getSoucoupeObject().getX(),
						soucoupe.getSoucoupeObject().getY(),
						50,
						5,
						null);
			}
		}

		for(int i=0;i<flotteEnnemis.getFlotte().size();i++)
		{
			Ennemi unMechant = flotteEnnemis.getFlotte().get(i);
			
			if(!unMechant.isMort() && unMechant.getEnnemiObject()!=null)
			{
				G.drawImage(unMechant.getTexture(),
						unMechant.getEnnemiObject().getX(),
						unMechant.getEnnemiObject().getY(),
						unMechant.getEnnemiObject().getW(),
						unMechant.getEnnemiObject().getH(),
						null);
			}
			
			if(unMechant != null && unMechant.getProjectileEnnemi()!=null && unMechant.getProjectileEnnemi().getProjectileObject()!=null)
			{
				G.drawImage(unMechant.getProjectileEnnemi().getTexture(true),
						unMechant.getProjectileEnnemi().getProjectileObject().getX(),
						unMechant.getProjectileEnnemi().getProjectileObject().getY(),
						unMechant.getProjectileEnnemi().getProjectileObject().getW(),
						unMechant.getProjectileEnnemi().getProjectileObject().getH(),
						null);
			}
		}
	}

	public FlotteEnnemis getFlotteEnnemis() 
	{
		return flotteEnnemis;
	}
	public void setFlotteEnnemis(FlotteEnnemis flotteEnnemis) 
	{
		this.flotteEnnemis = flotteEnnemis;
	}
	
	public Soucoupe getSoucoupe() 
	{
		return soucoupe;
	}
	public void setSoucoupe(Soucoupe soucoupe) 
	{
		this.soucoupe = soucoupe;
	}
}
