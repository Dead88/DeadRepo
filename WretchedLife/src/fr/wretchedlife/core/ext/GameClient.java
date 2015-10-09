package fr.wretchedlife.core.ext;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.Game;
import fr.wretchedlife.core.utils.XmlTools;
import fr.wretchedlife.map.GameMap;

public class GameClient extends Game implements Runnable {
	
	private Socket clientSocket;
	private PrintStream out;
	private DataInputStream in;
	
	boolean isMapReady = false;
	
	public GameClient( String serverAddr ) throws Exception {
		super( false );
		
		System.out.println("Initalizing client...");
		
		this.clientSocket = new Socket(serverAddr , Constants.multiplayerPort );
		this.out = new PrintStream( getClientSocket().getOutputStream(), true );
		this.in = new DataInputStream( getClientSocket().getInputStream() );
		
		System.out.println("Client started...");
	}
	
	@Override
	public synchronized void run() {
		try {
			while(true) {
				try {
					if( !isMapReady ) {
						handleOutput( true, false );

						handleInput();
					}
					else {
						out.print("MAPOK");
					}
				}
				catch(Exception e ) {
					e.printStackTrace();
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {  getClientSocket().close(); } catch (IOException e) {}
		}
	}

	public Socket getClientSocket() {return clientSocket;}
	public void setClientSocket(Socket clientSocket) {this.clientSocket = clientSocket;}
	
	private String handleInput() throws Exception {
		String msg = "";
		String line = "";
		
		if( in.available() < 1 ) return null;
		
		while( ( line = in.readUTF() ) != null ) {
			msg += line;
			if( msg != null && !"".equals(msg))
				break;
		}
		
		try {
			GameMap gameMap = XmlTools.getObjectFromXMLString( msg );
			isMapReady = true;
			System.out.println("map retrieved !");
		}
		catch(Exception e) {
			System.out.println("map retrieve failed");
		}
		return msg;
	}
	
	private void handleOutput( boolean sendMapRequest, boolean sendAreasRequest ) throws Exception {
		if( sendMapRequest ) {
			out.print("GAMEMAP");
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		getClientSocket().close();
	}
}
