package com.test.admin.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.admin.model.ProfileApp;

public class ProfileServices {

	private static SessionFactory factory;
	
	public ProfileServices() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}		
	}	
	
	public List<ProfileApp> getAllProfiles() {
		List<ProfileApp> listProfiles = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
	    
		try{
			tx = session.beginTransaction();
			listProfiles = session.createQuery("FROM ProfileApp").list(); 
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return listProfiles;
	}
	
	public ProfileApp addProfile(ProfileApp profile) {		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(profile);
			tx.commit();
			
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return profile;
	}	
	
}
