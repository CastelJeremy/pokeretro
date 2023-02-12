CREATE TABLE IF NOT EXISTS public.users
(
    id uuid NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.token_blacklist
(
    token character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT token_blacklist_pkey PRIMARY KEY (token)
);