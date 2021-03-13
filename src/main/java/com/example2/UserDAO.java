package com.example2;

import javax.servlet.ServletException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example2.User;

import java.util.List;
import java.io.IOException;

public class UserDAO {

	public void addUserDetails(String name, String email, String password) {
		
		try {
			Configuration configuration = new Configuration();
			SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			User user = new User();
			//user.setID(id);
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			session.save(user);
			transaction.commit();
			session.close();
			System.out.println("user added");
			
		} catch(HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}
	
	public boolean checkForUser(String name) {

		boolean status = true;
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User user = new User();
		List<User> check = session.createQuery("from User where name =" + "'" + name + "'", User.class).list();
		for(User u : check) {
			if(u.getName().contains(name)) {
				status = false;
				System.out.println("user already exists");
			} else status = true;
		}
		return status;
	}
	
	public static boolean verifyDetails(String name, String password) {

		boolean status = false;
		try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession(); 
            List<User> list = session.createQuery("from User where name =" + "'" + name + "'", User.class).list();
            session.close();
            for(User u : list) {;
            	if(u.getPassword().equals(password)) {
            		status = true;
            	}
            }
            session.close(); 
		} catch (Exception ex) {
            throw ex;
		}
		return status;
	}
}