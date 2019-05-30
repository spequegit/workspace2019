package main.info.skrzypczak.dagmara.rest.service;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import main.info.skrzypczak.dagmara.dao.AlertStore;
import main.info.skrzypczak.dagmara.entity.Alert;

@Path("")
public class LimitationService {

	private Gson gson;
	private static AlertStore alertStore;

	public LimitationService() {
		gson = new Gson();
		alertStore = AlertStore.getInstance();
	}

	@PUT
	@Path("/alert")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addAlert(Alert alert) {
		if (alert == null) {
			return Response.status(BAD_REQUEST).build();
		}
		alertStore.add(alert);
		String json = gson.toJson(alertStore.getAlerts());
		return Response.ok(json, MediaType.APPLICATION_JSON).status(CREATED).build();

	}

	@GET
	@Path("/alert")
	@Produces("application/json")
	@Consumes("application/json")
	public String findAlert(@QueryParam("pair") String pair) {
		if (!alertStore.contains(pair)) {
			return "{}";
		}
		return gson.toJson(alertStore.get(pair));
	}

	@DELETE
	@Path("/alert")
	@Produces("application/json")
	@Consumes("application/json")
	public Response removeAlert(@QueryParam("pair") String pair) {
		if (pair == null) {
			return Response.status(BAD_REQUEST).build();
		}
		alertStore.remove(pair);
		String json = gson.toJson(alertStore.getAlerts());
		return Response.ok(json, MediaType.APPLICATION_JSON).status(OK).build();
	}

}
