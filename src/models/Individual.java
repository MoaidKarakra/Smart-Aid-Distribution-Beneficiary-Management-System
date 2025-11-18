package models;

/**
 * Represents an individual beneficiary with status
 */
public class Individual extends Beneficiary implements Comparable<Individual> {
    private static final long serialVersionUID = 1L;
    
    private String status;
    
    public Individual(String id, String name, String city, String status) {
        super(id, name, city);
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String getType() {
        return "Individual";
    }
    
    @Override
    public int compareTo(Individual other) {
        return this.status.compareTo(other.status);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Status: " + status;
    }
}
