package iaws.ws;

import java.util.ArrayList;

import javax.swing.text.DocumentFilter.FilterBypass;

import iaws.domain.tisseovelib.AvailableBikesRequest;
import iaws.domain.tisseovelib.AvailableBikesResponse;
import iaws.domain.tisseovelib.LikeRequest;
import iaws.domain.tisseovelib.LikeResponse;
import iaws.domain.tisseovelib.TransportLine;
import iaws.domain.tisseovelib.User;
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
	
	private ArrayList<User> userList;
	
	private BikeService bikeService;
	private BusMetroService busMetroService;
	
	@Autowired
	public AvailableBikesEndpoint(BikeService bikeService,BusMetroService busMetroService) {
	    this.bikeService = bikeService;
	    this.busMetroService=busMetroService;
	    this.userList=new ArrayList<User>();
	    User user1=new User("Assyl","Louahadj","assyl.louahadj@gmail.com","Toulouse",14.8,15.25);
	    user1.setId(1);
	    userList.add(user1);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AvailableBikesRequest")                  
	public @ResponsePayload AvailableBikesResponse handleAvailableBikesRequest(@RequestPayload AvailableBikesRequest availableBikesRequest) {
			String name=availableBikesRequest.getName();
			AvailableBikesResponse response=new AvailableBikesResponse();
			response.setAvailableBikes(bikeService.filterStationsByName(name).getAvailableBikes());
			return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LikeRequest")                  
	public @ResponsePayload LikeResponse handleLikeRequest(@RequestPayload LikeRequest likeRequest) {
			LikeResponse response=new LikeResponse();
			User currentUser=filterUserByID(likeRequest.getId());
			if (currentUser!=null) {
				TransportLine currentLine=busMetroService.filterStationsByNameAndShortname
						(likeRequest.getName(), likeRequest.getShortName());
				if(currentLine!=null) {
					if(currentUser.likeUnlike(currentLine.getId(),likeRequest.isLike())){
						response.setEtat("OK");
					}
					else{
						if(likeRequest.isLike())
							response.setEtat("Already Liked");
						else
							response.setEtat("Already Unliked");
					}
					
				}
				response.setEtat("ERROR : Line doesn't exist");
			}
			response.setEtat("ERROR : User doesn't exist");
			return response;
	}
	
	public User filterUserByID(long id){
		for (int i=0;i<userList.size();i++) {
			if(userList.get(i).getId()==id) {
				return userList.get(i); 
			}
		}
		return null;
	}
}
