-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2_snapshot20190921
-- PostgreSQL version: 11.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
-- 

-- object: public.license | type: TABLE --
-- DROP TABLE IF EXISTS public.license CASCADE;
CREATE TABLE public.license (
	id uuid NOT NULL,
	key_pair_id uuid NOT NULL,
	name text NOT NULL,
	body text NOT NULL,
	created_at bigint NOT NULL,
	signature text NOT NULL,
	CONSTRAINT pk_license PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.license OWNER TO postgres;
-- ddl-end --

-- object: public.key_pair | type: TABLE --
-- DROP TABLE IF EXISTS public.key_pair CASCADE;
CREATE TABLE public.key_pair (
	id uuid NOT NULL,
	name text NOT NULL,
	public_key text NOT NULL,
	private_key text NOT NULL,
	created_at bigint NOT NULL,
	CONSTRAINT pk_key_pair PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.key_pair OWNER TO postgres;
-- ddl-end --

-- object: idx_license_tbl_key_pair_id | type: INDEX --
-- DROP INDEX IF EXISTS public.idx_license_tbl_key_pair_id CASCADE;
CREATE INDEX idx_license_tbl_key_pair_id ON public.license
	USING hash
	(
	  key_pair_id
	);
-- ddl-end --

-- object: fk_license_tbl_key_pair_id | type: CONSTRAINT --
-- ALTER TABLE public.license DROP CONSTRAINT IF EXISTS fk_license_tbl_key_pair_id CASCADE;
ALTER TABLE public.license ADD CONSTRAINT fk_license_tbl_key_pair_id FOREIGN KEY (key_pair_id)
REFERENCES public.key_pair (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


