package iaws.services.impl;


import iaws.domain.tisseovelib.CheckPoint;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.domain.tisseovelib.TransportLine;
import iaws.domain.tisseovelib.User;
import iaws.services.BusMetroService;
import iaws.ws.ToolBox;

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
	public TransportLine filterLinesByID(long id){
		for (int i=0;i<lineList.size();i++) {
			if(lineList.get(i).getId()==id) {
				return lineList.get(i); 
			}
		}
		return null;
	}
	
	@Override
	public TransportLine filterLinesByShortname(String shortName){
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
			JSONArray array=new JSONObject(ToolBox.get("http://pt.data.tisseo.fr/departureBoard?stopPointId="+String.valueOf(id)
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
	
	public String getAvailableLines(Coordonnees coordonnees1, Coordonnees coordonnees2){
		String res="";
		ArrayList<TransportLine> lineList=getNearestAvailableLines(coordonnees1, coordonnees2);
		
		for(int i=0;i<lineList.size();i++){
			res+=lineList.get(i).getShortName()+" ; ";
		}
		
		return res;
	}
	
	public ArrayList<TransportLine> getNearestAvailableLines(Coordonnees coord1,Coordonnees coord2) {
		ArrayList<TransportLine>resList = new ArrayList<TransportLine>();
		ArrayList<TransportLine> startCPList = getNearestLines(coord1,10);
		ArrayList<TransportLine> endCPList = getNearestLines(coord2,10);
		
		for(int i=0;i<startCPList.size();i++){
			TransportLine currentLine=startCPList.get(i);
			if(endCPList.contains(currentLine) && !resList.contains(currentLine) && isDisrupted(currentLine.getShortName())){
				resList.add(currentLine);
			}
		}
		
		return resList;
	}
	
	private boolean isDisrupted(String shortName) {
		try {
			JSONArray array=new JSONObject(ToolBox.get("http://pt.data.tisseo.fr/linesDisruptedList?lineShortName="+shortName+
					"&format=json&key=a03561f2fd10641d96fb8188d209414d8")).getJSONArray("lines");
			if(array.length()==0){
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<TransportLine> getNearestLines(Coordonnees coord, int nbResults){
		refreshList();
		ArrayList<String> shortNameList = new ArrayList<String>();
		ArrayList<TransportLine> resList=new ArrayList<TransportLine>();
		
		String box1=String.valueOf((int)(coord.getLatitude()-0.01));
		String box2=String.valueOf((int)(coord.getLongitude()-0.01));
		String box3=String.valueOf((int)(coord.getLatitude()+0.01));
		String box4=String.valueOf((int)(coord.getLongitude()+0.01));
		
		
		try {
			JSONArray array=new JSONObject(ToolBox.get("http://pt.data.tisseo.fr/stopPointsList?bbox="
						+box1+","+box2+","+box3+","+box4+"&sortByDistance=1&displayCoordXY=1&format=json"
					+ "&key=a03561f2fd10641d96fb8188d209414d8")).getJSONObject("physicalStops").getJSONArray("physicalStop");
			
			int max = (nbResults>array.length()) ? array.length() : nbResults;
			
			for (int i=0;i<max;i++) {
				JSONArray array2=array.getJSONObject(i).getJSONArray("destinations");
				for(int j=0;j<array2.length();j++){
					JSONArray array3=array2.getJSONObject(i).getJSONArray("line");
					for(int k=0;k<array3.length();k++){
						String currentShortName=array3.getJSONObject(k).getString("shortName");
						if(!shortNameList.contains(currentShortName)){
							shortNameList.add(currentShortName);
						}
					}
				}
			}
			
			for(int i=0;i<shortNameList.size();i++){
				resList.add(filterLinesByShortname(shortNameList.get(i)));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return resList;
	}
	
	
	public CheckPoint getNearestCheckPoint(Coordonnees coord,long id){
		CheckPoint currentCheckPoint;
		ArrayList<CheckPoint> checkPointList=getLineCheckPoints(id);
		
		if(!checkPointList.isEmpty()){
			CheckPoint res=checkPointList.get(0);
			int bestValue=ToolBox.getDistMeter(coord,res.getCoordonnees());
			
			for (int i=0;i<checkPointList.size();i++) {
				currentCheckPoint=checkPointList.get(i);
				int prov=ToolBox.getDistMeter(coord,currentCheckPoint.getCoordonnees());
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
			JSONArray array=new JSONObject(ToolBox.get("http://pt.data.tisseo.fr/stopPointsList?displayCoordXY=1&format=json&lineId="+String.valueOf(id)
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
			JSONArray array=new JSONObject(ToolBox.get("http://pt.data.tisseo.fr/linesList?format=json"
					+ "&key=a03561f2fd10641d96fb8188d209414d8")).getJSONObject("lines").getJSONArray("line");
			
			for (int i=0;i<array.length();i++) {
				TransportLine currentLine;
				JSONObject line = array.getJSONObject(i);
				if((currentLine=filterLinesByID(line.getLong("id")))==null){
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
	
	public static void main(String[] args) {
		BusMetroServImpl serv=new BusMetroServImpl();
		User currentUser=new User("Assyl","Louahadj","assyl.louahadj@gmail.com","Toulouse",14.8,15.25);
		currentUser.setId(0);
		System.out.println(serv.getLineList().size());
		TransportLine currentLine=serv.filterLinesByShortname("B");
		if (currentLine!=null) {
			System.out.println("OK");
			if(currentUser.likeUnlike(currentLine.getId(),true)) {
				currentLine.setNbLikes(currentLine.getNbLikes()+1);
				System.out.println("OK");
			}
		}		
	}
}
