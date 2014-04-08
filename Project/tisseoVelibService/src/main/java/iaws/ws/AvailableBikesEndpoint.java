package iaws.ws;

import iaws.domain.tisseovelib.AvailableBikesRequest;
import iaws.domain.tisseovelib.AvailableBikesResponse;
import iaws.domain.tisseovelib.LikeRequest;
import iaws.domain.tisseovelib.LikeResponse;
import iaws.services.BikeService;
import iaws.services.BusMetroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AvailableBikesEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.example.org/TisseoVelib";
	
	private BikeService bikeService;
	private BusMetroService busMetroService;
	
	@Autowired
	public AvailableBikesEndpoint(BikeService bikeService) {
	    this.bikeService = bikeService;
	    this.busMetroService=busMetroService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AvailableBikesRequest")                  
	public @ResponsePayload AvailableBikesResponse handleAvailableBikesRequest(@RequestPayload AvailableBikesRequest availableBikesRequest) {
			String name=availableBikesRequest.getName();
			AvailableBikesResponse response=new AvailableBikesResponse();
			response.setAvailableBikes(bikeService.filterStationsByName(name).getAvailableBikes());
			return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LikeRequest")                  
	public @ResponsePayload LikeResponse handleLikeRequest(@RequestPayload LikeRequest availableBikesRequest) {
			String name=availableBikesRequest.getName();
			LikeResponse response=new LikeResponse();
			response.setEtat("OK");
			return response;
	}
}
