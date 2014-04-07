package iaws.ws;

import iaws.services.AvailableBikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
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
	public void handleHolidayRequest(@RequestPayload Element availableBikesRequest)               
	      throws Exception {
	    //TODO A compléter
	  }
	
}
