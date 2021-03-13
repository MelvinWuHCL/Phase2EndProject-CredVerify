package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example2.HibernateUtil;
import com.example2.User;
import com.example2.UserDAO;

/**
 * Servlet implementation class UserAuthenticate
 */
@WebServlet ("/login")
public class UserAuthenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAuthenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {
               SessionFactory factory = HibernateUtil.getSessionFactory();
               
               Session session = factory.openSession();
               // using HQL
               List<User> list = session.createQuery("from User", User.class).list();
               
               session.close();
               
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<b>User Listing</b><br>");
                for(User p: list) {
                        out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                        ", Email: " + String.valueOf(p.getEmail()) + ", Password: " + p.getPassword() + "<br>");
                }
                
            out.println("</body></html>");
            
            
        } catch (Exception ex) {
                throw ex;
        }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();  
			try {
	            String name = request.getParameter("name");  
	            String password = request.getParameter("password");  
	            SessionFactory factory = HibernateUtil.getSessionFactory();
	            Session session = factory.openSession();
	            if(UserDAO.verifyDetails(name, password)) {  
	                RequestDispatcher rd = request.getRequestDispatcher("welcome");  
	                rd.forward(request,response);  
	            }  
	            else {    
	                RequestDispatcher rd = request.getRequestDispatcher("LogInPage.jsp");  
	                rd.include(request,response);  
	                out.print("Username or password error...try again");
	            }  
	            out.close();
	            session.close();  
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

}
