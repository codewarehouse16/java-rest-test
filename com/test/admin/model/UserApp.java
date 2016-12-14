package com.test.admin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tbl_user")
public class UserApp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int idUser;
	
	private String nombres;
	
	private String apellidos;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="tbl_profile_by_user", joinColumns={@JoinColumn(name="idUser")}, inverseJoinColumns={@JoinColumn(name="idProfile")})	
	private List<ProfileApp> profiles = new ArrayList<ProfileApp>();
	
	public UserApp() {

	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<ProfileApp> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileApp> profiles) {
		this.profiles = profiles;
	}
	
}
