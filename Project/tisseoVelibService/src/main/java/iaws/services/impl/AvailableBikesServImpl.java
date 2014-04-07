package iaws.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import iaws.services.AvailableBikesService;

public class AvailableBikesServImpl implements AvailableBikesService {

	private String stationList;
	
	public AvailableBikesServImpl(){
		//GET https://api.jcdecaux.com/vls/v1/stations?contract={contract_name} HTTP/1.1
		//Pour récupérer la liste des stations
		refreshList();
	}
	
	public String getStationList() {
		return stationList;
	}
	
	public void refreshList() {
		try {
			stationList=get("https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=e3b3de809763e6912653716a66ea7cea8a175a16");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String get(String url) throws IOException{
		String source ="";
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(
		new InputStreamReader(
		yc.getInputStream()));
		String inputLine;
		 
		while ((inputLine = in.readLine()) != null)
		source +=inputLine;
		in.close();
		return source;
	}

}
