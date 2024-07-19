-- Name: Arda Harman
-- ID: 21070006054

-- drop schema SE2222_21070006054;
create schema SE2222_21070006054;
use SE2222_21070006054;


-- Short and simple demonstration of each term and what they are:

--       (employs)          
-- Company  ->     Trainers

--     (subscribes to)
-- Client   ->     Trainers    

--        (has a)                   (make training program)
-- Client   ->     Training Program            <-           Trainer

--        (has a)                   (make diet program)
-- Client   ->     Training Program            <-           Dietician

--      (used by)
-- Gym     ->        Client

--      (used by)
-- Gym     ->        Trainer

--                             (used by)
--  Trainer Finder Application    ->     Client

--                             (used by)
--  Trainer Finder Application  ->   Trainer

--                             (used by)
--  Trainer Finder Application  ->   App Admin





--     DEFINITIONS
 

-- Trainer Finder Application table
CREATE TABLE TrainerFinderApplication (
  name VARCHAR(255),
  platforms_available VARCHAR(255),
  categoryOfApp VARCHAR(255),
  Price FLOAT,
  Publisher VARCHAR(255),
  Owner VARCHAR(255),
  AppStoreID INT,
  
  PRIMARY KEY (AppStoreID)
);

-- Client table
CREATE TABLE Client (
  AppStoreID INT,
  ClientID INT,
  name VARCHAR(255),
  age INT,
  gender VARCHAR(10),
  weight FLOAT,
  health_status VARCHAR(255),
  training_background VARCHAR(255),
  credit_card VARCHAR(255),
  PRIMARY KEY (ClientID),
  FOREIGN KEY (AppStoreID) references TrainerFinderApplication(AppStoreID)
);

-- Trainer table
CREATE TABLE Trainer (
  AppStoreID INT,
  trainerID INT,
  name VARCHAR(255),
  age INT,
  gender VARCHAR(10),
  weight FLOAT,
  health_status VARCHAR(255),
  mothertongue VARCHAR(255),
  experience CHAR(1),
  price FLOAT,
  PRIMARY KEY (trainerID),
  FOREIGN KEY (AppStoreID) references TrainerFinderApplication(AppStoreID)
);

-- Training Program table
CREATE TABLE TrainingProgram (
  TrainingProgram_id INT,
  clientID INT,
  repetition_and_set_amount_of_exercises VARCHAR(255),
  program_name VARCHAR(255),
  PRIMARY KEY (TrainingProgram_id),
  FOREIGN key (clientID) references Client(ClientID)
);

-- Dietician table
CREATE TABLE Dietician (
  name VARCHAR(255),
  age INT,
  background VARCHAR(255),
  experience VARCHAR(255),
  degree_of_profession VARCHAR(255),
  dieticianID INT,
  PRIMARY KEY (dieticianID)
);

-- Diet Program table
CREATE TABLE DietProgram (
  clientID INT,
  DietProgramID INT,
  meals VARCHAR(255),
  time_period_of_program VARCHAR(255),
  
  PRIMARY KEY (DietProgramID),
  FOREIGN KEY (clientID) references Client(ClientID)
);

-- Gym table
CREATE TABLE Gym (
  gymID INT,
  weights_brand VARCHAR(255),
  machines_brand VARCHAR(255),
  stretching_bands_brand VARCHAR(255),
  medical_equipments_amount INT,
  address VARCHAR(255),
  staff_amount INT,
  
  PRIMARY KEY (gymID)
);



-- App Admin table
CREATE TABLE AppAdmin (
  AppStore_id INT,
  tasks VARCHAR(255),
  position VARCHAR(255),
  application_admin_id INT,
  PRIMARY KEY (AppStore_id, application_admin_id)
);

-- Company table
CREATE TABLE Company (
  name VARCHAR(255),
  location VARCHAR(255),
  PRIMARY KEY (name)
);

-- Employ table
CREATE TABLE Employ (
  trainerID INT,
  name VARCHAR(255),
  
  PRIMARY KEY (trainerID, name),
  FOREIGN KEY (trainerID) references Trainer(trainerID),
  FOREIGN KEY (name) references Company(name)
);

-- trainer_gym table
CREATE TABLE trainer_gym (
  trainerID INT,
  gymID INT,
    
  PRIMARY KEY (trainerID, gymID),
  FOREIGN KEY (trainerID) REFERENCES Trainer(trainerID),
  FOREIGN KEY (gymID) REFERENCES Gym(gymID)
);

  
  -- client_gym table
CREATE TABLE client_gym (
clientID INT,
gymID INT,

PRIMARY KEY (clientID, gymID),
FOREIGN KEY (clientID) REFERENCES Client(ClientID),
FOREIGN KEY (gymID) REFERENCES Gym(gymID)
);

-- Subscribe table
CREATE TABLE Subscribe (
trainerID INT,
clientID INT,
PRIMARY KEY (trainerID, clientID),
FOREIGN KEY (trainerID) REFERENCES Trainer(trainerID),
FOREIGN KEY (clientID) REFERENCES Client(ClientID)
);


-- make_a_diet_program table
CREATE TABLE make_a_diet_program (
dieticianID INT,
ClientID INT,
PRIMARY KEY (dieticianID, ClientID),
FOREIGN KEY (dieticianID) REFERENCES Dietician(dieticianID),
FOREIGN KEY (ClientID) REFERENCES Client(ClientID)
);

-- make_training_program table
CREATE TABLE make_training_program (
trainerID INT,
clientID INT,
TrainingProgram_id INT,

PRIMARY KEY (trainerID, TrainingProgram_id),
FOREIGN KEY (trainerID) REFERENCES Trainer(trainerID),
FOREIGN KEY (clientID) REFERENCES Client(ClientID),
FOREIGN KEY (TrainingProgram_id) REFERENCES TrainingProgram(TrainingProgram_id)
);





--       INSERTIONS 

  
-- TrainerFinderApplication Table
INSERT INTO TrainerFinderApplication (name, platforms_available, categoryOfApp, Price, Publisher, Owner, AppStoreID)
VALUES ('App 1', 'iOS, Android', 'Fitness', 4.99, 'Publisher 1', 'Owner 1', 123);

-- Client Table
INSERT INTO Client (AppStoreID, ClientID, name, age, gender, weight, health_status, training_background, credit_card)
VALUES (123, 101, 'Client 1', 30, 'Female', 65.5, 'Good', 'Beginner', '1234-5678-9012-3456'),
       (123, 102, 'Client 2', 25, 'Male', 75.2, 'Excellent', 'Intermediate', '9876-5432-1098-7654'),
       (123, 103, 'Client 3', 35, 'Male', 150.0, 'Poor', 'Advanced', '1111-2222-3333-4444');

-- Trainer Table
INSERT INTO Trainer (AppStoreID, trainerID, name, age, gender, weight, health_status, mothertongue, experience, price)
VALUES (123, 201, 'Trainer 1', 35, 'Male', 80.0, 'Good', 'English', 5, 50.0),
       (123, 202, 'Trainer 2', 28, 'Female', 62.5, 'Excellent', 'French', 3, 40.0),
	   (123, 203, 'Trainer 3', 44, 'Male', 103.0, 'Good', 'Turkish', 5, 720.0),
       (123, 204, 'Trainer 4', 31, 'Female', 52.2, 'Excellent', 'Swedish', 3, 120.0);

-- TrainingProgram Table
INSERT INTO TrainingProgram (TrainingProgram_id, clientID, repetition_and_set_amount_of_exercises, program_name)
VALUES (1, 101, '3 sets of 10 reps', 'Program 1'),
       (2, 102, '4 sets of 12 reps', 'Program 2'),
       (3, 103, '5 sets of 8 reps', 'Program 3'),
       (4, 102, '5 sets of 5 reps', 'Program 4'),
       (5, 101, '2 sets of 15 reps', 'Program 5');

-- Dietician Table
INSERT INTO Dietician (name, age, background, experience, degree_of_profession, dieticianID)
VALUES ('Dietician 1', 40, 'Nutrition Science', '10 years', 'Master''s Degree', 301),
       ('Dietician 2', 35, 'Dietetics', '7 years', 'Bachelor''s Degree', 302);

-- DietProgram Table
INSERT INTO DietProgram (clientID, DietProgramID, meals, time_period_of_program)
VALUES (101, 1, 'Balanced diet', '4 weeks'),
       (102, 2, 'Low carb diet', '8 weeks'),
       (103, 3, 'Vegan diet', '12 weeks'),
       (101, 4, 'Bulk diet', '3 weeks');

-- Gym Table
INSERT INTO Gym (gymID, weights_brand, machines_brand, stretching_bands_brand, medical_equipments_amount, address, staff_amount)
VALUES (1, 'Brand 1', 'Brand 2', 'Brand 3', 5, 'Gym 1 Address', 10),
       (2, 'Brand 2', 'Brand 3', 'Brand 4', 8, 'Gym 2 Address', 12);

-- AppAdmin Table
INSERT INTO AppAdmin (AppStore_id, tasks, position, application_admin_id)
VALUES (123, 'Task 1', 'Admin', 1),
       (123, 'Task 2', 'Admin', 2);

-- Company Table
INSERT INTO Company (name, location)
VALUES ('Company 1', 'Location 1'),
       ('Company 2', 'Location 2');

INSERT INTO Employ (trainerID, name)
VALUES (201, 'Company 1'),
       (202, 'Company 2'),
       (203, 'Company 2'),
       (204, 'Company 1');

-- Trainer_Gym Table
INSERT INTO trainer_gym (trainerID, gymID)
VALUES (201, 1),
       (202, 2),
       (203, 2),
       (204, 1);

-- Client_Gym Table
INSERT INTO client_gym (clientID, gymID)
VALUES (101, 1),
       (102, 2),
       (103, 2);

-- Subscribe Table
INSERT INTO Subscribe (trainerID, clientID)
VALUES (201, 101),
       (202, 102),
       (203, 102),
       (204, 101),
       (203, 103);

-- Make_a_Diet_Program Table
INSERT INTO make_a_diet_program (dieticianID, ClientID)
VALUES (301, 101),
	   (302, 101),
       (302, 102),
       (301, 103);

-- Make_Training_Program Table
INSERT INTO make_training_program (trainerID, clientID, TrainingProgram_id)
VALUES (201, 101, 1),
       (202, 102, 4),
       (203, 102, 1),
       (204, 101, 5),
       (203, 103, 3);
       
       




-----    Queries
	
-- join two or more tables

select trainer.name, trainer.trainerID, subscribe.clientID
from trainer, subscribe
where trainer.trainerID = subscribe.trainerID;



-- group functions
	-- amount of how much trainer does each company has
SELECT name, COUNT(name)
from employ
GROUP BY (name);


-- one or more sub-query(es)
		-- the ID of the client who had subscribed to the cheapest trainer
SELECT clientID, trainerID
from subscribe
WHERE trainerID = (
	select trainerID
    from trainer
    where price = 
		(SELECT min(price)
        from trainer)
);


-- update
UPDATE trainer
SET price = 70
WHERE trainerID = 203;


-- delete
DELETE FROM appadmin 
WHERE application_admin_id = 2;




-- arithmetic functions
SELECT name, price + 100.1
from trainerfinderapplication;


-- alias
SELECT trainerID as "ID of trainer", mothertongue as "main language", name as "name of trainer"
from trainer;