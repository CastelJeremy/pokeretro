CREATE TABLE IF NOT EXISTS public.eggs
(
    id bigint NOT NULL,
    id_pokemon bigint,
    "time" integer,
    weight integer,
    CONSTRAINT eggs_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.inventories
(
    id_trainer uuid NOT NULL,
    money integer NOT NULL,
    CONSTRAINT inventories_pkey PRIMARY KEY (id_trainer)
);

CREATE TABLE IF NOT EXISTS public.trainers
(
    id uuid NOT NULL,
    gender character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT trainers_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.inventories_eggs
(
    inventory_id_trainer uuid NOT NULL,
    eggs_id bigint NOT NULL,
    CONSTRAINT uk_l93x5g8wb1lm8ba91jd0vacod UNIQUE (eggs_id),
    CONSTRAINT fkcxg2t1p2bj6dailcgnjhom057 FOREIGN KEY (inventory_id_trainer)
        REFERENCES public.inventories (id_trainer) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkdt044u08v53dm1rm6yr8hyvqp FOREIGN KEY (eggs_id)
        REFERENCES public.eggs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);