

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
	public TransportLine filterStationsByNameAndShortname(String name, String shortName){
		for (int i=0;i<lineList.size();i++) {
			TransportLine currentLine = lineList.get(i);
			if(currentLine.getName().equals(name) && currentLine.getShortName().equals(shortName)) {
				return currentLine;
			}
		}
		return null;
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
	
	public static void main(String[] args) {
		BusMetroServImpl serv=new BusMetroServImpl();
		User currentUser=new User("Assyl","Louahadj","assyl.louahadj@gmail.com","Toulouse",14.8,15.25);
		currentUser.setId(0);
		System.out.println(serv.getLineList().size());
		TransportLine currentLine=serv.filterStationsByNameAndShortname
					("Borderouge / Ramonville", "B");
		if (currentLine!=null) {
			System.out.println("OK");
			if(currentUser.likeUnlike(currentLine.getId(),true)) {
				System.out.println("OK");
			}
			if(currentUser.likeUnlike(currentLine.getId(),true)) {
				System.out.println("OK");
			}
			if(currentUser.likeUnlike(currentLine.getId(),true)) {
				System.out.println("OK");
			}
			if(currentUser.likeUnlike(currentLine.getId(),true)) {
				System.out.println("OK");
			}
		}
	}
}
