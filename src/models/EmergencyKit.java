package models;

/**
 * Represents an emergency kit aid item.
 */
public class EmergencyKit extends AidItem {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new EmergencyKit aid item.
     * 
     * @param code        Unique code for the emergency kit
     * @param description Description of the emergency kit
     */
    public EmergencyKit(String code, String description) {
        super(code, description);
    }
    
    /**
     * Returns the category of the aid item.
     * 
     * @return "Emergency Kit"
     */
    @Override
    public String getCategory() {
        return "Emergency Kit";
    }
}
