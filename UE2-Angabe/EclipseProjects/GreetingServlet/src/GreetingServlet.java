import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*@WebServlet(name = "GreetingServlet", urlPatterns = {"/GreetingServlet"})*/
public class GreetingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Greeting Servlet</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello " + request.getParameter("userName") + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    public String getServletInfo() {
        return "This is a simple servlet that greets whoever provides his name.";
    }
}
