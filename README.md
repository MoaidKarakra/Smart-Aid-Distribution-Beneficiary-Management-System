# Smart Aid Distribution & Beneficiary Management System

Console-based Java application for managing aid distribution in Palestinian communities.

## Overview

This system helps humanitarian organizations in Palestine manage beneficiaries and distribute aid items. The application simulates real tasks performed by NGOs, local committees, municipalities, and volunteer groups in areas like Gaza, Rafah, Khan Younis, Jerusalem, Jenin, Tulkarm, Hebron, Ramallah, Jabalia, and Nablus.

## Features

- **Beneficiary Management**: Register and manage families and individuals
- **Aid Item Tracking**: Add and manage different types of aid (food packages, medical kits, winter bags, emergency kits)
- **Distribution Recording**: Track when aid is distributed to beneficiaries
- **Statistical Reports**: Generate various reports on aid distribution
- **File Operations**: Save and load data in text or binary format

## Project Structure

```
src/
├── models/
│   ├── Beneficiary.java         # Abstract base class for beneficiaries
│   ├── Family.java              # Family beneficiary type
│   ├── Individual.java          # Individual beneficiary type
│   ├── AidItem.java             # Abstract base class for aid items
│   ├── FoodPackage.java         # Food package aid item
│   ├── MedicalKit.java          # Medical kit aid item
│   ├── WinterBag.java           # Winter bag aid item
│   ├── EmergencyKit.java        # Emergency kit aid item
│   └── DistributionEvent.java   # Distribution event record
├── manager/
│   ├── AidManager.java          # Core system manager
│   └── FileOperations.java      # File operations interface
├── exceptions/
│   ├── DuplicateRegistrationException.java
│   ├── ItemNotFoundException.java
│   └── CityNotServedException.java
└── AidSystemDriver.java         # Main driver class with menu interface
```

## Compilation Instructions

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compile
Navigate to the `src` directory and compile all Java files:

```bash
cd src
javac AidSystemDriver.java models/*.java manager/*.java exceptions/*.java
```

## Running Instructions

After compilation, run the main driver class:

```bash
java AidSystemDriver
```

## Usage Guide

### Main Menu

The system provides a menu-driven interface with the following options:

1. **Register Beneficiary**: Add a new family or individual beneficiary
2. **Add Aid Item**: Register new aid items (food, medical, winter, emergency)
3. **Record Aid Distribution**: Log when aid is given to a beneficiary
4. **Display All Beneficiaries**: View all registered beneficiaries
5. **Display All Aid Items**: View all registered aid items
6. **Display All Distribution Events**: View all distribution records
7. **Generate Statistical Reports**: Access statistical analysis submenu
8. **Save to Text File**: Export data to a text file
9. **Save to Binary File**: Export data to a binary file
10. **Load from Text File**: Import data from a text file
11. **Load from Binary File**: Import data from a binary file
0. **Exit**: Close the application

### Statistical Reports Submenu

1. Number of families served in a city
2. Total aid items distributed
3. Count aid items by category
4. Beneficiaries served between two dates
5. Most served city
0. Back to main menu

## Served Cities

The system supports the following Palestinian cities:
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

## Example Workflow

1. Register beneficiaries (families and individuals)
2. Add aid items to the inventory
3. Record distribution events when aid is given
4. Generate reports to analyze distribution patterns
5. Save data for future use

## Data Persistence

The system supports two file formats:
- **Text files** (.txt): Human-readable format
- **Binary files** (.dat): Efficient serialized format

## Error Handling

The system includes custom exceptions:
- `DuplicateRegistrationException`: Prevents duplicate IDs
- `ItemNotFoundException`: Handles missing beneficiaries or items
- `CityNotServedException`: Validates city names

## Notes

- All dates should be entered in yyyy-MM-dd format
- Beneficiary IDs and aid item codes must be unique
- The system validates all inputs and provides error messages
- Data is stored in memory and must be saved before exiting
