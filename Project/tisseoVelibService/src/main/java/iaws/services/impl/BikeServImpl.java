package iaws.services.impl;

import iaws.domain.tisseovelib.BikeStation;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.services.BikeService;
import iaws.ws.ToolBox;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class BikeServImpl implements BikeService {

	private ArrayList<BikeStation> stationList;
	
	public BikeServImpl(){
		stationList=new ArrayList<BikeStation>();
		refreshList();
	}
	
	public ArrayList<BikeStation> getStationList() {
		return stationList;
	}
	
	public void refreshList() {
		try {
			buildList(ToolBox.get("https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse"
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
			int bestValue=ToolBox.getDistMeter(coord,res.getCoordonnees());
			
			for (int i=0;i<stationList.size();i++) {
				currentStation=stationList.get(i);
				int prov=ToolBox.getDistMeter(coord,currentStation.getCoordonnees());
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
	
	public BikeStation filterStationsByName(String name) {
		for (int i=0;i<stationList.size();i++) {
			if(stationList.get(i).getName().equals(name)) {
				return stationList.get(i); 
			}
		}
		return null;
	}
	
	public static void main(String args[]) {
		System.out.println(new BikeServImpl().filterStationsByName("00040 - COLOMBETTE").getAvailableBikes());
	}
}
