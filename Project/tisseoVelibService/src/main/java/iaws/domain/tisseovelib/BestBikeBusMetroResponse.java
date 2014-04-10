//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.04.10 à 01:23:02 PM CEST 
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
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timeEstimed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="linesAvailable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startBikeStation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endBikeStation" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "type",
    "timeEstimed",
    "linesAvailable",
    "startBikeStation",
    "endBikeStation"
})
@XmlRootElement(name = "BestBikeBusMetroResponse")
public class BestBikeBusMetroResponse {

    @XmlElement(required = true)
    protected String type;
    protected int timeEstimed;
    @XmlElement(required = true)
    protected String linesAvailable;
    @XmlElement(required = true)
    protected String startBikeStation;
    @XmlElement(required = true)
    protected String endBikeStation;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété timeEstimed.
     * 
     */
    public int getTimeEstimed() {
        return timeEstimed;
    }

    /**
     * Définit la valeur de la propriété timeEstimed.
     * 
     */
    public void setTimeEstimed(int value) {
        this.timeEstimed = value;
    }

    /**
     * Obtient la valeur de la propriété linesAvailable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesAvailable() {
        return linesAvailable;
    }

    /**
     * Définit la valeur de la propriété linesAvailable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesAvailable(String value) {
        this.linesAvailable = value;
    }

    /**
     * Obtient la valeur de la propriété startBikeStation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartBikeStation() {
        return startBikeStation;
    }

    /**
     * Définit la valeur de la propriété startBikeStation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartBikeStation(String value) {
        this.startBikeStation = value;
    }

    /**
     * Obtient la valeur de la propriété endBikeStation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndBikeStation() {
        return endBikeStation;
    }

    /**
     * Définit la valeur de la propriété endBikeStation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndBikeStation(String value) {
        this.endBikeStation = value;
    }

}
