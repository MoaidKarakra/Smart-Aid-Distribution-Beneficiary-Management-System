import manager.AidManager;
import models.*;
import exceptions.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Main driver class for the Smart Aid Distribution System
 */
public class AidSystemDriver {
    private static AidManager aidManager = new AidManager();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        registerBeneficiary();
                        break;
                    case 2:
                        addAidItem();
                        break;
                    case 3:
                        recordDistribution();
                        break;
                    case 4:
                        displayAllBeneficiaries();
                        break;
                    case 5:
                        displayAllAidItems();
                        break;
                    case 6:
                        displayAllDistributionEvents();
                        break;
                    case 7:
                        statisticalReportsMenu();
                        break;
                    case 8:
                        saveToTextFile();
                        break;
                    case 9:
                        saveToBinaryFile();
                        break;
                    case 10:
                        loadFromTextFile();
                        break;
                    case 11:
                        loadFromBinaryFile();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using Smart Aid Distribution System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n==== Smart Aid Distribution System ====");
        System.out.println("1. Register Beneficiary");
        System.out.println("2. Add Aid Item");
        System.out.println("3. Record Aid Distribution");
        System.out.println("4. Display All Beneficiaries");
        System.out.println("5. Display All Aid Items");
        System.out.println("6. Display All Distribution Events");
        System.out.println("7. Generate Statistical Reports");
        System.out.println("8. Save to Text File");
        System.out.println("9. Save to Binary File");
        System.out.println("10. Load from Text File");
        System.out.println("11. Load from Binary File");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void registerBeneficiary() {
        try {
            System.out.println("\n=== Register Beneficiary ===");
            System.out.print("Enter beneficiary type (1-Family, 2-Individual): ");
            int type = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter ID: ");
            String id = scanner.nextLine().trim();
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            
            System.out.println("Served cities: " + String.join(", ", aidManager.getServedCities()));
            System.out.print("Enter City: ");
            String city = scanner.nextLine().trim();
            
            Beneficiary beneficiary = null;
            
            if (type == 1) {
                System.out.print("Enter number of family members: ");
                int membersCount = Integer.parseInt(scanner.nextLine().trim());
                beneficiary = new Family(id, name, city, membersCount);
            } else if (type == 2) {
                System.out.print("Enter status (e.g., Elderly, Student, Patient, Orphan): ");
                String status = scanner.nextLine().trim();
                beneficiary = new Individual(id, name, city, status);
            } else {
                System.out.println("Invalid beneficiary type.");
                return;
            }
            
            aidManager.registerBeneficiary(beneficiary);
            System.out.println("Beneficiary registered successfully!");
            
        } catch (DuplicateRegistrationException | CityNotServedException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        }
    }
    
    private static void addAidItem() {
        try {
            System.out.println("\n=== Add Aid Item ===");
            System.out.println("1. Food Package");
            System.out.println("2. Medical Kit");
            System.out.println("3. Winter Bag");
            System.out.println("4. Emergency Kit");
            System.out.print("Enter item type: ");
            int type = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter item code: ");
            String code = scanner.nextLine().trim();
            
            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();
            
            AidItem item = null;
            
            switch (type) {
                case 1:
                    item = new FoodPackage(code, description);
                    break;
                case 2:
                    item = new MedicalKit(code, description);
                    break;
                case 3:
                    item = new WinterBag(code, description);
                    break;
                case 4:
                    item = new EmergencyKit(code, description);
                    break;
                default:
                    System.out.println("Invalid item type.");
                    return;
            }
            
            aidManager.addAidItem(item);
            System.out.println("Aid item added successfully!");
            
        } catch (DuplicateRegistrationException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        }
    }
    
    private static void recordDistribution() {
        try {
            System.out.println("\n=== Record Aid Distribution ===");
            System.out.print("Enter beneficiary ID: ");
            String beneficiaryId = scanner.nextLine().trim();
            
            System.out.print("Enter aid item code: ");
            String itemCode = scanner.nextLine().trim();
            
            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine().trim();
            
            Date date = dateFormat.parse(dateStr);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            
            aidManager.recordDistribution(beneficiaryId, itemCode, calendar);
            System.out.println("Distribution recorded successfully!");
            
        } catch (ItemNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd format.");
        }
    }
    
    private static void displayAllBeneficiaries() {
        System.out.println("\n=== All Beneficiaries ===");
        List<Beneficiary> beneficiaries = aidManager.getAllBeneficiaries();
        
        if (beneficiaries.isEmpty()) {
            System.out.println("No beneficiaries registered.");
        } else {
            for (int i = 0; i < beneficiaries.size(); i++) {
                System.out.println((i + 1) + ". " + beneficiaries.get(i));
            }
        }
    }
    
    private static void displayAllAidItems() {
        System.out.println("\n=== All Aid Items ===");
        List<AidItem> items = aidManager.getAllAidItems();
        
        if (items.isEmpty()) {
            System.out.println("No aid items registered.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
    
    private static void displayAllDistributionEvents() {
        System.out.println("\n=== All Distribution Events ===");
        List<DistributionEvent> events = aidManager.getAllDistributionEvents();
        
        if (events.isEmpty()) {
            System.out.println("No distribution events recorded.");
        } else {
            for (int i = 0; i < events.size(); i++) {
                System.out.println("\n" + (i + 1) + ". " + events.get(i));
                System.out.println("---");
            }
        }
    }
    
    private static void statisticalReportsMenu() {
        boolean inReportsMenu = true;
        
        while (inReportsMenu) {
            System.out.println("\n==== Statistical Reports ====");
            System.out.println("1. Number of families served in a city");
            System.out.println("2. Total aid items distributed");
            System.out.println("3. Count aid items by category");
            System.out.println("4. Beneficiaries served between two dates");
            System.out.println("5. Most served city");
            System.out.println("0. Back to main menu");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        familiesServedInCity();
                        break;
                    case 2:
                        totalAidItemsDistributed();
                        break;
                    case 3:
                        countAidItemsByCategory();
                        break;
                    case 4:
                        beneficiariesBetweenDates();
                        break;
                    case 5:
                        mostServedCity();
                        break;
                    case 0:
                        inReportsMenu = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
                if (inReportsMenu && choice != 0) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    private static void familiesServedInCity() {
        System.out.print("Enter city name: ");
        String city = scanner.nextLine().trim();
        int count = aidManager.getFamiliesServedInCity(city);
        System.out.println("Number of families served in " + city + ": " + count);
    }
    
    private static void totalAidItemsDistributed() {
        int total = aidManager.getTotalAidItemsDistributed();
        System.out.println("Total aid items distributed: " + total);
    }
    
    private static void countAidItemsByCategory() {
        Map<String, Integer> categoryCount = aidManager.getAidItemsByCategory();
        System.out.println("Aid items distributed by category:");
        if (categoryCount.isEmpty()) {
            System.out.println("No distributions recorded.");
        } else {
            for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
    
    private static void beneficiariesBetweenDates() {
        try {
            System.out.print("Enter start date (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine().trim();
            Date startDate = dateFormat.parse(startDateStr);
            GregorianCalendar startCal = new GregorianCalendar();
            startCal.setTime(startDate);
            
            System.out.print("Enter end date (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine().trim();
            Date endDate = dateFormat.parse(endDateStr);
            GregorianCalendar endCal = new GregorianCalendar();
            endCal.setTime(endDate);
            
            List<Beneficiary> beneficiaries = aidManager.getBeneficiariesServedBetweenDates(startCal, endCal);
            System.out.println("Beneficiaries served between " + startDateStr + " and " + endDateStr + ":");
            
            if (beneficiaries.isEmpty()) {
                System.out.println("No beneficiaries found in this date range.");
            } else {
                for (int i = 0; i < beneficiaries.size(); i++) {
                    System.out.println((i + 1) + ". " + beneficiaries.get(i));
                }
            }
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd format.");
        }
    }
    
    private static void mostServedCity() {
        String city = aidManager.getMostServedCity();
        System.out.println("Most served city: " + city);
    }
    
    private static void saveToTextFile() {
        try {
            System.out.print("Enter filename (e.g., data.txt): ");
            String filename = scanner.nextLine().trim();
            aidManager.saveToTextFile(filename);
            System.out.println("Data saved to text file successfully!");
        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
    private static void saveToBinaryFile() {
        try {
            System.out.print("Enter filename (e.g., data.dat): ");
            String filename = scanner.nextLine().trim();
            aidManager.saveToBinaryFile(filename);
            System.out.println("Data saved to binary file successfully!");
        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
    private static void loadFromTextFile() {
        try {
            System.out.print("Enter filename (e.g., data.txt): ");
            String filename = scanner.nextLine().trim();
            aidManager.loadFromTextFile(filename);
            System.out.println("Data loaded from text file successfully!");
        } catch (Exception e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
    
    private static void loadFromBinaryFile() {
        try {
            System.out.print("Enter filename (e.g., data.dat): ");
            String filename = scanner.nextLine().trim();
            aidManager.loadFromBinaryFile(filename);
            System.out.println("Data loaded from binary file successfully!");
        } catch (Exception e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
