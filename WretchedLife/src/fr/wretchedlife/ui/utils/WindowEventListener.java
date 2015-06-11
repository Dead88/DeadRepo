package fr.wretchedlife.ui.utils;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import fr.wretchedlife.ui.Window;

public class WindowEventListener implements WindowListener{

	private Window window;
	
	public WindowEventListener( Window window ) {
		this.window = window;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		//System.out.println(e.toString());
	}

	@Override
	public void windowClosed(WindowEvent e) {
		//System.out.println(e.toString());
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//System.out.println(e.toString());
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		//System.out.println(e.toString());
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		//System.out.println(e.toString());
	}

	@Override
	public void windowIconified(WindowEvent e) {
		//System.out.println(e.toString());
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//System.out.println(e.toString());
		window.displayMainMenu();
	}

}
