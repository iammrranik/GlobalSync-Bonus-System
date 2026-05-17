-- ==========================================
-- 1. DATABASE INITIALIZATION
-- ==========================================

CREATE DATABASE IF NOT EXISTS globalsync_db;
USE globalsync_db;

-- ==========================================
-- 2. TABLE CREATION (DDL)
-- ==========================================

-- Create Employees Table
CREATE TABLE IF NOT EXISTS employees (
employee_id         INT PRIMARY KEY AUTO_INCREMENT,
name                VARCHAR(100) NOT NULL,
designation         VARCHAR(50) NOT NULL,
base_salary         FLOAT NOT NULL,
role                VARCHAR(20) NOT NULL,
last_promotion_date DATE
);

-- Create Performance Reviews Table
CREATE TABLE IF NOT EXISTS performance_reviews (
id                  INT PRIMARY KEY AUTO_INCREMENT,
employee_id         INT NOT NULL,
review_year         INT NOT NULL,
task_completion     INT NOT NULL,
attendance          INT NOT NULL,
team_collaboration  INT NOT NULL,
problem_solving     INT NOT NULL,
communication       INT NOT NULL,
leadership          INT NOT NULL,
client_satisfaction INT NOT NULL,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

-- Create Bonus Records Table
CREATE TABLE IF NOT EXISTS bonus_records (
id                  INT PRIMARY KEY AUTO_INCREMENT,
employee_id         INT NOT NULL,
review_year         INT NOT NULL,
total_kpi_score     INT NOT NULL,
category            VARCHAR(20) NOT NULL,
bonus_percentage    FLOAT NOT NULL,
bonus_amount        FLOAT NOT NULL,
total_compensation  FLOAT NOT NULL,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

-- ==========================================
-- 3. DATA POPULATION (DML)
-- ==========================================

-- Insert 5 sample Employees
INSERT INTO employees (name, designation, base_salary, role, last_promotion_date) VALUES
('Tahsin Ahmed', 'Software Engineer', 55000.0, 'EMPLOYEE', '2025-01-15'),
('Nusrat Jahan', 'Senior Developer', 85000.0, 'MANAGER', '2024-06-10'),
('Arif Rayhan', 'QA Engineer', 45000.0, 'EMPLOYEE', '2025-03-01'),
('Sajid Hasan', 'Tech Lead', 120000.0, 'MANAGER', '2023-11-20'),
('Fariha Khan', 'HR Specialist', 60000.0, 'ADMIN', NULL);

-- Insert 5 sample Performance Reviews
INSERT INTO performance_reviews (employee_id, review_year, task_completion, attendance, team_collaboration, problem_solving, communication, leadership, client_satisfaction) VALUES
(1, 2026, 23, 14, 13, 14, 9, 8, 9),  -- Total: 90 (Gold)
(2, 2026, 21, 13, 12, 11, 8, 9, 8),  -- Total: 80 (Silver)
(3, 2026, 18, 11, 10, 10, 7, 6, 7),  -- Total: 69 (Bronze)
(4, 2026, 24, 15, 14, 14, 9, 10, 9), -- Total: 95 (Gold)
(5, 2026, 15, 10, 9, 8, 6, 5, 6);    -- Total: 59 (None)

-- Insert 5 sample Bonus Records
INSERT INTO bonus_records (employee_id, review_year, total_kpi_score, category, bonus_percentage, bonus_amount, total_compensation) VALUES
(1, 2026, 90, 'GOLD', 20.0, 11000.0, 66000.0),
(2, 2026, 80, 'SILVER', 12.0, 10200.0, 95200.0),
(3, 2026, 69, 'BRONZE', 5.0, 2250.0, 47250.0),
(4, 2026, 95, 'GOLD', 20.0, 24000.0, 144000.0),
(5, 2026, 59, 'NONE', 0.0, 0.0, 60000.0);

-- ==========================================
-- 4. UTILITY & MAINTENANCE COMMANDS
-- ==========================================

-- Check all data
SELECT * FROM employees;
SELECT * FROM performance_reviews;
SELECT * FROM bonus_records;

-- Clear calculation history but keep employees
TRUNCATE TABLE bonus_records;
TRUNCATE TABLE performance_reviews;

-- Delete a specific record
DELETE FROM employees WHERE employee_id = 5;

-- Update employee data manually
UPDATE employees SET base_salary = 58000.0 WHERE employee_id = 1;