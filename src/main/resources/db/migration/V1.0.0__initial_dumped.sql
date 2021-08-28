--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)

-- SET statement_timeout = 0;
-- SET lock_timeout = 0;
-- SET idle_in_transaction_session_timeout = 0;
-- SET client_encoding = 'UTF8';
-- SET standard_conforming_strings = on;
-- SELECT pg_catalog.set_config('search_path', '', false);
-- SET check_function_bodies = false;
-- SET xmloption = content;
-- SET client_min_messages = warning;
-- SET row_security = off;

-- SET default_tablespace = '';

-- SET default_table_access_method = heap;

--
-- Name: exam; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam (
    last_modified_date timestamp without time zone,
    id bigint NOT NULL
);

--
-- Name: exam_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam_event (
    id bigint NOT NULL,
    created_date timestamp without time zone,
    description character varying(255),
    last_modified_date timestamp without time zone,
    date_time_end timestamp without time zone,
    date_time_start timestamp without time zone,
    title character varying(255)
);

--
-- Name: exam_event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.exam_event ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.exam_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: exam_part; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam_part (
    id bigint NOT NULL,
    content text,
    title character varying(255),
    type character varying(255),
    parent_id bigint,
    childs_order integer
);

--
-- Name: exam_part_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.exam_part ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.exam_part_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: exam_part_reference; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam_part_reference (
    id bigint NOT NULL,
    number integer,
    exam_part_id bigint
);

--
-- Name: exam_part_reference_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.exam_part_reference ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.exam_part_reference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: inscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inscription (
    id bigint NOT NULL,
    event_id bigint,
    postulant_name character varying(255)
);



--
-- Name: inscription_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.inscription ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.inscription_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: postulant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postulant (
    first_name character varying(255),
    last_name character varying(255),
    mother_last_name character varying(255),
    program_code character varying(255),
    upg_code character varying(255),
    name character varying(255) NOT NULL
);

--
-- Name: postulant_exam; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postulant_exam (
    id bigint NOT NULL,
    date_time_end timestamp without time zone,
    date_time_start timestamp without time zone,
    event_id bigint,
    inscription_id bigint
);

--
-- Name: postulant_exam_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.postulant_exam ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.postulant_exam_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: postulant_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postulant_question (
    last_modified_date timestamp without time zone,
    id bigint NOT NULL,
    postulant_answer_id bigint,
    postulant_exam_id bigint
);

--
-- Name: postulant_question_alternative_references; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.postulant_question_alternative_references (
    postulant_question_id bigint NOT NULL,
    alternative_references_id bigint NOT NULL
);

--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    blank_score double precision,
    correct_score double precision,
    incorrect_score double precision,
    id bigint NOT NULL,
    answer_id bigint
);

--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    name character varying(255) NOT NULL,
    password character varying(255),
    role character varying(255)
);

--
-- Name: exam_event exam_event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam_event
    ADD CONSTRAINT exam_event_pkey PRIMARY KEY (id);


--
-- Name: exam_part exam_part_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam_part
    ADD CONSTRAINT exam_part_pkey PRIMARY KEY (id);


--
-- Name: exam_part_reference exam_part_reference_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam_part_reference
    ADD CONSTRAINT exam_part_reference_pkey PRIMARY KEY (id);


--
-- Name: exam exam_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_pkey PRIMARY KEY (id);


--
-- Name: inscription inscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscription
    ADD CONSTRAINT inscription_pkey PRIMARY KEY (id);


--
-- Name: postulant_exam postulant_exam_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_exam
    ADD CONSTRAINT postulant_exam_pkey PRIMARY KEY (id);


--
-- Name: postulant postulant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant
    ADD CONSTRAINT postulant_pkey PRIMARY KEY (name);


--
-- Name: postulant_question postulant_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question
    ADD CONSTRAINT postulant_question_pkey PRIMARY KEY (id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: postulant_question_alternative_references uk_5x0pmn8gyosi0lturqrujm8y4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question_alternative_references
    ADD CONSTRAINT uk_5x0pmn8gyosi0lturqrujm8y4 UNIQUE (alternative_references_id);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (name);


--
-- Name: exam_part fk1hctvr5hvvrtrkrfblw0jpxbj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam_part
    ADD CONSTRAINT fk1hctvr5hvvrtrkrfblw0jpxbj FOREIGN KEY (parent_id) REFERENCES public.exam_part(id);


--
-- Name: postulant_exam fk1nbs3vtphjae2thb7m2lhwmsu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_exam
    ADD CONSTRAINT fk1nbs3vtphjae2thb7m2lhwmsu FOREIGN KEY (inscription_id) REFERENCES public.inscription(id);


--
-- Name: postulant fk1t3vkmp6s21suftn8gsfenc52; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant
    ADD CONSTRAINT fk1t3vkmp6s21suftn8gsfenc52 FOREIGN KEY (name) REFERENCES public.user_account(name);


--
-- Name: postulant_question_alternative_references fk6o6artk2w0wb5utmxa0c5hxfi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question_alternative_references
    ADD CONSTRAINT fk6o6artk2w0wb5utmxa0c5hxfi FOREIGN KEY (postulant_question_id) REFERENCES public.postulant_question(id);


--
-- Name: postulant_question fk7qa1rtka5rqd900gv0nrkklia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question
    ADD CONSTRAINT fk7qa1rtka5rqd900gv0nrkklia FOREIGN KEY (id) REFERENCES public.exam_part_reference(id);


--
-- Name: exam_part_reference fk8l3guu75h5tbigur9xjta7jm5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam_part_reference
    ADD CONSTRAINT fk8l3guu75h5tbigur9xjta7jm5 FOREIGN KEY (exam_part_id) REFERENCES public.exam_part(id);


--
-- Name: postulant_question fk8m596r5f6nfmo74m80cw5s56s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question
    ADD CONSTRAINT fk8m596r5f6nfmo74m80cw5s56s FOREIGN KEY (postulant_exam_id) REFERENCES public.postulant_exam(id);


--
-- Name: postulant_question_alternative_references fk9rasje04epxbqapbm205n9tfa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question_alternative_references
    ADD CONSTRAINT fk9rasje04epxbqapbm205n9tfa FOREIGN KEY (alternative_references_id) REFERENCES public.exam_part_reference(id);


--
-- Name: postulant_question fkco2b4wtw4dudndoel1putgoqu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_question
    ADD CONSTRAINT fkco2b4wtw4dudndoel1putgoqu FOREIGN KEY (postulant_answer_id) REFERENCES public.exam_part(id);


--
-- Name: inscription fkeootrlv6d09mbetjehk6k03l8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscription
    ADD CONSTRAINT fkeootrlv6d09mbetjehk6k03l8 FOREIGN KEY (postulant_name) REFERENCES public.postulant(name);


--
-- Name: question fkihvhjlpjrhsai40joyire1pd1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT fkihvhjlpjrhsai40joyire1pd1 FOREIGN KEY (answer_id) REFERENCES public.exam_part(id);


--
-- Name: postulant_exam fkihw83rmm4hgvq72xva7b77gru; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.postulant_exam
    ADD CONSTRAINT fkihw83rmm4hgvq72xva7b77gru FOREIGN KEY (event_id) REFERENCES public.exam_event(id);


--
-- Name: exam fkjjxyuablc25s767dyo422t8d4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT fkjjxyuablc25s767dyo422t8d4 FOREIGN KEY (id) REFERENCES public.exam_part(id);


--
-- Name: inscription fklm8h1bf5mbapfblsv7gy81h73; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscription
    ADD CONSTRAINT fklm8h1bf5mbapfblsv7gy81h73 FOREIGN KEY (event_id) REFERENCES public.exam_event(id);


--
-- Name: question fknlbbwvahdeby9vp1f7vuyyxmm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT fknlbbwvahdeby9vp1f7vuyyxmm FOREIGN KEY (id) REFERENCES public.exam_part(id);


--
-- PostgreSQL database dump complete
--
