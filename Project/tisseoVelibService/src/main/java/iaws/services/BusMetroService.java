package iaws.services;

import iaws.domain.tisseovelib.CheckPoint;
import iaws.domain.tisseovelib.Coordonnees;
import iaws.domain.tisseovelib.TransportLine;

public interface BusMetroService {

	public TransportLine filterLinesByID(long id);
	public TransportLine filterLinesByShortname(String shortName);
	public CheckPoint getNearestCheckPoint(Coordonnees coord,long id);
	public String getNextTimeToCheckPoint(long id);
	public String getAvailableLines(
			Coordonnees coordonnees1, Coordonnees coordonnees2);
}
