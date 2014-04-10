package iaws.ws;

import java.util.ArrayList;

import iaws.domain.tisseovelib.AvailableBikesRequest;
import iaws.domain.tisseovelib.AvailableBikesResponse;
import iaws.domain.tisseovelib.BikeStation;
import iaws.domain.tisseovelib.CheckPoint;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.domain.tisseovelib.LikeRequest;
import iaws.domain.tisseovelib.LikeResponse;
import iaws.domain.tisseovelib.NextBusMetroRequest;
import iaws.domain.tisseovelib.NextBusMetroResponse;
import iaws.domain.tisseovelib.TransportLine;
import iaws.domain.tisseovelib.User;
import iaws.services.BikeService;
import iaws.services.BusMetroService;
import iaws.domain.tisseovelib.BestBikeBusMetroRequest;
import iaws.domain.tisseovelib.BestBikeBusMetroResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AvailableBikesEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.example.org/TisseoVelib";
	private static final Coordonnees UNIVERSITE = new Coordonnees(43.5608814,1.4633499);
	
	private static final int WALKSPEED_KM_H = 5;
	private static final int BIKE_KM_H = 10;
	private static final int BUS_METRO_KM_H = 30;
	
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
		boolean like=likeRequest.isLike();
		
		User currentUser=ToolBox.filterUserByID(userList,likeRequest.getId());
		if (currentUser!=null) {
			TransportLine currentLine=busMetroService.filterLinesByShortname(likeRequest.getShortName());
			if(currentLine!=null) {
				if(currentUser.likeUnlike(currentLine.getId(),like)){
					if(like){
						currentLine.setNbLikes(currentLine.getNbLikes()+1);
					}
					else {
						currentLine.setNbLikes(currentLine.getNbLikes()-1);
					}
					response.setEtat("OK");
				}
				else{
					if(like) {
						response.setEtat("OK : Already Liked");
					}
					else {
						response.setEtat("OK : Already Unliked");
					}
				}
			}
			else {
				response.setEtat("ERROR : Line doesn't exist");
			}
		}
		else {
			response.setEtat("ERROR : User doesn't exist");
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "NextBusMetroRequest")                  
	public @ResponsePayload NextBusMetroResponse handleNextBusMetroRequest(@RequestPayload NextBusMetroRequest nextBusMetroRequest) {
		NextBusMetroResponse response=new NextBusMetroResponse();
		TransportLine currentLine = busMetroService.filterLinesByShortname(nextBusMetroRequest.getShortName());
		if(currentLine!=null) {
			CheckPoint nearestCheckPoint=busMetroService.getNearestCheckPoint(UNIVERSITE, currentLine.getId());
			response.setName(nearestCheckPoint.getName());
			response.setTime(busMetroService.getNextTimeToCheckPoint(nearestCheckPoint.getId()));
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "BestBikeBusMetroRequest")                  
	public @ResponsePayload BestBikeBusMetroResponse handleBestBikeBusMetroRequest(@RequestPayload BestBikeBusMetroRequest bestBikeBusMetroRequest) {
		BestBikeBusMetroResponse response=new BestBikeBusMetroResponse();
		Coordonnees destCoordonnees=bestBikeBusMetroRequest.getCoordonnees();
		
		boolean bikeIsBetter=false;
		int timeEstimed;
		
		int dist=ToolBox.getDistMeter(UNIVERSITE, destCoordonnees);
		if(dist<8000) {
			bikeIsBetter=true;
			BikeStation startStation=bikeService.getNearestBikeStation(UNIVERSITE);
			BikeStation endStation=bikeService.getNearestBikeStation(destCoordonnees);
			int bikeDist=ToolBox.getDistMeter(startStation.getCoordonnees(), endStation.getCoordonnees());
			int walkDist=ToolBox.getDistMeter(UNIVERSITE, startStation.getCoordonnees())
					+ToolBox.getDistMeter(endStation.getCoordonnees(), destCoordonnees);
			
			if(walkDist>(dist/4) || dist-bikeDist<0){
				bikeIsBetter=false;
			}
			else{
				timeEstimed=bikeDist*60/(BIKE_KM_H*1000) + walkDist*60/(WALKSPEED_KM_H*1000);
				response.setType("Bike");
				response.setStartBikeStation(endStation.getName());
				response.setEndBikeStation(endStation.getName());
				response.setLinesAvailable("");
				response.setTimeEstimed(timeEstimed);
			}
			
		}
		if(!bikeIsBetter){
			String availableLines = busMetroService.getAvailableLines(UNIVERSITE,destCoordonnees);
			timeEstimed=dist*60/(BUS_METRO_KM_H*1000);
			response.setType("Bus/Metro");
			response.setStartBikeStation("");
			response.setEndBikeStation("");
			response.setLinesAvailable(availableLines);
			response.setTimeEstimed(timeEstimed);
		}
		
		return response;
	}
}
