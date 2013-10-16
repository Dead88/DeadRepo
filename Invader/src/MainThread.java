public class MainThread implements Runnable 
{
	private int vitesse;
	
	public MainThread(int vitesse) 
	{
		this.vitesse = vitesse;
	}

	public int getVitesse() 
	{
		return vitesse;
	}
	public void setVitesse(int vitesse) 
	{
		this.vitesse = vitesse;
	}

	@SuppressWarnings("static-access")
	public void run()
	{
		Fenetre f = new Fenetre();
		
		while(true)
		{
			try 
			{		
				Thread.currentThread().sleep(getVitesse());
				f.Jouer(this);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
