package iaws.services;

import iaws.domain.tisseovelib.BikeStation;

public interface AvailableBikesService {
	
	public BikeStation filterStationsByName(String name);
}
