create table department(
id char(5),
name varchar(40) not null,
budget numeric(8,2),
primary key(id));

create table classroom(
building varchar(40) not null,
room_number int,
capacity int,
primary key(building, room_number)
); 

create table instructor(
id char(5),
name varchar(40) not null,
salary numeric(8,2),
dept_id char(5),
primary key(id),
foreign key(dept_id) references department(id) );


create table student(
id char(5),
name varchar(40) not null,
total_credit int,
dept_id char(5),
adviser_id char(5),
primary key(id),
foreign key(adviser_id) references instructor(id),
foreign key(dept_id) references department(id) );

create table course(
id char(5),
title varchar(40) not null,
credits int(7),
dept_id char(5),
primary key(id),
foreign key(dept_id) references department(id)
);


create table section(
id char(5),
semester char(5),
year int(4),
course_id char(5),
ins_id char(5),
building varchar(40) not null,
room_number int(2),
slot_id char(5),
primary key(id, semester, year, course_id),
foreign key(course_id) references course(id),
foreign key(ins_id) references instructor(id),
foreign key(building, room_number) references classroom(building, room_number)

);



create table timeslot(
id char(5), 
day varchar(40) not null, 
start_time varchar(40) not null, 
end_time varchar(40) not null,
primary key(id)
);



create table prereq(
course_id char(5),
prereq_id char(5),
primary key(course_id, prereq_id),
foreign key(course_id) references course(id)
);

create table takes(
std_id char(5), 
sec_id char(5), 
semester char(5), 
year int(4), 
course_id char(5), 
grade int(3),
primary key(std_id,sec_id, semester, year, course_id),
foreign key(std_id) references student(id),
foreign key(sec_id,semester,year) references section(id,semester,year),
foreign key(course_id) references course(id)
);
