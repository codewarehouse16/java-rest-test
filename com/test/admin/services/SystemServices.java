package com.test.admin.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.admin.model.SystemApp;

public class SystemServices {

	private static SessionFactory factory;
	
	public SystemServices() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}		
	}	
	
	public List<SystemApp> getAllSystems() {
		List<SystemApp> listSystems = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
	    
		try{
			tx = session.beginTransaction();
			listSystems = session.createQuery("FROM SystemApp").list(); 
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}
		
		
		return listSystems;
	}
	
	public SystemApp getSystem(int systemId) {
		SystemApp system = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			system = (SystemApp) session.get(SystemApp.class, systemId);
			tx.commit();
			
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		return system;
		
	}
	
	public SystemApp addSystem(SystemApp system) {
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(system); 
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}
		finally {
	         session.close(); 
		}		
		
		
		return system;
	}
	
	public SystemApp updateSystem(SystemApp system) {
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(system);
			tx.commit();
		}
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
		}
		finally {
	         session.close(); 
		}
		
		return system;
	}
	
	public boolean deleteSystem(int systemId) {
		boolean flag = false;
		SystemApp system = null;
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			system = (SystemApp) session.get(SystemApp.class, systemId);
			if (system != null) {
				session.delete(system);
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
