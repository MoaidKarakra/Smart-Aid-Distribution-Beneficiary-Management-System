package models;

/**
 * Represents a family beneficiary with member count
 */
public class Family extends Beneficiary implements Comparable<Family> {
    private static final long serialVersionUID = 1L;
    
    private int membersCount;
    
    public Family(String id, String name, String city, int membersCount) {
        super(id, name, city);
        this.membersCount = membersCount;
    }
    
    public int getMembersCount() {
        return membersCount;
    }
    
    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }
    
    @Override
    public String getType() {
        return "Family";
    }
    
    @Override
    public int compareTo(Family other) {
        return Integer.compare(this.membersCount, other.membersCount);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Members: " + membersCount;
    }
}
