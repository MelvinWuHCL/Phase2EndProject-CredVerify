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

import java.util.Random;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet(urlPatterns = "/register")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
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
                        out.println(" Name: " + p.getName() + ", Email: " + p.getEmail() + ", Password: " + p.getPassword() + "<br>");
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
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession(); 
        
        //Random random = new Random();
        //int id = random.nextInt(1000);
		try {		
			UserDAO userDAO = new UserDAO();
			if(userDAO.checkForUser(name)) {
				userDAO.addUserDetails(name, email, password);
	            RequestDispatcher rd = request.getRequestDispatcher("/RegisterApproved");  
	        	rd.forward(request,response);
			} else {
                RequestDispatcher rd = request.getRequestDispatcher("/CheckForUser");  
            	rd.forward(request,response);  
            	//"/UserExists.jsp"
			}  
            session.close(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
