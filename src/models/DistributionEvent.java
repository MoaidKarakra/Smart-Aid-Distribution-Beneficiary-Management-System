package models;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Represents a distribution event where an aid item is given to a beneficiary.
 */
public class DistributionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private AidItem item;
    private Beneficiary beneficiary;
    private GregorianCalendar date;
    
    /**
     * Constructs a new DistributionEvent.
     * 
     * @param item        The aid item being distributed
     * @param beneficiary The beneficiary receiving the aid
     * @param date        The date of distribution
     */
    public DistributionEvent(AidItem item, Beneficiary beneficiary, GregorianCalendar date) {
        this.item = item;
        this.beneficiary = beneficiary;
        this.date = date;
    }
    
    public AidItem getItem() {
        return item;
    }
    
    public void setItem(AidItem item) {
        this.item = item;
    }
    
    public Beneficiary getBeneficiary() {
        return beneficiary;
    }
    
    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }
    
    public GregorianCalendar getDate() {
        return date;
    }
    
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }
    
    /**
     * Returns a string representation of the distribution event.
     * 
     * @return Formatted string with event details
     */
    @Override
    public String toString() {
        String dateStr = String.format("%02d/%02d/%d", 
                                      date.get(GregorianCalendar.DAY_OF_MONTH),
                                      date.get(GregorianCalendar.MONTH) + 1,
                                      date.get(GregorianCalendar.YEAR));
        
        return String.format("Date: %s | Item: %s (%s) | Beneficiary: %s (%s) from %s",
                           dateStr,
                           item.getCode(),
                           item.getCategory(),
                           beneficiary.getName(),
                           beneficiary.getType(),
                           beneficiary.getCity());
    }
}
