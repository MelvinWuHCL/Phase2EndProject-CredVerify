package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckForUser")
public class CheckForUser extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CheckForUser() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
        out.println("<html><body>");
		out.println("<center>User Already Exists</center></br>");
		out.println("<center><html><body></center></br>");
		out.println("<center><a href=\"index.jsp\">Back to Registration Page</a></center></br>");
		out.println("<center><a href=\"index.jsp\">Proceed to Login Page</a></center></br>");
        out.println("</body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
