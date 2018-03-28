--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6rc1
-- Dumped by pg_dump version 9.6rc1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: infiquest; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA infiquest;


ALTER SCHEMA infiquest OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

SET search_path TO infiquest,pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: answer; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE answer (
    answer_content text NOT NULL,
    answer_upvotes numeric NOT NULL,
    answer_downvotes numeric NOT NULL,
    answer_timestamp bigint NOT NULL,
    answer_id bigint NOT NULL,
    question_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE answer OWNER TO postgres;

--
-- Name: answer_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE answer_id_seq OWNER TO postgres;

--
-- Name: answer_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE answer_id_seq OWNED BY answer.answer_id;


--
-- Name: answer_question_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE answer_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE answer_question_id_seq OWNER TO postgres;

--
-- Name: answer_question_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE answer_question_id_seq OWNED BY answer.question_id;


--
-- Name: answer_user_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE answer_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE answer_user_id_seq OWNER TO postgres;

--
-- Name: answer_user_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE answer_user_id_seq OWNED BY answer.user_id;


--
-- Name: question; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE question (
    question_title text NOT NULL,
    question_description text NOT NULL,
    question_upvotes numeric,
    question_downvotes numeric,
    question_timestamp bigint NOT NULL,
    question_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE question OWNER TO postgres;

--
-- Name: questionusergroup; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE questionusergroup (
    question_id bigint NOT NULL,
    usergroup_id bigint NOT NULL,
    qug_id bigint NOT NULL
);


ALTER TABLE questionusergroup OWNER TO postgres;

--
-- Name: questionUsergroup_question_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE "questionUsergroup_question_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "questionUsergroup_question_id_seq" OWNER TO postgres;

--
-- Name: questionUsergroup_question_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE "questionUsergroup_question_id_seq" OWNED BY questionusergroup.question_id;


--
-- Name: questionUsergroup_qug_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE "questionUsergroup_qug_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "questionUsergroup_qug_id_seq" OWNER TO postgres;

--
-- Name: questionUsergroup_qug_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE "questionUsergroup_qug_id_seq" OWNED BY questionusergroup.qug_id;


--
-- Name: questionUsergroup_usergroup_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE "questionUsergroup_usergroup_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "questionUsergroup_usergroup_id_seq" OWNER TO postgres;

--
-- Name: questionUsergroup_usergroup_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE "questionUsergroup_usergroup_id_seq" OWNED BY questionusergroup.usergroup_id;


--
-- Name: question_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE question_id_seq OWNER TO postgres;

--
-- Name: question_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE question_id_seq OWNED BY question.question_id;


--
-- Name: question_user_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE question_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE question_user_id_seq OWNER TO postgres;

--
-- Name: question_user_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE question_user_id_seq OWNED BY question.user_id;


--
-- Name: questiontag; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE questiontag (
    qt_id bigint NOT NULL,
    question_id bigint NOT NULL,
    tag_id bigint NOT NULL
);


ALTER TABLE questiontag OWNER TO postgres;

--
-- Name: questiontag_qt_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE questiontag_qt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE questiontag_qt_id_seq OWNER TO postgres;

--
-- Name: questiontag_qt_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE questiontag_qt_id_seq OWNED BY questiontag.qt_id;


--
-- Name: questiontag_question_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE questiontag_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE questiontag_question_id_seq OWNER TO postgres;

--
-- Name: questiontag_question_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE questiontag_question_id_seq OWNED BY questiontag.question_id;


--
-- Name: questiontag_tag_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE questiontag_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE questiontag_tag_id_seq OWNER TO postgres;

--
-- Name: questiontag_tag_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE questiontag_tag_id_seq OWNED BY questiontag.tag_id;


--
-- Name: tags; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE tags (
    tag_name text NOT NULL,
    tag_image text,
    tag_timestamp bigint NOT NULL,
    tag_id bigint NOT NULL
);


ALTER TABLE tags OWNER TO postgres;

--
-- Name: tags_tag_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE tags_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tags_tag_id_seq OWNER TO postgres;

--
-- Name: tags_tag_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE tags_tag_id_seq OWNED BY tags.tag_id;


--
-- Name: usergroup; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE usergroup (
    usergroup_name text NOT NULL,
    usergroup_image text,
    usergroup_emailids text NOT NULL,
    usergroup_creation_timestamp bigint NOT NULL,
    usergroup_id bigint NOT NULL
);


ALTER TABLE usergroup OWNER TO postgres;

--
-- Name: usergroup_usergroup_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE usergroup_usergroup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usergroup_usergroup_id_seq OWNER TO postgres;

--
-- Name: usergroup_usergroup_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE usergroup_usergroup_id_seq OWNED BY usergroup.usergroup_id;


--
-- Name: users; Type: TABLE; Schema: infiquest; Owner: postgres
--

CREATE TABLE users (
    user_name text NOT NULL,
    user_emailid text NOT NULL,
    user_displaypic text,
    user_password character varying(256) NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: infiquest; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: infiquest; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.user_id;


--
-- Name: answer answer_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY answer ALTER COLUMN answer_id SET DEFAULT nextval('answer_id_seq'::regclass);


--
-- Name: answer question_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY answer ALTER COLUMN question_id SET DEFAULT nextval('answer_question_id_seq'::regclass);


--
-- Name: answer user_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY answer ALTER COLUMN user_id SET DEFAULT nextval('answer_user_id_seq'::regclass);


--
-- Name: question question_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY question ALTER COLUMN question_id SET DEFAULT nextval('question_id_seq'::regclass);


--
-- Name: question user_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY question ALTER COLUMN user_id SET DEFAULT nextval('question_user_id_seq'::regclass);


--
-- Name: questiontag qt_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questiontag ALTER COLUMN qt_id SET DEFAULT nextval('questiontag_qt_id_seq'::regclass);


--
-- Name: questiontag question_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questiontag ALTER COLUMN question_id SET DEFAULT nextval('questiontag_question_id_seq'::regclass);


--
-- Name: questiontag tag_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questiontag ALTER COLUMN tag_id SET DEFAULT nextval('questiontag_tag_id_seq'::regclass);


--
-- Name: questionusergroup question_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questionusergroup ALTER COLUMN question_id SET DEFAULT nextval('"questionUsergroup_question_id_seq"'::regclass);


--
-- Name: questionusergroup usergroup_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questionusergroup ALTER COLUMN usergroup_id SET DEFAULT nextval('"questionUsergroup_usergroup_id_seq"'::regclass);


--
-- Name: questionusergroup qug_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questionusergroup ALTER COLUMN qug_id SET DEFAULT nextval('"questionUsergroup_qug_id_seq"'::regclass);


--
-- Name: tags tag_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY tags ALTER COLUMN tag_id SET DEFAULT nextval('tags_tag_id_seq'::regclass);


--
-- Name: usergroup usergroup_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY usergroup ALTER COLUMN usergroup_id SET DEFAULT nextval('usergroup_usergroup_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: answer answer_pkey; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (answer_id);


--
-- Name: questionusergroup pk_questionusergroup; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questionusergroup
    ADD CONSTRAINT pk_questionusergroup PRIMARY KEY (qug_id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_pkey PRIMARY KEY (question_id);


--
-- Name: questiontag questiontag_pkey; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questiontag
    ADD CONSTRAINT questiontag_pkey PRIMARY KEY (qt_id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (tag_id);


--
-- Name: usergroup usergroup_pkey; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY usergroup
    ADD CONSTRAINT usergroup_pkey PRIMARY KEY (usergroup_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: answer fk_answer_questionid; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk_answer_questionid FOREIGN KEY (question_id) REFERENCES question(question_id);


--
-- Name: answer fk_answer_userid; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk_answer_userid FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- Name: question fk_question_userid; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY question
    ADD CONSTRAINT fk_question_userid FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- Name: questiontag fk_questiontag_question; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questiontag
    ADD CONSTRAINT fk_questiontag_question FOREIGN KEY (question_id) REFERENCES question(question_id);


--
-- Name: questiontag fk_questiontag_tag; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questiontag
    ADD CONSTRAINT fk_questiontag_tag FOREIGN KEY (tag_id) REFERENCES tags(tag_id);


--
-- Name: questionusergroup fk_questionusergroup_usergroup; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questionusergroup
    ADD CONSTRAINT fk_questionusergroup_usergroup FOREIGN KEY (usergroup_id) REFERENCES usergroup(usergroup_id);


--
-- Name: questionusergroup fk_questionusergroupid_question; Type: FK CONSTRAINT; Schema: infiquest; Owner: postgres
--

ALTER TABLE ONLY questionusergroup
    ADD CONSTRAINT fk_questionusergroupid_question FOREIGN KEY (question_id) REFERENCES question(question_id);


--
-- PostgreSQL database dump complete
--

alter role postgres set search_path = infiquest ;