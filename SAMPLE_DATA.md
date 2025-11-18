# Sample Data and Usage Examples

## Sample Input Data

### Sample Beneficiaries

#### Families
```
ID: F001
Name: Al-Masri Family
City: Gaza
Members: 7

ID: F002
Name: Abu Ahmed Family
City: Rafah
Members: 5

ID: F003
Name: Al-Qassem Family
City: Khan Younis
Members: 6

ID: F004
Name: Awad Family
City: Jenin
Members: 4
```

#### Individuals
```
ID: I001
Name: Fatima Hassan
City: Jerusalem
Status: Elderly

ID: I002
Name: Mohammed Ali
City: Tulkarm
Status: Student

ID: I003
Name: Aisha Ibrahim
City: Hebron
Status: Patient

ID: I004
Name: Omar Khalil
City: Ramallah
Status: Orphan

ID: I005
Name: Layla Said
City: Nablus
Status: Elderly
```

### Sample Aid Items

#### Food Packages
```
Code: FP001
Description: Rice, oil, sugar, flour, and canned goods

Code: FP002
Description: Fresh vegetables and fruits basket

Code: FP003
Description: Basic nutrition pack with dairy products
```

#### Medical Kits
```
Code: MK001
Description: First aid kit with bandages and antiseptics

Code: MK002
Description: Diabetes management supplies

Code: MK003
Description: Pain relief and fever medication kit
```

#### Winter Bags
```
Code: WB001
Description: Blankets, warm clothes, and heating supplies

Code: WB002
Description: Children's winter clothing set

Code: WB003
Description: Family winter essentials pack
```

#### Emergency Kits
```
Code: EK001
Description: Flashlight, batteries, water bottles, and emergency tools

Code: EK002
Description: Hygiene and sanitation emergency supplies
```

### Sample Distribution Events
```
Date: 2024-01-15
Beneficiary: F001 (Al-Masri Family)
Aid Item: FP001 (Rice, oil, sugar, flour, and canned goods)

Date: 2024-01-16
Beneficiary: I001 (Fatima Hassan)
Aid Item: MK002 (Diabetes management supplies)

Date: 2024-01-20
Beneficiary: F002 (Abu Ahmed Family)
Aid Item: WB001 (Blankets, warm clothes, and heating supplies)

Date: 2024-01-22
Beneficiary: I003 (Aisha Ibrahim)
Aid Item: MK001 (First aid kit with bandages and antiseptics)

Date: 2024-01-25
Beneficiary: F003 (Al-Qassem Family)
Aid Item: FP002 (Fresh vegetables and fruits basket)

Date: 2024-02-01
Beneficiary: I002 (Mohammed Ali)
Aid Item: FP003 (Basic nutrition pack with dairy products)

Date: 2024-02-05
Beneficiary: F001 (Al-Masri Family)
Aid Item: EK001 (Flashlight, batteries, water bottles, and emergency tools)

Date: 2024-02-10
Beneficiary: I004 (Omar Khalil)
Aid Item: WB002 (Children's winter clothing set)
```

## Sample Console Session

### Starting the Application
```
$ java AidSystemDriver

==== Smart Aid Distribution System ====
1. Register Beneficiary
2. Add Aid Item
3. Record Aid Distribution
4. Display All Beneficiaries
5. Display All Aid Items
6. Display All Distribution Events
7. Generate Statistical Reports
8. Save to Text File
9. Save to Binary File
10. Load from Text File
11. Load from Binary File
0. Exit
Enter your choice:
```

### Example 1: Registering a Family

```
Enter your choice: 1

=== Register Beneficiary ===
Enter beneficiary type (1-Family, 2-Individual): 1
Enter ID: F001
Enter Name: Al-Masri Family
Served cities: Gaza, Rafah, Khan Younis, Jerusalem, Jenin, Tulkarm, Hebron, Ramallah, Jabalia, Nablus
Enter City: Gaza
Enter number of family members: 7
Beneficiary registered successfully!

Press Enter to continue...
```

### Example 2: Registering an Individual

```
Enter your choice: 1

=== Register Beneficiary ===
Enter beneficiary type (1-Family, 2-Individual): 2
Enter ID: I001
Enter Name: Fatima Hassan
Served cities: Gaza, Rafah, Khan Younis, Jerusalem, Jenin, Tulkarm, Hebron, Ramallah, Jabalia, Nablus
Enter City: Jerusalem
Enter status (e.g., Elderly, Student, Patient, Orphan): Elderly
Beneficiary registered successfully!

Press Enter to continue...
```

### Example 3: Adding an Aid Item

```
Enter your choice: 2

=== Add Aid Item ===
1. Food Package
2. Medical Kit
3. Winter Bag
4. Emergency Kit
Enter item type: 1
Enter item code: FP001
Enter description: Rice, oil, sugar, flour, and canned goods
Aid item added successfully!

Press Enter to continue...
```

### Example 4: Recording a Distribution

```
Enter your choice: 3

=== Record Aid Distribution ===
Enter beneficiary ID: F001
Enter aid item code: FP001
Enter date (yyyy-MM-dd): 2024-01-15
Distribution recorded successfully!

Press Enter to continue...
```

### Example 5: Displaying All Beneficiaries

```
Enter your choice: 4

=== All Beneficiaries ===
1. ID: F001, Name: Al-Masri Family, City: Gaza, Type: Family, Members: 7
2. ID: F002, Name: Abu Ahmed Family, City: Rafah, Type: Family, Members: 5
3. ID: I001, Name: Fatima Hassan, City: Jerusalem, Type: Individual, Status: Elderly
4. ID: I002, Name: Mohammed Ali, City: Tulkarm, Type: Individual, Status: Student

Press Enter to continue...
```

### Example 6: Generating Statistical Reports

```
Enter your choice: 7

==== Statistical Reports ====
1. Number of families served in a city
2. Total aid items distributed
3. Count aid items by category
4. Beneficiaries served between two dates
5. Most served city
0. Back to main menu
Enter your choice: 1

Enter city name: Gaza
Number of families served in Gaza: 1

Press Enter to continue...

==== Statistical Reports ====
1. Number of families served in a city
2. Total aid items distributed
3. Count aid items by category
4. Beneficiaries served between two dates
5. Most served city
0. Back to main menu
Enter your choice: 3

Aid items distributed by category:
  Food Package: 3
  Medical Kit: 2
  Winter Bag: 2
  Emergency Kit: 1

Press Enter to continue...
```

### Example 7: Saving to Text File

```
Enter your choice: 8

Enter filename (e.g., data.txt): aid_data.txt
Data saved to text file successfully!

Press Enter to continue...
```

### Example 8: Loading from Text File

```
Enter your choice: 10

Enter filename (e.g., data.txt): aid_data.txt
Data loaded from text file successfully!

Press Enter to continue...
```

## Sample Text File Format

Content of `aid_data.txt`:

```
=== BENEFICIARIES ===
FAMILY|F001|Al-Masri Family|Gaza|7
FAMILY|F002|Abu Ahmed Family|Rafah|5
FAMILY|F003|Al-Qassem Family|Khan Younis|6
INDIVIDUAL|I001|Fatima Hassan|Jerusalem|Elderly
INDIVIDUAL|I002|Mohammed Ali|Tulkarm|Student
INDIVIDUAL|I003|Aisha Ibrahim|Hebron|Patient
=== AID ITEMS ===
FoodPackage|FP001|Rice, oil, sugar, flour, and canned goods
FoodPackage|FP002|Fresh vegetables and fruits basket
MedicalKit|MK001|First aid kit with bandages and antiseptics
MedicalKit|MK002|Diabetes management supplies
WinterBag|WB001|Blankets, warm clothes, and heating supplies
EmergencyKit|EK001|Flashlight, batteries, water bottles, and emergency tools
=== DISTRIBUTION EVENTS ===
F001|FP001|1705276800000
I001|MK002|1705363200000
F002|WB001|1705708800000
I003|MK001|1705881600000
F003|FP002|1706140800000
```

## Error Handling Examples

### Example 1: Duplicate Registration

```
Enter your choice: 1

=== Register Beneficiary ===
Enter beneficiary type (1-Family, 2-Individual): 1
Enter ID: F001
Enter Name: Test Family
Served cities: Gaza, Rafah, Khan Younis, Jerusalem, Jenin, Tulkarm, Hebron, Ramallah, Jabalia, Nablus
Enter City: Gaza
Enter number of family members: 5
Error: Beneficiary with ID F001 already exists

Press Enter to continue...
```

### Example 2: City Not Served

```
Enter your choice: 1

=== Register Beneficiary ===
Enter beneficiary type (1-Family, 2-Individual): 1
Enter ID: F999
Enter Name: Test Family
Served cities: Gaza, Rafah, Khan Younis, Jerusalem, Jenin, Tulkarm, Hebron, Ramallah, Jabalia, Nablus
Enter City: Dubai
Error: City Dubai is not in the served areas

Press Enter to continue...
```

### Example 3: Item Not Found

```
Enter your choice: 3

=== Record Aid Distribution ===
Enter beneficiary ID: F999
Enter aid item code: FP001
Enter date (yyyy-MM-dd): 2024-01-15
Error: Beneficiary with ID F999 not found

Press Enter to continue...
```

### Example 4: Invalid Date Format

```
Enter your choice: 3

=== Record Aid Distribution ===
Enter beneficiary ID: F001
Enter aid item code: FP001
Enter date (yyyy-MM-dd): 15-01-2024
Error: Invalid date format. Please use yyyy-MM-dd format.

Press Enter to continue...
```

## Statistical Report Examples

### Report 1: Families Served in City
```
Input: Gaza
Output: Number of families served in Gaza: 2
```

### Report 2: Total Aid Items
```
Output: Total aid items distributed: 8
```

### Report 3: Aid Items by Category
```
Output:
Aid items distributed by category:
  Food Package: 3
  Medical Kit: 2
  Winter Bag: 2
  Emergency Kit: 1
```

### Report 4: Beneficiaries Between Dates
```
Input: 
  Start date: 2024-01-15
  End date: 2024-01-31
  
Output:
Beneficiaries served between 2024-01-15 and 2024-01-31:
1. ID: F001, Name: Al-Masri Family, City: Gaza, Type: Family, Members: 7
2. ID: I001, Name: Fatima Hassan, City: Jerusalem, Type: Individual, Status: Elderly
3. ID: F002, Name: Abu Ahmed Family, City: Rafah, Type: Family, Members: 5
4. ID: I003, Name: Aisha Ibrahim, City: Hebron, Type: Individual, Status: Patient
5. ID: F003, Name: Al-Qassem Family, City: Khan Younis, Type: Family, Members: 6
```

### Report 5: Most Served City
```
Output: Most served city: Gaza
```
