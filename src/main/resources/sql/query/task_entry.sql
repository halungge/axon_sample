--- generated with PG ADMIN

---------------------------------

-- Table: public.task_entry

-- DROP TABLE public.task_entry;

CREATE TABLE public.task_entry
(
    id uuid NOT NULL,
    completed boolean NOT NULL,
    starred boolean NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT task_entry_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.task_entry
    OWNER to pg;