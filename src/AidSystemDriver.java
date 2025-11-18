import models.*;
import manager.*;
import exceptions.*;
import java.util.*;
import java.io.IOException;

/**
 * Main driver class for the Smart Aid Distribution & Beneficiary Management System.
 * Provides a console-based menu interface for managing aid distribution.
 */
public class AidSystemDriver {
    
    private static AidManager aidManager = new AidManager();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  Smart Aid Distribution & Beneficiary Management System  ║");
        System.out.println("║            Serving Palestinian Communities                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                running = handleMainMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        System.out.println("\nThank you for using the Aid Distribution System!");
        scanner.close();
    }
    
    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("\n╔════════════════ MAIN MENU ════════════════╗");
        System.out.println("║ 1. Register New Beneficiary               ║");
        System.out.println("║ 2. Add New Aid Item                       ║");
        System.out.println("║ 3. Record Distribution Event              ║");
        System.out.println("║ 4. Display All Beneficiaries              ║");
        System.out.println("║ 5. Display All Aid Items                  ║");
        System.out.println("║ 6. Display All Distribution Events        ║");
        System.out.println("║ 7. Statistical Reports                    ║");
        System.out.println("║ 8. Save Data to Text File                 ║");
        System.out.println("║ 9. Load Data from Text File               ║");
        System.out.println("║ 10. Save Data to Binary File              ║");
        System.out.println("║ 11. Load Data from Binary File            ║");
        System.out.println("║ 0. Exit                                   ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Handles the user's main menu choice.
     * 
     * @param choice The menu option selected
     * @return false if user wants to exit, true otherwise
     */
    private static boolean handleMainMenuChoice(int choice) {
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
                aidManager.displayAllBeneficiaries();
                break;
            case 5:
                aidManager.displayAllAidItems();
                break;
            case 6:
                aidManager.displayAllDistributionEvents();
                break;
            case 7:
                showStatisticalReports();
                break;
            case 8:
                saveToTextFile();
                break;
            case 9:
                loadFromTextFile();
                break;
            case 10:
                saveToBinaryFile();
                break;
            case 11:
                loadFromBinaryFile();
                break;
            case 0:
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
    
    /**
     * Registers a new beneficiary in the system.
     */
    private static void registerBeneficiary() {
        System.out.println("\n=== Register New Beneficiary ===");
        
        try {
            System.out.print("Enter beneficiary type (1 for Family, 2 for Individual): ");
            int type = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.println("\nServed Cities:");
            String[] cities = aidManager.getServedCities();
            for (int i = 0; i < cities.length; i++) {
                System.out.println((i + 1) + ". " + cities[i]);
            }
            System.out.print("Enter City: ");
            String city = scanner.nextLine();
            
            Beneficiary beneficiary;
            if (type == 1) {
                System.out.print("Enter number of family members: ");
                int membersCount = Integer.parseInt(scanner.nextLine());
                beneficiary = new Family(id, name, city, membersCount);
            } else if (type == 2) {
                System.out.print("Enter status (Elderly/Student/Patient/Orphan): ");
                String status = scanner.nextLine();
                beneficiary = new Individual(id, name, city, status);
            } else {
                System.out.println("Invalid beneficiary type.");
                return;
            }
            
            aidManager.addBeneficiary(beneficiary);
            System.out.println("✓ Beneficiary registered successfully!");
            
        } catch (DuplicateRegistrationException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (CityNotServedException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format.");
        }
    }
    
    /**
     * Adds a new aid item to the system.
     */
    private static void addAidItem() {
        System.out.println("\n=== Add New Aid Item ===");
        
        try {
            System.out.println("Select item category:");
            System.out.println("1. Food Package");
            System.out.println("2. Medical Kit");
            System.out.println("3. Winter Bag");
            System.out.println("4. Emergency Kit");
            System.out.print("Enter choice: ");
            int category = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter item code: ");
            String code = scanner.nextLine();
            
            System.out.print("Enter item description: ");
            String description = scanner.nextLine();
            
            AidItem item;
            switch (category) {
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
                    System.out.println("Invalid category.");
                    return;
            }
            
            aidManager.addAidItem(item);
            System.out.println("✓ Aid item added successfully!");
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format.");
        }
    }
    
    /**
     * Records a distribution event.
     */
    private static void recordDistribution() {
        System.out.println("\n=== Record Distribution Event ===");
        
        try {
            System.out.print("Enter beneficiary ID: ");
            String beneficiaryId = scanner.nextLine();
            Beneficiary beneficiary = aidManager.findBeneficiaryById(beneficiaryId);
            
            if (beneficiary == null) {
                System.out.println("✗ Beneficiary not found.");
                return;
            }
            
            System.out.print("Enter aid item code: ");
            String itemCode = scanner.nextLine();
            AidItem item = aidManager.findAidItemByCode(itemCode);
            
            if (item == null) {
                System.out.println("✗ Aid item not found.");
                return;
            }
            
            System.out.print("Enter distribution date (DD MM YYYY): ");
            String dateInput = scanner.nextLine();
            String[] dateParts = dateInput.split(" ");
            
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            
            GregorianCalendar date = new GregorianCalendar(year, month - 1, day);
            DistributionEvent event = new DistributionEvent(item, beneficiary, date);
            
            aidManager.recordDistribution(event);
            System.out.println("✓ Distribution event recorded successfully!");
            
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("✗ Invalid input format.");
        }
    }
    
    /**
     * Displays the statistical reports submenu.
     */
    private static void showStatisticalReports() {
        boolean inReports = true;
        while (inReports) {
            System.out.println("\n╔════════════ STATISTICAL REPORTS ══════════╗");
            System.out.println("║ 1. Families Served in Specific City      ║");
            System.out.println("║ 2. Total Aid Items Distributed           ║");
            System.out.println("║ 3. Aid Items by Category                 ║");
            System.out.println("║ 4. Beneficiaries Served Between Dates    ║");
            System.out.println("║ 5. Most Served City                      ║");
            System.out.println("║ 0. Return to Main Menu                   ║");
            System.out.println("╚═══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                inReports = handleReportsMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    /**
     * Handles the user's statistical reports menu choice.
     * 
     * @param choice The menu option selected
     * @return false if user wants to return to main menu, true otherwise
     */
    private static boolean handleReportsMenuChoice(int choice) {
        switch (choice) {
            case 1:
                reportFamiliesInCity();
                break;
            case 2:
                reportTotalDistributions();
                break;
            case 3:
                reportItemsByCategory();
                break;
            case 4:
                reportBeneficiariesBetweenDates();
                break;
            case 5:
                reportMostServedCity();
                break;
            case 0:
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
    
    /**
     * Reports the number of families served in a specific city.
     */
    private static void reportFamiliesInCity() {
        System.out.print("\nEnter city name: ");
        String city = scanner.nextLine();
        int count = aidManager.getFamiliesServedInCity(city);
        System.out.println("Families served in " + city + ": " + count);
    }
    
    /**
     * Reports the total number of aid items distributed.
     */
    private static void reportTotalDistributions() {
        int total = aidManager.getTotalAidItemsDistributed();
        System.out.println("\nTotal aid items distributed: " + total);
    }
    
    /**
     * Reports aid items filtered by category.
     */
    private static void reportItemsByCategory() {
        System.out.print("\nEnter category (Food Package/Medical Kit/Winter Bag/Emergency Kit): ");
        String category = scanner.nextLine();
        ArrayList<AidItem> items = aidManager.getItemsByCategory(category);
        
        if (items.isEmpty()) {
            System.out.println("No items found in category: " + category);
        } else {
            System.out.println("\n=== Items in category: " + category + " ===");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
    
    /**
     * Reports beneficiaries served between two dates.
     */
    private static void reportBeneficiariesBetweenDates() {
        try {
            System.out.print("\nEnter start date (DD MM YYYY): ");
            String startInput = scanner.nextLine();
            String[] startParts = startInput.split(" ");
            GregorianCalendar startDate = new GregorianCalendar(
                Integer.parseInt(startParts[2]),
                Integer.parseInt(startParts[1]) - 1,
                Integer.parseInt(startParts[0])
            );
            
            System.out.print("Enter end date (DD MM YYYY): ");
            String endInput = scanner.nextLine();
            String[] endParts = endInput.split(" ");
            GregorianCalendar endDate = new GregorianCalendar(
                Integer.parseInt(endParts[2]),
                Integer.parseInt(endParts[1]) - 1,
                Integer.parseInt(endParts[0])
            );
            
            Set<Beneficiary> beneficiaries = aidManager.getBeneficiariesServedBetweenDates(
                startDate, endDate);
            
            System.out.println("\n=== Beneficiaries Served Between Dates ===");
            System.out.println("Total: " + beneficiaries.size());
            int i = 1;
            for (Beneficiary b : beneficiaries) {
                System.out.println(i++ + ". " + b);
            }
            
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("✗ Invalid date format.");
        }
    }
    
    /**
     * Reports the city with the most beneficiaries.
     */
    private static void reportMostServedCity() {
        String result = aidManager.getMostServedCity();
        System.out.println("\nMost served city: " + result);
    }
    
    /**
     * Saves data to a text file.
     */
    private static void saveToTextFile() {
        System.out.print("\nEnter filename (e.g., data.txt): ");
        String filename = scanner.nextLine();
        
        try {
            aidManager.saveToTextFile(filename);
            System.out.println("✓ Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("✗ Error saving data: " + e.getMessage());
        }
    }
    
    /**
     * Loads data from a text file.
     */
    private static void loadFromTextFile() {
        System.out.print("\nEnter filename (e.g., data.txt): ");
        String filename = scanner.nextLine();
        
        try {
            aidManager.loadFromTextFile(filename);
            System.out.println("✓ Data loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("✗ Error loading data: " + e.getMessage());
        }
    }
    
    /**
     * Saves data to a binary file.
     */
    private static void saveToBinaryFile() {
        System.out.print("\nEnter filename (e.g., data.bin): ");
        String filename = scanner.nextLine();
        
        try {
            aidManager.saveToBinaryFile(filename);
            System.out.println("✓ Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("✗ Error saving data: " + e.getMessage());
        }
    }
    
    /**
     * Loads data from a binary file.
     */
    private static void loadFromBinaryFile() {
        System.out.print("\nEnter filename (e.g., data.bin): ");
        String filename = scanner.nextLine();
        
        try {
            aidManager.loadFromBinaryFile(filename);
            System.out.println("✓ Data loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("✗ Error loading data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("✗ Error: Invalid file format.");
        }
    }
}
