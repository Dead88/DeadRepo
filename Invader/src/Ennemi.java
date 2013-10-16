import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ennemi
{
	private Carre body;
	private Projectile projectileEnnemi;
	private int compteurTir;
	private int delaiTir;
	private boolean estMort;

	public Ennemi(int x, int y)
	{
		body = new Carre(x,y,getTexture().getWidth(null),getTexture().getHeight(null));
		this.setCompteurTir(0);
		this.setDelaiTir(CommonMethod.getNouveauDelai(30, 70));
		this.setProjectileEnnemi(null);
		this.tuer(false);
	}
	
	public Image getTexture()
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(".\\img\\ennemi.jpg"));
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}
		return (Image)img;
	}
	
	public Projectile getProjectileEnnemi() 
	{
		return projectileEnnemi;
	}

	public void setProjectileEnnemi(Projectile coupDeFeuEnnemi) 
	{
		this.projectileEnnemi = coupDeFeuEnnemi;
	}
	
	public Carre getEnnemiObject()
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
	

	public boolean isMort() 
	{
		return estMort;
	}
	public void tuer(boolean estMort) 
	{
		this.estMort = estMort;
	}
}
