//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.04.07 à 05:52:44 PM CEST 
//


package iaws.domain.tisseovelib;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.tisseovelib package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.tisseovelib
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TransportLine }
     * 
     */
    public TransportLine createTransportLine() {
        return new TransportLine();
    }

    /**
     * Create an instance of {@link AvailableBikesResponse }
     * 
     */
    public AvailableBikesResponse createAvailableBikesResponse() {
        return new AvailableBikesResponse();
    }

    /**
     * Create an instance of {@link NextBusMetroResponse }
     * 
     */
    public NextBusMetroResponse createNextBusMetroResponse() {
        return new NextBusMetroResponse();
    }

    /**
     * Create an instance of {@link AvailableBikesRequest }
     * 
     */
    public AvailableBikesRequest createAvailableBikesRequest() {
        return new AvailableBikesRequest();
    }

    /**
     * Create an instance of {@link Bikestation }
     * 
     */
    public Bikestation createBikestation() {
        return new Bikestation();
    }

    /**
     * Create an instance of {@link Coordonnees }
     * 
     */
    public Coordonnees createCoordonnees() {
        return new Coordonnees();
    }

    /**
     * Create an instance of {@link LikeResponse }
     * 
     */
    public LikeResponse createLikeResponse() {
        return new LikeResponse();
    }

    /**
     * Create an instance of {@link LikeRequest }
     * 
     */
    public LikeRequest createLikeRequest() {
        return new LikeRequest();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link NextBusMetroRequest }
     * 
     */
    public NextBusMetroRequest createNextBusMetroRequest() {
        return new NextBusMetroRequest();
    }

}
