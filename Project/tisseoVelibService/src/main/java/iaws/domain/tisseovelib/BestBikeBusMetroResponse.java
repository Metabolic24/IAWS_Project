//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2014.04.10 � 01:23:02 PM CEST 
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
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� type.
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
     * D�finit la valeur de la propri�t� type.
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
     * Obtient la valeur de la propri�t� timeEstimed.
     * 
     */
    public int getTimeEstimed() {
        return timeEstimed;
    }

    /**
     * D�finit la valeur de la propri�t� timeEstimed.
     * 
     */
    public void setTimeEstimed(int value) {
        this.timeEstimed = value;
    }

    /**
     * Obtient la valeur de la propri�t� linesAvailable.
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
     * D�finit la valeur de la propri�t� linesAvailable.
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
     * Obtient la valeur de la propri�t� startBikeStation.
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
     * D�finit la valeur de la propri�t� startBikeStation.
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
     * Obtient la valeur de la propri�t� endBikeStation.
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
     * D�finit la valeur de la propri�t� endBikeStation.
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
