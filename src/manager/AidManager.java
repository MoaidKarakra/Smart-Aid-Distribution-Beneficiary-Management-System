package manager;

import models.*;
import exceptions.*;
import java.io.*;
import java.util.*;

/**
 * Main manager class for the aid distribution system.
 * Manages beneficiaries, aid items, and distribution events.
 */
public class AidManager implements FileOperations {
    
    private ArrayList<Beneficiary> beneficiaries;
    private ArrayList<AidItem> aidItems;
    private ArrayList<DistributionEvent> distributionEvents;
    
    // Palestinian cities served by the organization
    private static final String[] SERVED_CITIES = {
        "Gaza", "Rafah", "Khan Younis", "Jerusalem", "Jenin",
        "Tulkarm", "Hebron", "Ramallah", "Jabalia", "Nablus"
    };
    
    /**
     * Constructs a new AidManager with empty lists.
     */
    public AidManager() {
        this.beneficiaries = new ArrayList<>();
        this.aidItems = new ArrayList<>();
        this.distributionEvents = new ArrayList<>();
    }
    
    /**
     * Checks if a city is in the served cities list.
     * 
     * @param city The city to check
     * @return true if the city is served, false otherwise
     */
    private boolean isCityServed(String city) {
        for (String servedCity : SERVED_CITIES) {
            if (servedCity.equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adds a beneficiary to the system with validation.
     * 
     * @param beneficiary The beneficiary to add
     * @throws DuplicateRegistrationException If a beneficiary with the same ID exists
     * @throws CityNotServedException If the beneficiary's city is not served
     */
    public void addBeneficiary(Beneficiary beneficiary) 
            throws DuplicateRegistrationException, CityNotServedException {
        
        // Check for duplicate ID
        for (Beneficiary existing : beneficiaries) {
            if (existing.getId().equals(beneficiary.getId())) {
                throw new DuplicateRegistrationException(
                    "Beneficiary with ID " + beneficiary.getId() + " already exists.");
            }
        }
        
        // Check if city is served
        if (!isCityServed(beneficiary.getCity())) {
            throw new CityNotServedException(
                "City " + beneficiary.getCity() + " is not in the served cities list.");
        }
        
        beneficiaries.add(beneficiary);
    }
    
    /**
     * Adds an aid item to the system.
     * 
     * @param item The aid item to add
     */
    public void addAidItem(AidItem item) {
        aidItems.add(item);
    }
    
    /**
     * Records a distribution event.
     * 
     * @param event The distribution event to record
     */
    public void recordDistribution(DistributionEvent event) {
        distributionEvents.add(event);
    }
    
    /**
     * Finds a beneficiary by their ID.
     * 
     * @param id The beneficiary ID to search for
     * @return The beneficiary if found, null otherwise
     */
    public Beneficiary findBeneficiaryById(String id) {
        for (Beneficiary beneficiary : beneficiaries) {
            if (beneficiary.getId().equals(id)) {
                return beneficiary;
            }
        }
        return null;
    }
    
    /**
     * Finds an aid item by its code.
     * 
     * @param code The aid item code to search for
     * @return The aid item if found, null otherwise
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
     * Displays all registered beneficiaries.
     */
    public void displayAllBeneficiaries() {
        if (beneficiaries.isEmpty()) {
            System.out.println("No beneficiaries registered.");
            return;
        }
        
        System.out.println("\n=== All Beneficiaries ===");
        for (int i = 0; i < beneficiaries.size(); i++) {
            System.out.println((i + 1) + ". " + beneficiaries.get(i));
        }
    }
    
    /**
     * Displays all aid items in the system.
     */
    public void displayAllAidItems() {
        if (aidItems.isEmpty()) {
            System.out.println("No aid items available.");
            return;
        }
        
        System.out.println("\n=== All Aid Items ===");
        for (int i = 0; i < aidItems.size(); i++) {
            System.out.println((i + 1) + ". " + aidItems.get(i));
        }
    }
    
    /**
     * Displays all distribution events.
     */
    public void displayAllDistributionEvents() {
        if (distributionEvents.isEmpty()) {
            System.out.println("No distribution events recorded.");
            return;
        }
        
        System.out.println("\n=== All Distribution Events ===");
        for (int i = 0; i < distributionEvents.size(); i++) {
            System.out.println((i + 1) + ". " + distributionEvents.get(i));
        }
    }
    
    /**
     * Gets the number of families served in a specific city.
     * 
     * @param city The city to check
     * @return The count of families in that city
     */
    public int getFamiliesServedInCity(String city) {
        int count = 0;
        for (Beneficiary beneficiary : beneficiaries) {
            if (beneficiary instanceof Family && 
                beneficiary.getCity().equalsIgnoreCase(city)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gets the total number of aid items distributed.
     * 
     * @return The total count of distribution events
     */
    public int getTotalAidItemsDistributed() {
        return distributionEvents.size();
    }
    
    /**
     * Gets a list of aid items by category.
     * 
     * @param category The category to filter by
     * @return ArrayList of aid items in the specified category
     */
    public ArrayList<AidItem> getItemsByCategory(String category) {
        ArrayList<AidItem> result = new ArrayList<>();
        for (AidItem item : aidItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }
    
    /**
     * Gets beneficiaries who received aid between two dates.
     * 
     * @param startDate Start date (inclusive)
     * @param endDate   End date (inclusive)
     * @return Set of beneficiaries served in the date range
     */
    public Set<Beneficiary> getBeneficiariesServedBetweenDates(
            GregorianCalendar startDate, GregorianCalendar endDate) {
        
        Set<Beneficiary> result = new HashSet<>();
        for (DistributionEvent event : distributionEvents) {
            GregorianCalendar eventDate = event.getDate();
            if (!eventDate.before(startDate) && !eventDate.after(endDate)) {
                result.add(event.getBeneficiary());
            }
        }
        return result;
    }
    
    /**
     * Gets the city with the most beneficiaries served.
     * 
     * @return The name of the most served city
     */
    public String getMostServedCity() {
        if (beneficiaries.isEmpty()) {
            return "No beneficiaries registered";
        }
        
        Map<String, Integer> cityCount = new HashMap<>();
        for (Beneficiary beneficiary : beneficiaries) {
            String city = beneficiary.getCity();
            cityCount.put(city, cityCount.getOrDefault(city, 0) + 1);
        }
        
        String mostServedCity = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : cityCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostServedCity = entry.getKey();
            }
        }
        
        return mostServedCity + " (" + maxCount + " beneficiaries)";
    }
    
    /**
     * Saves all data to a text file.
     * 
     * @param filename The name of the file to save to
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void saveToTextFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Save beneficiaries
            writer.println("=== BENEFICIARIES ===");
            writer.println(beneficiaries.size());
            for (Beneficiary b : beneficiaries) {
                if (b instanceof Family) {
                    Family f = (Family) b;
                    writer.println("Family," + f.getId() + "," + f.getName() + "," + 
                                 f.getCity() + "," + f.getMembersCount());
                } else if (b instanceof Individual) {
                    Individual ind = (Individual) b;
                    writer.println("Individual," + ind.getId() + "," + ind.getName() + "," + 
                                 ind.getCity() + "," + ind.getStatus());
                }
            }
            
            // Save aid items
            writer.println("=== AID ITEMS ===");
            writer.println(aidItems.size());
            for (AidItem item : aidItems) {
                String type = item.getClass().getSimpleName();
                writer.println(type + "," + item.getCode() + "," + item.getDescription());
            }
            
            // Save distribution events
            writer.println("=== DISTRIBUTION EVENTS ===");
            writer.println(distributionEvents.size());
            for (DistributionEvent event : distributionEvents) {
                GregorianCalendar date = event.getDate();
                writer.println(event.getItem().getCode() + "," + 
                             event.getBeneficiary().getId() + "," +
                             date.get(GregorianCalendar.DAY_OF_MONTH) + "," +
                             (date.get(GregorianCalendar.MONTH) + 1) + "," +
                             date.get(GregorianCalendar.YEAR));
            }
        }
    }
    
    /**
     * Loads all data from a text file.
     * 
     * @param filename The name of the file to load from
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void loadFromTextFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            beneficiaries.clear();
            aidItems.clear();
            distributionEvents.clear();
            
            // Load beneficiaries
            reader.readLine(); // Skip header
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                String[] parts = line.split(",");
                if (parts[0].equals("Family")) {
                    beneficiaries.add(new Family(parts[1], parts[2], parts[3], 
                                                Integer.parseInt(parts[4])));
                } else if (parts[0].equals("Individual")) {
                    beneficiaries.add(new Individual(parts[1], parts[2], parts[3], parts[4]));
                }
            }
            
            // Load aid items
            reader.readLine(); // Skip header
            count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "FoodPackage":
                        aidItems.add(new FoodPackage(parts[1], parts[2]));
                        break;
                    case "MedicalKit":
                        aidItems.add(new MedicalKit(parts[1], parts[2]));
                        break;
                    case "WinterBag":
                        aidItems.add(new WinterBag(parts[1], parts[2]));
                        break;
                    case "EmergencyKit":
                        aidItems.add(new EmergencyKit(parts[1], parts[2]));
                        break;
                }
            }
            
            // Load distribution events
            reader.readLine(); // Skip header
            count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                String[] parts = line.split(",");
                AidItem item = findAidItemByCode(parts[0]);
                Beneficiary beneficiary = findBeneficiaryById(parts[1]);
                GregorianCalendar date = new GregorianCalendar(
                    Integer.parseInt(parts[4]), // year
                    Integer.parseInt(parts[3]) - 1, // month (0-based)
                    Integer.parseInt(parts[2]) // day
                );
                if (item != null && beneficiary != null) {
                    distributionEvents.add(new DistributionEvent(item, beneficiary, date));
                }
            }
        }
    }
    
    /**
     * Saves all data to a binary file.
     * 
     * @param filename The name of the file to save to
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void saveToBinaryFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(beneficiaries);
            oos.writeObject(aidItems);
            oos.writeObject(distributionEvents);
        }
    }
    
    /**
     * Loads all data from a binary file.
     * 
     * @param filename The name of the file to load from
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException If the class of a serialized object cannot be found
     */
    @Override
    @SuppressWarnings("unchecked")
    public void loadFromBinaryFile(String filename) 
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            beneficiaries = (ArrayList<Beneficiary>) ois.readObject();
            aidItems = (ArrayList<AidItem>) ois.readObject();
            distributionEvents = (ArrayList<DistributionEvent>) ois.readObject();
        }
    }
    
    // Getters for the collections
    public ArrayList<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }
    
    public ArrayList<AidItem> getAidItems() {
        return aidItems;
    }
    
    public ArrayList<DistributionEvent> getDistributionEvents() {
        return distributionEvents;
    }
    
    public String[] getServedCities() {
        return SERVED_CITIES.clone();
    }
}
