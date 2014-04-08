
public interface BusMetroService {

	public TransportLine filterStationsByID(long id);
	public TransportLine filterStationsByNameAndShortname(String name, String shortName);
}
