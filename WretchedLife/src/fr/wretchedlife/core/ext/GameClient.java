package fr.wretchedlife.core.ext;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;

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
			
			while(true) {
				try {
					System.out.println( in.readUTF() );
				}
				catch(SocketException se) {
					System.out.println("Connection lost...");
					break;
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
