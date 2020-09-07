CREATE TABLE IF NOT EXISTS public.statut
(
    id integer NOT NULL,
    nom character varying(10)  NOT NULL,
    CONSTRAINT statut_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.tache
(
    numero character varying(11) NOT NULL,
    com_1N character varying(2),
    date_1N date,
    CONSTRAINT tache_pkey PRIMARY KEY (numero)
);
CREATE TABLE IF NOT EXISTS public.tache_statut
(
    id_tache character varying(11),
    id_statut integer,
    date date NOT NULL,
    CONSTRAINT tache_statut_pkey PRIMARY KEY (id_tache, id_statut),
    CONSTRAINT tache_fkey FOREIGN KEY (id_tache)
        REFERENCES public.tache (numero) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT statut_fkey FOREIGN KEY (id_statut)
        REFERENCES public.statut (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);