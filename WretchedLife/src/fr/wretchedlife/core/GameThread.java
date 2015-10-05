package fr.wretchedlife.core;

import fr.wretchedlife.core.utils.GameEventListener;
import fr.wretchedlife.ui.Window;

public class GameThread implements Runnable 
{
	public GameThread() {}

	public void run()
	{
		Window w = new Window();
		GameEventListener gameEvents = new GameEventListener();
		
		while(true)
		{
			try 
			{		
				w.render();
				gameEvents.listen( w );
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
