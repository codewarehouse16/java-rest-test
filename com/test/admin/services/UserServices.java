package com.test.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.admin.model.ProfileApp;
import com.test.admin.model.UserApp;

public class UserServices {

	private static SessionFactory factory;
	
	public UserServices() {
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}		
	}

	public List<UserApp> getAllUsers() {
		List<UserApp> listUsers = new ArrayList<UserApp>();
						
		Session session = factory.openSession();
		Transaction tx = null;
	    
		try{
			tx = session.beginTransaction();
			listUsers = session.createQuery("FROM UserApp").list(); 
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}
	      
		return listUsers;
		
	}
	
	public UserApp addUser(UserApp user) {		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();			
			session.save(user); 
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return user;
	}
	
	public boolean updateUser(UserApp user) {
		boolean flag = false;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.update(user); 
			tx.commit();
			
			flag = true;
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return flag;
	}	
	
	public UserApp getUser(int idUser) {
		UserApp user = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			user = (UserApp) session.get(UserApp.class, idUser);
			tx.commit();
			
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return user;
	}	
	
	public boolean deleteUser(int idUser) {
		boolean flag = false;
		UserApp user = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			user = (UserApp) session.get(UserApp.class, idUser);
			if (user != null) {
				session.delete(user);
				flag = true;
			}
			tx.commit();
			
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return flag;
	}		

	
	public boolean addProfile(int idUser, ProfileApp profile) {
		boolean flag = false;
		UserApp user = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			user = (UserApp) session.get(UserApp.class, idUser);
			if (user != null) {
				if (profile.getIdProfile() != 0) {
					profile = (ProfileApp) session.get(ProfileApp.class, profile.getIdProfile());
				}
				else {
					session.save(profile);
				}
				user.getProfiles().add(profile);
				session.save(user);
				
				flag = true;
			}
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return flag;
	}	
	
}
