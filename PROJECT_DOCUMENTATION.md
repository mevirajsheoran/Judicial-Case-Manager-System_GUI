# Judicial Case Manager System - Complete Documentation

## Table of Contents
1. [Project Overview](#1-project-overview)
2. [System Architecture](#2-system-architecture)
3. [Database Schema](#3-database-schema)
4. [Package Structure](#4-package-structure)
5. [Core Components](#5-core-components)
6. [GUI Layer](#6-gui-layer)
7. [DAO Layer](#7-dao-layer)
8. [Model Layer](#8-model-layer)
9. [Exception Handling](#9-exception-handling)
10. [How to Run](#10-how-to-run)

---

## 1. Project Overview

**Judicial Case Manager** is a comprehensive desktop application built in Core Java with Swing GUI that manages court cases, hearings, evidence, appeals, and related judicial records. It connects to a MySQL database to store and retrieve all judicial data.

### Key Features
- **Case Management**: Create, read, update, delete (CRUD) case records
- **Person Management**: Manage petitioners, respondents, witnesses, police officers
- **Hearing Scheduling**: Schedule and track court hearings
- **Evidence Tracking**: Record and manage case evidence
- **Bail Management**: Track bail requests and conditions
- **Court Management**: Manage courts, courtrooms, and court staff
- **Settlement Tracking**: Record case settlements
- **Appeals Management**: Handle case appeals

---

## 2. System Architecture

The application follows a **3-tier architecture**:

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐   │
│  │ LoginFrame   │  │AdminDashboard│  │  GUI Panels      │   │
│  └──────────────┘  └──────────────┘  └──────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    BUSINESS LOGIC LAYER                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐   │
│  │  DAO Classes │  │   Models     │  │   Exceptions     │   │
│  └──────────────┘  └──────────────┘  └──────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                     DATA ACCESS LAYER                        │
│              ┌─────────────────────────┐                     │
│              │   MySQL Database        │                     │
│              │   (judicialcasedb)      │                     │
│              └─────────────────────────┘                     │
└─────────────────────────────────────────────────────────────┘
```

---

## 3. Database Schema

The database `judicialcasedb` contains **22 tables** organized as follows:

### 3.1 Core Tables

| Table Name | Description | Records |
|------------|-------------|---------|
| **Court** | Stores court information (Supreme Court, High Courts, etc.) | 20 |
| **Case_Category** | Categories of cases (Criminal, Civil, Family, etc.) | 20 |
| **Case_details** | Main case records with status and court assignment | 20 |
| **Verdict** | Case verdicts with decisions and penalties | 20 |

### 3.2 Person-Related Tables

| Table Name | Description | Relationship |
|------------|-------------|--------------|
| **Person** | Base table for all individuals | Parent table |
| **Petitioner** | Persons who file cases | Extends Person |
| **Respondent** | Persons who respond to cases | Extends Person |
| **Witness** | Case witnesses | Extends Person |
| **Police_Officer** | Law enforcement personnel | Extends Person + Police_Station |
| **Lawyer** | Legal representatives | Independent |
| **Judge** | Court judges | Linked to Court |

### 3.3 Case Process Tables

| Table Name | Description |
|------------|-------------|
| **Hearing** | Court hearing schedules and outcomes |
| **Evidence** | Case evidence with type and description |
| **FIR** | First Information Reports |
| **Charges** | Charges filed under legal acts |
| **Bail** | Bail requests and conditions |
| **Settlement** | Out-of-court settlements |
| **Appeal** | Case appeals to higher courts |
| **Case_History** | Timeline of case events |

### 3.4 Supporting Tables

| Table Name | Description |
|------------|-------------|
| **Police_Station** | Police station information |
| **Court_Staff** | Administrative court personnel |
| **Court_Room** | Specific courtroom assignments |
| **Legal_Act** | Indian legal acts and sections |

### Entity Relationship Diagram (Simplified)

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│    Court    │────<│Case_details │>────│Case_Category│
└──────┬──────┘     └──────┬──────┘     └─────────────┘
       │                   │
       ▼                   ▼
┌─────────────┐     ┌─────────────┐
│    Judge    │     │   Hearing   │
└─────────────┘     └─────────────┘
                          │
                    ┌─────┴─────┐
                    ▼           ▼
              ┌─────────┐ ┌─────────┐
              │Evidence │ │ Verdict │
              └─────────┘ └─────────┘
```

---

## 4. Package Structure

```
src/
├── main/
│   ├── App.java                          # Application entry point
│   ├── dao/                              # Data Access Objects
│   │   ├── AppealDAO.java
│   │   ├── BailRequestDAO.java
│   │   ├── CaseDAO.java
│   │   ├── CaseHistoryDAO.java
│   │   ├── CourtDAO.java
│   │   ├── CourtRoomDAO.java
│   │   ├── CourtStaffDAO.java
│   │   ├── EvidenceDAO.java
│   │   ├── HearingDAO.java
│   │   ├── PersonDAO.java
│   │   ├── PetitionerDAO.java
│   │   ├── PoliceOfficerDAO.java
│   │   ├── RespondentDAO.java
│   │   ├── SettlementDAO.java
│   │   └── WitnessDAO.java
│   ├── db/
│   │   └── DatabaseConnection.java       # Database connection handler
│   ├── exception/                        # Custom exceptions
│   │   ├── CaseAlreadyClosedException.java
│   │   ├── CaseNotFoundException.java
│   │   ├── DatabaseException.java
│   │   ├── DuplicateHearingException.java
│   │   ├── UserNotFoundException.java
│   │   └── ValidationException.java
│   ├── gui/                              # User Interface
│   │   ├── AdminDashboard.java
│   │   ├── LoginFrame.java
│   │   ├── components/
│   │   │   └── ButtonFactory.java
│   │   └── panels/
│   │       ├── AppealPanel.java
│   │       ├── BailPanel.java
│   │       ├── CaseHistoryPanel.java
│   │       ├── CaseManagementPanel.java
│   │       ├── CourtPanel.java
│   │       ├── CourtRoomPanel.java
│   │       ├── CourtStaffPanel.java
│   │       ├── EvidencePanel.java
│   │       ├── HearingPanel.java
│   │       ├── PersonManagementPanel.java
│   │       ├── PetitionerPanel.java
│   │       ├── PoliceOfficerPanel.java
│   │       ├── RespondentPanel.java
│   │       ├── SettlementPanel.java
│   │       └── WitnessPanel.java
│   ├── model/                            # Entity models
│   │   ├── Appeal.java
│   │   ├── BailRequest.java
│   │   ├── CaseDetails.java
│   │   ├── CaseHistory.java
│   │   ├── Court.java
│   │   ├── CourtRoom.java
│   │   ├── CourtStaff.java
│   │   ├── Evidence.java
│   │   ├── Hearing.java
│   │   ├── Judge.java
│   │   ├── Lawyer.java
│   │   ├── Person.java (abstract)
│   │   ├── Settlement.java
│   │   └── person/
│   │       ├── Petitioner.java
│   │       ├── PoliceOfficer.java
│   │       ├── Respondent.java
│   │       └── Witness.java
│   ├── resources/
│   │   └── application.properties        # DB configuration
│   └── utils/
└── JudicialCaseManagerSystem.sql         # Database schema + seed data
```

---

## 5. Core Components

### 5.1 Application Entry Point

**File:** `main/App.java`

The `App` class is the application entry point that:
1. Tests database connectivity on startup
2. Launches the `LoginFrame` using `SwingUtilities.invokeLater()`
3. Handles database connection failures gracefully

```java
public class App {
    public static void main(String[] args) {
        // 1. Test DB Connection
        // 2. Launch LoginFrame on successful connection
        // 3. Show error dialog if connection fails
    }
}
```

### 5.2 Database Connection

**File:** `main/db/DatabaseConnection.java`

A **Singleton pattern** implementation for database connectivity:

- **Configuration**: Reads from `application.properties`
- **Properties**: `db.url`, `db.user`, `db.password`
- **Methods**:
  - `getConnection()` - Returns singleton connection instance
  - `closeConnection()` - Safely closes the connection

---

## 6. GUI Layer

### 6.1 LoginFrame

**File:** `main/gui/LoginFrame.java`

The authentication entry point with:
- Username and password fields
- Hardcoded credentials: `admin` / `admin123`
- Opens `AdminDashboard` on successful login
- Shows error dialog on invalid credentials

### 6.2 AdminDashboard

**File:** `main/gui/AdminDashboard.java`

The main application window featuring:
- **CardLayout** for panel switching
- **Sidebar menu** with navigation buttons
- **15 functional modules** accessible from the sidebar

**Available Modules:**
1. Cases - CaseManagementPanel
2. Persons - PersonManagementPanel
3. Evidence - EvidencePanel
4. Hearings - HearingPanel
5. Appeals - AppealPanel
6. CourtRooms - CourtRoomPanel
7. Court Staff - CourtStaffPanel
8. Bail Requests - BailPanel
9. Case History - CaseHistoryPanel
10. Settlements - SettlementPanel

### 6.3 Management Panels

Each panel follows a consistent CRUD pattern:

**Example: CaseManagementPanel**
- **Table Display**: JTable with DefaultTableModel showing all cases
- **Buttons**: Add, Edit, Delete (via ButtonFactory)
- **Dialogs**: JOptionPane forms for data input
- **Features**:
  - Load all cases on panel initialization
  - Add new case with validation
  - Edit selected case (prevents editing closed cases)
  - Delete with confirmation dialog
  - Real-time table refresh after operations

**Common Panel Features:**
- BorderLayout organization
- JTable with JScrollPane for data display
- Form dialogs for Create/Update operations
- Error handling with JOptionPane messages
- DAO integration for database operations

### 6.4 ButtonFactory

**File:** `main/gui/components/ButtonFactory.java`

A **Factory pattern** for consistent button styling:

| Method | Style | Use Case |
|--------|-------|----------|
| `createButton()` | Primary blue | Standard actions |
| `createSuccessButton()` | Green | Add, Save, Confirm |
| `createDangerButton()` | Red | Delete, Remove |
| `createNeutralButton()` | Gray | Cancel, Back |
| `createPrimaryButton()` | Material blue | Main actions |
| `createSecondaryButton()` | Light gray | Secondary actions |

---

## 7. DAO Layer

### 7.1 Data Access Pattern

All DAO classes follow a consistent pattern implementing CRUD operations:

**CaseDAO Example:**
```java
public class CaseDAO {
    public void addCase(CaseDetails caseDetails) throws DatabaseException
    public CaseDetails getCaseById(int caseId) throws DatabaseException
    public static List<CaseDetails> getAllCases() throws DatabaseException
    public void updateCase(CaseDetails caseDetails) throws DatabaseException, CaseAlreadyClosedException
    public boolean deleteCase(int caseId) throws DatabaseException
}
```

### 7.2 DAO Classes Summary

| DAO Class | Entity | Key Methods |
|-----------|--------|-------------|
| CaseDAO | CaseDetails | addCase, getCaseById, getAllCases, updateCase, deleteCase |
| PersonDAO | Person | addPerson, getPersonById, getAllPersons, updatePerson, deletePerson |
| HearingDAO | Hearing | addHearing, getHearingById, getAllHearings, updateHearing, deleteHearing |
| EvidenceDAO | Evidence | addEvidence, getEvidenceById, getAllEvidence, updateEvidence, deleteEvidence |
| AppealDAO | Appeal | addAppeal, getAppealById, getAllAppeals, updateAppeal, deleteAppeal |
| BailRequestDAO | BailRequest | addBailRequest, getBailRequestById, getAllBailRequests, updateBailRequest, deleteBailRequest |
| CourtDAO | Court | addCourt, getCourtById, getAllCourts, updateCourt, deleteCourt |
| CourtStaffDAO | CourtStaff | addCourtStaff, getCourtStaffById, getAllCourtStaff, updateCourtStaff, deleteCourtStaff |
| SettlementDAO | Settlement | addSettlement, getSettlementById, getAllSettlements, updateSettlement, deleteSettlement |

### 7.3 Business Rules Implementation

**CaseDAO** enforces important business rules:
- **Closed Case Protection**: Prevents updates to cases with status "Closed" or "Disposed"
- **Throws**: `CaseAlreadyClosedException` when attempting to modify closed cases

---

## 8. Model Layer

### 8.1 Entity Classes

#### CaseDetails
**File:** `main/model/CaseDetails.java`

Represents a court case with attributes:
- `caseId` (int) - Primary key
- `caseNumber` (String) - Unique case identifier
- `filingDate` (Date) - When the case was filed
- `caseType` (String) - Type of case
- `status` (String) - Current status (Pending, Ongoing, Closed, etc.)
- `categoryId` (int) - FK to Case_Category
- `courtId` (int) - FK to Court

#### Person (Abstract Base Class)
**File:** `main/model/Person.java`

Base class for all person types:
- `personId` (int)
- `name` (String)
- `dateOfBirth` (Date)
- `contact` (String)
- `address` (String)
- `nationalId` (String)

**Abstract Method:** `getRole()` - Implemented by subclasses

#### Person Subclasses

| Class | Additional Attributes | Relationship |
|-------|----------------------|--------------|
| **Petitioner** | petitionFiledDate, legalRepresentative | Extends Person |
| **Respondent** | responseSubmittedDate, legalRepresentative | Extends Person |
| **Witness** | testimonyDate, typeOfWitness | Extends Person |
| **PoliceOfficer** | policeStationId, badgeNumber, officerRank, department | Extends Person + PoliceStation |

#### Other Key Models

| Class | Key Attributes |
|-------|---------------|
| **Hearing** | hearingId, caseId, judgeId, date, time, description, outcome |
| **Evidence** | evidenceId, caseId, type, description, collectedBy, submissionDate |
| **Court** | courtId, courtName, location, jurisdictionLevel |
| **Judge** | judgeId, name, designation, experienceYears, courtId |
| **Lawyer** | lawyerId, name, contact, specialization, barRegistrationNumber, experienceYears |
| **BailRequest** | bailId, caseId, personId, requestDate, status, amount, conditions |
| **Appeal** | appealId, originalCaseId, appealDate, reason, status, courtId |
| **Settlement** | settlementId, caseId, terms, date, agreementSigned |
| **CaseHistory** | historyId, caseId, eventDate, eventDescription, updatedBy |

---

## 9. Exception Handling

### 9.1 Custom Exceptions

**File Location:** `main/exception/`

| Exception | Purpose | Usage |
|-----------|---------|-------|
| **DatabaseException** | Wraps SQLException | All DAO operations |
| **CaseNotFoundException** | Case ID doesn't exist | Lookup operations |
| **UserNotFoundException** | User authentication failed | Login operations |
| **CaseAlreadyClosedException** | Attempting to modify closed case | Update operations |
| **DuplicateHearingException** | Hearing already scheduled | Scheduling conflicts |
| **ValidationException** | Invalid input data | Form validation |

### 9.2 Exception Hierarchy

```
Exception
├── DatabaseException
├── CaseNotFoundException
├── UserNotFoundException
├── CaseAlreadyClosedException
├── DuplicateHearingException
└── ValidationException
```

### 9.3 Error Handling Pattern

All GUI panels follow this pattern:
```java
try {
    // Database operation
} catch (DatabaseException ex) {
    JOptionPane.showMessageDialog(this, 
        "Error: " + ex.getMessage(), 
        "Error", 
        JOptionPane.ERROR_MESSAGE);
}
```

---

## 10. How to Run

### 10.1 Prerequisites

- **Java**: JDK 8 or higher
- **MySQL**: 5.7 or higher
- **IDE**: IntelliJ IDEA / Eclipse / VS Code (optional)

### 10.2 Database Setup

1. **Create Database:**
   ```sql
   mysql -u root -p < src/JudicialCaseManagerSystem.sql
   ```

2. **Verify Tables:**
   ```sql
   USE judicialcasedb;
   SHOW TABLES;
   ```

### 10.3 Configuration

1. **Create/Edit:** `src/main/resources/application.properties`
   ```properties
   db.url=jdbc:mysql://localhost:3306/judicialcasedb
   db.user=root
   db.password=your_password
   ```

### 10.4 Compile and Run

**Using Command Line:**
```bash
# Compile
javac -cp "lib/mysql-connector-java-8.0.x.jar" -d out src/main/**/*.java

# Run
java -cp "out:lib/mysql-connector-java-8.0.x.jar" main.App
```

**Using IDE:**
1. Import project into IntelliJ IDEA / Eclipse
2. Add MySQL Connector/J to classpath
3. Run `main.App`

### 10.5 Login Credentials

- **Username:** `admin`
- **Password:** `admin123`

---

## 11. Class Reference

### 11.1 GUI Components Summary

| Class | Purpose | Location |
|-------|---------|----------|
| App | Entry point | main/App.java |
| LoginFrame | Authentication | main/gui/LoginFrame.java |
| AdminDashboard | Main window | main/gui/AdminDashboard.java |
| ButtonFactory | UI component factory | main/gui/components/ButtonFactory.java |
| CaseManagementPanel | Case CRUD | main/gui/panels/CaseManagementPanel.java |
| PersonManagementPanel | Person CRUD | main/gui/panels/PersonManagementPanel.java |
| EvidencePanel | Evidence CRUD | main/gui/panels/EvidencePanel.java |
| HearingPanel | Hearing CRUD | main/gui/panels/HearingPanel.java |
| AppealPanel | Appeal CRUD | main/gui/panels/AppealPanel.java |
| BailPanel | Bail CRUD | main/gui/panels/BailPanel.java |
| CourtRoomPanel | Courtroom CRUD | main/gui/panels/CourtRoomPanel.java |
| CourtStaffPanel | Staff CRUD | main/gui/panels/CourtStaffPanel.java |
| CaseHistoryPanel | History tracking | main/gui/panels/CaseHistoryPanel.java |
| SettlementPanel | Settlement CRUD | main/gui/panels/SettlementPanel.java |
| PetitionerPanel | Petitioner CRUD | main/gui/panels/PetitionerPanel.java |
| PoliceOfficerPanel | Officer CRUD | main/gui/panels/PoliceOfficerPanel.java |
| RespondentPanel | Respondent CRUD | main/gui/panels/RespondentPanel.java |
| WitnessPanel | Witness CRUD | main/gui/panels/WitnessPanel.java |

### 11.2 DAO Components Summary

| Class | Entity | Location |
|-------|--------|----------|
| CaseDAO | CaseDetails | main/dao/CaseDAO.java |
| PersonDAO | Person | main/dao/PersonDAO.java |
| HearingDAO | Hearing | main/dao/HearingDAO.java |
| EvidenceDAO | Evidence | main/dao/EvidenceDAO.java |
| AppealDAO | Appeal | main/dao/AppealDAO.java |
| BailRequestDAO | BailRequest | main/dao/BailRequestDAO.java |
| CourtDAO | Court | main/dao/CourtDAO.java |
| CourtRoomDAO | CourtRoom | main/dao/CourtRoomDAO.java |
| CourtStaffDAO | CourtStaff | main/dao/CourtStaffDAO.java |
| SettlementDAO | Settlement | main/dao/SettlementDAO.java |
| CaseHistoryDAO | CaseHistory | main/dao/CaseHistoryDAO.java |
| PetitionerDAO | Petitioner | main/dao/PetitionerDAO.java |
| RespondentDAO | Respondent | main/dao/RespondentDAO.java |
| PoliceOfficerDAO | PoliceOfficer | main/dao/PoliceOfficerDAO.java |
| WitnessDAO | Witness | main/dao/WitnessDAO.java |

### 11.3 Model Components Summary

| Class | Type | Location |
|-------|------|----------|
| CaseDetails | Entity | main/model/CaseDetails.java |
| Person | Abstract | main/model/Person.java |
| Petitioner | Entity | main/model/person/Petitioner.java |
| Respondent | Entity | main/model/person/Respondent.java |
| Witness | Entity | main/model/person/Witness.java |
| PoliceOfficer | Entity | main/model/person/PoliceOfficer.java |
| Hearing | Entity | main/model/Hearing.java |
| Evidence | Entity | main/model/Evidence.java |
| Court | Entity | main/model/Court.java |
| CourtRoom | Entity | main/model/CourtRoom.java |
| CourtStaff | Entity | main/model/CourtStaff.java |
| Judge | Entity | main/model/Judge.java |
| Lawyer | Entity | main/model/Lawyer.java |
| BailRequest | Entity | main/model/BailRequest.java |
| Appeal | Entity | main/model/Appeal.java |
| Settlement | Entity | main/model/Settlement.java |
| CaseHistory | Entity | main/model/CaseHistory.java |

---

## 12. Design Patterns Used

| Pattern | Implementation | Purpose |
|---------|---------------|---------|
| **Singleton** | DatabaseConnection | Single DB connection instance |
| **Factory** | ButtonFactory | Consistent button creation |
| **DAO** | All DAO classes | Data access abstraction |
| **Template Method** | Person subclasses | Common person behavior |

---

## 13. Future Enhancements

Potential improvements for the system:

1. **Authentication**: Replace hardcoded credentials with database-backed authentication
2. **Role-Based Access**: Different dashboards for judges, lawyers, clerks
3. **Search/Filter**: Advanced search functionality in all panels
4. **Reporting**: Generate PDF reports for cases and hearings
5. **Notifications**: Alert system for upcoming hearings
6. **Audit Trail**: Log all data modifications
7. **Backup/Restore**: Database backup functionality
8. **Export**: Export data to Excel/CSV formats

---

**Documentation Version:** 1.0  
**Last Updated:** March 2026  
**Project:** Judicial Case Manager  
**Language:** Core Java (JDK 8+)  
**Database:** MySQL 5.7+  
**GUI Framework:** Swing
