-- ১. Employees Table
CREATE TABLE employees (
employee_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
designation VARCHAR(50) NOT NULL,
base_salary DOUBLE NOT NULL,
role VARCHAR(20) NOT NULL, -- EMPLOYEE, MANAGER, ADMIN
last_promotion_date DATE
);

-- ২. Performance Reviews Table
CREATE TABLE performance_reviews (
review_id INT PRIMARY KEY AUTO_INCREMENT,
employee_id INT NOT NULL,
review_year INT NOT NULL,
task_completion INT NOT NULL,
attendance INT NOT NULL,
team_collaboration INT NOT NULL,
problem_solving INT NOT NULL,
communication INT NOT NULL,
leadership INT NOT NULL,
client_satisfaction INT NOT NULL,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

-- ৩. Bonus Records Table
CREATE TABLE bonus_records (
bonus_id INT PRIMARY KEY AUTO_INCREMENT,
employee_id INT NOT NULL,
review_year INT NOT NULL,
total_kpi_score INT NOT NULL,
category VARCHAR(20) NOT NULL, -- Gold Tier, Silver Tier, Bronze Tier, No Tier
bonus_percentage DOUBLE NOT NULL,
bonus_amount DOUBLE NOT NULL,
total_compensation DOUBLE NOT NULL,
FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);