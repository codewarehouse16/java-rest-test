package com.test.admin.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.admin.model.ProfileApp;
import com.test.admin.model.UserApp;
import com.test.admin.services.UserServices;


@Path("users")
public class UserResources {

	private UserServices services = new UserServices(); 
	
    @GET	
    @Produces(MediaType.APPLICATION_JSON)
	public List<UserApp> getAllUsers() {
		return services.getAllUsers();
	}
    
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserApp getUser(@PathParam("userId") int id) {
    	return services.getUser(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserApp addUser(UserApp user) {
    	services.addUser(user);
    	return user;
    }
    
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserApp updateUser(@PathParam("userId") int id, UserApp user) {
    	user.setIdUser(id);
    	services.updateUser(user);
    	return user;
    }    
    
    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("userId") int id) {
    	services.deleteUser(id);
    }     	
	
    @POST
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProfileApp addProfile(@PathParam("userId") int id, ProfileApp profile) {
    	services.addProfile(id, profile);
    	return profile;
    }    
    
}
