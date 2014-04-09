package iaws.services.impl;

import iaws.domain.tisseovelib.BikeStation;
import iaws.domain.tisseovelib.CheckPoint;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.services.BikeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class BikeServImpl implements BikeService {

	private ArrayList<BikeStation> stationList;
	
	//GET https://api.jcdecaux.com/vls/v1/stations?contract={contract_name} HTTP/1.1
	//Pour récupérer la liste des stations
	
	public BikeServImpl(){
		stationList=new ArrayList<BikeStation>();
		refreshList();
	}
	
	public ArrayList<BikeStation> getStationList() {
		return stationList;
	}
	
	public void refreshList() {
		try {
			buildList(get("https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse"
					+ "&apiKey=e3b3de809763e6912653716a66ea7cea8a175a16"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buildList(String liste) {
		stationList.clear();
		try {
			JSONArray array = new JSONArray(liste);
			
			for (int i=0;i<array.length();i++) {
				BikeStation newStation=new BikeStation();
				JSONObject currentStation = array.getJSONObject(i);
				newStation.setId(currentStation.getLong("number"));
				newStation.setName(currentStation.getString("name"));
				newStation.setAdresse(currentStation.getString("address"));
				newStation.setStatus(currentStation.getString("status"));
				newStation.setAvailableBikes(currentStation.getInt("available_bikes"));
				newStation.setCoordonnees(new Coordonnees(currentStation.getJSONObject("position").getDouble("lng"),currentStation.getJSONObject("position").getDouble("lat")));
				stationList.add(newStation);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public BikeStation getNearestBikeStation(Coordonnees coord){
		refreshList();
		
		if(!stationList.isEmpty()){
			BikeStation currentStation;
			BikeStation res=stationList.get(0);
			int bestValue=getDistanceEnMetreAvec(coord,res.getCoordonnees());
			
			for (int i=0;i<stationList.size();i++) {
				currentStation=stationList.get(i);
				int prov=getDistanceEnMetreAvec(coord,currentStation.getCoordonnees());
				if(prov<bestValue && prov>=0){
					bestValue=prov;
					res=currentStation;
				}
			}
			return res;
		}
		else {
			return null;
		}
	}
	
	public int getDistanceEnMetreAvec(Coordonnees coordonnees1,Coordonnees coordonnees2){

		int toReturn = getDistanceBetween(coordonnees1.getLatitude(), coordonnees1.getLongitude(),
				coordonnees2.getLatitude(),coordonnees2.getLongitude());

		return toReturn;
	}

	/**
	 * Distance en metre entre 2 coordonnees
	 * http://stackoverflow.com/questions/27928/how-do-i-calculate-distance-between-two-latitude-longitude-points
	 * @return distance en metre
	 */
	private int getDistanceBetween(double lat1, double lon1, double lat2,double lon2) {
		  int R = 6371; // Radius of the earth in km
		  double dLat = deg2rad(lat2-lat1);  // deg2rad below
		  double dLon = deg2rad(lon2-lon1); 
		  Double a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2)
		    ; 
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  double d = R * c; // Distance in km
		  return (int)(d * 1000);
	}

	private double deg2rad(double deg) {
		return deg * (Math.PI/180);
	}
	
	public BikeStation filterStationsByName(String name) {
		for (int i=0;i<stationList.size();i++) {
			if(stationList.get(i).getName().equals(name)) {
				return stationList.get(i); 
			}
		}
		return null;
	}
	
	public String get(String url) throws IOException{
		String inputLine;
		String source ="";
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(yc.getInputStream()));
		
		 
		while ((inputLine = in.readLine()) != null)
			source +=inputLine;
		
		in.close();
		return source;
	}
}
