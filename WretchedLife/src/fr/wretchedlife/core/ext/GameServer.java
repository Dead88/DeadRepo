package fr.wretchedlife.core.ext;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;
import fr.wretchedlife.core.utils.XmlTools;
import fr.wretchedlife.map.GameMap;

public class GameServer extends SinglePlayerGame implements Runnable {

	private ServerSocket host;

	public GameServer() {

		super( true );

		System.out.println("Game ready, initalizing server...");

		try {
			this.host = new ServerSocket(Constants.multiplayerPort);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Server started...");
	}

	public ServerSocket getHost() {
		return host;
	}

	public void setHost(ServerSocket host) {
		this.host = host;
	}

	@Override
	public void run() {
		
		while( true ) {
			try {
				Socket serverSocket = null;
				
				try {
					serverSocket = getHost().accept();
				}
				catch(SocketException es) {
					continue;
				}
				
				ClientWorker clientWorker = new ClientWorker( serverSocket );
				Thread clientWorkerThread = new Thread( clientWorker );
				clientWorkerThread.start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		getHost().close();
	}
	
	class ClientWorker implements Runnable {
		
		private Socket serverSocket;
		private PrintStream out;
		private DataInputStream in;
		
		private boolean hasSendedMap = false;
		
		public ClientWorker( Socket serverSocket ) throws Exception {
			this.serverSocket = serverSocket;
			this.out = new PrintStream( getServerSocket().getOutputStream(), true );
			this.in = new DataInputStream( getServerSocket().getInputStream() );
		}

		@Override
		public synchronized void run() {
			try {
				while( true ) {
					try {
						if( !hasSendedMap ) {
							String inputResult = handleInput();
							if( inputResult == null) continue;
							
							if( inputResult.contains("GAMEMAP") ) {
								handleOutput( true, false);
							}
							else if( inputResult.contains("MAPOK") ) {
								System.out.println("client gamemap is ready");
								hasSendedMap = true;
							}
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try { getServerSocket().close(); } catch (IOException e) {}
			}
		}
		
		public Socket getServerSocket() {return serverSocket;}
		public void setServerSocket(Socket serverSocket) {this.serverSocket = serverSocket;}

		private String handleInput() throws Exception {
			String msg = "";
			String line = "";
			
			if( in.available() < 1 ) return null;
			
			while( ( line = in.readUTF() ) != null ) {
				msg += line;
				if( msg != null && !"".equals(msg))
					break;
			}
			
			return msg;
		}
		
		private void handleOutput( boolean sendMap, boolean sendAreas ) throws Exception {
			if( sendMap ) {
				out.print( getCurrentRegionData() );
				System.out.println("gamemap sended");
			}
		}
		
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			getHost().close();
		}
	}
	
	public String getCurrentRegionData() {
		GameMap currentRegionClone = new GameMap( getCurrentRegion().getName(),
			getCurrentRegion().getType(),	
			null, 
			getCurrentRegion().getNumberOfAreas(), 
			getCurrentRegion().getNumberOfLines(),
			null, 
			getCurrentRegion().getGroundTexturePath(), 
			getCurrentRegion().getGroundOverTexturePath(), 
			getCurrentRegion().getGroundSelectedTexturePath()
		);
		
		String message = XmlTools.getXMLStringFromObject( currentRegionClone );
		return message;
	}
}
