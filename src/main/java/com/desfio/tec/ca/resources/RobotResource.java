package com.desfio.tec.ca.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desfio.tec.ca.controller.RobotController;
import com.desfio.tec.ca.exception.InvalidException;
import com.desfio.tec.ca.exception.RepeatedException;

@Component
@Path("/rest/mars")
public class RobotResource {
	
	@Autowired
	private RobotController controller;
	
	@POST
	@Path("/{coords}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response execute(@PathParam("coords") String coords) {
		Response response = null;
		try {
			response = Response.ok(this.controller.move(coords).toString()).build();
		} catch (InvalidException e) {
			response = Response.status(400).entity(e.getMessage()).build();
		} catch (RepeatedException e) {
			response = Response.ok(this.controller.getCoords().toString()).build();
		} catch (Exception e) {
			response = Response.serverError().entity(e.getMessage()).build();
		}
		
		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCoords() {
		return Response.ok(this.controller.getCoords().toString()).build();
	}
}
