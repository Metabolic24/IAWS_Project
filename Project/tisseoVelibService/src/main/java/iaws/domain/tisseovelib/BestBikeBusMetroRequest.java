//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.04.09 à 11:31:28 AM CEST 
//


package iaws.domain.tisseovelib;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.example.org/TisseoVelib}coordonnees"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "coordonnees"
})
@XmlRootElement(name = "BestBikeBusMetroRequest")
public class BestBikeBusMetroRequest {

    @XmlElement(required = true)
    protected Coordonnees coordonnees;

    /**
     * Obtient la valeur de la propriété coordonnees.
     * 
     * @return
     *     possible object is
     *     {@link Coordonnees }
     *     
     */
    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    /**
     * Définit la valeur de la propriété coordonnees.
     * 
     * @param value
     *     allowed object is
     *     {@link Coordonnees }
     *     
     */
    public void setCoordonnees(Coordonnees value) {
        this.coordonnees = value;
    }

}
