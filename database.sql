-- CREATE DATABASE studentDB;
-- use studentDB;


CREATE TABLE academy (
	academy_id INTEGER PRIMARY KEY IDENTITY,
	academy_name VARCHAR(20) NOT NULL,
	academy_leader VARCHAR(20) NOT NULL
);

CREATE TABLE major (
	major_id INTEGER PRIMARY KEY IDENTITY,
	major_name VARCHAR(20) NOT NULL,
	academy_id INTEGER NOT NULL,
	CONSTRAINT fk_major_academy_id FOREIGN KEY (academy_id) REFERENCES academy(academy_id) ON DELETE CASCADE
);

CREATE TABLE class (
	class_id INTEGER PRIMARY KEY IDENTITY,
	class_name VARCHAR(20) NOT NULL,
	major_id INTEGER NOT NULL,
	CONSTRAINT fk_class_major_id FOREIGN KEY (major_id) REFERENCES major(major_id) ON DELETE CASCADE
);

CREATE TABLE dormitory (
	dormitory_id INTEGER PRIMARY KEY IDENTITY,
	dormitory_name VARCHAR(60)
);

CREATE TABLE student (
	student_id CHAR(9) PRIMARY KEY,
	student_name VARCHAR(20) NOT NULL,
	student_year INTEGER NOT NULL,
	student_sex CHAR(4) NOT NULL,
	student_birthday DATE NOT NULL,
	student_nation VARCHAR(20) NOT NULL,
	class_id INTEGER NOT NULL,
	dormitory_id INTEGER NOT NULL,
	CONSTRAINT fk_student_class_id FOREIGN KEY (class_id) REFERENCES class(class_id) ON DELETE CASCADE,
	CONSTRAINT fk_student_dormitory_id FOREIGN KEY (dormitory_id) REFERENCES dormitory(dormitory_id) ON DELETE CASCADE
);

CREATE TABLE teacher (
	teacher_id CHAR(9) PRIMARY KEY,
	teacher_name VARCHAR(20) NOT NULL,
	teacher_sex CHAR(4) NOT NULL,
	academy_id INTEGER NOT NULL,
	CONSTRAINT fk_teacher_teacher_academy_id FOREIGN KEY (academy_id) REFERENCES academy(academy_id)
);

CREATE TABLE course (
	course_id INTEGER PRIMARY KEY IDENTITY,
	course_name VARCHAR(20) NOT NULL,
	course_time INTEGER NOT NULL,
	course_score DECIMAL NOT NULL,
	teacher_id CHAR(9) NOT NULL,
	CONSTRAINT fk_course_course_teacher_id FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id) ON DELETE CASCADE
);

CREATE TABLE student_course (
	student_id CHAR(9) NOT NULL,
	course_id INTEGER NOT NULL,
	score DECIMAL,
	PRIMARY KEY(student_id, course_id),
	CONSTRAINT fk_student_course_student_id FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
	CONSTRAINT fk_student_course_course_id FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE
);


-- CREATE VIEW student_detail (student_id,	student_name, student_sex, student_birthday, student_nation, dormitory_name, academy_name, major_name, class_name)
-- AS
-- SELECT student.student_id, student.student_name, student.student_sex, student.student_birthday, student.student_nation, dormitory.dormitory_name, academy.academy_name, major.major_name, class.class_name
-- FROM student, dormitory, academy, major, class
-- WHERE student.dormitory_id = dormitory.dormitory_id AND student.class_id = class.class_id AND class.major_id = major.major_id AND major.academy_id = academy.academy_id;