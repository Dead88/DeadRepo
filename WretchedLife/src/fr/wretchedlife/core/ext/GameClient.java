package fr.wretchedlife.core.ext;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.map.GameMap;

public class GameClient extends SinglePlayerGame implements Runnable {
	
	private Socket client;
	
	public GameClient( String serverAddr ) throws Exception {
		super( false );
		this.client = new Socket(serverAddr , Constants.multiplayerPort );
	}
	
	@Override
	public void run() {
		
		try {
			PrintStream out = new PrintStream( getClient().getOutputStream() );
			DataInputStream in = new DataInputStream( getClient().getInputStream() );
			
			Gson gson = new Gson();
			
			while(true) {
				try {
					
				}
				catch(Exception e ) {
					break;
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { client.close(); } catch (IOException e) {}
		}
	}

	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		getClient().close();
	}
}
