package com.test.admin.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.admin.model.ProfileApp;
import com.test.admin.model.SystemApp;
import com.test.admin.services.ProfileServices;
import com.test.admin.services.SystemServices;

@Path("systems/{systemId}/profiles")
public class ProfileResources {

	private ProfileServices profileServices = new ProfileServices();
	private SystemServices systemServices = new SystemServices();
	
	public ProfileResources() {
		
	}
	
    @GET	
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfileApp> getAllProfiles() {
    	return profileServices.getAllProfiles();
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProfileApp addProfile(@PathParam("systemId") int systemId, ProfileApp profile) {
    	SystemApp system = systemServices.getSystem(systemId);
    	if (system != null) {
    		profile.setSystem(system);
    		profile = profileServices.addProfile(profile);
    	}
    	return profile;
    }
    
	
}
