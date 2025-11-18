package models;

import java.io.Serializable;

/**
 * Abstract base class for all beneficiaries in the aid distribution system
 */
public abstract class Beneficiary implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String id;
    protected String name;
    protected String city;
    
    public Beneficiary(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    
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
    
    public abstract String getType();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Beneficiary other = (Beneficiary) obj;
        return id != null && id.equals(other.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", City: " + city + ", Type: " + getType();
    }
}
