set search_path to final_javac_prj_sch;

CREATE TABLE admin (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);
INSERT INTO admin(username, password)
VALUES
    ('admin', '123456');

CREATE TABLE student (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         dob DATE NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         sex BOOLEAN NOT NULL,
                         phone VARCHAR(20),
                         password VARCHAR(255) NOT NULL,
                         created_at DATE DEFAULT CURRENT_DATE
);

INSERT INTO student(name, dob, email, sex, phone, password)
VALUES
    ('Nguyen Van A','2003-05-10','a@gmail.com',true,'0123456789','123123'),
    ('Tran Thi B','2004-07-20','b@gmail.com',false,'0987654321','123456');


CREATE TABLE course (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        duration INT NOT NULL,
                        instructor VARCHAR(100) NOT NULL,
                        created_at DATE DEFAULT CURRENT_DATE
);

INSERT INTO course(name, duration, instructor)
VALUES
    ('Java Core', 30, 'Nguyen Van Teacher'),
    ('Database PostgreSQL', 20, 'Tran Van Teacher');


CREATE TABLE enrollment (
                            id SERIAL PRIMARY KEY,
                            student_id INT NOT NULL,
                            course_id INT NOT NULL,
                            registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            status VARCHAR(20)
                                CHECK (status IN ('WAITING','DENIED','CANCEL','CONFIRM'))
                                                    DEFAULT 'WAITING',

                            CONSTRAINT fk_student
                                FOREIGN KEY(student_id)
                                    REFERENCES student(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_course
                                FOREIGN KEY(course_id)
                                    REFERENCES course(id)
                                    ON DELETE CASCADE
);
INSERT INTO enrollment(student_id, course_id)
VALUES
    (1,1),
    (1,2),
    (2,1);



-- INSERT INTO admin (username, password) VALUES
--                                            ('admin2', 'pass_abc'), ('manager', 'secure123'), ('root', 'root123'),
--                                            ('staff_01', 'password'), ('staff_02', 'password'), ('supervisor', 'super123'),
--                                            ('mod_01', 'modpass'), ('mod_02', 'modpass'), ('lead_dev', 'dev123'),
--                                            ('director', 'dir456'), ('hr_dept', 'hr789'), ('it_support', 'it000'),
--                                            ('accountant', 'acc111'), ('editor', 'edit222'), ('analyst', 'ana333'),
--                                            ('guest_admin', 'gst444'), ('system', 'sys555'), ('owner', 'own666'),
--                                            ('vp_admin', 'vp777'), ('chief', 'chf888');

INSERT INTO student (name, dob, email, sex, phone, password) VALUES
                                                                 ('Le Van Cuong', '2003-01-15', 'cuong.le@gmail.com', true, '0912000001', 'pass123'),
                                                                 ('Pham Thanh Hoa', '2004-02-20', 'hoa.pham@gmail.com', false, '0912000002', 'pass123'),
                                                                 ('Hoang Minh Duc', '2002-03-10', 'duc.hoang@gmail.com', true, '0912000003', 'pass123'),
                                                                 ('Dang Thu Thao', '2005-04-05', 'thao.dang@gmail.com', false, '0912000004', 'pass123'),
                                                                 ('Bui Anh Tu', '2003-11-22', 'tu.bui@gmail.com', true, '0912000005', 'pass123'),
                                                                 ('Vu Hoang Yen', '2004-06-12', 'yen.vu@gmail.com', false, '0912000006', 'pass123'),
                                                                 ('Ngo Quoc Bao', '2001-08-30', 'bao.ngo@gmail.com', true, '0912000007', 'pass123'),
                                                                 ('Ly My Linh', '2004-09-18', 'linh.ly@gmail.com', false, '0912000008', 'pass123'),
                                                                 ('Do Tien Dat', '2003-12-25', 'dat.do@gmail.com', true, '0912000009', 'pass123'),
                                                                 ('Trinh Kim Chi', '2002-05-14', 'chi.trinh@gmail.com', false, '0912000010', 'pass123'),
                                                                 ('Mai Xuan Truong', '2004-07-07', 'truong.mai@gmail.com', true, '0912000011', 'pass123'),
                                                                 ('Dinh Ngoc Diep', '2005-01-01', 'diep.dinh@gmail.com', false, '0912000012', 'pass123'),
                                                                 ('Quach Tuan Du', '2003-10-10', 'du.quach@gmail.com', true, '0912000013', 'pass123'),
                                                                 ('Luong Bich Huu', '2004-04-20', 'huu.luong@gmail.com', false, '0912000014', 'pass123'),
                                                                 ('Phan Manh Quynh', '2002-09-09', 'quynh.phan@gmail.com', true, '0912000015', 'pass123'),
                                                                 ('Trieu Le Quyen', '2003-03-03', 'quyen.trieu@gmail.com', false, '0912000016', 'pass123'),
                                                                 ('Kieu Minh Tuan', '2001-06-15', 'tuan.kieu@gmail.com', true, '0912000017', 'pass123'),
                                                                 ('Lam Tam Nhu', '2004-08-08', 'nhu.lam@gmail.com', false, '0912000018', 'pass123'),
                                                                 ('Ta Quang Thang', '2003-02-14', 'thang.ta@gmail.com', true, '0912000019', 'pass123'),
                                                                 ('Vo Ha Tram', '2005-05-25', 'tram.vo@gmail.com', false, '0912000020', 'pass123');


INSERT INTO course (name, duration, instructor) VALUES
                                                    ('Spring Boot Basic', 45, 'Dr. Adam'),
                                                    ('ReactJS for Beginner', 35, 'Mr. Bob'),
                                                    ('Python for Data Science', 60, 'Ms. Clara'),
                                                    ('Microservices Architecture', 50, 'Dr. Adam'),
                                                    ('AWS Cloud Practitioner', 40, 'Mr. David'),
                                                    ('Docker & Kubernetes', 30, 'Mr. Erik'),
                                                    ('Machine Learning Foundation', 70, 'Ms. Clara'),
                                                    ('C++ Advanced', 50, 'Prof. Frank'),
                                                    ('UI/UX Design', 25, 'Ms. Gina'),
                                                    ('Mobile Dev with Flutter', 45, 'Mr. Henry'),
                                                    ('Node.js Backend', 40, 'Mr. Ian'),
                                                    ('Angular Framework', 35, 'Ms. Jane'),
                                                    ('PHP Laravel Master', 45, 'Mr. Kevin'),
                                                    ('Cyber Security Basic', 55, 'Mr. Liam'),
                                                    ('DevOps Roadmap', 60, 'Mr. Mike'),
                                                    ('Artificial Intelligence', 80, 'Dr. Adam'),
                                                    ('Swift for iOS', 40, 'Ms. Naomi'),
                                                    ('Golang for System', 30, 'Mr. Oscar'),
                                                    ('VueJS Essentials', 25, 'Ms. Pauline'),
                                                    ('Unit Testing in Java', 20, 'Mr. Quinton');




INSERT INTO enrollment (student_id, course_id, status) VALUES
                                                           (1, 1, 'CONFIRM'), (4, 2, 'WAITING'), (5, 4, 'CONFIRM'),
                                                           (6, 5, 'DENIED'),  (7, 6, 'CANCEL'),  (9, 7, 'CONFIRM'),
                                                           (10, 9, 'WAITING'),(11, 10, 'CONFIRM'),(12, 11, 'WAITING'),
                                                           (13, 12, 'CONFIRM'),(14, 13, 'DENIED'), (15, 14, 'CONFIRM'),
                                                           (16, 15, 'WAITING'),(17, 16, 'CANCEL'), (18, 17, 'CONFIRM'),
                                                           (19, 18, 'WAITING'),(20, 19, 'CONFIRM'),(21, 20, 'DENIED'),
                                                           (22, 1, 'CONFIRM'), (1, 2, 'WAITING');

