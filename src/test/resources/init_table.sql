drop table if exists  groups, students, courses, student_course cascade;
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

create table student_course(
                               id serial primary key,
                               student_id int references students(student_id) on delete cascade,
                               course_id int references courses(course_id) on delete cascade
);