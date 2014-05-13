--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2014-04-25 21:58:36 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 178 (class 3079 OID 11756)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1987 (class 0 OID 0)
-- Dependencies: 178
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 16515)
-- Name: poll_answers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE poll_answers (
    id bigint NOT NULL,
    answer text,
    poll_question_id bigint
);


ALTER TABLE public.poll_answers OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16513)
-- Name: poll_answers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE poll_answers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poll_answers_id_seq OWNER TO postgres;

--
-- TOC entry 1988 (class 0 OID 0)
-- Dependencies: 174
-- Name: poll_answers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE poll_answers_id_seq OWNED BY poll_answers.id;


--
-- TOC entry 177 (class 1259 OID 16547)
-- Name: poll_fillings; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE poll_fillings (
    id bigint NOT NULL,
    poll_question_id bigint,
    poll_answers_id bigint
);


ALTER TABLE public.poll_fillings OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16545)
-- Name: poll_fillings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE poll_fillings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poll_fillings_id_seq OWNER TO postgres;

--
-- TOC entry 1989 (class 0 OID 0)
-- Dependencies: 176
-- Name: poll_fillings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE poll_fillings_id_seq OWNED BY poll_fillings.id;


--
-- TOC entry 173 (class 1259 OID 16499)
-- Name: poll_questions; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE poll_questions (
    id bigint NOT NULL,
    poll_id bigint,
    poll_question text,
    multiple boolean
);


ALTER TABLE public.poll_questions OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16497)
-- Name: poll_questions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE poll_questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poll_questions_id_seq OWNER TO postgres;

--
-- TOC entry 1990 (class 0 OID 0)
-- Dependencies: 172
-- Name: poll_questions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE poll_questions_id_seq OWNED BY poll_questions.id;


--
-- TOC entry 171 (class 1259 OID 16488)
-- Name: polls; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE polls (
    id bigint NOT NULL,
    poll_title text
);


ALTER TABLE public.polls OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16486)
-- Name: polls_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE polls_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.polls_id_seq OWNER TO postgres;

--
-- TOC entry 1991 (class 0 OID 0)
-- Dependencies: 170
-- Name: polls_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE polls_id_seq OWNED BY polls.id;


--
-- TOC entry 1850 (class 2604 OID 16518)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_answers ALTER COLUMN id SET DEFAULT nextval('poll_answers_id_seq'::regclass);


--
-- TOC entry 1851 (class 2604 OID 16550)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_fillings ALTER COLUMN id SET DEFAULT nextval('poll_fillings_id_seq'::regclass);


--
-- TOC entry 1849 (class 2604 OID 16502)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_questions ALTER COLUMN id SET DEFAULT nextval('poll_questions_id_seq'::regclass);


--
-- TOC entry 1848 (class 2604 OID 16491)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY polls ALTER COLUMN id SET DEFAULT nextval('polls_id_seq'::regclass);


--
-- TOC entry 1977 (class 0 OID 16515)
-- Dependencies: 175
-- Data for Name: poll_answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY poll_answers (id, answer, poll_question_id) FROM stdin;
\.


--
-- TOC entry 1992 (class 0 OID 0)
-- Dependencies: 174
-- Name: poll_answers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('poll_answers_id_seq', 1, false);


--
-- TOC entry 1979 (class 0 OID 16547)
-- Dependencies: 177
-- Data for Name: poll_fillings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY poll_fillings (id, poll_question_id, poll_answers_id) FROM stdin;
\.


--
-- TOC entry 1993 (class 0 OID 0)
-- Dependencies: 176
-- Name: poll_fillings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('poll_fillings_id_seq', 1, false);





--
-- TOC entry 1994 (class 0 OID 0)
-- Dependencies: 172
-- Name: poll_questions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('poll_questions_id_seq', 3, true);





--
-- TOC entry 1995 (class 0 OID 0)
-- Dependencies: 170
-- Name: polls_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('polls_id_seq', 2, true);


--
-- TOC entry 1858 (class 2606 OID 16520)
-- Name: poll_answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY poll_answers
    ADD CONSTRAINT poll_answers_pkey PRIMARY KEY (id);


--
-- TOC entry 1860 (class 2606 OID 16552)
-- Name: poll_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY poll_fillings
    ADD CONSTRAINT poll_id_pk PRIMARY KEY (id);


--
-- TOC entry 1855 (class 2606 OID 16507)
-- Name: poll_questions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY poll_questions
    ADD CONSTRAINT poll_questions_pkey PRIMARY KEY (id);


--
-- TOC entry 1853 (class 2606 OID 16496)
-- Name: polls_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY polls
    ADD CONSTRAINT polls_pkey PRIMARY KEY (id);


--
-- TOC entry 1856 (class 1259 OID 16544)
-- Name: fki_poll_question_fk_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_poll_question_fk_id ON poll_answers USING btree (poll_question_id);


--
-- TOC entry 1864 (class 2606 OID 16558)
-- Name: poll_answer_fk_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_fillings
    ADD CONSTRAINT poll_answer_fk_id FOREIGN KEY (poll_answers_id) REFERENCES poll_answers(id);


--
-- TOC entry 1862 (class 2606 OID 16539)
-- Name: poll_question_fk_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_answers
    ADD CONSTRAINT poll_question_fk_id FOREIGN KEY (poll_question_id) REFERENCES poll_questions(id);


--
-- TOC entry 1863 (class 2606 OID 16553)
-- Name: poll_question_fk_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_fillings
    ADD CONSTRAINT poll_question_fk_id FOREIGN KEY (poll_question_id) REFERENCES poll_questions(id);


--
-- TOC entry 1861 (class 2606 OID 16508)
-- Name: poll_questions_poll_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY poll_questions
    ADD CONSTRAINT poll_questions_poll_id_fkey FOREIGN KEY (poll_id) REFERENCES polls(id);


--
-- TOC entry 1986 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-04-25 21:58:36 CEST

--
-- PostgreSQL database dump complete
--

