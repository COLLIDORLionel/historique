CREATE TABLE IF NOT EXISTS public.statut
(
    id bigint NOT NULL DEFAULT nextval('statut_id_seq'::regclass),
    nom character varying(10),
    CONSTRAINT statut_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tache
(
    numero character varying(11) NOT NULL,
    com_1n character varying(2),
    date_1n date,
    CONSTRAINT tache_pkey PRIMARY KEY (numero)
);

CREATE TABLE IF NOT EXCISTS public.tache_statuts
(
    id bigint NOT NULL DEFAULT nextval('tache_statuts_id_seq'::regclass),
    date_statut date NOT NULL,
    statut_id bigint,
    tache_numero character varying(11),
    CONSTRAINT tache_statuts_pkey PRIMARY KEY (id),
    CONSTRAINT uk_tache_statut_date UNIQUE (tache_numero, statut_id, date_statut),
    CONSTRAINT statut_fk FOREIGN KEY (statut_id)
        REFERENCES public.statut (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tache_fk FOREIGN KEY (tache_numero)
        REFERENCES public.tache (numero) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);