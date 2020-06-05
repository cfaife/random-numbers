package mz.co.vm.randomnumber.controller;

import java.util.List;


import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import mz.co.vm.randomnumber.entity.EstatisticEntity;
import mz.co.vm.randomnumber.entity.PendingEntity;
import mz.co.vm.randomnumber.entity.RandomNumberEntity;
import mz.co.vm.randomnumber.service.RandomService;

/**
 * 
 * @author Clerio Afredo Faife
 *
 */
@Path("/numbers")
public class RandomNumberController {

	@EJB
	private RandomService randomService;

	@POST
	@Path("/random")
	public Response requestNewRandomNumber(@HeaderParam("X-Max-Wait") Long xMaxWait) {

		RandomNumberEntity rne = randomService.generateNewNumber(xMaxWait);
		Response.ResponseBuilder rp = Response.ok(rne);
		Response response = rp.header("x-request-duration", rne.getTimeSecs()).build();

		return response;
	}

	@GET
	@Path("/history")
	public List<RandomNumberEntity> getRandomNumbers() {
		return this.randomService.getHistory();
	}

	@PUT
	@Path("/<requestId>/cancel")
	public Response cancelRandomRequest() {
		Response.ResponseBuilder rp = Response.ok();
		Response response = rp.build();

		return response;
	}

	@GET
	@Path("/stats")
	public EstatisticEntity getStats() {
		return this.randomService.getStats();
	}

	@GET
	@Path("/pending")
	public List<PendingEntity> getPendingRequests() {
		return this.randomService.getPendingRequest();
	}

	@PUT
	@Path("/threads/{size}")
	public Response changeThreadPoolSize(@PathParam("username") Integer size) {
		Response.ResponseBuilder rp = Response.ok();
		Response response = rp.build();

		return response;
	}

}
