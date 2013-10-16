import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Projectile 
{
	private Carre body;
	
	public Projectile(int x,int y,boolean isEnnemi)
	{
		if(isEnnemi)
		{
			body=new Carre(x,
					y+getTexture(true).getHeight(null),
					getTexture(true).getWidth(null)
					,getTexture(true).getHeight(null));
		}
		else
		{
			body=new Carre(x,
					y-getTexture(false).getHeight(null),
					getTexture(false).getWidth(null)
					,getTexture(false).getHeight(null));
		}
	}
	
	public Image getTexture(boolean isEnnemi)
	{
		BufferedImage img = null;
		try
		{
			if(isEnnemi)
			{
				img = ImageIO.read(new File(".\\img\\projectile2.jpg"));
			}
			else img = ImageIO.read(new File(".\\img\\projectile1.jpg"));
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}
		return (Image)img;
	}
	
	public Carre getProjectileObject()
	{
		return this.body;
	}
}
