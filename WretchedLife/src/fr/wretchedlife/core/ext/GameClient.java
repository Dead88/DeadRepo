package fr.wretchedlife.core.ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.core.utils.XmlTools;
import fr.wretchedlife.map.GameMap;

public class GameClient extends SinglePlayerGame implements Runnable {
	
	private Socket clientSocket;
	boolean isMapReady = false;
	
	public GameClient( String serverAddr ) throws Exception {
		super( false );
		
		System.out.println("Initalizing client...");
		
		this.clientSocket = new Socket(serverAddr , Constants.multiplayerPort );

		System.out.println("Client started...");
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				try {
					if( !isMapReady ) {
						handleOutput( true, false );
						
						String inputResult = handleInput();
						if( inputResult == null) continue;
						
						System.out.println( handleInput() );
						isMapReady = true;
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
	
	private synchronized String handleInput() throws Exception {
		BufferedReader in = new BufferedReader( new InputStreamReader( getClientSocket().getInputStream() ) );
		String msg = "";
		String line = "";
		
		while( ( line = in.readLine() ) != null ) {
			msg += line;
		}
		
		try {
			GameMap gameMap = XmlTools.getObjectFromXMLString( msg );
		}
		catch(Exception e) {
			System.out.println("map retrieve failed");
		}
		return msg;
	}
	
	private synchronized void handleOutput( boolean sendMapRequest, boolean sendAreasRequest ) throws Exception {
		PrintWriter out = new PrintWriter( getClientSocket().getOutputStream() );
		
		if( sendMapRequest ) {
			out.print("GAMEMAP");
			out.flush();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		getClientSocket().close();
	}
}
