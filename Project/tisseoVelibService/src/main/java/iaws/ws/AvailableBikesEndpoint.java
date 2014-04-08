package iaws.ws;

import iaws.domain.tisseovelib.AvailableBikesRequest;
import iaws.domain.tisseovelib.AvailableBikesResponse;
import iaws.services.AvailableBikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AvailableBikesEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.example.org/TisseoVelib";
	
	private AvailableBikesService bikeService;
	
	@Autowired
	public AvailableBikesEndpoint(AvailableBikesService bikeService) {
	    this.bikeService = bikeService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AvailableBikesRequest")                  
	public @ResponsePayload AvailableBikesResponse handleHolidayRequest(@RequestPayload AvailableBikesRequest availableBikesRequest)               
	      throws Exception {
			String name=availableBikesRequest.getName();
			AvailableBikesResponse response=new AvailableBikesResponse();
			response.setAvailableBikes(bikeService.filterStationsByName(name).getAvailableBikes());
			return response;
	}
}
