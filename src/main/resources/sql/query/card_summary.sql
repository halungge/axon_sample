--generated with PGADMIN
-------------------------

-- Table: public.card_summary

-- DROP TABLE public.card_summary;

CREATE TABLE public.card_summary
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    initial_value integer NOT NULL,
    remaining_value integer NOT NULL,
    CONSTRAINT card_summary_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.card_summary
    OWNER to pg;