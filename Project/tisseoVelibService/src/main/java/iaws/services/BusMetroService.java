package iaws.services;

import iaws.domain.tisseovelib.TransportLine;

public interface BusMetroService {

	public TransportLine filterStationsByID(long id);
	
}
