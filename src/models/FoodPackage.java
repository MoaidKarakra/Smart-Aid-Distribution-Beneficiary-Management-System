package models;

/**
 * Represents a food package aid item
 */
public class FoodPackage extends AidItem {
    private static final long serialVersionUID = 1L;
    
    public FoodPackage(String code, String description) {
        super(code, description);
    }
    
    @Override
    public String getCategory() {
        return "Food Package";
    }
}
