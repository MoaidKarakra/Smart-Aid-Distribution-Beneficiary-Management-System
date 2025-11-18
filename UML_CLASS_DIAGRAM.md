# UML Class Diagram

## System Architecture

This document provides a comprehensive UML class diagram representation of the Smart Aid Distribution & Beneficiary Management System.

## Class Diagram

```
┌─────────────────────────────────────┐
│         <<interface>>               │
│       FileOperations                │
├─────────────────────────────────────┤
│ + saveToTextFile(filename: String)  │
│ + loadFromTextFile(filename: String)│
│ + saveToBinaryFile(filename: String)│
│ + loadFromBinaryFile(filename)      │
└─────────────────────────────────────┘
                  △
                  │ implements
                  │
┌─────────────────────────────────────────────────────────┐
│                    AidManager                           │
├─────────────────────────────────────────────────────────┤
│ - beneficiaries: ArrayList<Beneficiary>                 │
│ - aidItems: ArrayList<AidItem>                          │
│ - distributionEvents: ArrayList<DistributionEvent>      │
│ - SERVED_CITIES: String[]                               │
├─────────────────────────────────────────────────────────┤
│ + addBeneficiary(b: Beneficiary)                        │
│ + addAidItem(item: AidItem)                             │
│ + recordDistribution(event: DistributionEvent)          │
│ + findBeneficiaryById(id: String): Beneficiary          │
│ + findAidItemByCode(code: String): AidItem              │
│ + displayAllBeneficiaries()                             │
│ + displayAllAidItems()                                  │
│ + displayAllDistributionEvents()                        │
│ + getFamiliesServedInCity(city: String): int            │
│ + getTotalAidItemsDistributed(): int                    │
│ + getItemsByCategory(category: String): ArrayList       │
│ + getBeneficiariesServedBetweenDates(...): Set          │
│ + getMostServedCity(): String                           │
│ + saveToTextFile(filename: String)                      │
│ + loadFromTextFile(filename: String)                    │
│ + saveToBinaryFile(filename: String)                    │
│ + loadFromBinaryFile(filename: String)                  │
│ - isCityServed(city: String): boolean                   │
└─────────────────────────────────────────────────────────┘
              │                    │
              │ manages            │ manages
              ▼                    ▼
┌─────────────────────────┐  ┌─────────────────────────┐
│  <<abstract>>           │  │  <<abstract>>           │
│     Beneficiary         │  │      AidItem            │
├─────────────────────────┤  ├─────────────────────────┤
│ - id: String            │  │ - code: String          │
│ - name: String          │  │ - description: String   │
│ - city: String          │  ├─────────────────────────┤
├─────────────────────────┤  │ + getCategory(): String │
│ + getType(): String     │  │ + getCode(): String     │
│ + getId(): String       │  │ + setCode(String)       │
│ + getName(): String     │  │ + getDescription()      │
│ + getCity(): String     │  │ + setDescription(String)│
│ + setId(String)         │  │ + toString(): String    │
│ + setName(String)       │  │ <<Serializable>>        │
│ + setCity(String)       │  └─────────────────────────┘
│ + equals(Object): bool  │           △
│ + toString(): String    │           │ extends
│ <<Serializable>>        │           │
└─────────────────────────┘  ┌────────┴────────┬──────────────┬───────────────┐
         △                   │                 │              │               │
         │ extends           │                 │              │               │
    ┌────┴────┐              │                 │              │               │
    │         │         ┌────────────┐  ┌─────────────┐ ┌────────────┐ ┌──────────────┐
┌──────────┐ ┌─────────────┐│ FoodPackage│  │ MedicalKit  │ │ WinterBag  │ │ EmergencyKit │
│  Family  │ │ Individual  ││            │  │             │ │            │ │              │
├──────────┤ ├─────────────┤├────────────┤  ├─────────────┤ ├────────────┤ ├──────────────┤
│- members │ │- status:    ││+ getCategory│  │+ getCategory│ │+ getCategory│ │+ getCategory │
│  Count:  │ │  String     ││  (): String│  │  (): String │ │  (): String│ │  (): String  │
│  int     │ │             ││returns     │  │returns      │ │returns     │ │returns       │
├──────────┤ ├─────────────┤│"Food       │  │"Medical Kit"│ │"Winter Bag"│ │"Emergency    │
│+ getType │ │+ getType()  ││ Package"   │  │             │ │            │ │  Kit"        │
│  ():     │ │  : String   │└────────────┘  └─────────────┘ └────────────┘ └──────────────┘
│  String  │ │  returns    │
│  returns │ │  "Individual│
│ "Family" │ │             │
│+ compare │ │+ getStatus()│
│  To()    │ │+ setStatus()│
│+ get/set │ │+ compareTo()│
│  Members │ │<<Comparable>│
│  Count() │ │             │
│<<Compara │ └─────────────┘
│  ble>>   │
└──────────┘


┌───────────────────────────────────────────┐
│        DistributionEvent                  │
├───────────────────────────────────────────┤
│ - item: AidItem                           │
│ - beneficiary: Beneficiary                │
│ - date: GregorianCalendar                 │
├───────────────────────────────────────────┤
│ + getItem(): AidItem                      │
│ + getBeneficiary(): Beneficiary           │
│ + getDate(): GregorianCalendar            │
│ + setItem(AidItem)                        │
│ + setBeneficiary(Beneficiary)             │
│ + setDate(GregorianCalendar)              │
│ + toString(): String                      │
│ <<Serializable>>                          │
└───────────────────────────────────────────┘
         │ contains
         ├──────────► AidItem
         └──────────► Beneficiary


┌────────────────────────────────────────┐
│  <<Exception>>                         │
│  DuplicateRegistrationException        │
├────────────────────────────────────────┤
│ + DuplicateRegistrationException       │
│   (message: String)                    │
└────────────────────────────────────────┘

┌────────────────────────────────────────┐
│  <<Exception>>                         │
│  ItemNotFoundException                 │
├────────────────────────────────────────┤
│ + ItemNotFoundException                │
│   (message: String)                    │
└────────────────────────────────────────┘

┌────────────────────────────────────────┐
│  <<Exception>>                         │
│  CityNotServedException                │
├────────────────────────────────────────┤
│ + CityNotServedException               │
│   (message: String)                    │
└────────────────────────────────────────┘


┌─────────────────────────────────────────┐
│       AidSystemDriver                   │
│       <<main class>>                    │
├─────────────────────────────────────────┤
│ - aidManager: AidManager (static)       │
│ - scanner: Scanner (static)             │
├─────────────────────────────────────────┤
│ + main(args: String[])                  │
│ - displayMainMenu()                     │
│ - handleMainMenuChoice(int): boolean    │
│ - registerBeneficiary()                 │
│ - addAidItem()                          │
│ - recordDistribution()                  │
│ - showStatisticalReports()              │
│ - handleReportsMenuChoice(int): boolean │
│ - reportFamiliesInCity()                │
│ - reportTotalDistributions()            │
│ - reportItemsByCategory()               │
│ - reportBeneficiariesBetweenDates()     │
│ - reportMostServedCity()                │
│ - saveToTextFile()                      │
│ - loadFromTextFile()                    │
│ - saveToBinaryFile()                    │
│ - loadFromBinaryFile()                  │
└─────────────────────────────────────────┘
              │ uses
              ▼
         AidManager
```

## Relationships

### Inheritance
- **Family** extends **Beneficiary**
- **Individual** extends **Beneficiary**
- **FoodPackage** extends **AidItem**
- **MedicalKit** extends **AidItem**
- **WinterBag** extends **AidItem**
- **EmergencyKit** extends **AidItem**

### Interface Implementation
- **AidManager** implements **FileOperations**
- **Family** implements **Comparable<Family>**
- **Individual** implements **Comparable<Individual>**

### Composition/Aggregation
- **AidManager** manages collections of **Beneficiary**, **AidItem**, and **DistributionEvent**
- **DistributionEvent** contains references to **AidItem** and **Beneficiary**
- **AidSystemDriver** uses **AidManager**

### Dependencies
- **AidManager** throws **DuplicateRegistrationException** and **CityNotServedException**
- **AidSystemDriver** catches all custom exceptions

## Design Patterns Used

### 1. Template Method Pattern
- Abstract classes **Beneficiary** and **AidItem** define the template
- Subclasses implement specific behavior (getType(), getCategory())

### 2. Strategy Pattern
- **FileOperations** interface allows different persistence strategies
- Implementation can be swapped without changing client code

### 3. Factory Pattern (Implicit)
- Different aid item types created based on user selection
- Different beneficiary types instantiated based on user choice

### 4. Singleton-like Pattern
- **AidManager** instance maintained statically in **AidSystemDriver**

## Key Interfaces and Abstract Classes

### FileOperations Interface
Defines contract for data persistence operations:
- Text file I/O
- Binary file I/O

### Beneficiary Abstract Class
Provides common beneficiary properties and behavior:
- ID-based equality
- Common attributes (id, name, city)
- Abstract getType() method for polymorphism

### AidItem Abstract Class
Provides common aid item structure:
- Code and description fields
- Abstract getCategory() method for polymorphism

## Exception Hierarchy

All custom exceptions extend **java.lang.Exception**:
- **DuplicateRegistrationException** - Prevents duplicate beneficiary IDs
- **ItemNotFoundException** - Handles missing aid items
- **CityNotServedException** - Validates city against served list

## Serialization

Classes implementing **Serializable**:
- Beneficiary (and all subclasses)
- AidItem (and all subclasses)
- DistributionEvent

This enables binary file persistence for all domain objects.

## Comparable Implementation

- **Family** - Sorted by member count (descending)
- **Individual** - Sorted by status priority (Elderly > Orphan > Patient > Student)

## Package Structure

```
(default package)
    └── AidSystemDriver.java

models
    ├── Beneficiary.java
    ├── Family.java
    ├── Individual.java
    ├── AidItem.java
    ├── FoodPackage.java
    ├── MedicalKit.java
    ├── WinterBag.java
    ├── EmergencyKit.java
    └── DistributionEvent.java

manager
    ├── FileOperations.java
    └── AidManager.java

exceptions
    ├── DuplicateRegistrationException.java
    ├── ItemNotFoundException.java
    └── CityNotServedException.java
```

## Notes

- All model classes are properly encapsulated with private fields and public accessors
- The system uses defensive programming with validation at entry points
- Palestinian city names are hard-coded in AidManager.SERVED_CITIES
- GregorianCalendar used for date handling throughout the system
- The driver class provides comprehensive error handling for user input
