--
-- PostgreSQL database dump
--

\restrict w31Q1r7VbvWnBvIv5PTjOlhcqzSZTFoXBJEZGzi7VZcSh7DFSYGrfIGgF8evgKE

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: final_javac_prj_sch; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA final_javac_prj_sch;


ALTER SCHEMA final_javac_prj_sch OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin; Type: TABLE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE TABLE final_javac_prj_sch.admin (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE final_javac_prj_sch.admin OWNER TO postgres;

--
-- Name: admin_id_seq; Type: SEQUENCE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE SEQUENCE final_javac_prj_sch.admin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE final_javac_prj_sch.admin_id_seq OWNER TO postgres;

--
-- Name: admin_id_seq; Type: SEQUENCE OWNED BY; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER SEQUENCE final_javac_prj_sch.admin_id_seq OWNED BY final_javac_prj_sch.admin.id;


--
-- Name: course; Type: TABLE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE TABLE final_javac_prj_sch.course (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    duration integer NOT NULL,
    instructor character varying(100) NOT NULL,
    created_at date DEFAULT CURRENT_DATE
);


ALTER TABLE final_javac_prj_sch.course OWNER TO postgres;

--
-- Name: course_id_seq; Type: SEQUENCE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE SEQUENCE final_javac_prj_sch.course_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE final_javac_prj_sch.course_id_seq OWNER TO postgres;

--
-- Name: course_id_seq; Type: SEQUENCE OWNED BY; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER SEQUENCE final_javac_prj_sch.course_id_seq OWNED BY final_javac_prj_sch.course.id;


--
-- Name: enrollment; Type: TABLE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE TABLE final_javac_prj_sch.enrollment (
    id integer NOT NULL,
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    registered_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(20) DEFAULT 'WAITING'::character varying,
    CONSTRAINT enrollment_status_check CHECK (((status)::text = ANY ((ARRAY['WAITING'::character varying, 'DENIED'::character varying, 'CANCEL'::character varying, 'CONFIRM'::character varying])::text[])))
);


ALTER TABLE final_javac_prj_sch.enrollment OWNER TO postgres;

--
-- Name: enrollment_id_seq; Type: SEQUENCE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE SEQUENCE final_javac_prj_sch.enrollment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE final_javac_prj_sch.enrollment_id_seq OWNER TO postgres;

--
-- Name: enrollment_id_seq; Type: SEQUENCE OWNED BY; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER SEQUENCE final_javac_prj_sch.enrollment_id_seq OWNED BY final_javac_prj_sch.enrollment.id;


--
-- Name: student; Type: TABLE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE TABLE final_javac_prj_sch.student (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    dob date NOT NULL,
    email character varying(100) NOT NULL,
    sex boolean NOT NULL,
    phone character varying(20),
    password character varying(255) NOT NULL,
    created_at date DEFAULT CURRENT_DATE
);


ALTER TABLE final_javac_prj_sch.student OWNER TO postgres;

--
-- Name: student_id_seq; Type: SEQUENCE; Schema: final_javac_prj_sch; Owner: postgres
--

CREATE SEQUENCE final_javac_prj_sch.student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE final_javac_prj_sch.student_id_seq OWNER TO postgres;

--
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER SEQUENCE final_javac_prj_sch.student_id_seq OWNED BY final_javac_prj_sch.student.id;


--
-- Name: admin id; Type: DEFAULT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.admin ALTER COLUMN id SET DEFAULT nextval('final_javac_prj_sch.admin_id_seq'::regclass);


--
-- Name: course id; Type: DEFAULT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.course ALTER COLUMN id SET DEFAULT nextval('final_javac_prj_sch.course_id_seq'::regclass);


--
-- Name: enrollment id; Type: DEFAULT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.enrollment ALTER COLUMN id SET DEFAULT nextval('final_javac_prj_sch.enrollment_id_seq'::regclass);


--
-- Name: student id; Type: DEFAULT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.student ALTER COLUMN id SET DEFAULT nextval('final_javac_prj_sch.student_id_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: final_javac_prj_sch; Owner: postgres
--

INSERT INTO final_javac_prj_sch.admin VALUES (1, 'admin', '123456');


--
-- Data for Name: course; Type: TABLE DATA; Schema: final_javac_prj_sch; Owner: postgres
--

INSERT INTO final_javac_prj_sch.course VALUES (1, 'Java Core', 30, 'Nguyen Van Teacher', '2026-03-05');
INSERT INTO final_javac_prj_sch.course VALUES (2, 'Database PostgreSQL', 20, 'Tran Van Teacher', '2026-03-05');
INSERT INTO final_javac_prj_sch.course VALUES (4, 'ReactJS', 120, 'Nguyễn Mạnh Cường', '2026-03-07');
INSERT INTO final_javac_prj_sch.course VALUES (5, 'HTML và CSS nâng cao', 80, 'Sơn Đặng', '2026-03-08');
INSERT INTO final_javac_prj_sch.course VALUES (6, 'Python cơ bản', 50, 'Sơn Thanh Hoá', '2026-03-08');
INSERT INTO final_javac_prj_sch.course VALUES (7, 'Nhập môn CNTT', 80, 'Trần Cao Tường', '2026-03-08');
INSERT INTO final_javac_prj_sch.course VALUES (9, 'Đại số tuyến tính', 90, 'Dũng', '2026-03-09');
INSERT INTO final_javac_prj_sch.course VALUES (10, 'Spring Boot Basic', 45, 'Dr. Adam', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (11, 'ReactJS for Beginner', 35, 'Mr. Bob', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (12, 'Python for Data Science', 60, 'Ms. Clara', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (13, 'Microservices Architecture', 50, 'Dr. Adam', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (14, 'AWS Cloud Practitioner', 40, 'Mr. David', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (15, 'Docker & Kubernetes', 30, 'Mr. Erik', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (17, 'C++ Advanced', 50, 'Prof. Frank', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (18, 'UI/UX Design', 25, 'Ms. Gina', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (19, 'Mobile Dev with Flutter', 45, 'Mr. Henry', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (20, 'Node.js Backend', 40, 'Mr. Ian', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (21, 'Angular Framework', 35, 'Ms. Jane', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (22, 'PHP Laravel Master', 45, 'Mr. Kevin', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (23, 'Cyber Security Basic', 55, 'Mr. Liam', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (24, 'DevOps Roadmap', 60, 'Mr. Mike', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (25, 'Artificial Intelligence', 80, 'Dr. Adam', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (26, 'Swift for iOS', 40, 'Ms. Naomi', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (27, 'Golang for System', 30, 'Mr. Oscar', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (28, 'VueJS Essentials', 25, 'Ms. Pauline', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (29, 'Unit Testing in Java', 20, 'Mr. Quinton', '2026-03-10');
INSERT INTO final_javac_prj_sch.course VALUES (16, 'Machine Learning', 70, 'Ms. Clara', '2026-03-10');


--
-- Data for Name: enrollment; Type: TABLE DATA; Schema: final_javac_prj_sch; Owner: postgres
--

INSERT INTO final_javac_prj_sch.enrollment VALUES (4, 4, 5, '2026-03-08 16:11:17.6402', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (7, 6, 1, '2026-03-08 23:47:17.003305', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (12, 7, 5, '2026-03-09 20:59:37.966428', 'CANCEL');
INSERT INTO final_javac_prj_sch.enrollment VALUES (6, 1, 5, '2026-03-08 18:13:29.474855', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (5, 4, 4, '2026-03-08 16:11:31.260023', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (19, 8, 7, '2026-03-10 14:28:10.507671', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (18, 8, 6, '2026-03-10 14:28:05.039302', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (16, 8, 4, '2026-03-10 14:27:37.063802', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (17, 6, 6, '2026-03-10 14:27:54.769331', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (2, 1, 2, '2026-03-05 07:06:50.288114', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (8, 6, 2, '2026-03-08 23:47:26.878303', 'CANCEL');
INSERT INTO final_javac_prj_sch.enrollment VALUES (15, 8, 2, '2026-03-10 14:26:19.601233', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (60, 1, 1, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (61, 4, 2, '2026-03-10 15:52:11.654246', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (62, 5, 4, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (63, 6, 5, '2026-03-10 15:52:11.654246', 'DENIED');
INSERT INTO final_javac_prj_sch.enrollment VALUES (64, 7, 6, '2026-03-10 15:52:11.654246', 'CANCEL');
INSERT INTO final_javac_prj_sch.enrollment VALUES (65, 9, 7, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (66, 10, 9, '2026-03-10 15:52:11.654246', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (67, 11, 10, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (68, 12, 11, '2026-03-10 15:52:11.654246', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (69, 13, 12, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (70, 14, 13, '2026-03-10 15:52:11.654246', 'DENIED');
INSERT INTO final_javac_prj_sch.enrollment VALUES (71, 15, 14, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (72, 16, 15, '2026-03-10 15:52:11.654246', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (73, 17, 16, '2026-03-10 15:52:11.654246', 'CANCEL');
INSERT INTO final_javac_prj_sch.enrollment VALUES (74, 18, 17, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (75, 19, 18, '2026-03-10 15:52:11.654246', 'WAITING');
INSERT INTO final_javac_prj_sch.enrollment VALUES (76, 20, 19, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (77, 21, 20, '2026-03-10 15:52:11.654246', 'DENIED');
INSERT INTO final_javac_prj_sch.enrollment VALUES (78, 22, 1, '2026-03-10 15:52:11.654246', 'CONFIRM');
INSERT INTO final_javac_prj_sch.enrollment VALUES (79, 1, 2, '2026-03-10 15:52:11.654246', 'WAITING');


--
-- Data for Name: student; Type: TABLE DATA; Schema: final_javac_prj_sch; Owner: postgres
--

INSERT INTO final_javac_prj_sch.student VALUES (4, 'Nguyễn Mạnh Cường', '2007-12-28', 'nmc281207@gmail.com', true, '0965111198', '12123456', '2026-03-07');
INSERT INTO final_javac_prj_sch.student VALUES (5, 'Nguyễn Sỹ Lương', '2007-08-27', 'luong@gmail.com', true, '0123456789', '1231234', '2026-03-08');
INSERT INTO final_javac_prj_sch.student VALUES (1, 'Nguyễn Đức Anh', '2003-05-10', 'a@gmail.com', true, '0123456789', '12345678', '2026-03-08');
INSERT INTO final_javac_prj_sch.student VALUES (6, 'Nguyễn Minh Hiếu', '2007-01-28', 'hieu@gmail.com', true, '0912121212', '1212345678', '2026-03-08');
INSERT INTO final_javac_prj_sch.student VALUES (7, 'Trần Lâm', '2007-11-05', 'lam@gmail.com', true, '0989898989', '123456', '2026-03-09');
INSERT INTO final_javac_prj_sch.student VALUES (8, 'Nguyễn Phương Hoa', '2007-01-12', 'hoa@gmail.com', false, '0912423456', '123455', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (9, 'Le Van Cuong', '2003-01-15', 'cuong.le@gmail.com', true, '0912000001', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (10, 'Pham Thanh Hoa', '2004-02-20', 'hoa.pham@gmail.com', false, '0912000002', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (11, 'Hoang Minh Duc', '2002-03-10', 'duc.hoang@gmail.com', true, '0912000003', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (12, 'Dang Thu Thao', '2005-04-05', 'thao.dang@gmail.com', false, '0912000004', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (13, 'Bui Anh Tu', '2003-11-22', 'tu.bui@gmail.com', true, '0912000005', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (14, 'Vu Hoang Yen', '2004-06-12', 'yen.vu@gmail.com', false, '0912000006', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (15, 'Ngo Quoc Bao', '2001-08-30', 'bao.ngo@gmail.com', true, '0912000007', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (16, 'Ly My Linh', '2004-09-18', 'linh.ly@gmail.com', false, '0912000008', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (17, 'Do Tien Dat', '2003-12-25', 'dat.do@gmail.com', true, '0912000009', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (18, 'Trinh Kim Chi', '2002-05-14', 'chi.trinh@gmail.com', false, '0912000010', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (19, 'Mai Xuan Truong', '2004-07-07', 'truong.mai@gmail.com', true, '0912000011', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (20, 'Dinh Ngoc Diep', '2005-01-01', 'diep.dinh@gmail.com', false, '0912000012', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (21, 'Quach Tuan Du', '2003-10-10', 'du.quach@gmail.com', true, '0912000013', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (22, 'Luong Bich Huu', '2004-04-20', 'huu.luong@gmail.com', false, '0912000014', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (23, 'Phan Manh Quynh', '2002-09-09', 'quynh.phan@gmail.com', true, '0912000015', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (24, 'Trieu Le Quyen', '2003-03-03', 'quyen.trieu@gmail.com', false, '0912000016', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (25, 'Kieu Minh Tuan', '2001-06-15', 'tuan.kieu@gmail.com', true, '0912000017', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (26, 'Lam Tam Nhu', '2004-08-08', 'nhu.lam@gmail.com', false, '0912000018', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (27, 'Ta Quang Thang', '2003-02-14', 'thang.ta@gmail.com', true, '0912000019', 'pass123', '2026-03-10');
INSERT INTO final_javac_prj_sch.student VALUES (28, 'Vo Ha Tram', '2005-05-25', 'tram.vo@gmail.com', false, '0912000020', 'pass123', '2026-03-10');


--
-- Name: admin_id_seq; Type: SEQUENCE SET; Schema: final_javac_prj_sch; Owner: postgres
--

SELECT pg_catalog.setval('final_javac_prj_sch.admin_id_seq', 1, true);


--
-- Name: course_id_seq; Type: SEQUENCE SET; Schema: final_javac_prj_sch; Owner: postgres
--

SELECT pg_catalog.setval('final_javac_prj_sch.course_id_seq', 29, true);


--
-- Name: enrollment_id_seq; Type: SEQUENCE SET; Schema: final_javac_prj_sch; Owner: postgres
--

SELECT pg_catalog.setval('final_javac_prj_sch.enrollment_id_seq', 79, true);


--
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: final_javac_prj_sch; Owner: postgres
--

SELECT pg_catalog.setval('final_javac_prj_sch.student_id_seq', 28, true);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: admin admin_username_key; Type: CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.admin
    ADD CONSTRAINT admin_username_key UNIQUE (username);


--
-- Name: course course_pkey; Type: CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: enrollment enrollment_pkey; Type: CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.enrollment
    ADD CONSTRAINT enrollment_pkey PRIMARY KEY (id);


--
-- Name: student student_email_key; Type: CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.student
    ADD CONSTRAINT student_email_key UNIQUE (email);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: enrollment fk_course; Type: FK CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.enrollment
    ADD CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES final_javac_prj_sch.course(id) ON DELETE CASCADE;


--
-- Name: enrollment fk_student; Type: FK CONSTRAINT; Schema: final_javac_prj_sch; Owner: postgres
--

ALTER TABLE ONLY final_javac_prj_sch.enrollment
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES final_javac_prj_sch.student(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

\unrestrict w31Q1r7VbvWnBvIv5PTjOlhcqzSZTFoXBJEZGzi7VZcSh7DFSYGrfIGgF8evgKE

