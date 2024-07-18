DROP database if exists se2224_proje;
CREATE DATABASE se2224_proje;
CREATE USER 'java'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON javabase.* TO 'java'@'localhost';


CREATE TABLE tasks (
  ID INT,
  Name VARCHAR(255),
  Description VARCHAR(255),
  Deadline DATETIME,
  Priority INT,
  Reminder_Image BOOLEAN,
  EntryDate DATETIME
);


INSERT INTO tasks (ID, Name, Description, Deadline, Priority, Reminder_Image, EntryDate) VALUES
(1, 'Football with Friends', 'A fun sport activity', '2023-05-24 19:00:00', 2, true, '2023-05-19 10:00:00'),
(2, 'Workout', 'Lift weights!', '2023-05-20 17:00:00', 3, true, '2023-05-24 12:00:00'),
(3, 'Coding', 'Java is easy', '2023-05-21 18:00:00', 2, true, '2023-05-21 11:00:00'),
(4, 'Study Lessons', 'Study to afefwefwefwef', '2023-05-22 22:00:00', 1, true, '2023-05-22 18:00:00'),
(5, 'Music Session', 'Music with band', '2023-05-26 21:00:00', 2, true, '2023-05-21 12:00:00'),
(6, 'Dating', 'Meet with gf', '2023-05-21 23:00:00', 1, true, '2023-05-19 17:00:00'),
(7, 'Pray', 'A fun sport activity', '2023-05-24 19:00:00', 1, true, '2023-05-19 09:00:00');
