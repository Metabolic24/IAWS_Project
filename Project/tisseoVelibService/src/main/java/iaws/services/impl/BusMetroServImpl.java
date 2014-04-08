package iaws.services.impl;


import iaws.domain.tisseovelib.CheckPoint;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.domain.tisseovelib.TransportLine;
import iaws.domain.tisseovelib.User;
import iaws.services.BusMetroService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class BusMetroServImpl implements BusMetroService {

	private ArrayList<TransportLine> lineList;
	
	public BusMetroServImpl(){
		lineList=new ArrayList<TransportLine>();
		refreshList();
	}
	
	public ArrayList<TransportLine> getLineList() {
		return lineList;
	}
	
	@Override
	public TransportLine filterStationsByID(long id){
		for (int i=0;i<lineList.size();i++) {
			if(lineList.get(i).getId()==id) {
				return lineList.get(i); 
			}
		}
		return null;
	}
	
	@Override
	public TransportLine filterStationsByShortname(String shortName){
		for (int i=0;i<lineList.size();i++) {
			TransportLine currentLine = lineList.get(i);
			if(currentLine.getShortName().equals(shortName)) {
				return currentLine;
			}
		}
		return null;
	}
	
	public String getNextTimeToCheckPoint(long id){
		String res="";
		try {
			JSONArray array=new JSONObject(get("http://pt.data.tisseo.fr/departureBoards?stopPointId="+String.valueOf(id)
						+"&format=json&number=1&key=a03561f2fd10641d96fb8188d209414d8"))
							.getJSONObject("departures").getJSONArray("departure");
			
			res=array.getJSONObject(0).getString("dateTime");
			res=res.split(" ")[1];
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public CheckPoint getNearestCheckPoint(Coordonnees coord,long id){
		CheckPoint currentCheckPoint;
		ArrayList<CheckPoint> checkPointList=getLineCheckPoints(id);
		
		if(!checkPointList.isEmpty()){
			CheckPoint res=checkPointList.get(0);
			int bestValue=getDistanceEnMetreAvec(coord,res.getCoordonnees());
			
			for (int i=0;i<checkPointList.size();i++) {
				currentCheckPoint=checkPointList.get(i);
				int prov=getDistanceEnMetreAvec(coord,currentCheckPoint.getCoordonnees());
				if(prov<bestValue && prov>=0){
					bestValue=prov;
					res=currentCheckPoint;
				}
			}
			return res;
		}
		else {
			return null;
		}
	}
	
	public ArrayList<CheckPoint> getLineCheckPoints(long id){
		ArrayList<CheckPoint> checkPointList=new ArrayList<CheckPoint>();
		try {
			JSONArray array=new JSONObject(get("http://pt.data.tisseo.fr/stopPointsList?displayCoordXY=1&format=json&lineId="+String.valueOf(id)
					+ "&key=a03561f2fd10641d96fb8188d209414d8")).getJSONObject("physicalStops").getJSONArray("physicalStop");
			
			for (int i=0;i<array.length();i++) {
				CheckPoint currentCheckPoint = new CheckPoint();
				JSONObject checkPoint = array.getJSONObject(i);
				currentCheckPoint.setId(checkPoint.getLong("id"));
				currentCheckPoint.setName(checkPoint.getString("name"));
				currentCheckPoint.setCoordonnees(new Coordonnees(checkPoint.getDouble("y"),checkPoint.getDouble("x")));
				checkPointList.add(currentCheckPoint);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return checkPointList;
	}
	
	
	public void refreshList() {
		try {
			JSONArray array=new JSONObject(get("http://pt.data.tisseo.fr/linesList?format=json"
					+ "&key=a03561f2fd10641d96fb8188d209414d8")).getJSONObject("lines").getJSONArray("line");
			
			for (int i=0;i<array.length();i++) {
				TransportLine currentLine;
				JSONObject line = array.getJSONObject(i);
				if((currentLine=filterStationsByID(line.getLong("id")))==null){
					currentLine=new TransportLine();
					currentLine.setId(line.getLong("id"));
					currentLine.setName(line.getString("name"));
					currentLine.setShortName(line.getString("shortName"));
					currentLine.setNbLikes(0);
					lineList.add(currentLine);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	
	
	public static void main(String[] args) {
		BusMetroServImpl serv=new BusMetroServImpl();
		User currentUser=new User("Assyl","Louahadj","assyl.louahadj@gmail.com","Toulouse",14.8,15.25);
		currentUser.setId(0);
		System.out.println(serv.getLineList().size());
		TransportLine currentLine=serv.filterStationsByShortname("B");
		if (currentLine!=null) {
			System.out.println("OK");
			if(currentUser.likeUnlike(currentLine.getId(),true)) {
				System.out.println("OK");
			}
		}
	}
}
