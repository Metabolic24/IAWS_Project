package iaws.ws;

import iaws.domain.tisseovelib.AvailableBikesRequest;
import iaws.domain.tisseovelib.AvailableBikesResponse;
import iaws.services.AvailableBikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;

@Endpoint
public class TisseoVelibEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.example.org/TisseoVelib";

	private XPath startDateExpression;
	
	private XPath endDateExpression;
	
	private XPath nameExpression;
	
	private AvailableBikesService bikeService;
	
	@Autowired
	public TisseoVelibEndpoint(AvailableBikesService bikeService)                      
	      throws JDOMException {
	    this.bikeService = bikeService;
	
	    Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);
	
	startDateExpression = XPath.newInstance("//hr:StartDate");
	startDateExpression.addNamespace(namespace);
	
	endDateExpression = XPath.newInstance("//hr:EndDate");
	endDateExpression.addNamespace(namespace);
	
	nameExpression = XPath.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
	    nameExpression.addNamespace(namespace);
	  }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AvailableBikesRequest")                  
	public @ResponsePayload AvailableBikesResponse handleHolidayRequest(@RequestPayload AvailableBikesRequest availableBikesRequest)               
	      throws Exception {
			//TODO A compléter
			String name=availableBikesRequest.getName();
			
			//GET https://api.jcdecaux.com/vls/v1/stations/{station_number}?contract={contract_name} HTTP/1.1
			//Pour récupérer les infos de la station dont le numéro est passé en paramètre 
			bikeService.get("");
			AvailableBikesResponse response=new AvailableBikesResponse();
			return response;
	}
}
