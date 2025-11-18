package models;

/**
 * Represents a medical kit aid item
 */
public class MedicalKit extends AidItem {
    private static final long serialVersionUID = 1L;
    
    public MedicalKit(String code, String description) {
        super(code, description);
    }
    
    @Override
    public String getCategory() {
        return "Medical Kit";
    }
}
