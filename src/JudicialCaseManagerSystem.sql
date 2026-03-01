create database judicialcasedb;

 use judicialcasedb;
 
-- 1
CREATE TABLE Court (
    Court_ID INT PRIMARY KEY,
    Court_Name VARCHAR(100),
    Location VARCHAR(255),
    Jurisdiction_Level VARCHAR(50)
);


INSERT INTO Court (Court_ID, Court_Name, Location, Jurisdiction_Level) VALUES
(1, 'Supreme Court of India', 'New Delhi', 'National'),
(2, 'Delhi High Court', 'Delhi', 'State'),
(3, 'Bombay High Court', 'Mumbai', 'State'),
(4, 'Madras High Court', 'Chennai', 'State'),
(5, 'Calcutta High Court', 'Kolkata', 'State'),
(6, 'Allahabad High Court', 'Prayagraj', 'State'),
(7, 'Karnataka High Court', 'Bangalore', 'State'),
(8, 'Punjab & Haryana High Court', 'Chandigarh', 'State'),
(9, 'Kerala High Court', 'Kochi', 'State'),
(10, 'Gujarat High Court', 'Ahmedabad', 'State'),
(11, 'Rajasthan High Court', 'Jodhpur', 'State'),
(12, 'Patna High Court', 'Patna', 'State'),
(13, 'Orissa High Court', 'Cuttack', 'State'),
(14, 'Jharkhand High Court', 'Ranchi', 'State'),
(15, 'Sikkim High Court', 'Gangtok', 'State'),
(16, 'Meghalaya High Court', 'Shillong', 'State'),
(17, 'Manipur High Court', 'Imphal', 'State'),
(18, 'Tripura High Court', 'Agartala', 'State'),
(19, 'Jammu & Kashmir High Court', 'Srinagar', 'State'),
(20, 'Andhra Pradesh High Court', 'Amaravati', 'State');

-- 2
CREATE TABLE Case_Category (
    Category_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Description TEXT
);
INSERT INTO Case_Category (Category_ID, Name, Description) VALUES
(1, 'Criminal', 'Offenses against the law including theft, assault, and murder'),
(2, 'Civil', 'Disputes between individuals or organizations over rights and duties'),
(3, 'Family', 'Cases related to divorce, custody, and inheritance'),
(4, 'Corporate', 'Legal issues involving businesses or corporations'),
(5, 'Labor', 'Disputes involving workers and employers'),
(6, 'Environmental', 'Cases related to environmental protection and regulation'),
(7, 'Tax', 'Cases concerning tax evasion or disputes'),
(8, 'Property', 'Disputes over property ownership and transactions'),
(9, 'Intellectual Property', 'Rights over creations of the mind like patents and trademarks'),
(10, 'Cybercrime', 'Crimes committed using computers or networks'),
(11, 'Bankruptcy', 'Cases related to insolvency and financial restructuring'),
(12, 'Consumer Protection', 'Disputes between consumers and sellers/service providers'),
(13, 'Contract', 'Disputes arising from contract violations'),
(14, 'Administrative', 'Disputes over decisions by government agencies'),
(15, 'Human Rights', 'Cases involving violations of fundamental rights'),
(16, 'Education', 'Legal matters related to educational institutions'),
(17, 'Election', 'Disputes related to electoral processes'),
(18, 'Insurance', 'Disputes over claims and policy enforcement'),
(19, 'Immigration', 'Cases involving citizenship, visas, and deportation'),
(20, 'Health', 'Legal issues related to medical services and malpractice');


-- 3. Person Table (Independent)
CREATE TABLE Person (
    Person_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Date_of_Birth DATE,
    Contact VARCHAR(20),
    Address VARCHAR(255),
    National_ID VARCHAR(50) UNIQUE
);
INSERT INTO Person (Person_ID, Name, Date_of_Birth, Contact, Address, National_ID) VALUES
(1, 'Aarav Mehta', '1985-03-15', '9876543210', '12 MG Road, Delhi', 'IND0012345'),
(2, 'Priya Sharma', '1990-07-22', '9811122233', 'Flat 204, Green Apartments, Mumbai', 'IND0012346'),
(3, 'Rahul Khanna', '1978-11-03', '9898989898', '56 Park Street, Kolkata', 'IND0012347'),
(4, 'Sneha Kapoor', '1995-01-10', '9845612300', '22 Lake View, Bengaluru', 'IND0012348'),
(5, 'Vikram Batra', '1980-06-27', '9823456789', '88 Civil Lines, Lucknow', 'IND0012349'),
(6, 'Anjali Desai', '1989-09-17', '9833011223', '9 Riverbank, Ahmedabad', 'IND0012350'),
(7, 'Rohan Verma', '1992-04-25', '9800223344', 'H No. 5, Sector 15, Chandigarh', 'IND0012351'),
(8, 'Meena Iyer', '1975-12-12', '9870034567', 'B-77, Besant Nagar, Chennai', 'IND0012352'),
(9, 'Arjun Sinha', '1983-05-30', '9867554433', '4 Gariahat Road, Kolkata', 'IND0012353'),
(10, 'Neha Joshi', '1996-02-28', '9810111122', '3 Patel Nagar, Delhi', 'IND0012354'),
(11, 'Suresh Raina', '1981-08-05', '9888001122', '20 Old City, Jaipur', 'IND0012355'),
(12, 'Ritika Chauhan', '1993-03-14', '9822334455', 'Hilltop Lane, Shimla', 'IND0012356'),
(13, 'Kabir Singh', '1987-06-18', '9899900011', '12 Sector A, Bhopal', 'IND0012357'),
(14, 'Tanya Mathur', '1994-10-09', '9801234567', 'Shastri Road, Pune', 'IND0012358'),
(15, 'Amit Patel', '1979-01-01', '9856223344', 'Main Street, Surat', 'IND0012359'),
(16, 'Pooja Nair', '1991-11-29', '9812345678', 'Coastal Road, Kochi', 'IND0012360'),
(17, 'Deepak Rao', '1984-07-19', '9821000001', 'Sector 4, Bhubaneswar', 'IND0012361'),
(18, 'Swati Deshmukh', '1997-12-03', '9876000123', 'Panchwati, Nashik', 'IND0012362'),
(19, 'Karan Malhotra', '1982-02-22', '9845678901', 'Central Avenue, Hyderabad', 'IND0012363'),
(20, 'Lavanya Shetty', '1990-09-11', '9833445566', 'MG Layout, Mangalore', 'IND0012364');


-- 4. Lawyer Table (Independent)
CREATE TABLE Lawyer (
    Lawyer_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Contact VARCHAR(20),
    Specialization VARCHAR(100),
    Bar_Registration_Number VARCHAR(50) UNIQUE,
    Experience_Years INT
);

INSERT INTO Lawyer (Lawyer_ID, Name, Contact, Specialization, Bar_Registration_Number, Experience_Years) VALUES
(1, 'Nikhil Arora', '9871100011', 'Criminal', 'BRN001', 12),
(2, 'Sakshi Menon', '9822200022', 'Civil', 'BRN002', 8),
(3, 'Manav Singh', '9813300033', 'Corporate', 'BRN003', 15),
(4, 'Aisha Qureshi', '9884400044', 'Family', 'BRN004', 10),
(5, 'Rajat Mehra', '9835500055', 'Labor', 'BRN005', 6),
(6, 'Divya Pillai', '9876600066', 'Environmental', 'BRN006', 9),
(7, 'Kunal Kapoor', '9827700077', 'Tax', 'BRN007', 14),
(8, 'Anita Dube', '9818800088', 'Property', 'BRN008', 7),
(9, 'Rishi Deshpande', '9899900099', 'Intellectual Property', 'BRN009', 13),
(10, 'Neelam Chauhan', '9801000100', 'Cybercrime', 'BRN010', 11),
(11, 'Varun Tiwari', '9842110011', 'Bankruptcy', 'BRN011', 10),
(12, 'Jaya Iyer', '9833220022', 'Consumer Protection', 'BRN012', 5),
(13, 'Harsh Verma', '9824330033', 'Contract', 'BRN013', 9),
(14, 'Simran Kaur', '9815440044', 'Administrative', 'BRN014', 6),
(15, 'Aman Bhatt', '9806550055', 'Human Rights', 'BRN015', 12),
(16, 'Megha Sood', '9877660066', 'Education', 'BRN016', 7),
(17, 'Pratik Jain', '9868770077', 'Election', 'BRN017', 8),
(18, 'Kritika Basu', '9859880088', 'Insurance', 'BRN018', 4),
(19, 'Arvind Nair', '9840990099', 'Immigration', 'BRN019', 11),
(20, 'Snehal Ghosh', '9831000100', 'Health', 'BRN020', 6);


-- 5. Police Station Table (Independent)
CREATE TABLE Police_Station (
    Station_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Location VARCHAR(255),
    Station_Code VARCHAR(20) UNIQUE,
    Contact_Number VARCHAR(20)
);
INSERT INTO Police_Station (Station_ID, Name, Location, Station_Code, Contact_Number) VALUES
(1, 'Connaught Place Station', 'Delhi', 'PSDL001', '01123450001'),
(2, 'Bandra Station', 'Mumbai', 'PSMH002', '02233440002'),
(3, 'Salt Lake Station', 'Kolkata', 'PSWB003', '03344550003'),
(4, 'Indiranagar Station', 'Bengaluru', 'PSKA004', '08055660004'),
(5, 'Hazratganj Station', 'Lucknow', 'PSUP005', '05226670005'),
(6, 'Navrangpura Station', 'Ahmedabad', 'PSGJ006', '07967780006'),
(7, 'Sector 17 Station', 'Chandigarh', 'PSCH007', '01726780007'),
(8, 'Adyar Station', 'Chennai', 'PSTN008', '04478890008'),
(9, 'Gariahat Station', 'Kolkata', 'PSWB009', '03388990009'),
(10, 'Karol Bagh Station', 'Delhi', 'PSDL010', '01199000010'),
(11, 'Vaishali Nagar Station', 'Jaipur', 'PSRJ011', '01411110011'),
(12, 'Mall Road Station', 'Shimla', 'PSHP012', '01772220012'),
(13, 'TT Nagar Station', 'Bhopal', 'PSMP013', '07553330013'),
(14, 'Shivaji Nagar Station', 'Pune', 'PSMH014', '02044440014'),
(15, 'Athwa Lines Station', 'Surat', 'PSGJ015', '02614550015'),
(16, 'Marine Drive Station', 'Kochi', 'PSKL016', '04846660016'),
(17, 'Kharavel Nagar Station', 'Bhubaneswar', 'PSOD017', '06747770017'),
(18, 'Dwarka Station', 'Delhi', 'PSDL018', '01188880018'),
(19, 'Charminar Station', 'Hyderabad', 'PSTG019', '04099990019'),
(20, 'Hampankatta Station', 'Mangalore', 'PSKA020', '08242220020');


-- 6
CREATE TABLE Case_details (
    Case_ID INT PRIMARY KEY,
    Case_Number VARCHAR(50) UNIQUE,
    Filing_Date DATE,
    Case_Type VARCHAR(100),
    Status VARCHAR(50),
    Category_ID INT,
    Court_ID INT,
    FOREIGN KEY (Category_ID) REFERENCES Case_Category(Category_ID),
    FOREIGN KEY (Court_ID) REFERENCES Court(Court_ID)
);

INSERT INTO Case_details (Case_ID, Case_Number, Filing_Date, Case_Type, Status, Category_ID, Court_ID, Verdict_ID) VALUES
(1, 'C-2024-001', '2024-01-10', 'Criminal', 'Pending', 1, 1, NULL),
(2, 'C-2024-002', '2024-01-15', 'Civil', 'Ongoing', 2, 2, NULL),
(3, 'C-2024-003', '2024-01-20', 'Family', 'Closed', 3, 3, NULL),
(4, 'C-2024-004', '2024-02-01', 'Criminal', 'Pending', 4, 4, NULL),
(5, 'C-2024-005', '2024-02-10', 'Civil', 'Appealed', 5, 5, NULL),
(6, 'C-2024-006', '2024-02-15', 'Property', 'Ongoing', 6, 6, NULL),
(7, 'C-2024-007', '2024-02-20', 'Labor', 'Closed', 7, 7, NULL),
(8, 'C-2024-008', '2024-03-01', 'Criminal', 'Pending', 8, 8, NULL),
(9, 'C-2024-009', '2024-03-05', 'Family', 'Ongoing', 9, 9, NULL),
(10, 'C-2024-010', '2024-03-10', 'Civil', 'Closed', 10, 10, NULL),
(11, 'C-2024-011', '2024-03-15', 'Criminal', 'Pending', 11, 11, NULL),
(12, 'C-2024-012', '2024-03-20', 'Corporate', 'Ongoing', 12, 12, NULL),
(13, 'C-2024-013', '2024-03-25', 'Civil', 'Appealed', 13, 13, NULL),
(14, 'C-2024-014', '2024-04-01', 'Criminal', 'Closed', 14, 14, NULL),
(15, 'C-2024-015', '2024-04-05', 'Family', 'Pending', 15, 15, NULL),
(16, 'C-2024-016', '2024-04-10', 'Labor', 'Ongoing', 16, 16, NULL),
(17, 'C-2024-017', '2024-04-15', 'Civil', 'Closed', 17, 17, NULL),
(18, 'C-2024-018', '2024-04-20', 'Criminal', 'Appealed', 18, 18, NULL),
(19, 'C-2024-019', '2024-04-25', 'Property', 'Pending', 19, 19, NULL),
(20, 'C-2024-020', '2024-04-30', 'Family', 'Ongoing', 20, 20, NULL);


-- 7
CREATE TABLE Verdict (
    Verdict_ID INT PRIMARY KEY,
    Case_ID INT UNIQUE,
    Date DATE,
    Decision TEXT,
    Remarks TEXT,
    Penalty DECIMAL(10,2),
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID)
);
ALTER TABLE Case_details ADD COLUMN Verdict_ID INT UNIQUE;
ALTER TABLE Case_details ADD FOREIGN KEY (Verdict_ID) REFERENCES Verdict(Verdict_ID);

INSERT INTO Verdict (Verdict_ID, Case_ID, Date, Decision, Remarks, Penalty) VALUES
(1, 1, '2024-03-20', 'Guilty', 'Strong evidence presented.', 5000.00),
(2, 2, '2024-03-25', 'Not Guilty', 'Lack of evidence.', 0.00),
(3, 3, '2024-04-01', 'Dismissed', 'Jurisdiction issue.', 0.00),
(4, 4, '2024-04-05', 'Guilty', 'Confession recorded.', 7000.00),
(5, 5, '2024-04-10', 'Not Guilty', 'Defendant cleared.', 0.00),
(6, 6, '2024-04-15', 'Guilty', 'Witness testimony strong.', 10000.00),
(7, 7, '2024-04-20', 'Dismissed', 'Technical error in filing.', 0.00),
(8, 8, '2024-04-25', 'Guilty', 'Video evidence confirmed.', 8000.00),
(9, 9, '2024-04-30', 'Not Guilty', 'Alibi verified.', 0.00),
(10, 10, '2024-05-01', 'Dismissed', 'Complaint withdrawn.', 0.00),
(11, 11, '2024-05-05', 'Guilty', 'Multiple charges proven.', 12000.00),
(12, 12, '2024-05-10', 'Not Guilty', 'Corporate settlement reached.', 0.00),
(13, 13, '2024-05-15', 'Guilty', 'Financial fraud confirmed.', 15000.00),
(14, 14, '2024-05-20', 'Dismissed', 'Incorrect filing procedure.', 0.00),
(15, 15, '2024-05-25', 'Guilty', 'Physical evidence presented.', 6000.00),
(16, 16, '2024-05-30', 'Not Guilty', 'Lack of witness cooperation.', 0.00),
(17, 17, '2024-06-01', 'Guilty', 'Employee confession.', 4000.00),
(18, 18, '2024-06-05', 'Dismissed', 'Appeal accepted.', 0.00),
(19, 19, '2024-06-10', 'Guilty', 'Real estate fraud.', 9000.00),
(20, 20, '2024-06-15', 'Not Guilty', 'Family court settlement.', 0.00);







UPDATE Case_details SET Verdict_ID = 1 WHERE Case_ID = 1;
UPDATE Case_details SET Verdict_ID = 2 WHERE Case_ID = 2;
UPDATE Case_details SET Verdict_ID = 3 WHERE Case_ID = 3;
UPDATE Case_details SET Verdict_ID = 4 WHERE Case_ID = 4;
UPDATE Case_details SET Verdict_ID = 5 WHERE Case_ID = 5;
UPDATE Case_details SET Verdict_ID = 6 WHERE Case_ID = 6;
UPDATE Case_details SET Verdict_ID = 7 WHERE Case_ID = 7;
UPDATE Case_details SET Verdict_ID = 8 WHERE Case_ID = 8;
UPDATE Case_details SET Verdict_ID = 9 WHERE Case_ID = 9;
UPDATE Case_details SET Verdict_ID = 10 WHERE Case_ID = 10;
UPDATE Case_details SET Verdict_ID = 11 WHERE Case_ID = 11;
UPDATE Case_details SET Verdict_ID = 12 WHERE Case_ID = 12;
UPDATE Case_details SET Verdict_ID = 13 WHERE Case_ID = 13;
UPDATE Case_details SET Verdict_ID = 14 WHERE Case_ID = 14;
UPDATE Case_details SET Verdict_ID = 15 WHERE Case_ID = 15;
UPDATE Case_details SET Verdict_ID = 16 WHERE Case_ID = 16;
UPDATE Case_details SET Verdict_ID = 17 WHERE Case_ID = 17;
UPDATE Case_details SET Verdict_ID = 18 WHERE Case_ID = 18;
UPDATE Case_details SET Verdict_ID = 19 WHERE Case_ID = 19;
UPDATE Case_details SET Verdict_ID = 20 WHERE Case_ID = 20;

-- 8
CREATE TABLE Judge (
    Judge_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Designation VARCHAR(100),
    Experience_Years INT,
    Court_ID INT,
    FOREIGN KEY (Court_ID) REFERENCES Court(Court_ID)
);

INSERT INTO Judge (Judge_ID, Name, Designation, Experience_Years, Court_ID) VALUES
(1, 'Justice Arvind Rao', 'Chief Justice', 25, 1),
(2, 'Justice Meera Das', 'Associate Judge', 18, 2),
(3, 'Justice Vikram Sinha', 'Senior Judge', 20, 3),
(4, 'Justice Neelam Gupta', 'Judge', 12, 4),
(5, 'Justice Ramesh Iyer', 'Associate Judge', 16, 5),
(6, 'Justice Fatima Rizvi', 'Judge', 10, 1),
(7, 'Justice Kabir Khan', 'Judge', 8, 2),
(8, 'Justice Ananya Nair', 'Judge', 9, 3),
(9, 'Justice Mohan Lal', 'Senior Judge', 22, 4),
(10, 'Justice Rekha Rathi', 'Judge', 14, 5),
(11, 'Justice Tarun Bhasin', 'Chief Justice', 26, 1),
(12, 'Justice Priya Shetty', 'Judge', 11, 2),
(13, 'Justice Varun Paul', 'Associate Judge', 13, 3),
(14, 'Justice Sneha Kapoor', 'Judge', 7, 4),
(15, 'Justice Aditya Menon', 'Senior Judge', 19, 5),
(16, 'Justice Divya Arora', 'Judge', 6, 1),
(17, 'Justice Sohail Ahmed', 'Judge', 10, 2),
(18, 'Justice Kriti Joshi', 'Judge', 9, 3),
(19, 'Justice Devansh Verma', 'Associate Judge', 12, 4),
(20, 'Justice Ishita Sen', 'Judge', 8, 5);




-- 9. Petitioner Table (Depends on Person, Lawyer)
CREATE TABLE Petitioner (
    Person_ID INT PRIMARY KEY,
    Petition_Filed_Date DATE,
    Legal_Representative INT,
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Legal_Representative) REFERENCES Lawyer(Lawyer_ID)
);
INSERT INTO Petitioner (Person_ID, Petition_Filed_Date, Legal_Representative) VALUES
(1, '2023-01-15', 3),
(2, '2023-02-20', 7),
(3, '2023-03-05', 1),
(4, '2023-04-18', 6),
(5, '2023-05-25', 4),
(6, '2023-06-10', 2),
(7, '2023-07-12', 8),
(8, '2023-08-30', 5),
(9, '2023-09-14', 9),
(10, '2023-10-22', 10),
(11, '2023-11-05', 12),
(12, '2023-11-19', 11),
(13, '2023-12-01', 13),
(14, '2023-12-15', 14),
(15, '2024-01-03', 15),
(16, '2024-01-20', 16),
(17, '2024-02-05', 17),
(18, '2024-02-18', 18),
(19, '2024-03-10', 19),
(20, '2024-03-25', 20);


-- 10. Respondent Table (Depends on Person, Lawyer)
CREATE TABLE Respondent (
    Person_ID INT PRIMARY KEY,
    Response_Submitted_Date DATE,
    Legal_Representative INT,
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Legal_Representative) REFERENCES Lawyer(Lawyer_ID)
);

INSERT INTO Respondent (Person_ID, Response_Submitted_Date, Legal_Representative) VALUES
(1, '2023-01-20', 5),
(2, '2023-02-25', 2),
(3, '2023-03-10', 6),
(4, '2023-04-22', 7),
(5, '2023-05-30', 1),
(6, '2023-06-15', 4),
(7, '2023-07-18', 3),
(8, '2023-08-31', 8),
(9, '2023-09-20', 10),
(10, '2023-10-25', 9),
(11, '2023-11-10', 11),
(12, '2023-11-22', 13),
(13, '2023-12-05', 12),
(14, '2023-12-18', 14),
(15, '2024-01-07', 15),
(16, '2024-01-25', 16),
(17, '2024-02-07', 17),
(18, '2024-02-20', 18),
(19, '2024-03-12', 19),
(20, '2024-03-28', 20);




-- 11 Police_Officer
CREATE TABLE Police_Officer (
    Person_ID INT PRIMARY KEY,
    Police_Station_ID INT,
    Badge_Number VARCHAR(50),
    Officer_Rank VARCHAR(50),  -- renamed column
    Department VARCHAR(100),
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Police_Station_ID) REFERENCES Police_Station(Station_ID)
);




INSERT INTO Police_Officer (Person_ID, Police_Station_ID, Badge_Number, Officer_Rank, Department) VALUES
(1, 1, 'B1001', 'Sub Inspector', 'Criminal Investigation'),
(2, 2, 'B1002', 'Inspector', 'Homicide'),
(3, 3, 'B1003', 'Head Constable', 'Traffic'),
(4, 4, 'B1004', 'Constable', 'Cyber Crime'),
(5, 5, 'B1005', 'Inspector', 'Anti-Narcotics'),
(6, 1, 'B1006', 'Sub Inspector', 'General Duty'),
(7, 2, 'B1007', 'Inspector', 'White Collar Crime'),
(8, 3, 'B1008', 'Constable', 'Criminal Investigation'),
(9, 4, 'B1009', 'Sub Inspector', 'Cyber Crime'),
(10, 5, 'B1010', 'Head Constable', 'Traffic'),
(11, 1, 'B1011', 'Inspector', 'General Duty'),
(12, 2, 'B1012', 'Constable', 'Anti-Narcotics'),
(13, 3, 'B1013', 'Sub Inspector', 'White Collar Crime'),
(14, 4, 'B1014', 'Inspector', 'Homicide'),
(15, 5, 'B1015', 'Constable', 'Traffic'),
(16, 1, 'B1016', 'Sub Inspector', 'Criminal Investigation'),
(17, 2, 'B1017', 'Head Constable', 'Cyber Crime'),
(18, 3, 'B1018', 'Inspector', 'General Duty'),
(19, 4, 'B1019', 'Constable', 'White Collar Crime'),
(20, 5, 'B1020', 'Sub Inspector', 'Anti-Narcotics');



-- 12 Witness Table (Depends on Person)
CREATE TABLE Witness (
    Person_ID INT PRIMARY KEY,
    Testimony_Date DATE,
    Type_of_Witness VARCHAR(100),
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID)
);

INSERT INTO Witness (Person_ID, Testimony_Date, Type_of_Witness) VALUES
(1, '2023-01-22', 'Eyewitness'),
(2, '2023-02-14', 'Expert'),
(3, '2023-03-01', 'Medical'),
(4, '2023-03-15', 'Character'),
(5, '2023-04-10', 'Police'),
(6, '2023-05-02', 'Eyewitness'),
(7, '2023-06-18', 'Expert'),
(8, '2023-07-05', 'Forensic'),
(9, '2023-07-25', 'Character'),
(10, '2023-08-12', 'Eyewitness'),
(11, '2023-09-03', 'Police'),
(12, '2023-09-28', 'Medical'),
(13, '2023-10-15', 'Eyewitness'),
(14, '2023-11-01', 'Expert'),
(15, '2023-11-20', 'Forensic'),
(16, '2023-12-05', 'Police'),
(17, '2023-12-22', 'Medical'),
(18, '2024-01-08', 'Eyewitness'),
(19, '2024-02-14', 'Character'),
(20, '2024-03-01', 'Expert');




-- 13. Court Staff Table (Depends on Court)
CREATE TABLE Court_Staff (
    Staff_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Role VARCHAR(100),
    Contact VARCHAR(20),
    Court_ID INT,
    FOREIGN KEY (Court_ID) REFERENCES Court(Court_ID)
);
INSERT INTO Court_Staff (Staff_ID, Name, Role, Contact, Court_ID) VALUES
(1, 'Amit Desai', 'Clerk', '9001010001', 1),
(2, 'Nisha Sharma', 'Typist', '9001010002', 2),
(3, 'Ravi Mehta', 'Bailiff', '9001010003', 3),
(4, 'Sunita Rao', 'Stenographer', '9001010004', 4),
(5, 'Manoj Yadav', 'Court Manager', '9001010005', 5),
(6, 'Kiran Bedi', 'Clerk', '9001010006', 1),
(7, 'Ajay Singh', 'Typist', '9001010007', 2),
(8, 'Pooja Patel', 'Bailiff', '9001010008', 3),
(9, 'Rahul Nair', 'Stenographer', '9001010009', 4),
(10, 'Priya Rathi', 'Court Manager', '9001010010', 5),
(11, 'Deepak Sinha', 'Clerk', '9001010011', 1),
(12, 'Komal Jain', 'Typist', '9001010012', 2),
(13, 'Vikas Rana', 'Bailiff', '9001010013', 3),
(14, 'Meena Joseph', 'Stenographer', '9001010014', 4),
(15, 'Arun Mishra', 'Court Manager', '9001010015', 5),
(16, 'Swati Thakur', 'Clerk', '9001010016', 1),
(17, 'Rohan Kapoor', 'Typist', '9001010017', 2),
(18, 'Anjali Saxena', 'Bailiff', '9001010018', 3),
(19, 'Naveen Das', 'Stenographer', '9001010019', 4),
(20, 'Neha Aggarwal', 'Court Manager', '9001010020', 5);


-- 14. Hearing Table (Depends on Case_details, Judge)
CREATE TABLE Hearing (
    Hearing_ID INT PRIMARY KEY,
    Case_ID INT,
    Judge_ID INT,
    Date DATE,
    Time TIME,
    Description TEXT,
    Outcome VARCHAR(255),
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Judge_ID) REFERENCES Judge(Judge_ID)
);
INSERT INTO Hearing (Hearing_ID, Case_ID, Judge_ID, Date, Time, Description, Outcome) VALUES
(1, 1, 1, '2023-01-10', '10:00:00', 'Initial hearing for case 1', 'Adjourned'),
(2, 2, 2, '2023-01-15', '11:30:00', 'Preliminary statements', 'In Progress'),
(3, 3, 3, '2023-01-20', '09:45:00', 'Witness statements recorded', 'Completed'),
(4, 4, 4, '2023-01-25', '12:00:00', 'Cross examination begins', 'Adjourned'),
(5, 5, 5, '2023-01-30', '10:15:00', 'Evidence submitted', 'Completed'),
(6, 6, 6, '2023-02-05', '11:00:00', 'Medical testimony', 'In Progress'),
(7, 7, 7, '2023-02-10', '10:45:00', 'Final hearing', 'Judgement Reserved'),
(8, 8, 8, '2023-02-15', '09:30:00', 'Verdict announcement', 'Closed'),
(9, 9, 9, '2023-02-20', '10:00:00', 'Settlement discussion', 'In Progress'),
(10, 10, 10, '2023-02-25', '11:15:00', 'Court ruling on motion', 'Adjourned'),
(11, 11, 11, '2023-03-01', '12:30:00', 'Initial hearing', 'Completed'),
(12, 12, 12, '2023-03-05', '10:00:00', 'Evidence analysis', 'In Progress'),
(13, 13, 13, '2023-03-10', '11:45:00', 'Cross examination', 'Adjourned'),
(14, 14, 14, '2023-03-15', '09:00:00', 'Final arguments', 'Judgement Reserved'),
(15, 15, 15, '2023-03-20', '10:30:00', 'Verdict hearing', 'Closed'),
(16, 16, 16, '2023-03-25', '11:00:00', 'Bail plea hearing', 'Rejected'),
(17, 17, 17, '2023-03-30', '09:45:00', 'Evidence challenge', 'Completed'),
(18, 18, 18, '2023-04-04', '10:15:00', 'Summons response', 'In Progress'),
(19, 19, 19, '2023-04-08', '12:00:00', 'Witness examination', 'Adjourned'),
(20, 20, 20, '2023-04-12', '10:45:00', 'Final judgement hearing', 'Closed');




-- 15. FIR Table (Depends on Case_details, Police Station, Police Officer)
CREATE TABLE FIR (
    FIR_ID INT PRIMARY KEY,
    Case_ID INT,
    Police_Station_ID INT,
    Filed_By INT,
    Date DATE,
    Details TEXT,
    Crime_Location VARCHAR(255),
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Police_Station_ID) REFERENCES Police_Station(Station_ID),
    FOREIGN KEY (Filed_By) REFERENCES Police_Officer(Person_ID)
);
INSERT INTO FIR (FIR_ID, Case_ID, Police_Station_ID, Filed_By, Date, Details, Crime_Location) VALUES
(1, 1, 1, 1, '2023-01-01', 'Robbery reported by local shop owner.', 'Market Street, Delhi'),
(2, 2, 2, 2, '2023-01-03', 'Complaint filed for property damage.', 'Sector 10, Mumbai'),
(3, 3, 3, 3, '2023-01-05', 'Domestic violence case registered.', 'MG Road, Bengaluru'),
(4, 4, 4, 4, '2023-01-07', 'Cyber fraud complaint lodged.', 'Salt Lake, Kolkata'),
(5, 5, 5, 5, '2023-01-09', 'Car theft case initiated.', 'Banjara Hills, Hyderabad'),
(6, 6, 1, 6, '2023-01-11', 'Illegal possession of arms reported.', 'Anna Nagar, Chennai'),
(7, 7, 2, 7, '2023-01-13', 'Minor assault complaint filed.', 'Sector 21, Chandigarh'),
(8, 8, 3, 8, '2023-01-15', 'Drug possession case.', 'Mansarovar, Jaipur'),
(9, 9, 4, 9, '2023-01-17', 'Online scam reported.', 'Hazratganj, Lucknow'),
(10, 10, 5, 10, '2023-01-19', 'Missing person report.', 'Thiruvanmiyur, Chennai'),
(11, 11, 1, 11, '2023-01-21', 'Cheque bounce complaint.', 'Andheri East, Mumbai'),
(12, 12, 2, 12, '2023-01-23', 'Assault near public park.', 'Saket, Delhi'),
(13, 13, 3, 13, '2023-01-25', 'Illegal construction complaint.', 'Koramangala, Bengaluru'),
(14, 14, 4, 14, '2023-01-27', 'Stolen vehicle report.', 'Alipore, Kolkata'),
(15, 15, 5, 15, '2023-01-29', 'Hacking of social media account.', 'Gachibowli, Hyderabad'),
(16, 16, 1, 16, '2023-01-31', 'Attempted burglary.', 'T Nagar, Chennai'),
(17, 17, 2, 17, '2023-02-02', 'Obscene calls complaint.', 'Sector 18, Noida'),
(18, 18, 3, 18, '2023-02-04', 'Vandalism in school premises.', 'Vaishali Nagar, Jaipur'),
(19, 19, 4, 19, '2023-02-06', 'Shoplifting complaint.', 'Hazratganj, Lucknow'),
(20, 20, 5, 20, '2023-02-08', 'Cybercrime via phishing email.', 'Thiruvanmiyur, Chennai');



-- 16. Evidence Table (Depends on Case_details, Police Officer)
CREATE TABLE Evidence (
    Evidence_ID INT PRIMARY KEY,
    Case_ID INT,
    Type VARCHAR(100),
    Description TEXT,
    Collected_By INT,
    Submission_Date DATE,
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Collected_By) REFERENCES Police_Officer(Person_ID)
);
INSERT INTO Evidence (Evidence_ID, Case_ID, Type, Description, Collected_By, Submission_Date) VALUES
(1, 1, 'Document', 'Bank transaction records of the suspect.', 1, '2023-01-02'),
(2, 2, 'Digital', 'Security camera footage from store.', 2, '2023-01-04'),
(3, 3, 'Physical', 'Broken glass with blood stains.', 3, '2023-01-06'),
(4, 4, 'Digital', 'Chat logs indicating fraud.', 4, '2023-01-08'),
(5, 5, 'Physical', 'Knife recovered from crime scene.', 5, '2023-01-10'),
(6, 6, 'Document', 'Affidavit of witness.', 6, '2023-01-12'),
(7, 7, 'Digital', 'Email threats from accused.', 7, '2023-01-14'),
(8, 8, 'Physical', 'Bag containing illegal substances.', 8, '2023-01-16'),
(9, 9, 'Document', 'Photocopy of disputed property papers.', 9, '2023-01-18'),
(10, 10, 'Digital', 'IP logs and trace from ISP.', 10, '2023-01-20'),
(11, 11, 'Physical', 'Bullet casing found near the scene.', 11, '2023-01-22'),
(12, 12, 'Document', 'Medical report of the victim.', 12, '2023-01-24'),
(13, 13, 'Digital', 'Video testimony of eyewitness.', 13, '2023-01-26'),
(14, 14, 'Physical', 'Broken lock and crowbar.', 14, '2023-01-28'),
(15, 15, 'Document', 'Signed confession of accomplice.', 15, '2023-01-30'),
(16, 16, 'Digital', 'Text message backups.', 16, '2023-02-01'),
(17, 17, 'Physical', 'Shirt with blood evidence.', 17, '2023-02-03'),
(18, 18, 'Document', 'Bank loan documents.', 18, '2023-02-05'),
(19, 19, 'Digital', 'Social media screenshots.', 19, '2023-02-07'),
(20, 20, 'Physical', 'Anonymous threatening letter.', 20, '2023-02-09');


-- 17. Legal Act Table (Independent)
CREATE TABLE Legal_Act (
    Act_ID INT PRIMARY KEY,
    Act_Name VARCHAR(255),
    Section VARCHAR(100),
    Description TEXT,
    Enactment_Year INT
);
INSERT INTO Legal_Act (Act_ID, Act_Name, Section, Description, Enactment_Year) VALUES
(1, 'Indian Penal Code', '302', 'Punishment for murder', 1860),
(2, 'Indian Penal Code', '420', 'Cheating and dishonestly inducing delivery of property', 1860),
(3, 'Indian Penal Code', '376', 'Punishment for rape', 1860),
(4, 'Indian Penal Code', '307', 'Attempt to murder', 1860),
(5, 'Indian Penal Code', '380', 'Theft in dwelling house', 1860),
(6, 'Narcotic Drugs and Psychotropic Substances Act', '21', 'Punishment for possession of drugs', 1985),
(7, 'Prevention of Corruption Act', '7', 'Public servant taking gratification other than legal remuneration', 1988),
(8, 'Information Technology Act', '66', 'Computer related offenses', 2000),
(9, 'Information Technology Act', '67', 'Publishing or transmitting obscene material in electronic form', 2000),
(10, 'Code of Criminal Procedure', '144', 'Power to issue order in urgent cases of nuisance or apprehended danger', 1973),
(11, 'Dowry Prohibition Act', '3', 'Penalty for giving or taking dowry', 1961),
(12, 'Juvenile Justice Act', '15', 'Trial of juvenile as adult', 2015),
(13, 'Domestic Violence Act', '4', 'Information to Protection Officer and exclusion of public', 2005),
(14, 'Motor Vehicles Act', '185', 'Driving by a drunken person or by a person under the influence of drugs', 1988),
(15, 'Right to Information Act', '6', 'Request for obtaining information', 2005),
(16, 'Protection of Children from Sexual Offences (POCSO) Act', '7', 'Sexual assault', 2012),
(17, 'Arms Act', '25', 'Punishment for illegal possession of arms', 1959),
(18, 'Environmental Protection Act', '15', 'Penalty for contravention of the provisions', 1986),
(19, 'Prevention of Money Laundering Act', '3', 'Offence of money-laundering', 2002),
(20, 'Scheduled Castes and Tribes (Prevention of Atrocities) Act', '3(1)(r)', 'Intentional insult or intimidation with intent to humiliate', 1989);

-- 18. Charges Table (Depends on Case_details, Legal Act)
CREATE TABLE Charges (
    Charge_ID INT PRIMARY KEY,
    Case_ID INT,
    Legal_Act_ID INT,
    Description TEXT,
    Severity_Level VARCHAR(50),
    Charge_Date DATE,
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Legal_Act_ID) REFERENCES Legal_Act(Act_ID)
);
INSERT INTO Charges (Charge_ID, Case_ID, Legal_Act_ID, Description, Severity_Level, Charge_Date) VALUES
(1, 1, 1, 'Accused of first-degree murder under IPC 302.', 'Felony', '2023-01-02'),
(2, 2, 2, 'Fraudulently obtained money under IPC 420.', 'Major', '2023-01-03'),
(3, 3, 3, 'Charged with rape under IPC 376.', 'Felony', '2023-01-04'),
(4, 4, 4, 'Attempted to kill the victim under IPC 307.', 'Major', '2023-01-05'),
(5, 5, 5, 'Committed theft from a residential premises.', 'Minor', '2023-01-06'),
(6, 6, 6, 'Illegal possession of narcotics.', 'Felony', '2023-01-07'),
(7, 7, 7, 'Accepting bribes in public office.', 'Major', '2023-01-08'),
(8, 8, 8, 'Unauthorized access to computer systems.', 'Major', '2023-01-09'),
(9, 9, 9, 'Sharing obscene material online.', 'Minor', '2023-01-10'),
(10, 10, 10, 'Violation of Section 144 during protests.', 'Minor', '2023-01-11'),
(11, 11, 11, 'Demanding and accepting dowry.', 'Major', '2023-01-12'),
(12, 12, 12, 'Tried juvenile as adult for heinous crime.', 'Major', '2023-01-13'),
(13, 13, 13, 'Domestic violence complaint filed.', 'Major', '2023-01-14'),
(14, 14, 14, 'Driving under the influence.', 'Minor', '2023-01-15'),
(15, 15, 15, 'Refused RTI request without reason.', 'Minor', '2023-01-16'),
(16, 16, 16, 'Sexual assault on a minor.', 'Felony', '2023-01-17'),
(17, 17, 17, 'Illegal firearm possession.', 'Major', '2023-01-18'),
(18, 18, 18, 'Environmental violations by factory.', 'Major', '2023-01-19'),
(19, 19, 19, 'Money laundering via fake accounts.', 'Felony', '2023-01-20'),
(20, 20, 20, 'Caste-based insult and intimidation.', 'Major', '2023-01-21');


-- 19. Bail Table (Depends on Case_details, Person)
CREATE TABLE Bail (
    Bail_ID INT PRIMARY KEY,
    Case_ID INT,
    Person_ID INT,
    Granted_Date DATE,
    Conditions TEXT,
    Bail_Amount DECIMAL(10,2),
    Status VARCHAR(50),
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID)
);
INSERT INTO Bail (Bail_ID, Case_ID, Person_ID, Granted_Date, Conditions, Bail_Amount, Status) VALUES
(1, 1, 1, '2023-01-03', 'Report weekly to police station', 5000.00, 'Granted'),
(2, 2, 2, '2023-01-05', 'Travel restrictions applied', 10000.00, 'Granted'),
(3, 3, 3, '2023-01-07', 'Surrender passport', 15000.00, 'Granted'),
(4, 4, 4, '2023-01-09', 'No contact with victim', 12000.00, 'Granted'),
(5, 5, 5, '2023-01-11', 'Stay within city limits', 7000.00, 'Granted'),
(6, 6, 6, '2023-01-13', 'Weekly counseling sessions', 3000.00, 'Granted'),
(7, 7, 7, '2023-01-15', 'Appear in all hearings', 8000.00, 'Granted'),
(8, 8, 8, '2023-01-17', 'Random drug tests', 5000.00, 'Granted'),
(9, 9, 9, '2023-01-19', 'No access to disputed property', 6000.00, 'Granted'),
(10, 10, 10, '2023-01-21', 'Electronic monitoring', 20000.00, 'Granted'),
(11, 11, 11, '2023-01-23', 'Stay at parental home', 4000.00, 'Granted'),
(12, 12, 12, '2023-01-25', 'Attend anger management', 5500.00, 'Granted'),
(13, 13, 13, '2023-01-27', 'Weekly check-in with lawyer', 3500.00, 'Granted'),
(14, 14, 14, '2023-01-29', 'Avoid media interaction', 2500.00, 'Granted'),
(15, 15, 15, '2023-01-31', 'Daily curfew 8PM–6AM', 7500.00, 'Granted'),
(16, 16, 16, '2023-02-02', 'Inform court of new address', 9000.00, 'Granted'),
(17, 17, 17, '2023-02-04', 'Attend court without fail', 10000.00, 'Granted'),
(18, 18, 18, '2023-02-06', 'Stay away from witnesses', 11000.00, 'Granted'),
(19, 19, 19, '2023-02-08', 'No alcohol or substance use', 9500.00, 'Granted'),
(20, 20, 20, '2023-02-10', 'Wear GPS ankle monitor', 15000.00, 'Granted');

-- 20. Settlement Table (Depends on Case_details)
CREATE TABLE Settlement (
    Settlement_ID INT PRIMARY KEY,
    Case_ID INT,
    Terms TEXT,
    Date DATE,
    Agreement_Signed BOOLEAN,
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID)
);
INSERT INTO Settlement (Settlement_ID, Case_ID, Terms, Date, Agreement_Signed) VALUES
(1, 1, 'Both parties agreed to monetary compensation of $5,000.', '2023-01-05', TRUE),
(2, 2, 'Plaintiff will drop charges in exchange for public apology.', '2023-01-07', TRUE),
(3, 3, 'Defendant will vacate disputed land within 30 days.', '2023-01-09', TRUE),
(4, 4, 'Company to reinstate employee with back pay.', '2023-01-11', TRUE),
(5, 5, 'Tenant agrees to pay remaining dues and vacate premises.', '2023-01-13', TRUE),
(6, 6, 'Both parties agree to joint custody of child.', '2023-01-15', TRUE),
(7, 7, 'Defendant to pay for damages and medical expenses.', '2023-01-17', TRUE),
(8, 8, 'Out-of-court agreement with NDA on incident.', '2023-01-19', TRUE),
(9, 9, 'Business partnership dissolved with asset split.', '2023-01-21', TRUE),
(10, 10, 'Vehicle damage reimbursed by insurance.', '2023-01-23', TRUE),
(11, 11, 'Victim agrees not to press charges after restitution.', '2023-01-25', TRUE),
(12, 12, 'Online content to be taken down with apology.', '2023-01-27', TRUE),
(13, 13, 'Slander charges dropped post public clarification.', '2023-01-29', TRUE),
(14, 14, 'Land boundary dispute resolved through survey.', '2023-01-31', TRUE),
(15, 15, 'Student re-admitted after disciplinary review.', '2023-02-02', TRUE),
(16, 16, 'Medical malpractice resolved via insurance payout.', '2023-02-04', TRUE),
(17, 17, 'Copyright dispute settled with licensing agreement.', '2023-02-06', TRUE),
(18, 18, 'Contract dispute closed with refund and service.', '2023-02-08', TRUE),
(19, 19, 'Family estate division settled via mediator.', '2023-02-10', TRUE),
(20, 20, 'Pet ownership dispute resolved via compromise.', '2023-02-12', TRUE);

-- 21. Case History Table (Depends on Case_details, Court Staff)
CREATE TABLE Case_History (
    History_ID INT PRIMARY KEY,
    Case_ID INT,
    Date DATE,
    Status_Update TEXT,
    Notes TEXT,
    Updated_By INT,
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Updated_By) REFERENCES Court_Staff(Staff_ID)
);
INSERT INTO Case_History (History_ID, Case_ID, Date, Status_Update, Notes, Updated_By) VALUES
(1, 1, '2023-01-06', 'Case filed and registered.', 'Initial filing completed.', 1),
(2, 2, '2023-01-08', 'Evidence submitted.', 'Digital footage provided.', 2),
(3, 3, '2023-01-10', 'First hearing held.', 'Judge requested additional info.', 3),
(4, 4, '2023-01-12', 'Witness statement recorded.', 'Cross-examination scheduled.', 4),
(5, 5, '2023-01-14', 'Adjourned.', 'Lawyer requested extension.', 5),
(6, 6, '2023-01-16', 'New evidence added.', 'Medical report submitted.', 6),
(7, 7, '2023-01-18', 'Next hearing scheduled.', 'Set for next month.', 7),
(8, 8, '2023-01-20', 'Case referred for mediation.', 'Mediation date fixed.', 8),
(9, 9, '2023-01-22', 'Mediation failed.', 'Reverting to court trial.', 9),
(10, 10, '2023-01-24', 'Judge reassigned.', 'New judge allocated.', 10),
(11, 11, '2023-01-26', 'Arguments heard.', 'Final arguments scheduled.', 11),
(12, 12, '2023-01-28', 'Final hearing completed.', 'Awaiting verdict.', 12),
(13, 13, '2023-01-30', 'Verdict announced.', 'Defendant found not guilty.', 13),
(14, 14, '2023-02-01', 'Appeal filed.', 'Appeal to High Court.', 14),
(15, 15, '2023-02-03', 'Bail granted.', 'On condition of weekly check-in.', 15),
(16, 16, '2023-02-05', 'Additional documents submitted.', 'Documents from second party.', 16),
(17, 17, '2023-02-07', 'Judge requested clarification.', 'On witness statements.', 17),
(18, 18, '2023-02-09', 'Court issued notice to respondent.', 'Notice received.', 18),
(19, 19, '2023-02-11', 'Case merged with related case.', 'Merged with Case_ID 21.', 19),
(20, 20, '2023-02-13', 'Closed as per settlement.', 'Settlement agreement enforced.', 20);



-- 22. Court Room Table (Depends on Court)
CREATE TABLE Court_Room (
    Room_ID INT PRIMARY KEY,
    Court_ID INT,
    Room_Number VARCHAR(20),
    Capacity INT,
    Availability_Status VARCHAR(50),
    FOREIGN KEY (Court_ID) REFERENCES Court(Court_ID)
);
INSERT INTO Court_Room (Room_ID, Court_ID, Room_Number, Capacity, Availability_Status) VALUES
(1, 1, 'A-101', 50, 'Available'),
(2, 1, 'A-102', 45, 'Occupied'),
(3, 2, 'B-201', 60, 'Under Maintenance'),
(4, 2, 'B-202', 55, 'Available'),
(5, 3, 'C-301', 70, 'Occupied'),
(6, 3, 'C-302', 65, 'Available'),
(7, 4, 'D-401', 80, 'Occupied'),
(8, 4, 'D-402', 75, 'Available'),
(9, 5, 'E-501', 100, 'Available'),
(10, 5, 'E-502', 95, 'Under Maintenance'),
(11, 1, 'A-103', 40, 'Available'),
(12, 2, 'B-203', 50, 'Occupied'),
(13, 3, 'C-303', 60, 'Available'),
(14, 4, 'D-403', 70, 'Available'),
(15, 5, 'E-503', 90, 'Occupied'),
(16, 1, 'A-104', 35, 'Available'),
(17, 2, 'B-204', 45, 'Under Maintenance'),
(18, 3, 'C-304', 55, 'Available'),
(19, 4, 'D-404', 65, 'Occupied'),
(20, 5, 'E-504', 85, 'Available');


-- 23. Appeal Table (Depends on Case_details, Person)
CREATE TABLE Appeal (
    Appeal_ID INT PRIMARY KEY,
    Case_ID INT,
    Filed_By INT,
    Date DATE,
    Reason TEXT,
    Status VARCHAR(50),
    Appeal_Level VARCHAR(50),
    FOREIGN KEY (Case_ID) REFERENCES Case_details(Case_ID),
    FOREIGN KEY (Filed_By) REFERENCES Person(Person_ID)
);

INSERT INTO Appeal (Appeal_ID, Case_ID, Filed_By, Date, Reason, Status, Appeal_Level) VALUES
(1, 1, 1, '2023-02-01', 'Disagreement with judgment.', 'Pending', 'High'),
(2, 2, 2, '2023-02-03', 'New evidence discovered.', 'Granted', 'High'),
(3, 3, 3, '2023-02-05', 'Ineffective counsel.', 'Dismissed', 'District'),
(4, 4, 4, '2023-02-07', 'Unfair trial proceedings.', 'Pending', 'Supreme'),
(5, 5, 5, '2023-02-09', 'Sentencing too harsh.', 'Granted', 'High'),
(6, 6, 6, '2023-02-11', 'Violation of rights.', 'Pending', 'District'),
(7, 7, 7, '2023-02-13', 'New witness testimony.', 'Granted', 'High'),
(8, 8, 8, '2023-02-15', 'Case mishandled.', 'Dismissed', 'Supreme'),
(9, 9, 9, '2023-02-17', 'Unlawful search claim.', 'Pending', 'High'),
(10, 10, 10, '2023-02-19', 'False evidence presented.', 'Granted', 'High'),
(11, 11, 11, '2023-02-21', 'Procedural errors.', 'Pending', 'District'),
(12, 12, 12, '2023-02-23', 'Judge bias.', 'Dismissed', 'High'),
(13, 13, 13, '2023-02-25', 'Misinterpretation of law.', 'Granted', 'Supreme'),
(14, 14, 14, '2023-02-27', 'Insufficient legal support.', 'Pending', 'District'),
(15, 15, 15, '2023-03-01', 'Verdict inconsistency.', 'Granted', 'High'),
(16, 16, 16, '2023-03-03', 'Improper procedure followed.', 'Pending', 'District'),
(17, 17, 17, '2023-03-05', 'Denied fair hearing.', 'Granted', 'Supreme'),
(18, 18, 18, '2023-03-07', 'Mistrial motion rejected.', 'Dismissed', 'High'),
(19, 19, 19, '2023-03-09', 'Contested evidence validity.', 'Pending', 'High'),
(20, 20, 20, '2023-03-11', 'Appeal for retrial.', 'Granted', 'Supreme');

