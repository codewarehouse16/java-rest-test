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

import com.test.admin.model.SystemApp;
import com.test.admin.services.SystemServices;

@Path("systems")
public class SystemResources {

	private SystemServices services = new SystemServices(); 
	
    @GET	
    @Produces(MediaType.APPLICATION_JSON)	
	public List<SystemApp> getAllSystems() {
		return services.getAllSystems();
	}

    @GET	
    @Path("/{systemId}")
    @Produces(MediaType.APPLICATION_JSON)	
	public List<SystemApp> getSystem(@PathParam("systemId") int id) {
		return services.getAllSystems();
	}    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SystemApp addSystem(SystemApp system) {
    	return services.addSystem(system);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public SystemApp updateSystem(SystemApp system) {
    	return services.updateSystem(system);
    }
    
    @DELETE
    @Path("/{systemId}")
    public boolean deleteSystem(@PathParam("systemId") int id) {
    	return services.deleteSystem(id);
    }
    
}
