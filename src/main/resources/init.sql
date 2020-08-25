drop table if exists  groups, students, course cascade;
create table groups (
group_id serial primary key,
name varchar(100)
);

create table students(
student_id serial primary key,
group_id int references groups(group_id),
first_name varchar (100),
last_name varchar (100)
);

create table courses(
course_id serial primary key,
course_name varchar(100),
course_description text
);