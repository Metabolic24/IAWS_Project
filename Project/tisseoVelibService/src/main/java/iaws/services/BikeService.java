package iaws.services;

import iaws.domain.tisseovelib.BikeStation;
import iaws.domain.tisseovelib.Coordonnees;

public interface BikeService {
	
	public BikeStation filterStationsByName(String name);
	public BikeStation getNearestBikeStation(Coordonnees coord);
}
