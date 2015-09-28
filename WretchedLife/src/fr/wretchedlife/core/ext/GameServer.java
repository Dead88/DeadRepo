package fr.wretchedlife.core.ext;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import fr.wretchedlife.Constants;
import fr.wretchedlife.core.SinglePlayerGame;

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
				Socket clientSocket = null;
				
				try {
					clientSocket = getHost().accept();
				}
				catch(SocketException es) {
					continue;
				}
				
				ClientWorker clientWorker = new ClientWorker( clientSocket );
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
		
		private Socket clientSocket;
		
		public ClientWorker( Socket clientSocket ) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				DataInputStream in = new DataInputStream( clientSocket.getInputStream() );
				PrintStream out = new PrintStream( clientSocket.getOutputStream() );
				
				while( true ) {
					try {
						out.println( "im at "+getPlayerArea().getX()+"/"+getPlayerArea().getY() );
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try { clientSocket.close(); } catch (IOException e) {}
			}
		}
		
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			getHost().close();
		}
	}
}
