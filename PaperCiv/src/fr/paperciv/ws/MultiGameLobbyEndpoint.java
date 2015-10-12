package fr.paperciv.ws;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import fr.paperciv.objs.User;
import fr.paperciv.objs.map.GameMap;

@ServerEndpoint(value = "/multigamelobby/{userJson}")
public class MultiGameLobbyEndpoint
{
	public class MultiGame {
		private User host;
		private Session hostSession;
		private GameMap gamemap;
		private ArrayList<User> connectedUsers;
		
		public MultiGame(User host, Session hostSession, GameMap gamemap,
				ArrayList<User> connectedUsers) {
			this.host = host;
			this.hostSession = hostSession;
			this.gamemap = gamemap;
			this.connectedUsers = connectedUsers;
		}
		
		public User getHost() {return host;}
		public void setHost(User host) {this.host = host;}
		public Session getHostSession() {return hostSession;}
		public void setHostSession(Session hostSession) {this.hostSession = hostSession;}
		public GameMap getGamemap() {return gamemap;}
		public void setGamemap(GameMap gamemap) {this.gamemap = gamemap;}
		public ArrayList<User> getConnectedUsers() {return connectedUsers;}
		public void setConnectedUsers(ArrayList<User> connectedUsers) {this.connectedUsers = connectedUsers;}
	}

	
	@OnOpen
	public void open(Session session, EndpointConfig conf) throws IOException 
	{ 
		String userJson = session.getPathParameters().get("userJson").replace("(","{").replace(")","}");
		
		boolean isClient = false;
		
		if(userJson.indexOf("isClient") != -1){
			userJson = userJson.replace("isClient", "");
			isClient = true;
		}
		
		Gson gson = new Gson();
		
		if(!isClient){
			User host = gson.fromJson( userJson, User.class);
			
			MultiGame multigame = new MultiGame(host, session, new GameMap(), new ArrayList<User>());
			session.getUserProperties().put("MultiGame", multigame);
			
			session.getBasicRemote().sendText("#<br>En attente de joueur...");
		}
		else {
			boolean gameFound = false;
			MultiGame multigame = null;
			
			for (Session sess : session.getOpenSessions()) 
			{
				if(sess.getUserProperties().get("MultiGame")!=null){
					multigame = (MultiGame) session.getUserProperties().get("MultiGame");
					
					System.out.println("a game from "+multigame.getHost().getUserName()+" has been found");
					
					multigame.getHostSession().getBasicRemote().sendText(userJson);
					gameFound = true;
					break;
				}
			}
			
			if(!gameFound){
				session.getBasicRemote().sendText("#<br>Aucune parties trouvées...");
			}
			else session.getBasicRemote().sendText("#<br>Bienvenue dans la partie de "+multigame.getHost().getUserName());
		}
	}
	
	@OnMessage
	public void message(Session session, String msg) throws IOException
	{
		Gson gson = new Gson();
		
		if(msg.indexOf("#")!=-1){
			User incomingUser = gson.fromJson( msg, User.class);
			MultiGame multigame = (MultiGame) session.getUserProperties().get("MultiGame");
			multigame.getConnectedUsers().add( incomingUser );
		}
	}
	
	@OnClose
	public void close(Session session, CloseReason closeReason) throws IOException
	{
		System.out.println(closeReason.getReasonPhrase());
	}
}