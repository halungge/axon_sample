-- GENERATE with PGADMIN
-----------------------------------------

-- Table: public.association_value_entry

-- DROP TABLE public.association_value_entry;

CREATE TABLE public.association_value_entry
(
    id bigint NOT NULL,
    association_key character varying(255) COLLATE pg_catalog."default" NOT NULL,
    association_value character varying(255) COLLATE pg_catalog."default",
    saga_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    saga_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT association_value_entry_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.association_value_entry
    OWNER to pg;
-- Index: idxgv5k1v2mh6frxuy5c0hgbau94

-- DROP INDEX public.idxgv5k1v2mh6frxuy5c0hgbau94;

CREATE INDEX idxgv5k1v2mh6frxuy5c0hgbau94
    ON public.association_value_entry USING btree
    (saga_id COLLATE pg_catalog."default" ASC NULLS LAST, saga_type COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: idxk45eqnxkgd8hpdn6xixn8sgft

-- DROP INDEX public.idxk45eqnxkgd8hpdn6xixn8sgft;

CREATE INDEX idxk45eqnxkgd8hpdn6xixn8sgft
    ON public.association_value_entry USING btree
    (saga_type COLLATE pg_catalog."default" ASC NULLS LAST, association_key COLLATE pg_catalog."default" ASC NULLS LAST, association_value COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;