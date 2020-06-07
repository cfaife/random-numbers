package mz.co.vm.randomnumber.resource;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.TimeoutException;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
@Stateless
@Path("v1")
public class RandomNumberResource {

	@EJB
	private RandomService randomService;


	@POST
	@Path("/random")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestNewRandomNumber(@HeaderParam("X-Max-Wait") Long xMaxWait) throws InterruptedException, ExecutionException, TimeoutException {

		RandomNumberEntity rne = this.randomService.generateNewRandomNumber(xMaxWait);
		Response.ResponseBuilder rp = Response.ok(rne);
		Response response = rp.header("x-request-duration", rne.getTimeSecs()).build();

		return response;
	}

	@GET
	@Path("/history")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RandomNumberEntity> getRandomNumbers() {
		return this.randomService.getHistory();
	}

	@PUT
	@Path("/cancel/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelRandomRequest(@PathParam(value = "requestId") String requestId) {
		
		UUID uuid  = UUID.fromString(requestId);
		boolean canceled = this.randomService.cancelRandomRequest(uuid);
		
		Response.ResponseBuilder rp = 
				Response
					.ok(canceled?"request "+uuid+ "canceled ":"request "+uuid+ "not canceled ");
		Response response = rp.build();
		
		return response;
	}

	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public EstatisticEntity getStats() {
		return this.randomService.getStats();
	}

	@GET
	@Path("/pending")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PendingEntity> getPendingRequests() {
		return this.randomService.getPendingRequest();
	}

	@PUT
	@Path("/threads/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeThreadPoolSize(@PathParam("size") Integer size) {
		randomService.changePoolThreadSize(size);
		Response.ResponseBuilder rp = Response.ok("Tread Pool size changed to "+size);
		
		Response response = rp.build();

		return response;
	}

}
