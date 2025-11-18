package models;

import java.io.Serializable;

/**
 * Abstract base class representing an aid item in the distribution system.
 * All aid items have a unique code and description.
 */
public abstract class AidItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String description;
    
    /**
     * Constructs a new AidItem with the specified details.
     * 
     * @param code        Unique code for the aid item
     * @param description Description of the aid item
     */
    public AidItem(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    /**
     * Returns the category of the aid item.
     * 
     * @return String representing the aid item category
     */
    public abstract String getCategory();
    
    // Getters and Setters
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
    
    @Override
    public String toString() {
        return String.format("Code: %s, Description: %s, Category: %s", 
                           code, description, getCategory());
    }
}
