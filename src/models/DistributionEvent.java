package models;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * Represents a distribution event of aid to a beneficiary
 */
public class DistributionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private AidItem item;
    private Beneficiary beneficiary;
    private GregorianCalendar date;
    
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
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Date: " + sdf.format(date.getTime()) + 
               "\nBeneficiary: " + beneficiary.getName() + " (ID: " + beneficiary.getId() + ")" +
               "\nAid Item: " + item.getDescription() + " (Code: " + item.getCode() + ")" +
               "\nCategory: " + item.getCategory();
    }
}
