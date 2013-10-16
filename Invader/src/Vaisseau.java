
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Vaisseau 
{
	private Carre body;
	private char Direction;
	private int nombreProjectile;
	private ArrayList<Projectile> projectilesJoueur;
	
	public Vaisseau(int nbProjectile)
	{
		body=new Carre(250,650,getTexture().getWidth(null),getTexture().getHeight(null));
		projectilesJoueur = new ArrayList<Projectile>();
		nombreProjectile = nbProjectile;
	}

	public Image getTexture()
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(".\\img\\vaisseau.jpg"));
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}
		return (Image)img;
	}
	
	public void Réinitialiser()
	{
		this.Direction='X';
		body=new Carre(250,650,getTexture().getWidth(null),getTexture().getHeight(null));
		this.setProjectilesJoueur(new ArrayList<Projectile>());
		this.setNombreProjectile(1);
	}
	
	public ArrayList<Projectile> getProjectilesJoueur()
	{
		return this.projectilesJoueur;
	}
	public void setProjectilesJoueur(ArrayList<Projectile> projectiles) 
	{
		this.projectilesJoueur = projectiles;
	}
	
	public Carre getJoueurObject()
	{
		return this.body;
	}
	
	public char getDirection() 
	{
		return Direction;
	}

	public void setDirection(char direction) 
	{
		Direction = direction;
	}
	
	public int getNombreProjectile()
	{
		return this.nombreProjectile;
	}
	
	public void setNombreProjectile(int n)
	{
		this.nombreProjectile = n;
	}
	
	public void DeplacerJoueur() throws InterruptedException
	{		
		switch(this.getDirection())
		{	
			case 'G':
			{
				if(this.getJoueurObject().getX()!=0)
				{
					this.getJoueurObject().setX(this.getJoueurObject().getX()-50);
				}			
				
				this.setDirection('X');
			}break;
			case 'D':
			{		
				if(this.getJoueurObject().getX()!=600)
				{
					
					this.getJoueurObject().setX(this.getJoueurObject().getX()+50);
				}
				
				this.setDirection('X');
			}break;
		}
	}
	
	public void DeplacerProjectileJoueur()
	{
		if(this.getProjectilesJoueur().size() > 0)
		{
			for(int i=0;i<this.getProjectilesJoueur().size();i++)
			{
				Projectile p = this.getProjectilesJoueur().get(i);
				
				if(p.getProjectileObject().getY()!=(-50))
				{
					p.getProjectileObject().setY(p.getProjectileObject().getY()-p.getProjectileObject().getH());
				}
				else this.getProjectilesJoueur().remove(i);
			}
		}
	}
}
