
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
			JSONArray array = new JSONArray(get(
					"https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse"
						+ "&apiKey=e3b3de809763e6912653716a66ea7cea8a175a16"));
			
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
	
	public static void main(String args[]) {
		System.out.println(new BikeServImpl().filterStationsByName("00040 - COLOMBETTE").getAvailableBikes());
	}
}
