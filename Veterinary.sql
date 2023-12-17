--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2023-12-17 21:07:51 +03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 25179)
-- Name: animal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animal (
    id bigint NOT NULL,
    breed character varying(255) NOT NULL,
    colour character varying(255) NOT NULL,
    date_of_birth date NOT NULL,
    gender character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    species character varying(255) NOT NULL,
    customer_id bigint
);


ALTER TABLE public.animal OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25178)
-- Name: animal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animal_id_seq OWNER TO postgres;

--
-- TOC entry 3651 (class 0 OID 0)
-- Dependencies: 219
-- Name: animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;


--
-- TOC entry 222 (class 1259 OID 25188)
-- Name: appointment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    animal_id bigint,
    doctor_id bigint
);


ALTER TABLE public.appointment OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25187)
-- Name: appointment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appointment_id_seq OWNER TO postgres;

--
-- TOC entry 3652 (class 0 OID 0)
-- Dependencies: 221
-- Name: appointment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;


--
-- TOC entry 224 (class 1259 OID 25195)
-- Name: available_date; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.available_date (
    id bigint NOT NULL,
    available_date date NOT NULL,
    doctor_id bigint
);


ALTER TABLE public.available_date OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25194)
-- Name: available_date_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.available_date_id_seq OWNER TO postgres;

--
-- TOC entry 3653 (class 0 OID 0)
-- Dependencies: 223
-- Name: available_date_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.available_date_id_seq OWNED BY public.available_date.id;


--
-- TOC entry 216 (class 1259 OID 25161)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 25160)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO postgres;

--
-- TOC entry 3654 (class 0 OID 0)
-- Dependencies: 215
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 218 (class 1259 OID 25170)
-- Name: doctor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    mail character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL
);


ALTER TABLE public.doctor OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25169)
-- Name: doctor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doctor_id_seq OWNER TO postgres;

--
-- TOC entry 3655 (class 0 OID 0)
-- Dependencies: 217
-- Name: doctor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doctor_id_seq OWNED BY public.doctor.id;


--
-- TOC entry 226 (class 1259 OID 25202)
-- Name: vaccine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    protection_finish_date date NOT NULL,
    protection_start_date date NOT NULL,
    animal_id bigint
);


ALTER TABLE public.vaccine OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25201)
-- Name: vaccine_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vaccine_id_seq OWNER TO postgres;

--
-- TOC entry 3656 (class 0 OID 0)
-- Dependencies: 225
-- Name: vaccine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccine_id_seq OWNED BY public.vaccine.id;


--
-- TOC entry 3470 (class 2604 OID 25182)
-- Name: animal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);


--
-- TOC entry 3471 (class 2604 OID 25191)
-- Name: appointment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);


--
-- TOC entry 3472 (class 2604 OID 25198)
-- Name: available_date id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date ALTER COLUMN id SET DEFAULT nextval('public.available_date_id_seq'::regclass);


--
-- TOC entry 3468 (class 2604 OID 25164)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 3469 (class 2604 OID 25173)
-- Name: doctor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor ALTER COLUMN id SET DEFAULT nextval('public.doctor_id_seq'::regclass);


--
-- TOC entry 3473 (class 2604 OID 25205)
-- Name: vaccine id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine ALTER COLUMN id SET DEFAULT nextval('public.vaccine_id_seq'::regclass);


--
-- TOC entry 3639 (class 0 OID 25179)
-- Dependencies: 220
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) FROM stdin;
9	2	Kahverengi, Beyaz, Siyah	2021-04-01	Female	Emotion	Tekir	7
11	0	Açık kahverengi	2020-06-01	Male	Lucky	Golden Retriever	9
12	2	Kahverengi, Beyaz	2022-03-01	Female	Daisy	Jack Russell Terrier	10
14	0	Beyaz	2023-05-01	Male	Pamuk	Tekir	11
13	0	Kahverengi, Beyaz	2021-12-01	Female	Duman	Siyam	7
10	3	Beyaz	2020-02-01	Male	Nature	Van	10
\.


--
-- TOC entry 3641 (class 0 OID 25188)
-- Dependencies: 222
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointment (id, appointment_date, animal_id, doctor_id) FROM stdin;
8	2024-01-24 10:00:00	10	5
9	2024-01-24 13:00:00	12	5
10	2024-01-26 14:00:00	9	5
11	2024-01-10 14:00:00	12	8
12	2024-01-15 15:00:00	11	8
\.


--
-- TOC entry 3643 (class 0 OID 25195)
-- Dependencies: 224
-- Data for Name: available_date; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.available_date (id, available_date, doctor_id) FROM stdin;
27	2024-01-10	8
28	2024-01-15	8
29	2024-01-20	9
30	2024-01-22	9
31	2024-01-07	7
32	2024-01-08	7
33	2024-01-15	6
34	2024-01-19	6
35	2024-01-24	5
36	2024-01-26	5
\.


--
-- TOC entry 3635 (class 0 OID 25161)
-- Dependencies: 216
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, address, city, mail, name, phone) FROM stdin;
7	Ümraniye	İstanbul	email_customer_1@patika.dev	Hatice Hanım	1111 222 33 44
8	Avcılar	İstanbul	email_customer_2@patika.dev	Ekin Hanım	2222 333 44 55
9	Ataşehir	İstanbul	email_customer_3@patika.dev	Ömer Bey	3333 444 55 66
10	Beykoz	İstanbul	email_customer_4@patika.dev	Zeki Bey	4444 555 66 77
11	Sarıyer	İstanbul	email_customer_5@patika.dev	Melda Hanım	2222 444 11 22
\.


--
-- TOC entry 3637 (class 0 OID 25170)
-- Dependencies: 218
-- Data for Name: doctor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctor (id, address, city, mail, name, phone) FROM stdin;
5	Şişli	İstanbul	email_doctor_1@patika.dev	Dr. Seda Hanım	0000 123 45 67
6	Kadıköy	İstanbul	email_doctor_2@patika.dev	Dr. Zeynep Hanım	0000 234 56 78
7	Bakırköy	İstanbul	email_doctor_3@patika.dev	Dr. Ahmet Bey	0000 345 67 89
8	Beşiktaş	İstanbul	email_doctor_4@patika.dev	Dr. İbrahim Bey	0000 111 22 33
9	Üsküdar	İstanbul	email_doctor_5@patika.dev	Dr. Esra Hanım	0000 222 33 44
\.


--
-- TOC entry 3645 (class 0 OID 25202)
-- Dependencies: 226
-- Data for Name: vaccine; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) FROM stdin;
9	123	Kuduz	2024-03-20	2023-12-10	11
10	124	Kuduz	2023-12-15	2023-08-15	12
11	125	Corona	2024-05-05	2023-12-05	9
12	126	Corona	2024-05-01	2023-12-01	10
13	127	Corona	2023-11-20	2023-06-20	13
14	128	Corona	2024-05-02	2023-12-02	13
\.


--
-- TOC entry 3657 (class 0 OID 0)
-- Dependencies: 219
-- Name: animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_id_seq', 14, true);


--
-- TOC entry 3658 (class 0 OID 0)
-- Dependencies: 221
-- Name: appointment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointment_id_seq', 12, true);


--
-- TOC entry 3659 (class 0 OID 0)
-- Dependencies: 223
-- Name: available_date_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.available_date_id_seq', 36, true);


--
-- TOC entry 3660 (class 0 OID 0)
-- Dependencies: 215
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 11, true);


--
-- TOC entry 3661 (class 0 OID 0)
-- Dependencies: 217
-- Name: doctor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctor_id_seq', 9, true);


--
-- TOC entry 3662 (class 0 OID 0)
-- Dependencies: 225
-- Name: vaccine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccine_id_seq', 14, true);


--
-- TOC entry 3479 (class 2606 OID 25186)
-- Name: animal animal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- TOC entry 3481 (class 2606 OID 25193)
-- Name: appointment appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);


--
-- TOC entry 3483 (class 2606 OID 25200)
-- Name: available_date available_date_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (id);


--
-- TOC entry 3475 (class 2606 OID 25168)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 3477 (class 2606 OID 25177)
-- Name: doctor doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);


--
-- TOC entry 3485 (class 2606 OID 25209)
-- Name: vaccine vaccine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);


--
-- TOC entry 3487 (class 2606 OID 25215)
-- Name: appointment fk2kkeptdxfuextg5ch7xp3ytie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);


--
-- TOC entry 3486 (class 2606 OID 25210)
-- Name: animal fk6pvxm5gfjqxclb651be9unswe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- TOC entry 3489 (class 2606 OID 25225)
-- Name: available_date fkk0d6pu1wxarsoou0x2e1cc2on; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);


--
-- TOC entry 3490 (class 2606 OID 25244)
-- Name: vaccine fkne3kmh8y5pcyxwl4u2w9prw6j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);


--
-- TOC entry 3488 (class 2606 OID 25220)
-- Name: appointment fkoeb98n82eph1dx43v3y2bcmsl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);


-- Completed on 2023-12-17 21:07:51 +03

--
-- PostgreSQL database dump complete
--

