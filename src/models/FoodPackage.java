package models;

/**
 * Represents a food package aid item.
 */
public class FoodPackage extends AidItem {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new FoodPackage aid item.
     * 
     * @param code        Unique code for the food package
     * @param description Description of the food package
     */
    public FoodPackage(String code, String description) {
        super(code, description);
    }
    
    /**
     * Returns the category of the aid item.
     * 
     * @return "Food Package"
     */
    @Override
    public String getCategory() {
        return "Food Package";
    }
}
