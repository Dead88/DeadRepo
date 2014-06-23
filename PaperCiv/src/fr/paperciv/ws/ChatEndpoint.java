package fr.paperciv.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{username}")
public class ChatEndpoint
{
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	
	@OnOpen
	public void open(Session session, EndpointConfig conf) throws IOException 
	{ 
		System.out.println("client : "+session.getPathParameters().get("username")+" is connecting");
		
		sendClientEvent(session, true);
		
		queue.add(session);
	}
	
	@OnMessage
	public void send(Session session, String msg) throws IOException
	{
		System.out.println(session.getPathParameters().get("username")+" send : "+msg);
		
		if("".equals(msg))
			return;
		
		ArrayList<Session> closedSessions= new ArrayList<Session>();
		String text = "";
	
		for (Session queuedSession : queue) 
		{
			if(!queuedSession.isOpen())
			{
				System.out.println(queuedSession.getPathParameters().get("username")+" is closed, stop sending");
				closedSessions.add(queuedSession);
			}
			else
			{
				text = session.getPathParameters().get("username")+" : "+msg;
				
				queuedSession.getBasicRemote().sendText( text );
			}   
		}
		
		queue.removeAll(closedSessions);
	}
	
	public void sendClientEvent(Session session, boolean isConnecting) throws IOException 
	{
		ArrayList<Session> closedSessions= new ArrayList<Session>();
		String text = "";
		
		for (Session queuedSession : queue) 
		{
			if(!queuedSession.isOpen())
			{
				System.out.println(queuedSession.getPathParameters().get("username")+" is closed, stop sending client event");
				closedSessions.add(queuedSession);
			}
			else
			{
				if(isConnecting)
					text = session.getPathParameters().get("username")+" is now connected";
				else text = session.getPathParameters().get("username")+" is now disconnected";
					
				queuedSession.getBasicRemote().sendText( text );
			}   
		}
		
		queue.removeAll(closedSessions);
	}
	
	@OnClose
	public void close(Session session, CloseReason closeReason) throws IOException
	{
		System.out.println(session.getPathParameters().get("username")+" closed the connection");
		
		queue.remove(session);
		
		sendClientEvent(session, false);
	}
	
	@OnError
	public void error(Session session, Throwable t) throws IOException
	{
		System.out.println(session.getPathParameters().get("username")+" crashed the connection");
		
		queue.remove(session);
		
		sendClientEvent(session, false);
	}
}