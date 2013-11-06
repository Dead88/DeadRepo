package fr.paperciv.struts.action;

import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.paperciv.common.Constants;
import fr.paperciv.common.PaperSession;
import fr.paperciv.factories.XmlFactory;
import fr.paperciv.objs.User;

public class LoginAction extends Action
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ServletOutputStream out = null;
		String username = "";
		String pass = "";
		
		ArrayList<User> users = null;
		boolean isLogged = false;
		User userObj = null;
		
		try
		{			
			if(request.getParameter("user")!=null && request.getParameter("pass")!=null)
			{
				username = request.getParameter("user");
				pass = request.getParameter("pass");

				users = XmlFactory.getUsers();
				
				for(int i=0;i<users.size();i++)
				{
					if(username.equals(users.get(i).getUserName())
					&& pass.equals(users.get(i).getPassWord()))
					{
						isLogged = true;
						userObj = new User(users.get(i).getId(), users.get(i).getUserName(), users.get(i).getPassWord());
					}
				}
				
				if(!isLogged)
				{
					Constants.sendResponse(response, "Connexion refusé");
				}
				else
				{
					PaperSession.setUserSession(request, userObj);
					Constants.initGameProperties(request);
					
					if(username.equals("admin")){
						response.sendRedirect(Constants.getUrlRedirect(request, "skirmish.do?playerRaceId=1&enemyRaceId=2&gameMapId=1"));
					}
					Constants.sendResponse(response, "OK");
				}
			}
			else
			{
				Constants.sendResponse(response, "Problème lors de la récupération du formulaire");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Constants.sendResponse(response, "Une Erreur est survenue lors de l'identification");
		}
		finally
		{
			if(out!=null)out.close();
			out = null;
		}
		return null;
	}
}
