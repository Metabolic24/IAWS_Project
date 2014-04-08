package iaws.services;

import iaws.domain.tisseovelib.BikeStation;

public interface BikeService {
	
	public BikeStation filterStationsByName(String name);
}
