CREATE TABLE public.eggs (
    id uuid PRIMARY KEY,
    id_pokemon INT NOT NULL,
    id_trainer uuid NOT NULL,
    "time" INT NOT NULL,
    weight INT NOT NULL,
    price INT NOT NULL
);

CREATE TABLE public.moneys (
    id_trainer uuid PRIMARY KEY,
    amount InT NOT NULL
);
