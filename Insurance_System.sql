create database mydb;
use mydb;
CREATE TABLE User (
    userId INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE Client (
    clientId INT PRIMARY KEY,
    clientName VARCHAR(255),
    contactInfo VARCHAR(255),
    policy VARCHAR(255) -- Assuming policy is stored as a string
);

CREATE TABLE Claim (
    claimId INT PRIMARY KEY,
    claimNumber VARCHAR(255),
    dateFiled DATE,
    claimAmount DECIMAL(10, 2),
    status VARCHAR(50),
    policy VARCHAR(255),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    paymentDate DATE,
    paymentAmount DECIMAL(10, 2),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

INSERT INTO User (userId, username, password, role) VALUES
(1, 'john_doe', 'password123', 'admin'),
(2, 'jane_smith', 'securePwd456', 'user'),
(3, 'bob_jones', 'pass1234', 'user');

INSERT INTO Client (clientId, clientName, contactInfo, policy) VALUES
(1, 'ABC Corporation', '123-456-7890', 'General Liability'),
(2, 'XYZ Ltd', '987-654-3210', 'Property Insurance'),
(3, 'LMN Industries', '555-123-4567', 'Workers Compensation');

INSERT INTO Claim (claimId, claimNumber, dateFiled, claimAmount, status, policy, clientId) VALUES
(1, 'CLM001', '2023-01-15', 5000.00, 'Pending', 'General Liability', 1),
(2, 'CLM002', '2023-02-20', 7500.00, 'Approved', 'Property Insurance', 2),
(3, 'CLM003', '2023-03-10', 3000.00, 'Denied', 'Workers Compensation', 3);

INSERT INTO Payment (paymentId, paymentDate, paymentAmount, clientId) VALUES
(1, '2023-01-20', 2000.00, 1),
(2, '2023-02-25', 3000.00, 2),
(3, '2023-03-15', 1500.00, 3);

CREATE TABLE Policy (
    policyId INT PRIMARY KEY AUTO_INCREMENT,
    policyName VARCHAR(255) NOT NULL,
    coverageAmount DECIMAL(10, 2) NOT NULL
);

INSERT INTO Policy (policyId, policyName, coverageAmount) VALUES
(1, 'Health Insurance', 5000.00),
(2, 'Auto Insurance', 10000.00),
(3, 'Home Insurance', 7500.00),
(4, 'Travel Insurance', 3000.00),
(5, 'Life Insurance', 15000.00),
(6, 'Property Insurance', 8000.00),
(7, 'Business Insurance', 12000.00),
(8, 'Motor Insurance', 6000.00),
(9, 'Accident Insurance', 4000.00),
(10, 'Pet Insurance', 2000.00);



