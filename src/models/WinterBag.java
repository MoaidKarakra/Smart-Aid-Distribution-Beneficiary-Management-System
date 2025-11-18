package models;

/**
 * Represents a winter bag aid item.
 */
public class WinterBag extends AidItem {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new WinterBag aid item.
     * 
     * @param code        Unique code for the winter bag
     * @param description Description of the winter bag
     */
    public WinterBag(String code, String description) {
        super(code, description);
    }
    
    /**
     * Returns the category of the aid item.
     * 
     * @return "Winter Bag"
     */
    @Override
    public String getCategory() {
        return "Winter Bag";
    }
}
