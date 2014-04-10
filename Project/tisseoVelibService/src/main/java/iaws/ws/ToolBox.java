package iaws.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import iaws.domain.tisseovelib.Coordonnees;
import iaws.domain.tisseovelib.User;

public class ToolBox{
	public static int getDistMeter(Coordonnees coordonnees1,Coordonnees coordonnees2){
		return getDistanceBetween(coordonnees1.getLatitude(), coordonnees1.getLongitude(),
				coordonnees2.getLatitude(),coordonnees2.getLongitude());

	}

	//http://stackoverflow.com/questions/27928/how-do-i-calculate-distance-between-two-latitude-longitude-points
	private static int getDistanceBetween(double lat1, double lon1, double lat2,double lon2) {
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

	private static double deg2rad(double deg) {
		return deg * (Math.PI/180);
	}
	
	public static String get(String url) throws IOException{
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
	
	public static User filterUserByID(ArrayList<User> userList,long id){
		for (int i=0;i<userList.size();i++) {
			if(userList.get(i).getId()==id) {
				return userList.get(i); 
			}
		}
		return null;
	}
	
}