package models;

/**
 * Represents an individual beneficiary in the aid distribution system.
 * Individuals are sorted by their status priority.
 */
public class Individual extends Beneficiary implements Comparable<Individual> {
    private static final long serialVersionUID = 1L;
    
    private String status; // Elderly, Student, Patient, Orphan
    
    /**
     * Constructs a new Individual beneficiary.
     * 
     * @param id     Unique identifier for the individual
     * @param name   Individual's name
     * @param city   City where the individual resides
     * @param status Individual's status (Elderly, Student, Patient, Orphan)
     */
    public Individual(String id, String name, String city, String status) {
        super(id, name, city);
        this.status = status;
    }
    
    /**
     * Returns the type of beneficiary.
     * 
     * @return "Individual"
     */
    @Override
    public String getType() {
        return "Individual";
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Returns the priority value for a given status.
     * Lower values indicate higher priority.
     * 
     * @param status The status to evaluate
     * @return Priority value (0-3)
     */
    private int getStatusPriority(String status) {
        if (status == null) return 4;
        switch (status.toLowerCase()) {
            case "elderly": return 0;
            case "orphan": return 1;
            case "patient": return 2;
            case "student": return 3;
            default: return 4;
        }
    }
    
    /**
     * Compares individuals based on their status priority.
     * Higher priority statuses come first.
     * 
     * @param other The individual to compare with
     * @return Negative if this has higher priority, positive if lower, 0 if equal
     */
    @Override
    public int compareTo(Individual other) {
        return Integer.compare(getStatusPriority(this.status), 
                             getStatusPriority(other.status));
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(", Status: %s", status);
    }
}
