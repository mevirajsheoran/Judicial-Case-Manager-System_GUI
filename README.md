# Judicial Case Manager System

A comprehensive desktop application for managing court cases, hearings, evidence, and judicial records built with Core Java and Swing GUI.

## Features

- **Case Management**: Create, update, delete, and track court cases
- **Person Management**: Manage petitioners, respondents, witnesses, and police officers
- **Hearing Scheduling**: Schedule and track court hearings with judges
- **Evidence Tracking**: Record and manage case evidence and documentation
- **Bail Management**: Process bail requests and track conditions
- **Court Administration**: Manage courts, courtrooms, and court staff
- **Appeals Handling**: Track case appeals to higher courts
- **Settlement Records**: Document out-of-court settlements
- **Case History**: Complete audit trail of case events

## Prerequisites

- **Java**: JDK 8 or higher
- **MySQL**: 5.7 or higher
- **MySQL Connector/J**: JDBC driver for MySQL

## Quick Start

### 1. Database Setup

```bash
# Import the database schema and seed data
mysql -u root -p < src/JudicialCaseManagerSystem.sql
```

### 2. Configure Database Connection

Create file: `src/main/resources/application.properties`

```properties
db.url=jdbc:mysql://localhost:3306/judicialcasedb
db.user=root
db.password=your_mysql_password
```

### 3. Build and Run

**Using IDE (IntelliJ IDEA / Eclipse):**
1. Import the project
2. Add MySQL Connector/J to the classpath
3. Run `main.App`

**Using Command Line:**
```bash
# Compile
javac -cp "lib/mysql-connector-java-8.0.x.jar" -d out src/main/**/*.java

# Run
java -cp "out:lib/mysql-connector-java-8.0.x.jar" main.App
```

## Login Credentials

- **Username:** `admin`
- **Password:** `admin123`

## Project Structure

```
src/
├── main/
│   ├── App.java                    # Application entry point
│   ├── dao/                        # Data Access Objects (15+ classes)
│   ├── db/                         # Database connection
│   ├── exception/                  # Custom exceptions
│   ├── gui/                        # Swing GUI components
│   │   ├── LoginFrame.java
│   │   ├── AdminDashboard.java
│   │   ├── components/
│   │   └── panels/                 # 15 management panels
│   ├── model/                      # Entity classes
│   │   └── person/                 # Person subclasses
│   └── resources/
│       └── application.properties  # DB config
└── JudicialCaseManagerSystem.sql   # Database schema
```

## Technologies Used

- **Core Java** (JDK 8+)
- **Swing** - GUI framework
- **MySQL** - Database
- **JDBC** - Database connectivity
- **Maven/Gradle** - Compatible (no build tool required)

## Database Schema

The system uses **22 tables** including:
- **Core**: Court, Case_Category, Case_details, Verdict
- **Persons**: Person, Petitioner, Respondent, Witness, Police_Officer, Lawyer, Judge
- **Process**: Hearing, Evidence, FIR, Charges, Bail, Settlement, Appeal, Case_History
- **Support**: Police_Station, Court_Staff, Court_Room, Legal_Act

## Design Patterns

- **Singleton**: DatabaseConnection
- **Factory**: ButtonFactory
- **DAO**: Data Access Object pattern for all entities
- **Template Method**: Person inheritance hierarchy

## Documentation

For detailed documentation, see [PROJECT_DOCUMENTATION.md](PROJECT_DOCUMENTATION.md) which includes:
- Complete system architecture
- Full database schema documentation
- Class reference for all 50+ components
- GUI layer documentation
- DAO layer documentation
- Exception handling details

## Troubleshooting

**Database connection failed:**
- Verify MySQL is running
- Check credentials in `application.properties`
- Ensure MySQL Connector/J is in classpath

**Table not found errors:**
- Run the SQL script to create tables
- Verify database name is `judicialcasedb`

## Future Enhancements

- Role-based access control (Judge, Lawyer, Clerk)
- Advanced search and filtering
- PDF report generation
- Email notifications for hearings
- Data export to Excel/CSV
- Database backup/restore functionality

## License

This project is for educational purposes.

---

**Version:** 1.0  
**Last Updated:** March 2026
