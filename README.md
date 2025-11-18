# Smart Aid Distribution & Beneficiary Management System

A comprehensive console-based Java application for managing aid distribution to beneficiaries in Palestinian communities. The system supports registration of families and individuals, tracking of various aid items, and recording of distribution events with complete file persistence.

## Project Overview

This system helps humanitarian organizations efficiently manage aid distribution by:
- Registering and tracking beneficiaries (families and individuals)
- Managing aid items across multiple categories
- Recording and tracking distribution events
- Generating statistical reports
- Persisting data through text and binary file formats

## Features

### Beneficiary Management
- Register families with member count tracking
- Register individuals with status categories (Elderly, Student, Patient, Orphan)
- Validate beneficiaries against served cities list
- Prevent duplicate registrations
- Sort and prioritize beneficiaries

### Aid Item Management
- Support for multiple aid categories:
  - Food Packages
  - Medical Kits
  - Winter Bags
  - Emergency Kits
- Track aid items with unique codes and descriptions

### Distribution Tracking
- Record distribution events with dates
- Link beneficiaries to aid items
- Track distribution history

### Statistical Reports
- Count families served per city
- Calculate total aid items distributed
- Filter items by category
- Find beneficiaries served between date ranges
- Identify most served city

### Data Persistence
- Save/load data in text file format
- Save/load data in binary file format
- Comprehensive error handling

## Directory Structure

```
Smart-Aid-Distribution-Beneficiary-Management-System/
├── src/
│   ├── AidSystemDriver.java          # Main driver class with menu interface
│   ├── models/                        # Model classes
│   │   ├── Beneficiary.java          # Abstract beneficiary class
│   │   ├── Family.java               # Family beneficiary type
│   │   ├── Individual.java           # Individual beneficiary type
│   │   ├── AidItem.java              # Abstract aid item class
│   │   ├── FoodPackage.java          # Food package type
│   │   ├── MedicalKit.java           # Medical kit type
│   │   ├── WinterBag.java            # Winter bag type
│   │   ├── EmergencyKit.java         # Emergency kit type
│   │   └── DistributionEvent.java    # Distribution event record
│   ├── manager/                       # Management classes
│   │   ├── FileOperations.java       # File operations interface
│   │   └── AidManager.java           # Main manager implementation
│   └── exceptions/                    # Custom exceptions
│       ├── DuplicateRegistrationException.java
│       ├── ItemNotFoundException.java
│       └── CityNotServedException.java
├── bin/                               # Compiled class files (generated)
├── README.md                          # This file
├── UML_CLASS_DIAGRAM.md              # UML class diagram documentation
├── SAMPLE_DATA.md                    # Sample data and usage examples
└── .gitignore                        # Git ignore file
```

## Served Cities

The system supports beneficiaries from the following Palestinian cities:
- Gaza
- Rafah
- Khan Younis
- Jerusalem
- Jenin
- Tulkarm
- Hebron
- Ramallah
- Jabalia
- Nablus

## Compilation Instructions

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal

### Compile the Project

From the project root directory:

```bash
# Create bin directory for compiled classes
mkdir -p bin

# Compile all Java files
javac -d bin -sourcepath src src/AidSystemDriver.java
```

This will compile all necessary classes and place them in the `bin/` directory.

## How to Run

After compilation, run the application from the project root:

```bash
java -cp bin AidSystemDriver
```

## Usage Guide

### Main Menu Options

1. **Register New Beneficiary** - Add families or individuals to the system
2. **Add New Aid Item** - Register aid items by category
3. **Record Distribution Event** - Log when aid is distributed to beneficiaries
4. **Display All Beneficiaries** - View all registered beneficiaries
5. **Display All Aid Items** - View all available aid items
6. **Display All Distribution Events** - View distribution history
7. **Statistical Reports** - Access reporting submenu
8. **Save Data to Text File** - Export data to text format
9. **Load Data from Text File** - Import data from text file
10. **Save Data to Binary File** - Export data to binary format
11. **Load Data from Binary File** - Import data from binary file
0. **Exit** - Close the application

### Statistical Reports Submenu

1. **Families Served in Specific City** - Count families by city
2. **Total Aid Items Distributed** - Overall distribution count
3. **Aid Items by Category** - Filter items by type
4. **Beneficiaries Served Between Dates** - Date range queries
5. **Most Served City** - Find city with most beneficiaries
0. **Return to Main Menu**

## Technical Details

### Object-Oriented Design
- Abstract classes for extensibility (Beneficiary, AidItem)
- Interface-based file operations
- Inheritance hierarchies for different beneficiary and aid types
- Polymorphism for flexible type handling

### Data Structures
- ArrayList for dynamic collections
- HashMap for city statistics
- HashSet for unique beneficiary tracking
- GregorianCalendar for date management

### Error Handling
- Custom exceptions for business logic validation
- Try-catch blocks for user input
- Comprehensive validation throughout

### Serialization
- All model classes implement Serializable
- Support for both text and binary file formats
- Backward compatibility for data loading

## Example Workflow

1. Start the application
2. Register beneficiaries (families and individuals)
3. Add aid items to inventory
4. Record distribution events
5. Generate statistical reports
6. Save data to file for persistence
7. Load data when restarting the application

## Development Notes

- All dates use GregorianCalendar for consistency
- City validation prevents registration from unserved areas
- ID uniqueness enforced through DuplicateRegistrationException
- Beneficiary equality based on ID field
- Sorting implemented via Comparable interface

## License

This project is developed for humanitarian aid distribution management in Palestinian communities.

## Contributing

For contributions or suggestions, please contact the development team.

---

**Built with dedication to serve Palestinian communities**
