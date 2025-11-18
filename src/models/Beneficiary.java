package models;

import java.io.Serializable;

/**
 * Abstract base class representing a beneficiary in the aid distribution system.
 * All beneficiaries have a unique ID, name, and city of residence.
 */
public abstract class Beneficiary implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String city;
    
    /**
     * Constructs a new Beneficiary with the specified details.
     * 
     * @param id   Unique identifier for the beneficiary
     * @param name Name of the beneficiary
     * @param city City where the beneficiary resides
     */
    public Beneficiary(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    
    /**
     * Returns the type of beneficiary.
     * 
     * @return String representing the beneficiary type
     */
    public abstract String getType();
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Checks equality based on the beneficiary's ID.
     * 
     * @param obj The object to compare with
     * @return true if the IDs are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Beneficiary)) return false;
        Beneficiary other = (Beneficiary) obj;
        return this.id != null && this.id.equals(other.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    /**
     * Returns a string representation of the beneficiary.
     * 
     * @return String containing beneficiary details
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, City: %s, Type: %s", 
                           id, name, city, getType());
    }
}
