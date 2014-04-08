//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2014.04.08 � 01:45:34 AM CEST 
//


package iaws.domain.tisseovelib;

import java.util.ArrayList;

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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adress" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "id",
    "firstName",
    "lastName",
    "mail",
    "adress",
    "coordonnees"
})
@XmlRootElement(name = "user")
public class User {

	private ArrayList<Long> likeList;
	
    protected long id;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String mail;
    @XmlElement(required = true)
    protected String adress;
    @XmlElement(required = true)
    protected Coordonnees coordonnees;

    public User(){
    	likeList=new ArrayList<Long>();
    }
    
    public User(String firstName, String lastName, String mail, String adress, double lat, double lon){
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.mail=mail;
    	this.adress=adress;
    	this.coordonnees=new Coordonnees(lat,lon);
    	likeList=new ArrayList<Long>();
    }
    
    public boolean checkLike(long id){
    	if(likeList.contains(id)){
    		return true;
    	}
    	else return false;
    }
    
    public boolean likeUnlike(long id,boolean like) {
    	if(like && !checkLike(id)){
    		likeList.add(id);
    		return true;
    	}
    	else if(!like && checkLike(id)){
    		likeList.add(id);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Obtient la valeur de la propri�t� id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * D�finit la valeur de la propri�t� id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propri�t� firstName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * D�finit la valeur de la propri�t� firstName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Obtient la valeur de la propri�t� lastName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * D�finit la valeur de la propri�t� lastName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Obtient la valeur de la propri�t� mail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMail() {
        return mail;
    }

    /**
     * D�finit la valeur de la propri�t� mail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMail(String value) {
        this.mail = value;
    }

    /**
     * Obtient la valeur de la propri�t� adress.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdress() {
        return adress;
    }

    /**
     * D�finit la valeur de la propri�t� adress.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdress(String value) {
        this.adress = value;
    }

    /**
     * Obtient la valeur de la propri�t� coordonnees.
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
     * D�finit la valeur de la propri�t� coordonnees.
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
