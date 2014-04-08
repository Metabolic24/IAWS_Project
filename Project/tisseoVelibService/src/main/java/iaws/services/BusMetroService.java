package iaws.services;

import iaws.domain.tisseovelib.CheckPoint;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.domain.tisseovelib.TransportLine;

public interface BusMetroService {

	public TransportLine filterStationsByID(long id);
	public TransportLine filterStationsByShortname(String shortName);
	public CheckPoint getNearestCheckPoint(Coordonnees coord,long id);
	public String getNextTimeToCheckPoint(long id);
}
