
public class Carre
{
	private int X,Y,W,H;
	
	public Carre(int X,int Y,int Epaisseur, int Hauteur)
	{
		this.X=X;
		this.Y=Y;
		this.W=Epaisseur;
		this.H=Hauteur;
    }

	public int getX()
	{
		return X;
	}
	public int getY()
	{
		return Y;
	}
	public int getW() 
	{
		return W;
	}

	public int getH() 
	{
		return H;
	}


	public void setX(int X)
	{
		this.X=X;
	}
	public void setY(int Y)
	{
		this.Y=Y;
	}
	public void setW(int w) 
	{
		W = w;
	}
	public void setH(int h) 
	{
		H = h;
	}
}
