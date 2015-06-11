package fr.wretchedlife.core;

import fr.wretchedlife.ui.Window;

public class GameThread implements Runnable 
{
	public GameThread() {}

	public void run()
	{
		Window w = new Window();
		
		while(true)
		{
			try 
			{		
				w.render();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
