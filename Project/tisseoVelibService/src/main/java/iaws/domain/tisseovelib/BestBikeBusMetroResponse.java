//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2014.04.09 � 11:31:28 AM CEST 
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
 *         &lt;element name="shortName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startCheckPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endCheckPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "shortName",
    "startCheckPoint",
    "endCheckPoint"
})
@XmlRootElement(name = "BestBikeBusMetroResponse")
public class BestBikeBusMetroResponse {

    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String shortName;
    @XmlElement(required = true)
    protected String startCheckPoint;
    @XmlElement(required = true)
    protected String endCheckPoint;

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
     * Obtient la valeur de la propri�t� shortName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * D�finit la valeur de la propri�t� shortName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Obtient la valeur de la propri�t� startCheckPoint.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCheckPoint() {
        return startCheckPoint;
    }

    /**
     * D�finit la valeur de la propri�t� startCheckPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCheckPoint(String value) {
        this.startCheckPoint = value;
    }

    /**
     * Obtient la valeur de la propri�t� endCheckPoint.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCheckPoint() {
        return endCheckPoint;
    }

    /**
     * D�finit la valeur de la propri�t� endCheckPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCheckPoint(String value) {
        this.endCheckPoint = value;
    }

}
