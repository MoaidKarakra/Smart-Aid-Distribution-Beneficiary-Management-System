package models;

/**
 * Represents a family beneficiary in the aid distribution system.
 * Families are sorted by their member count.
 */
public class Family extends Beneficiary implements Comparable<Family> {
    private static final long serialVersionUID = 1L;
    
    private int membersCount;
    
    /**
     * Constructs a new Family beneficiary.
     * 
     * @param id           Unique identifier for the family
     * @param name         Family name
     * @param city         City where the family resides
     * @param membersCount Number of family members
     */
    public Family(String id, String name, String city, int membersCount) {
        super(id, name, city);
        this.membersCount = membersCount;
    }
    
    /**
     * Returns the type of beneficiary.
     * 
     * @return "Family"
     */
    @Override
    public String getType() {
        return "Family";
    }
    
    public int getMembersCount() {
        return membersCount;
    }
    
    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }
    
    /**
     * Compares families based on their member count.
     * Families with more members come first.
     * 
     * @param other The family to compare with
     * @return Negative if this family is larger, positive if smaller, 0 if equal
     */
    @Override
    public int compareTo(Family other) {
        return Integer.compare(other.membersCount, this.membersCount);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(", Members: %d", membersCount);
    }
}
