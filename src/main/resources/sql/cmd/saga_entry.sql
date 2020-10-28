-- GENERATED with PGADMIN
------------------------

-- Table: public.saga_entry

-- DROP TABLE public.saga_entry;

CREATE TABLE public.saga_entry
(
    saga_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    revision character varying(255) COLLATE pg_catalog."default",
    saga_type character varying(255) COLLATE pg_catalog."default",
    serialized_saga oid,
    CONSTRAINT saga_entry_pkey PRIMARY KEY (saga_id)
)

TABLESPACE pg_default;

ALTER TABLE public.saga_entry
    OWNER to pg;