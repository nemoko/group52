package controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.User.Interest;
import model.UserPool;

/**
 *
 * @author Mayerhofer
 */
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserPool userpool;
    
    @Override
    public void init() throws ServletException {
        super.init();
        userpool = new UserPool();
    }
  
    /**
     * Responsible for login
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action==null) {
            return;
        }
        if(action.equals("login")) {                           
            HttpSession session = request.getSession(true);
            User user = userpool.getUser(request.getParameter("username"), request.getParameter("password"));
            if(user == null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loginfail.html");
                dispatcher.forward(request, response);
            } else {
                session.setAttribute("user", user);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userpage.jsp");
                dispatcher.forward(request, response);
            }               
        } else if (action.equals("logout")){
            request.getSession(true).invalidate();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
            dispatcher.forward(request, response);
        } else if (action.equals("userdata")) {
            User user =(User)request.getSession().getAttribute("user");
            
            if(user!=null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userdata.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userdatafail.html");
                dispatcher.forward(request, response);
            }
        }
    }

    /**
     * Responsible for updating a user and creating new users.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);         
        User user =(User)session.getAttribute("user");
        boolean newuser = false;
        if(user == null) {
            user = new User();            
            newuser = true;
            user.setUsername(request.getParameter("username"));
        } else {
            user = userpool.getUser(user.getUsername());
        }
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));        
        user.setPassword(request.getParameter("password"));

        String[] inter = request.getParameterValues("interests");
        if(!newuser) {
            user.clearInterests();
        }
        if(inter != null ) {
            List<String> interests = Arrays.asList(inter);
            if(interests.contains("webEngineering")) {
                user.addInterest(Interest.WEBENINEERING);                
            }
            if(interests.contains("modelEngineering")) {
                user.addInterest(Interest.MODELENGINEERING);                
            }
            if(interests.contains("semanticWeb")) {
                user.addInterest(Interest.SEMANTICWEB);                
            }
            if(interests.contains("objectOrientedModeling")) {
                user.addInterest(Interest.OBJECTORIENTEDMODELING);                
            }
            if(interests.contains("businessInformatics")) {
                user.addInterest(Interest.BUSINESSINFORMATICS);                
            }         
        } 
        
        session.setAttribute("user", user);
        
        if(newuser) {
            userpool.registerUser(user);
        }           
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userpage.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet for registration";
    }
}
