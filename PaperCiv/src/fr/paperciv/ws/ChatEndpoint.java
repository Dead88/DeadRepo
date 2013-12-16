package fr.paperciv.ws;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint
{
	@OnOpen
	public void open(Session session, EndpointConfig conf) 
	{ 
	}
	
	@OnMessage
	public void message(Session session, String msg) throws IOException
	{
		for (Session sess : session.getOpenSessions()) 
		{
			sess.getBasicRemote().sendText(msg);
        }
	}
}