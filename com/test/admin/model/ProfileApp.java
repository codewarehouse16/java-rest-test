package com.test.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tbl_profile")
public class ProfileApp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int idProfile;
	
	private String name;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private SystemApp system;
	
	public ProfileApp() {
		
	}

	public int getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(int idProfile) {
		this.idProfile = idProfile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SystemApp getSystem() {
		return system;
	}

	public void setSystem(SystemApp system) {
		this.system = system;
	}
	
}
