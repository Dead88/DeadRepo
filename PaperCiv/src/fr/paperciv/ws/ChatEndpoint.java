package fr.paperciv.ws;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{username}")
public class ChatEndpoint
{
	@OnOpen
	public void open(Session session, EndpointConfig conf) throws IOException 
	{ 
		for (Session sess : session.getOpenSessions()) 
		{
			if(!session.getPathParameters().get("username").equals(sess.getPathParameters().get("username")))
			{
				sess.getBasicRemote().sendText(session.getPathParameters().get("username")+" is now connected");
			}
		}
	}
	
	@OnMessage
	public void message(Session session, String msg) throws IOException
	{
		for (Session sess : session.getOpenSessions()) 
		{
			sess.getBasicRemote().sendText(msg);
        }
	}
	
	@OnClose
	public void close(Session session, CloseReason closeReason) throws IOException
	{
		for (Session sess : session.getOpenSessions()) 
		{
			if(!session.getPathParameters().get("username").equals(sess.getPathParameters().get("username")))
			{
				sess.getBasicRemote().sendText(session.getPathParameters().get("username")+" is now disconnected");
			}
		}
	}
}