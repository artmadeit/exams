--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)

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

--
-- Data for Name: exam_part; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (1, NULL, 'Examen 1', 'EXAM', NULL, NULL);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (2, NULL, 'CAPACIDADES COMUNICATIVAS', 'SECTION', 1, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (55, NULL, 'CAPACIDADES LOGICO MATEMATICAS', 'SECTION', 1, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (81, NULL, 'CAPACIDADES INVESTIGATIVAS', 'SECTION', 1, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (107, NULL, 'PENSAMIENTO CRITICO', 'SECTION', 1, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (3, 'El desarrollo...', 'Texto 1, examen:1', 'TEXT', 2, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (29, 'Los ingresos ...', 'Texto 2, examen: 1', 'TEXT', 2, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (4, 'P1-E1', NULL, 'QUESTION', 3, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (9, 'P2-E1', NULL, 'QUESTION', 3, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (14, 'P3-E1', NULL, 'QUESTION', 3, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (19, 'P4-E1', NULL, 'QUESTION', 3, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (24, 'P5-E1', NULL, 'QUESTION', 3, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (5, 'alternativa A', NULL, 'ALTERNATIVE', 4, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (6, 'alternativa B', NULL, 'ALTERNATIVE', 4, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (7, 'alternativa C', NULL, 'ALTERNATIVE', 4, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (8, 'alternativa D', NULL, 'ALTERNATIVE', 4, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (10, 'alternativa A', NULL, 'ALTERNATIVE', 9, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (11, 'alternativa B', NULL, 'ALTERNATIVE', 9, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (12, 'alternativa C', NULL, 'ALTERNATIVE', 9, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (13, 'alternativa D', NULL, 'ALTERNATIVE', 9, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (15, 'alternativa A', NULL, 'ALTERNATIVE', 14, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (16, 'alternativa B', NULL, 'ALTERNATIVE', 14, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (17, 'alternativa C', NULL, 'ALTERNATIVE', 14, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (18, 'alternativa D', NULL, 'ALTERNATIVE', 14, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (20, 'alternativa A', NULL, 'ALTERNATIVE', 19, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (21, 'alternativa B', NULL, 'ALTERNATIVE', 19, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (22, 'alternativa C', NULL, 'ALTERNATIVE', 19, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (23, 'alternativa D', NULL, 'ALTERNATIVE', 19, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (25, 'alternativa A', NULL, 'ALTERNATIVE', 24, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (26, 'alternativa B', NULL, 'ALTERNATIVE', 24, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (27, 'alternativa C', NULL, 'ALTERNATIVE', 24, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (28, 'alternativa D', NULL, 'ALTERNATIVE', 24, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (30, 'P6-E1', NULL, 'QUESTION', 29, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (35, 'P7-E1', NULL, 'QUESTION', 29, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (40, 'P8-E1', NULL, 'QUESTION', 29, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (45, 'P9-E1', NULL, 'QUESTION', 29, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (50, 'P10-E1', NULL, 'QUESTION', 29, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (31, 'alternativa A', NULL, 'ALTERNATIVE', 30, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (32, 'alternativa B', NULL, 'ALTERNATIVE', 30, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (33, 'alternativa C', NULL, 'ALTERNATIVE', 30, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (34, 'alternativa D', NULL, 'ALTERNATIVE', 30, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (36, 'alternativa A', NULL, 'ALTERNATIVE', 35, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (37, 'alternativa B', NULL, 'ALTERNATIVE', 35, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (38, 'alternativa C', NULL, 'ALTERNATIVE', 35, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (39, 'alternativa D', NULL, 'ALTERNATIVE', 35, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (41, 'alternativa A', NULL, 'ALTERNATIVE', 40, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (42, 'alternativa B', NULL, 'ALTERNATIVE', 40, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (43, 'alternativa C', NULL, 'ALTERNATIVE', 40, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (44, 'alternativa D', NULL, 'ALTERNATIVE', 40, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (46, 'alternativa A', NULL, 'ALTERNATIVE', 45, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (47, 'alternativa B', NULL, 'ALTERNATIVE', 45, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (48, 'alternativa C', NULL, 'ALTERNATIVE', 45, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (49, 'alternativa D', NULL, 'ALTERNATIVE', 45, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (51, 'alternativa A', NULL, 'ALTERNATIVE', 50, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (52, 'alternativa B', NULL, 'ALTERNATIVE', 50, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (53, 'alternativa C', NULL, 'ALTERNATIVE', 50, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (54, 'alternativa D', NULL, 'ALTERNATIVE', 50, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (56, 'P11-E1', NULL, 'QUESTION', 55, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (61, 'P12-E1', NULL, 'QUESTION', 55, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (66, 'P13-E1', NULL, 'QUESTION', 55, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (71, 'P14-E1', NULL, 'QUESTION', 55, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (76, 'P15-E1', NULL, 'QUESTION', 55, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (57, 'alternativa A', NULL, 'ALTERNATIVE', 56, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (58, 'alternativa B', NULL, 'ALTERNATIVE', 56, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (59, 'alternativa C', NULL, 'ALTERNATIVE', 56, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (60, 'alternativa D', NULL, 'ALTERNATIVE', 56, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (62, 'alternativa A', NULL, 'ALTERNATIVE', 61, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (63, 'alternativa B', NULL, 'ALTERNATIVE', 61, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (64, 'alternativa C', NULL, 'ALTERNATIVE', 61, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (65, 'alternativa D', NULL, 'ALTERNATIVE', 61, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (67, 'alternativa A', NULL, 'ALTERNATIVE', 66, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (68, 'alternativa B', NULL, 'ALTERNATIVE', 66, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (69, 'alternativa C', NULL, 'ALTERNATIVE', 66, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (70, 'alternativa D', NULL, 'ALTERNATIVE', 66, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (72, 'alternativa A', NULL, 'ALTERNATIVE', 71, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (73, 'alternativa B', NULL, 'ALTERNATIVE', 71, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (74, 'alternativa C', NULL, 'ALTERNATIVE', 71, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (75, 'alternativa D', NULL, 'ALTERNATIVE', 71, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (77, 'alternativa A', NULL, 'ALTERNATIVE', 76, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (78, 'alternativa B', NULL, 'ALTERNATIVE', 76, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (79, 'alternativa C', NULL, 'ALTERNATIVE', 76, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (80, 'alternativa D', NULL, 'ALTERNATIVE', 76, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (82, 'P16-E1', NULL, 'QUESTION', 81, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (87, 'P17-E1', NULL, 'QUESTION', 81, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (92, 'P18-E1', NULL, 'QUESTION', 81, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (97, 'P19-E1', NULL, 'QUESTION', 81, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (102, 'P20-E1', NULL, 'QUESTION', 81, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (83, 'alternativa A', NULL, 'ALTERNATIVE', 82, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (84, 'alternativa B', NULL, 'ALTERNATIVE', 82, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (85, 'alternativa C', NULL, 'ALTERNATIVE', 82, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (86, 'alternativa D', NULL, 'ALTERNATIVE', 82, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (88, 'alternativa A', NULL, 'ALTERNATIVE', 87, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (89, 'alternativa B', NULL, 'ALTERNATIVE', 87, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (90, 'alternativa C', NULL, 'ALTERNATIVE', 87, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (91, 'alternativa D', NULL, 'ALTERNATIVE', 87, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (93, 'alternativa A', NULL, 'ALTERNATIVE', 92, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (94, 'alternativa B', NULL, 'ALTERNATIVE', 92, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (95, 'alternativa C', NULL, 'ALTERNATIVE', 92, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (96, 'alternativa D', NULL, 'ALTERNATIVE', 92, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (98, 'alternativa A', NULL, 'ALTERNATIVE', 97, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (99, 'alternativa B', NULL, 'ALTERNATIVE', 97, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (100, 'alternativa C', NULL, 'ALTERNATIVE', 97, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (101, 'alternativa D', NULL, 'ALTERNATIVE', 97, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (103, 'alternativa A', NULL, 'ALTERNATIVE', 102, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (104, 'alternativa B', NULL, 'ALTERNATIVE', 102, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (105, 'alternativa C', NULL, 'ALTERNATIVE', 102, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (106, 'alternativa D', NULL, 'ALTERNATIVE', 102, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (108, 'P21-E1', NULL, 'QUESTION', 107, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (113, 'P22-E1', NULL, 'QUESTION', 107, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (118, 'P23-E1', NULL, 'QUESTION', 107, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (123, 'P24-E1', NULL, 'QUESTION', 107, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (128, 'P25-E1', NULL, 'QUESTION', 107, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (109, 'alternativa A', NULL, 'ALTERNATIVE', 108, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (110, 'alternativa B', NULL, 'ALTERNATIVE', 108, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (111, 'alternativa C', NULL, 'ALTERNATIVE', 108, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (112, 'alternativa D', NULL, 'ALTERNATIVE', 108, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (114, 'alternativa A', NULL, 'ALTERNATIVE', 113, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (115, 'alternativa B', NULL, 'ALTERNATIVE', 113, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (116, 'alternativa C', NULL, 'ALTERNATIVE', 113, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (117, 'alternativa D', NULL, 'ALTERNATIVE', 113, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (119, 'alternativa A', NULL, 'ALTERNATIVE', 118, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (120, 'alternativa B', NULL, 'ALTERNATIVE', 118, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (121, 'alternativa C', NULL, 'ALTERNATIVE', 118, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (122, 'alternativa D', NULL, 'ALTERNATIVE', 118, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (124, 'alternativa A', NULL, 'ALTERNATIVE', 123, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (125, 'alternativa B', NULL, 'ALTERNATIVE', 123, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (126, 'alternativa C', NULL, 'ALTERNATIVE', 123, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (127, 'alternativa D', NULL, 'ALTERNATIVE', 123, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (129, 'alternativa A', NULL, 'ALTERNATIVE', 128, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (130, 'alternativa B', NULL, 'ALTERNATIVE', 128, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (131, 'alternativa C', NULL, 'ALTERNATIVE', 128, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (132, 'alternativa D', NULL, 'ALTERNATIVE', 128, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (133, NULL, 'Examen 2', 'EXAM', NULL, NULL);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (134, NULL, 'CAPACIDADES COMUNICATIVAS', 'SECTION', 133, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (187, NULL, 'CAPACIDADES LOGICO MATEMATICAS', 'SECTION', 133, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (213, NULL, 'CAPACIDADES INVESTIGATIVAS', 'SECTION', 133, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (239, NULL, 'PENSAMIENTO CRITICO', 'SECTION', 133, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (135, 'El desarrollo...', 'Texto 1, examen:2', 'TEXT', 134, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (161, 'Los ingresos ...', 'Texto 2, examen: 2', 'TEXT', 134, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (136, 'P1-E2', NULL, 'QUESTION', 135, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (141, 'P2-E2', NULL, 'QUESTION', 135, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (146, 'P3-E2', NULL, 'QUESTION', 135, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (151, 'P4-E2', NULL, 'QUESTION', 135, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (156, 'P5-E2', NULL, 'QUESTION', 135, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (137, 'alternativa A', NULL, 'ALTERNATIVE', 136, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (138, 'alternativa B', NULL, 'ALTERNATIVE', 136, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (139, 'alternativa C', NULL, 'ALTERNATIVE', 136, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (140, 'alternativa D', NULL, 'ALTERNATIVE', 136, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (142, 'alternativa A', NULL, 'ALTERNATIVE', 141, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (143, 'alternativa B', NULL, 'ALTERNATIVE', 141, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (144, 'alternativa C', NULL, 'ALTERNATIVE', 141, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (145, 'alternativa D', NULL, 'ALTERNATIVE', 141, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (147, 'alternativa A', NULL, 'ALTERNATIVE', 146, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (148, 'alternativa B', NULL, 'ALTERNATIVE', 146, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (149, 'alternativa C', NULL, 'ALTERNATIVE', 146, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (150, 'alternativa D', NULL, 'ALTERNATIVE', 146, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (152, 'alternativa A', NULL, 'ALTERNATIVE', 151, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (153, 'alternativa B', NULL, 'ALTERNATIVE', 151, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (154, 'alternativa C', NULL, 'ALTERNATIVE', 151, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (155, 'alternativa D', NULL, 'ALTERNATIVE', 151, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (157, 'alternativa A', NULL, 'ALTERNATIVE', 156, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (158, 'alternativa B', NULL, 'ALTERNATIVE', 156, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (159, 'alternativa C', NULL, 'ALTERNATIVE', 156, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (160, 'alternativa D', NULL, 'ALTERNATIVE', 156, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (162, 'P6-E2', NULL, 'QUESTION', 161, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (167, 'P7-E2', NULL, 'QUESTION', 161, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (172, 'P8-E2', NULL, 'QUESTION', 161, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (177, 'P9-E2', NULL, 'QUESTION', 161, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (182, 'P10-E2', NULL, 'QUESTION', 161, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (163, 'alternativa A', NULL, 'ALTERNATIVE', 162, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (164, 'alternativa B', NULL, 'ALTERNATIVE', 162, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (165, 'alternativa C', NULL, 'ALTERNATIVE', 162, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (166, 'alternativa D', NULL, 'ALTERNATIVE', 162, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (168, 'alternativa A', NULL, 'ALTERNATIVE', 167, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (169, 'alternativa B', NULL, 'ALTERNATIVE', 167, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (170, 'alternativa C', NULL, 'ALTERNATIVE', 167, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (171, 'alternativa D', NULL, 'ALTERNATIVE', 167, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (173, 'alternativa A', NULL, 'ALTERNATIVE', 172, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (174, 'alternativa B', NULL, 'ALTERNATIVE', 172, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (175, 'alternativa C', NULL, 'ALTERNATIVE', 172, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (176, 'alternativa D', NULL, 'ALTERNATIVE', 172, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (178, 'alternativa A', NULL, 'ALTERNATIVE', 177, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (179, 'alternativa B', NULL, 'ALTERNATIVE', 177, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (180, 'alternativa C', NULL, 'ALTERNATIVE', 177, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (181, 'alternativa D', NULL, 'ALTERNATIVE', 177, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (183, 'alternativa A', NULL, 'ALTERNATIVE', 182, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (184, 'alternativa B', NULL, 'ALTERNATIVE', 182, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (185, 'alternativa C', NULL, 'ALTERNATIVE', 182, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (186, 'alternativa D', NULL, 'ALTERNATIVE', 182, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (188, 'P11-E2', NULL, 'QUESTION', 187, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (193, 'P12-E2', NULL, 'QUESTION', 187, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (198, 'P13-E2', NULL, 'QUESTION', 187, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (203, 'P14-E2', NULL, 'QUESTION', 187, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (208, 'P15-E2', NULL, 'QUESTION', 187, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (189, 'alternativa A', NULL, 'ALTERNATIVE', 188, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (190, 'alternativa B', NULL, 'ALTERNATIVE', 188, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (191, 'alternativa C', NULL, 'ALTERNATIVE', 188, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (192, 'alternativa D', NULL, 'ALTERNATIVE', 188, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (194, 'alternativa A', NULL, 'ALTERNATIVE', 193, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (195, 'alternativa B', NULL, 'ALTERNATIVE', 193, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (196, 'alternativa C', NULL, 'ALTERNATIVE', 193, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (197, 'alternativa D', NULL, 'ALTERNATIVE', 193, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (199, 'alternativa A', NULL, 'ALTERNATIVE', 198, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (200, 'alternativa B', NULL, 'ALTERNATIVE', 198, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (201, 'alternativa C', NULL, 'ALTERNATIVE', 198, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (202, 'alternativa D', NULL, 'ALTERNATIVE', 198, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (204, 'alternativa A', NULL, 'ALTERNATIVE', 203, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (205, 'alternativa B', NULL, 'ALTERNATIVE', 203, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (206, 'alternativa C', NULL, 'ALTERNATIVE', 203, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (207, 'alternativa D', NULL, 'ALTERNATIVE', 203, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (209, 'alternativa A', NULL, 'ALTERNATIVE', 208, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (210, 'alternativa B', NULL, 'ALTERNATIVE', 208, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (211, 'alternativa C', NULL, 'ALTERNATIVE', 208, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (212, 'alternativa D', NULL, 'ALTERNATIVE', 208, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (214, 'P16-E2', NULL, 'QUESTION', 213, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (219, 'P17-E2', NULL, 'QUESTION', 213, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (224, 'P18-E2', NULL, 'QUESTION', 213, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (229, 'P19-E2', NULL, 'QUESTION', 213, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (234, 'P20-E2', NULL, 'QUESTION', 213, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (215, 'alternativa A', NULL, 'ALTERNATIVE', 214, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (216, 'alternativa B', NULL, 'ALTERNATIVE', 214, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (217, 'alternativa C', NULL, 'ALTERNATIVE', 214, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (218, 'alternativa D', NULL, 'ALTERNATIVE', 214, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (220, 'alternativa A', NULL, 'ALTERNATIVE', 219, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (221, 'alternativa B', NULL, 'ALTERNATIVE', 219, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (222, 'alternativa C', NULL, 'ALTERNATIVE', 219, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (223, 'alternativa D', NULL, 'ALTERNATIVE', 219, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (225, 'alternativa A', NULL, 'ALTERNATIVE', 224, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (226, 'alternativa B', NULL, 'ALTERNATIVE', 224, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (227, 'alternativa C', NULL, 'ALTERNATIVE', 224, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (228, 'alternativa D', NULL, 'ALTERNATIVE', 224, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (230, 'alternativa A', NULL, 'ALTERNATIVE', 229, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (231, 'alternativa B', NULL, 'ALTERNATIVE', 229, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (232, 'alternativa C', NULL, 'ALTERNATIVE', 229, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (233, 'alternativa D', NULL, 'ALTERNATIVE', 229, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (235, 'alternativa A', NULL, 'ALTERNATIVE', 234, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (236, 'alternativa B', NULL, 'ALTERNATIVE', 234, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (237, 'alternativa C', NULL, 'ALTERNATIVE', 234, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (238, 'alternativa D', NULL, 'ALTERNATIVE', 234, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (240, 'P21-E2', NULL, 'QUESTION', 239, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (245, 'P22-E2', NULL, 'QUESTION', 239, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (250, 'P23-E2', NULL, 'QUESTION', 239, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (255, 'P24-E2', NULL, 'QUESTION', 239, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (260, 'P25-E2', NULL, 'QUESTION', 239, 4);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (241, 'alternativa A', NULL, 'ALTERNATIVE', 240, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (242, 'alternativa B', NULL, 'ALTERNATIVE', 240, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (243, 'alternativa C', NULL, 'ALTERNATIVE', 240, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (244, 'alternativa D', NULL, 'ALTERNATIVE', 240, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (246, 'alternativa A', NULL, 'ALTERNATIVE', 245, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (247, 'alternativa B', NULL, 'ALTERNATIVE', 245, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (248, 'alternativa C', NULL, 'ALTERNATIVE', 245, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (249, 'alternativa D', NULL, 'ALTERNATIVE', 245, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (251, 'alternativa A', NULL, 'ALTERNATIVE', 250, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (252, 'alternativa B', NULL, 'ALTERNATIVE', 250, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (253, 'alternativa C', NULL, 'ALTERNATIVE', 250, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (254, 'alternativa D', NULL, 'ALTERNATIVE', 250, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (256, 'alternativa A', NULL, 'ALTERNATIVE', 255, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (257, 'alternativa B', NULL, 'ALTERNATIVE', 255, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (258, 'alternativa C', NULL, 'ALTERNATIVE', 255, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (259, 'alternativa D', NULL, 'ALTERNATIVE', 255, 3);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (261, 'alternativa A', NULL, 'ALTERNATIVE', 260, 0);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (262, 'alternativa B', NULL, 'ALTERNATIVE', 260, 1);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (263, 'alternativa C', NULL, 'ALTERNATIVE', 260, 2);
INSERT INTO public.exam_part (id, content, title, type, parent_id, childs_order) VALUES (264, 'alternativa D', NULL, 'ALTERNATIVE', 260, 3);


--
-- Data for Name: exam; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.exam (last_modified_date, id) VALUES ('2021-08-20 13:32:53.420197', 1);
INSERT INTO public.exam (last_modified_date, id) VALUES ('2021-08-20 13:32:53.858804', 133);


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 4, 5);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 9, 10);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 14, 15);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 19, 20);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 24, 25);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 30, 31);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 35, 36);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 40, 41);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 45, 46);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 50, 51);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 56, 57);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 61, 62);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 66, 67);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 71, 72);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 76, 77);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 82, 83);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 87, 88);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 92, 93);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 97, 98);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 102, 103);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 108, 109);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 113, 114);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 118, 119);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 123, 124);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 128, 129);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 136, 137);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 141, 142);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 146, 147);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 151, 152);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 156, 157);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 162, 163);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 167, 168);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 172, 173);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 177, 178);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 182, 183);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 188, 189);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 193, 194);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 198, 199);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 203, 204);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 208, 209);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 214, 215);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 219, 220);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 224, 225);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 229, 230);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 234, 235);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 240, 241);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 245, 246);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 250, 251);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 255, 256);
INSERT INTO public.question (blank_score, correct_score, incorrect_score, id, answer_id) VALUES (0, 4, -1, 260, 261);


--
-- Name: exam_part_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exam_part_id_seq', 264, true);


--
-- PostgreSQL database dump complete
--

