--GENERATED from PGADMIN
----------------------------

-- Table: public.domain_event_entry

-- DROP TABLE public.domain_event_entry;

CREATE TABLE public.domain_event_entry
(
    global_index bigint NOT NULL,
    event_identifier character varying(255) COLLATE pg_catalog."default" NOT NULL,
    meta_data oid,
    payload oid NOT NULL,
    payload_revision character varying(255) COLLATE pg_catalog."default",
    payload_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    time_stamp character varying(255) COLLATE pg_catalog."default" NOT NULL,
    aggregate_identifier character varying(255) COLLATE pg_catalog."default" NOT NULL,
    sequence_number bigint NOT NULL,
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT domain_event_entry_pkey PRIMARY KEY (global_index),
    CONSTRAINT uk8s1f994p4la2ipb13me2xqm1w UNIQUE (aggregate_identifier, sequence_number),
    CONSTRAINT uk_fwe6lsa8bfo6hyas6ud3m8c7x UNIQUE (event_identifier)
)

TABLESPACE pg_default;

ALTER TABLE public.domain_event_entry
    OWNER to pg;