package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import beans.User.Interest;
import beans.UserPool;


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
            response.setContentType("text/html;charset=UTF-8");

            HttpSession session = request.getSession(true);

            User user = userpool.getUser(request.getParameter("username"), request.getParameter("password"));

            if(user == null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loginfail.html");
                dispatcher.forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Userpage</title>");
                out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hello " +  user.getFirstname() + " " + user.getLastname() + "</h1>");
                out.println("<p>");
                out.println("What do you want to do?<br/>");
                out.println("<a href=\"LoginServlet?action=userdata\">Update user data</a><br/>");
                out.println("<a href=\"LoginServlet?action=logout\">Logout</a>");
                out.println("</p>");
                out.println("</body>");
                out.println("</html>");
            }

            session.setAttribute("user", user);   
        } else if (action.equals("logout")){
            request.getSession(true).invalidate();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
            dispatcher.forward(request, response);
        } else if (action.equals("userdata")) {
            User user =(User)request.getSession().getAttribute("user");
        
            PrintWriter out = response.getWriter();

            if(user != null) {       
                out.println("<html><head><title>Update user data</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>");
                out.println("<body>");
                out.println("<h1>Update user data</h1>");
                out.println("Please update your data.");
                out.println("<form action=\"LoginServlet\" method=\"POST\">");
                out.println("<h2>Personal Data</h2>");
                out.println("<p>");
                out.println("Firstname: <input type=\"text\" name=\"firstname\" value=\"" + user.getFirstname() + "\" /><br/>");
                out.println("Lastname: <input type=\"text\" name=\"lastname\" value=\"" + user.getLastname() + "\" /><br/>");
                out.println("</p>");
                out.println("<h2>Login Data</h2>");
                out.println("<p>");
                out.println("Username: <input type=\"text\" name=\"username\" value=\"" + user.getUsername() + "\" disabled=\"disabled\"/><br/>");
                out.println("Password: <input type=\"text\" name=\"password\" value=\"" + user.getPassword() + "\" /><br/>");
                out.println("</p>");
                out.println("<h2>Interests</h2>");
                out.println("<p>");
                if(user.getInterests().contains(User.Interest.WEBENINEERING)){
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"webEngineering\" checked=\"checked\"/>Web Engineering<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"webEngineering\"/>Web Engineering<br/>");
                }
                if(user.getInterests().contains(User.Interest.MODELENGINEERING)){
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"modelEngineering\" checked=\"checked\"/>Model Engineering<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"modelEngineering\"/>Model Engineering<br/>");
                }
                if(user.getInterests().contains(User.Interest.SEMANTICWEB)){
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"semanticWeb\" checked=\"checked\"/>Semantic Web<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"semanticWeb\"/>Semantic Web<br/>");
                }
                if(user.getInterests().contains(User.Interest.OBJECTORIENTEDMODELING)){
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"objectOrientedModeling\" checked=\"checked\"/>Object Oriented Modeling<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"objectOrientedModeling\"/>Object Oriented Modeling<br/>");
                }
                if(user.getInterests().contains(User.Interest.BUSINESSINFORMATICS)){
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"businessInformatics\" checked=\"checked\"/>Business Informatics<br/>");
                } else {
                    out.println("<input type=\"checkbox\" name =\"interests\" value =\"businessInformatics\"/>Business Informatics<br/>");            
                }
                out.println("</p>");
                out.println("<input type=\"submit\" value=\"Submit\" name=\"submit\"/>");
                out.println("</form></body></html>");
            } else {
                out.println("<html><head><title>Update user data failed</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>");
                out.println("<body>");
                out.println("<h1>You have to login first!</h1>");
                out.println("<a href=\"login.html\">Login</a>");
                out.println("</body></html>");
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
        response.setContentType("text/html;charset=UTF-8");

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
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Userpage</title>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello " +  user.getFirstname() + " " + user.getLastname() + "</h1>");
        out.println("<p>");
        if(newuser) {
            out.println("Thanks for registration!");
        } else {
            out.println("Thanks for updating your data!");            
        }        
        out.println("</p>");
        out.println("<p>");
        out.println("What do you want to do?<br/>");
        out.println("<a href=\"LoginServlet?action=userdata\">Update user data</a><br/>");
        out.println("<a href=\"LoginServlet?action=logout\">Logout</a>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet for registration";
    }
}
