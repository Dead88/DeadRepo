import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Soucoupe
{
	private Carre body;
	private char direction;
	private Projectile projectileSoucoupe;
	private int compteurTir;
	private int delaiTir;
	private int pointVie;
	boolean mort;

	public Soucoupe(int x, int y)
	{
		body = new Carre(x,y,getTexture().getWidth(null),getTexture().getHeight(null));
		this.setCompteurTir(0);
		this.setDelaiTir(CommonMethod.getNouveauDelai(1, 1));
		this.setProjectileSoucoupe(null);
		this.setMort(false);
		this.setPointVie(3);
		this.setDirection('G');
	}
	
	public void DeplacerSoucoupe()
	{
		if(this.getDirection()=='G' && this.getSoucoupeObject().getX()!=0)
		{
			this.getSoucoupeObject().setX(this.getSoucoupeObject().getX()-20);
		}
		else if (this.getDirection()=='B' && this.getSoucoupeObject().getY()!=240)
		{
			this.getSoucoupeObject().setY(this.getSoucoupeObject().getY()+20);
		}
		else if (this.getDirection()=='D' && this.getSoucoupeObject().getX()!=600)
		{
			this.getSoucoupeObject().setX(this.getSoucoupeObject().getX()+20);
		}
		else if (this.getDirection()=='H' && this.getSoucoupeObject().getY()!=160)
		{
			this.getSoucoupeObject().setY(this.getSoucoupeObject().getY()-20);
		}
		else if(this.getSoucoupeObject().getX()==0 && this.getSoucoupeObject().getY()==160)this.setDirection('B');
		else if(this.getSoucoupeObject().getY()==240 && this.getSoucoupeObject().getX()==0)this.setDirection('D');
		else if(this.getSoucoupeObject().getX()==600 && this.getSoucoupeObject().getY()==240)this.setDirection('H');
		else if(this.getSoucoupeObject().getY()==160 && this.getSoucoupeObject().getX()==600)this.setDirection('G');
	}
	
	public Image getTexture()
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(".\\img\\soucoupe.jpg"));
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}
		return (Image)img;
	}
	
	public void DeplacerProjectileSoucoupe()
	{	
		if(!this.isMort())this.setCompteurTir(this.getCompteurTir()+1);

		if(this.getCompteurTir()==this.getDelaiTir())
		{
			this.setCompteurTir(0);
			this.setDelaiTir(CommonMethod.getNouveauDelai(1,1));
					
			if(this.getProjectileSoucoupe()==null && !this.isMort() && this.getDirection()!='H' && this.getDirection()!='B')
			{
				Projectile p = new Projectile(this.getSoucoupeObject().getX()+20, this.getSoucoupeObject().getY()+10, true);
				this.setProjectileSoucoupe(p);
			}
		}
		
		if(this.getProjectileSoucoupe()!=null)
		{
			Projectile p = this.getProjectileSoucoupe();

			if(p.getProjectileObject().getY()<685)
			{
				p.getProjectileObject().setY(p.getProjectileObject().getY()+p.getProjectileObject().getH());
			}
			else 
			{
				this.setProjectileSoucoupe(null);
			}
		}
	}
	
	public Image getTexturePV(int i)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(".\\img\\soucoupe_pv"+i+".jpg"));
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}
		return (Image)img;
	}
	
	public Projectile getProjectileSoucoupe() 
	{
		return projectileSoucoupe;
	}

	public void setProjectileSoucoupe(Projectile projectileSoucoupe) 
	{
		this.projectileSoucoupe = projectileSoucoupe;
	}
	
	public Carre getSoucoupeObject()
	{
		return this.body;
	}
	
	public int getDelaiTir() 
	{
		return delaiTir;
	}
	public void setDelaiTir(int delaiTir) 
	{
		this.delaiTir = delaiTir;
	}
	
	public int getCompteurTir() 
	{
		return compteurTir;
	}
	public void setCompteurTir(int compteurTir) 
	{
		this.compteurTir = compteurTir;
	}
	
	
	public int getPointVie() 
	{
		return pointVie;
	}
	public void setPointVie(int pointVie) 
	{
		this.pointVie = pointVie;
	}
	
	public char getDirection() 
	{
		return direction;
	}
	public void setDirection(char direction) 
	{
		this.direction = direction;
	}
	
	public boolean isMort() 
	{
		return mort;
	}
	public void setMort(boolean mort) 
	{
		this.mort = mort;
	}

}
