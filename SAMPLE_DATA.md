# Sample Data and Usage Examples

This document provides sample data formats and usage examples for the Smart Aid Distribution & Beneficiary Management System.

## Sample Text File Format

When saving data to a text file, the system uses the following format:

### Example: data.txt

```
=== BENEFICIARIES ===
5
Family,F001,Al-Masri Family,Gaza,6
Family,F002,Abu Ahmad Family,Rafah,8
Individual,I001,Ahmed Hassan,Jerusalem,Elderly
Individual,I002,Fatima Ali,Hebron,Orphan
Individual,I003,Omar Yousef,Nablus,Student
=== AID ITEMS ===
6
FoodPackage,FP001,Basic food supplies - rice, oil, sugar, flour
FoodPackage,FP002,Extended food package with canned goods
MedicalKit,MK001,First aid kit with bandages and medications
WinterBag,WB001,Winter clothing and blankets
EmergencyKit,EK001,Emergency supplies with water and flashlight
MedicalKit,MK002,Chronic disease medication package
=== DISTRIBUTION EVENTS ===
4
FP001,F001,15,11,2024
MK001,I001,10,11,2024
WB001,F002,20,11,2024
FP002,I002,18,11,2024
```

### Format Explanation

**Beneficiaries Section:**
```
=== BENEFICIARIES ===
[count]
[Type],[ID],[Name],[City],[Additional Field]
```
- For Family: Additional field is member count (integer)
- For Individual: Additional field is status (Elderly/Student/Patient/Orphan)

**Aid Items Section:**
```
=== AID ITEMS ===
[count]
[Type],[Code],[Description]
```
- Type can be: FoodPackage, MedicalKit, WinterBag, EmergencyKit

**Distribution Events Section:**
```
=== DISTRIBUTION EVENTS ===
[count]
[ItemCode],[BeneficiaryID],[Day],[Month],[Year]
```
- Dates are in DD,MM,YYYY format

## Sample Console Output

### Application Startup

```
╔═══════════════════════════════════════════════════════════╗
║  Smart Aid Distribution & Beneficiary Management System  ║
║            Serving Palestinian Communities                ║
╚═══════════════════════════════════════════════════════════╝

╔════════════════ MAIN MENU ════════════════╗
║ 1. Register New Beneficiary               ║
║ 2. Add New Aid Item                       ║
║ 3. Record Distribution Event              ║
║ 4. Display All Beneficiaries              ║
║ 5. Display All Aid Items                  ║
║ 6. Display All Distribution Events        ║
║ 7. Statistical Reports                    ║
║ 8. Save Data to Text File                 ║
║ 9. Load Data from Text File               ║
║ 10. Save Data to Binary File              ║
║ 11. Load Data from Binary File            ║
║ 0. Exit                                   ║
╚═══════════════════════════════════════════╝
Enter your choice: 
```

### Example 1: Registering a Family

```
Enter your choice: 1

=== Register New Beneficiary ===
Enter beneficiary type (1 for Family, 2 for Individual): 1
Enter ID: F001
Enter Name: Al-Masri Family

Served Cities:
1. Gaza
2. Rafah
3. Khan Younis
4. Jerusalem
5. Jenin
6. Tulkarm
7. Hebron
8. Ramallah
9. Jabalia
10. Nablus
Enter City: Gaza
Enter number of family members: 6
✓ Beneficiary registered successfully!
```

### Example 2: Registering an Individual

```
Enter your choice: 1

=== Register New Beneficiary ===
Enter beneficiary type (1 for Family, 2 for Individual): 2
Enter ID: I001
Enter Name: Ahmed Hassan

Served Cities:
1. Gaza
2. Rafah
3. Khan Younis
4. Jerusalem
5. Jenin
6. Tulkarm
7. Hebron
8. Ramallah
9. Jabalia
10. Nablus
Enter City: Jerusalem
Enter status (Elderly/Student/Patient/Orphan): Elderly
✓ Beneficiary registered successfully!
```

### Example 3: Adding an Aid Item

```
Enter your choice: 2

=== Add New Aid Item ===
Select item category:
1. Food Package
2. Medical Kit
3. Winter Bag
4. Emergency Kit
Enter choice: 1
Enter item code: FP001
Enter item description: Basic food supplies - rice, oil, sugar, flour
✓ Aid item added successfully!
```

### Example 4: Recording a Distribution Event

```
Enter your choice: 3

=== Record Distribution Event ===
Enter beneficiary ID: F001
Enter aid item code: FP001
Enter distribution date (DD MM YYYY): 15 11 2024
✓ Distribution event recorded successfully!
```

### Example 5: Display All Beneficiaries

```
Enter your choice: 4

=== All Beneficiaries ===
1. ID: F001, Name: Al-Masri Family, City: Gaza, Type: Family, Members: 6
2. ID: F002, Name: Abu Ahmad Family, City: Rafah, Type: Family, Members: 8
3. ID: I001, Name: Ahmed Hassan, City: Jerusalem, Type: Individual, Status: Elderly
4. ID: I002, Name: Fatima Ali, City: Hebron, Type: Individual, Status: Orphan
5. ID: I003, Name: Omar Yousef, City: Nablus, Type: Individual, Status: Student
```

### Example 6: Display All Aid Items

```
Enter your choice: 5

=== All Aid Items ===
1. Code: FP001, Description: Basic food supplies - rice, oil, sugar, flour, Category: Food Package
2. Code: FP002, Description: Extended food package with canned goods, Category: Food Package
3. Code: MK001, Description: First aid kit with bandages and medications, Category: Medical Kit
4. Code: WB001, Description: Winter clothing and blankets, Category: Winter Bag
5. Code: EK001, Description: Emergency supplies with water and flashlight, Category: Emergency Kit
```

### Example 7: Display Distribution Events

```
Enter your choice: 6

=== All Distribution Events ===
1. Date: 15/11/2024 | Item: FP001 (Food Package) | Beneficiary: Al-Masri Family (Family) from Gaza
2. Date: 10/11/2024 | Item: MK001 (Medical Kit) | Beneficiary: Ahmed Hassan (Individual) from Jerusalem
3. Date: 20/11/2024 | Item: WB001 (Winter Bag) | Beneficiary: Abu Ahmad Family (Family) from Rafah
4. Date: 18/11/2024 | Item: FP002 (Food Package) | Beneficiary: Fatima Ali (Individual) from Hebron
```

### Example 8: Statistical Reports Menu

```
Enter your choice: 7

╔════════════ STATISTICAL REPORTS ══════════╗
║ 1. Families Served in Specific City      ║
║ 2. Total Aid Items Distributed           ║
║ 3. Aid Items by Category                 ║
║ 4. Beneficiaries Served Between Dates    ║
║ 5. Most Served City                      ║
║ 0. Return to Main Menu                   ║
╚═══════════════════════════════════════════╝
Enter your choice: 
```

### Example 9: Report - Families in City

```
Enter your choice: 1

Enter city name: Gaza
Families served in Gaza: 1
```

### Example 10: Report - Total Distributions

```
Enter your choice: 2

Total aid items distributed: 4
```

### Example 11: Report - Items by Category

```
Enter your choice: 3

Enter category (Food Package/Medical Kit/Winter Bag/Emergency Kit): Food Package

=== Items in category: Food Package ===
1. Code: FP001, Description: Basic food supplies - rice, oil, sugar, flour, Category: Food Package
2. Code: FP002, Description: Extended food package with canned goods, Category: Food Package
```

### Example 12: Report - Beneficiaries Between Dates

```
Enter your choice: 4

Enter start date (DD MM YYYY): 01 11 2024
Enter end date (DD MM YYYY): 20 11 2024

=== Beneficiaries Served Between Dates ===
Total: 4
1. ID: F001, Name: Al-Masri Family, City: Gaza, Type: Family, Members: 6
2. ID: I001, Name: Ahmed Hassan, City: Jerusalem, Type: Individual, Status: Elderly
3. ID: F002, Name: Abu Ahmad Family, City: Rafah, Type: Family, Members: 8
4. ID: I002, Name: Fatima Ali, City: Hebron, Type: Individual, Status: Orphan
```

### Example 13: Report - Most Served City

```
Enter your choice: 5

Most served city: Gaza (1 beneficiaries)
```

### Example 14: Save to Text File

```
Enter your choice: 8

Enter filename (e.g., data.txt): aid_data.txt
✓ Data saved successfully to aid_data.txt
```

### Example 15: Load from Text File

```
Enter your choice: 9

Enter filename (e.g., data.txt): aid_data.txt
✓ Data loaded successfully from aid_data.txt
```

### Example 16: Error Handling - Duplicate Registration

```
Enter your choice: 1

=== Register New Beneficiary ===
Enter beneficiary type (1 for Family, 2 for Individual): 1
Enter ID: F001
Enter Name: Another Family

Served Cities:
1. Gaza
2. Rafah
3. Khan Younis
4. Jerusalem
5. Jenin
6. Tulkarm
7. Hebron
8. Ramallah
9. Jabalia
10. Nablus
Enter City: Gaza
Enter number of family members: 4
✗ Error: Beneficiary with ID F001 already exists.
```

### Example 17: Error Handling - City Not Served

```
Enter your choice: 1

=== Register New Beneficiary ===
Enter beneficiary type (1 for Family, 2 for Individual): 1
Enter ID: F010
Enter Name: Test Family

Served Cities:
1. Gaza
2. Rafah
3. Khan Younis
4. Jerusalem
5. Jenin
6. Tulkarm
7. Hebron
8. Ramallah
9. Jabalia
10. Nablus
Enter City: Haifa
Enter number of family members: 5
✗ Error: City Haifa is not in the served cities list.
```

### Example 18: Error Handling - Beneficiary Not Found

```
Enter your choice: 3

=== Record Distribution Event ===
Enter beneficiary ID: F999
✗ Beneficiary not found.
```

### Example 19: Exit Application

```
Enter your choice: 0

Thank you for using the Aid Distribution System!
```

## Complete Usage Scenario

### Scenario: Setting Up and Using the System

1. **Start the application**
   ```bash
   java -cp bin AidSystemDriver
   ```

2. **Register beneficiaries** (Option 1)
   - Add 2-3 families with different member counts
   - Add 2-3 individuals with different statuses

3. **Add aid items** (Option 2)
   - Add various types of aid items (Food, Medical, Winter, Emergency)

4. **Record distributions** (Option 3)
   - Distribute items to registered beneficiaries
   - Use current or recent dates

5. **View all data** (Options 4, 5, 6)
   - Verify beneficiaries are registered
   - Verify aid items are in system
   - Check distribution events

6. **Generate reports** (Option 7)
   - Check statistics for different cities
   - View total distributions
   - Find beneficiaries served in date ranges

7. **Save data** (Option 8 or 10)
   - Save to text file for human readability
   - Save to binary file for efficient storage

8. **Test persistence** (Option 9 or 11)
   - Exit and restart the application
   - Load previously saved data
   - Verify all data is restored correctly

## Tips for Testing

1. **Test Validation:**
   - Try registering with duplicate IDs
   - Try cities not in the served list
   - Try distributing to non-existent beneficiaries

2. **Test Sorting:**
   - Register families with different member counts
   - Register individuals with different statuses
   - Observe the natural ordering when displayed

3. **Test File Operations:**
   - Save and load text files
   - Save and load binary files
   - Verify data integrity after loading

4. **Test Reports:**
   - Use different date ranges
   - Check different cities
   - Verify counts and statistics

5. **Test Edge Cases:**
   - Empty system (no data)
   - Single beneficiary/item
   - Large datasets

## Sample Dataset for Quick Testing

You can create a text file with this content for quick testing:

```
=== BENEFICIARIES ===
10
Family,F001,Al-Qassam Family,Gaza,7
Family,F002,Abu Khaled Family,Rafah,5
Family,F003,Al-Najjar Family,Khan Younis,9
Family,F004,Al-Masri Family,Hebron,4
Family,F005,Abu Salem Family,Nablus,6
Individual,I001,Ahmed Hassan,Jerusalem,Elderly
Individual,I002,Fatima Abdullah,Gaza,Orphan
Individual,I003,Omar Mahmoud,Jenin,Patient
Individual,I004,Sarah Ali,Ramallah,Student
Individual,I005,Ibrahim Yousef,Tulkarm,Elderly
=== AID ITEMS ===
8
FoodPackage,FP001,Basic food package - 25kg
FoodPackage,FP002,Extended food package - 50kg
MedicalKit,MK001,First aid medical kit
MedicalKit,MK002,Chronic disease medication
WinterBag,WB001,Winter clothes for family
WinterBag,WB002,Winter blankets set
EmergencyKit,EK001,Emergency survival kit
EmergencyKit,EK002,Emergency shelter supplies
=== DISTRIBUTION EVENTS ===
15
FP001,F001,15,11,2024
FP002,F002,16,11,2024
MK001,I001,10,11,2024
WB001,F003,20,11,2024
EK001,F004,12,11,2024
FP001,F005,18,11,2024
MK002,I003,14,11,2024
WB002,F001,22,11,2024
FP002,I002,17,11,2024
EK002,F002,19,11,2024
MK001,I005,11,11,2024
WB001,F004,21,11,2024
FP001,I004,13,11,2024
EK001,F003,23,11,2024
MK002,I001,24,11,2024
```

Save this as `test_data.txt` and load it using option 9 to quickly populate the system with test data.
