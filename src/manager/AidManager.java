package manager;

import models.*;
import exceptions.*;
import java.io.*;
import java.util.*;

/**
 * Core system class for managing aid distribution
 */
public class AidManager implements FileOperations {
    private ArrayList<Beneficiary> beneficiaries;
    private ArrayList<AidItem> aidItems;
    private ArrayList<DistributionEvent> distributionEvents;
    
    private static final String[] SERVED_CITIES = {
        "Gaza", "Rafah", "Khan Younis", "Jerusalem", "Jenin", 
        "Tulkarm", "Hebron", "Ramallah", "Jabalia", "Nablus"
    };
    
    public AidManager() {
        this.beneficiaries = new ArrayList<>();
        this.aidItems = new ArrayList<>();
        this.distributionEvents = new ArrayList<>();
    }
    
    /**
     * Register a new beneficiary
     */
    public void registerBeneficiary(Beneficiary beneficiary) throws DuplicateRegistrationException, CityNotServedException {
        // Check for duplicates
        for (Beneficiary b : beneficiaries) {
            if (b.getId().equals(beneficiary.getId())) {
                throw new DuplicateRegistrationException("Beneficiary with ID " + beneficiary.getId() + " already exists");
            }
        }
        
        // Validate city
        if (!isCityServed(beneficiary.getCity())) {
            throw new CityNotServedException("City " + beneficiary.getCity() + " is not in the served areas");
        }
        
        beneficiaries.add(beneficiary);
    }
    
    /**
     * Add a new aid item
     */
    public void addAidItem(AidItem item) throws DuplicateRegistrationException {
        // Check for duplicates
        for (AidItem ai : aidItems) {
            if (ai.getCode().equals(item.getCode())) {
                throw new DuplicateRegistrationException("Aid item with code " + item.getCode() + " already exists");
            }
        }
        
        aidItems.add(item);
    }
    
    /**
     * Record a distribution event
     */
    public void recordDistribution(String beneficiaryId, String itemCode, GregorianCalendar date) 
            throws ItemNotFoundException {
        Beneficiary beneficiary = findBeneficiaryById(beneficiaryId);
        if (beneficiary == null) {
            throw new ItemNotFoundException("Beneficiary with ID " + beneficiaryId + " not found");
        }
        
        AidItem item = findAidItemByCode(itemCode);
        if (item == null) {
            throw new ItemNotFoundException("Aid item with code " + itemCode + " not found");
        }
        
        DistributionEvent event = new DistributionEvent(item, beneficiary, date);
        distributionEvents.add(event);
    }
    
    /**
     * Find beneficiary by ID
     */
    public Beneficiary findBeneficiaryById(String id) {
        for (Beneficiary b : beneficiaries) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }
    
    /**
     * Find aid item by code
     */
    public AidItem findAidItemByCode(String code) {
        for (AidItem item : aidItems) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Check if a city is served
     */
    private boolean isCityServed(String city) {
        for (String servedCity : SERVED_CITIES) {
            if (servedCity.equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }
    
    // Statistical reporting methods
    
    /**
     * Get number of families served in a specific city
     */
    public int getFamiliesServedInCity(String city) {
        Set<String> uniqueFamilyIds = new HashSet<>();
        for (DistributionEvent event : distributionEvents) {
            Beneficiary b = event.getBeneficiary();
            if (b instanceof Family && b.getCity().equalsIgnoreCase(city)) {
                uniqueFamilyIds.add(b.getId());
            }
        }
        return uniqueFamilyIds.size();
    }
    
    /**
     * Get total number of aid items distributed
     */
    public int getTotalAidItemsDistributed() {
        return distributionEvents.size();
    }
    
    /**
     * Get count of aid items distributed by category
     */
    public Map<String, Integer> getAidItemsByCategory() {
        Map<String, Integer> categoryCount = new HashMap<>();
        for (DistributionEvent event : distributionEvents) {
            String category = event.getItem().getCategory();
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }
        return categoryCount;
    }
    
    /**
     * Get beneficiaries served between two dates
     */
    public List<Beneficiary> getBeneficiariesServedBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate) {
        Set<String> uniqueBeneficiaryIds = new HashSet<>();
        List<Beneficiary> result = new ArrayList<>();
        
        for (DistributionEvent event : distributionEvents) {
            GregorianCalendar eventDate = event.getDate();
            if (!eventDate.before(startDate) && !eventDate.after(endDate)) {
                Beneficiary b = event.getBeneficiary();
                if (!uniqueBeneficiaryIds.contains(b.getId())) {
                    uniqueBeneficiaryIds.add(b.getId());
                    result.add(b);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Get the most frequently served city
     */
    public String getMostServedCity() {
        Map<String, Integer> cityCount = new HashMap<>();
        for (DistributionEvent event : distributionEvents) {
            String city = event.getBeneficiary().getCity();
            cityCount.put(city, cityCount.getOrDefault(city, 0) + 1);
        }
        
        if (cityCount.isEmpty()) {
            return "None";
        }
        
        String mostServedCity = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : cityCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostServedCity = entry.getKey();
            }
        }
        
        return mostServedCity;
    }
    
    // Display methods
    
    public List<Beneficiary> getAllBeneficiaries() {
        return new ArrayList<>(beneficiaries);
    }
    
    public List<AidItem> getAllAidItems() {
        return new ArrayList<>(aidItems);
    }
    
    public List<DistributionEvent> getAllDistributionEvents() {
        return new ArrayList<>(distributionEvents);
    }
    
    // File operations
    
    @Override
    public void saveToTextFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Save beneficiaries
            writer.println("=== BENEFICIARIES ===");
            for (Beneficiary b : beneficiaries) {
                if (b instanceof Family) {
                    Family f = (Family) b;
                    writer.println("FAMILY|" + f.getId() + "|" + f.getName() + "|" + 
                                 f.getCity() + "|" + f.getMembersCount());
                } else if (b instanceof Individual) {
                    Individual i = (Individual) b;
                    writer.println("INDIVIDUAL|" + i.getId() + "|" + i.getName() + "|" + 
                                 i.getCity() + "|" + i.getStatus());
                }
            }
            
            // Save aid items
            writer.println("=== AID ITEMS ===");
            for (AidItem item : aidItems) {
                writer.println(item.getClass().getSimpleName() + "|" + 
                             item.getCode() + "|" + item.getDescription());
            }
            
            // Save distribution events
            writer.println("=== DISTRIBUTION EVENTS ===");
            for (DistributionEvent event : distributionEvents) {
                writer.println(event.getBeneficiary().getId() + "|" + 
                             event.getItem().getCode() + "|" +
                             event.getDate().getTimeInMillis());
            }
        }
    }
    
    @Override
    public void loadFromTextFile(String filename) throws IOException {
        beneficiaries.clear();
        aidItems.clear();
        distributionEvents.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String section = "";
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                if (line.startsWith("===")) {
                    section = line;
                    continue;
                }
                
                String[] parts = line.split("\\|");
                
                if (section.contains("BENEFICIARIES")) {
                    if (parts[0].equals("FAMILY")) {
                        Family f = new Family(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                        beneficiaries.add(f);
                    } else if (parts[0].equals("INDIVIDUAL")) {
                        Individual i = new Individual(parts[1], parts[2], parts[3], parts[4]);
                        beneficiaries.add(i);
                    }
                } else if (section.contains("AID ITEMS")) {
                    AidItem item = null;
                    switch (parts[0]) {
                        case "FoodPackage":
                            item = new FoodPackage(parts[1], parts[2]);
                            break;
                        case "MedicalKit":
                            item = new MedicalKit(parts[1], parts[2]);
                            break;
                        case "WinterBag":
                            item = new WinterBag(parts[1], parts[2]);
                            break;
                        case "EmergencyKit":
                            item = new EmergencyKit(parts[1], parts[2]);
                            break;
                    }
                    if (item != null) {
                        aidItems.add(item);
                    }
                } else if (section.contains("DISTRIBUTION EVENTS")) {
                    Beneficiary b = findBeneficiaryById(parts[0]);
                    AidItem item = findAidItemByCode(parts[1]);
                    if (b != null && item != null) {
                        GregorianCalendar date = new GregorianCalendar();
                        date.setTimeInMillis(Long.parseLong(parts[2]));
                        DistributionEvent event = new DistributionEvent(item, b, date);
                        distributionEvents.add(event);
                    }
                }
            }
        }
    }
    
    @Override
    public void saveToBinaryFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(beneficiaries);
            oos.writeObject(aidItems);
            oos.writeObject(distributionEvents);
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void loadFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            beneficiaries = (ArrayList<Beneficiary>) ois.readObject();
            aidItems = (ArrayList<AidItem>) ois.readObject();
            distributionEvents = (ArrayList<DistributionEvent>) ois.readObject();
        }
    }
    
    public String[] getServedCities() {
        return SERVED_CITIES;
    }
}
