package models;

import java.io.Serializable;

/**
 * Abstract base class for all aid items in the distribution system
 */
public abstract class AidItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String code;
    protected String description;
    
    public AidItem(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public abstract String getCategory();
    
    @Override
    public String toString() {
        return "Code: " + code + ", Description: " + description + ", Category: " + getCategory();
    }
}
