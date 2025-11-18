# UML Class Diagram

## Class Hierarchy Overview

```
┌──────────────────────────────────────────────────────────────────────────┐
│                     Smart Aid Distribution System                         │
└──────────────────────────────────────────────────────────────────────────┘

                         ┌─────────────────┐
                         │  AidSystemDriver│
                         ├─────────────────┤
                         │ - aidManager    │
                         │ - scanner       │
                         │ - dateFormat    │
                         ├─────────────────┤
                         │ + main()        │
                         │ + displayMenu() │
                         └────────┬────────┘
                                  │
                                  │ uses
                                  ▼
                         ┌─────────────────┐
                         │   AidManager    │
                         ├─────────────────┤
                         │ - beneficiaries │
                         │ - aidItems      │
                         │ - events        │
                         ├─────────────────┤
                         │ + register()    │
                         │ + addItem()     │
                         │ + record()      │
                         │ + getStats()    │
                         └─────────────────┘
```

## Detailed Class Descriptions

### 1. Abstract Class: Beneficiary

```
┌────────────────────────────────────┐
│         <<abstract>>               │
│         Beneficiary                │
├────────────────────────────────────┤
│ # id: String                       │
│ # name: String                     │
│ # city: String                     │
├────────────────────────────────────┤
│ + Beneficiary(id, name, city)      │
│ + getId(): String                  │
│ + setId(id: String): void          │
│ + getName(): String                │
│ + setName(name: String): void      │
│ + getCity(): String                │
│ + setCity(city: String): void      │
│ + getType(): String <<abstract>>   │
│ + equals(obj: Object): boolean     │
│ + hashCode(): int                  │
│ + toString(): String               │
└────────────────────────────────────┘
                 △
                 │
        ┌────────┴────────┐
        │                 │
┌───────┴──────┐  ┌──────┴────────┐
│    Family    │  │  Individual   │
├──────────────┤  ├───────────────┤
│ - members    │  │ - status      │
│   Count: int │  │   : String    │
├──────────────┤  ├───────────────┤
│ + getType()  │  │ + getType()   │
│ + compareTo()│  │ + compareTo() │
└──────────────┘  └───────────────┘
  implements          implements
  Comparable<         Comparable<
    Family>             Individual>
```

### 2. Abstract Class: AidItem

```
┌────────────────────────────────────┐
│         <<abstract>>               │
│          AidItem                   │
├────────────────────────────────────┤
│ # code: String                     │
│ # description: String              │
├────────────────────────────────────┤
│ + AidItem(code, description)       │
│ + getCode(): String                │
│ + setCode(code: String): void      │
│ + getDescription(): String         │
│ + setDescription(desc: String)     │
│ + getCategory(): String <<abstract>>│
│ + toString(): String               │
└────────────────────────────────────┘
                 △
                 │
     ┌───────────┼───────────┐
     │           │           │
┌────┴─────┐ ┌──┴───────┐ ┌─┴────────┐
│  Food    │ │ Medical  │ │  Winter  │
│ Package  │ │   Kit    │ │   Bag    │
├──────────┤ ├──────────┤ ├──────────┤
│+getCategory│+getCategory│+getCategory│
└──────────┘ └──────────┘ └──────────┘
      
     ┌────────────┐
     │ Emergency  │
     │    Kit     │
     ├────────────┤
     │+getCategory│
     └────────────┘
```

### 3. Class: DistributionEvent

```
┌────────────────────────────────────┐
│      DistributionEvent             │
├────────────────────────────────────┤
│ - item: AidItem                    │
│ - beneficiary: Beneficiary         │
│ - date: GregorianCalendar          │
├────────────────────────────────────┤
│ + DistributionEvent(item, ben, dt) │
│ + getItem(): AidItem               │
│ + setItem(item: AidItem): void     │
│ + getBeneficiary(): Beneficiary    │
│ + setBeneficiary(ben: Beneficiary) │
│ + getDate(): GregorianCalendar     │
│ + setDate(date: GregorianCalendar) │
│ + toString(): String               │
└────────────────────────────────────┘
        │                    │
        │ has-a              │ has-a
        ▼                    ▼
   ┌─────────┐         ┌──────────────┐
   │ AidItem │         │ Beneficiary  │
   └─────────┘         └──────────────┘
```

### 4. Interface: FileOperations

```
┌────────────────────────────────────────┐
│      <<interface>>                     │
│      FileOperations                    │
├────────────────────────────────────────┤
│ + saveToTextFile(filename: String)     │
│ + loadFromTextFile(filename: String)   │
│ + saveToBinaryFile(filename: String)   │
│ + loadFromBinaryFile(filename: String) │
└────────────────────────────────────────┘
                 △
                 │ implements
                 │
         ┌───────┴────────┐
         │   AidManager   │
         └────────────────┘
```

### 5. Class: AidManager

```
┌──────────────────────────────────────────────────┐
│              AidManager                          │
├──────────────────────────────────────────────────┤
│ - beneficiaries: ArrayList<Beneficiary>          │
│ - aidItems: ArrayList<AidItem>                   │
│ - distributionEvents: ArrayList<DistributionEvent>│
│ - SERVED_CITIES: String[]                        │
├──────────────────────────────────────────────────┤
│ + AidManager()                                   │
│ + registerBeneficiary(b: Beneficiary): void      │
│ + addAidItem(item: AidItem): void                │
│ + recordDistribution(bid, code, date): void      │
│ + findBeneficiaryById(id: String): Beneficiary   │
│ + findAidItemByCode(code: String): AidItem       │
│ + getFamiliesServedInCity(city: String): int     │
│ + getTotalAidItemsDistributed(): int             │
│ + getAidItemsByCategory(): Map<String, Integer>  │
│ + getBeneficiariesServedBetweenDates(...): List  │
│ + getMostServedCity(): String                    │
│ + getAllBeneficiaries(): List<Beneficiary>       │
│ + getAllAidItems(): List<AidItem>                │
│ + getAllDistributionEvents(): List<...>          │
│ + saveToTextFile(filename: String): void         │
│ + loadFromTextFile(filename: String): void       │
│ + saveToBinaryFile(filename: String): void       │
│ + loadFromBinaryFile(filename: String): void     │
│ + getServedCities(): String[]                    │
└──────────────────────────────────────────────────┘
```

### 6. Exception Classes

```
┌──────────────────────────────────┐
│         Exception                │
└──────────────────────────────────┘
                △
                │ extends
        ┌───────┼──────────┐
        │       │          │
┌───────┴───────┐ ┌────────┴────────┐ ┌────────────────────┐
│  Duplicate    │ │  ItemNotFound   │ │  CityNotServed     │
│ Registration  │ │   Exception     │ │   Exception        │
│  Exception    │ │                 │ │                    │
├───────────────┤ ├─────────────────┤ ├────────────────────┤
│ + constructor │ │ + constructor   │ │ + constructor      │
└───────────────┘ └─────────────────┘ └────────────────────┘
```

### 7. Main Driver Class

```
┌────────────────────────────────────────────┐
│         AidSystemDriver                    │
├────────────────────────────────────────────┤
│ - aidManager: AidManager                   │
│ - scanner: Scanner                         │
│ - dateFormat: SimpleDateFormat             │
├────────────────────────────────────────────┤
│ + main(args: String[]): void               │
│ - displayMainMenu(): void                  │
│ - registerBeneficiary(): void              │
│ - addAidItem(): void                       │
│ - recordDistribution(): void               │
│ - displayAllBeneficiaries(): void          │
│ - displayAllAidItems(): void               │
│ - displayAllDistributionEvents(): void     │
│ - statisticalReportsMenu(): void           │
│ - familiesServedInCity(): void             │
│ - totalAidItemsDistributed(): void         │
│ - countAidItemsByCategory(): void          │
│ - beneficiariesBetweenDates(): void        │
│ - mostServedCity(): void                   │
│ - saveToTextFile(): void                   │
│ - saveToBinaryFile(): void                 │
│ - loadFromTextFile(): void                 │
│ - loadFromBinaryFile(): void               │
└────────────────────────────────────────────┘
```

## Relationships

### Inheritance
- **Family** and **Individual** inherit from **Beneficiary**
- **FoodPackage**, **MedicalKit**, **WinterBag**, **EmergencyKit** inherit from **AidItem**
- All custom exceptions inherit from **Exception**

### Implementation
- **Family** implements **Comparable<Family>**
- **Individual** implements **Comparable<Individual>**
- **AidManager** implements **FileOperations**
- **Beneficiary**, **AidItem**, **DistributionEvent** implement **Serializable**

### Composition
- **DistributionEvent** has-a **AidItem** and **Beneficiary**
- **AidManager** contains ArrayLists of **Beneficiary**, **AidItem**, and **DistributionEvent**
- **AidSystemDriver** uses **AidManager**

### Dependencies
- **AidManager** uses all model classes and exception classes
- **AidSystemDriver** uses **AidManager**, model classes, and exception classes

## Key Design Patterns

1. **Template Method**: Abstract methods in Beneficiary and AidItem
2. **Strategy Pattern**: Different beneficiary and aid item types
3. **Factory-like**: Creation of different beneficiary and aid item types in driver
4. **Data Access Object**: AidManager acts as a DAO for all entities
5. **Singleton-like**: Static instance of AidManager in driver
