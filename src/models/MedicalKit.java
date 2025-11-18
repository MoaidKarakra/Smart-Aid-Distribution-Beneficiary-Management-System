package models;

/**
 * Represents a medical kit aid item.
 */
public class MedicalKit extends AidItem {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new MedicalKit aid item.
     * 
     * @param code        Unique code for the medical kit
     * @param description Description of the medical kit
     */
    public MedicalKit(String code, String description) {
        super(code, description);
    }
    
    /**
     * Returns the category of the aid item.
     * 
     * @return "Medical Kit"
     */
    @Override
    public String getCategory() {
        return "Medical Kit";
    }
}
