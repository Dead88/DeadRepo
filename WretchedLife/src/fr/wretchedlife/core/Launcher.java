package fr.wretchedlife.core;

public class Launcher {
	public static void main(String[] args) 
	{
		GameThread mT = new GameThread();
		Thread t = new Thread(mT);
		t.start();
	}
}
