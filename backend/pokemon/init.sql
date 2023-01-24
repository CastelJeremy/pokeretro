CREATE TABLE public.types (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE public.capacities (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    category VARCHAR(32) NOT NULL,
    power INT NOT NULL,
    accuracy INT NOT NULL,
    pp INT NOT NULL,
    id_type INT NOT NULL,
    FOREIGN KEY (id_type) REFERENCES types(id)
);

CREATE TABLE public.pokemons (
    id INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    hp INT NOT NULL,
    attack INT NOT NULL,
    defense INT NOT NULL,
    special_attack INT NOT NULL,
    special_defense INT NOT NULL,
    speed INT NOT NULL,
    evolution_level INT DEFAULT NULL,
    id_evolution INT DEFAULT NULL,
    FOREIGN KEY (id_evolution) REFERENCES pokemons(id)
);

CREATE TABLE public.pokemon_type (
    id_pokemon INT NOT NULL,
    id_type INT NOT NULL,
    PRIMARY KEY (id_pokemon, id_type),
    FOREIGN KEY (id_pokemon) REFERENCES pokemons(id),
    FOREIGN KEY (id_type) REFERENCES types(id)
);

CREATE TABLE public.pokemon_capacity (
    id_pokemon INT NOT NULL,
    id_capacity INT NOT NULL,
    level INT NOT NULL,
    PRIMARY KEY (id_pokemon, id_capacity),
    FOREIGN KEY (id_pokemon) REFERENCES pokemons(id),
    FOREIGN KEY (id_capacity) REFERENCES capacities(id)
);

INSERT INTO types VALUES (1, 'normal'), (2, 'fire'), (3, 'water'), (4, 'electric'), (5, 'grass'), (6, 'ice'), (7, 'fighting'), (8, 'poison'), (9, 'ground'), (10, 'flying'), (11, 'psychic'), (12, 'bug'), (13, 'rock'), (14, 'ghost'), (15, 'dragon'), (16, 'dark'), (17, 'steel'), (18, 'fairy');
INSERT INTO capacities VALUES (1, 'growl', 'status', 0, 100, 40, 1), (2, 'tackle', 'physical', 40, 100, 35, 1);
INSERT INTO pokemons VALUES (1, 'bulbasaur', 45, 49, 49, 65, 65, 45, 16, 2), (2, 'ivysaur', 60, 62, 63, 80, 80, 60, 32, 3), (3, 'venusaur', 80, 82, 83, 100, 100, 80, null, null);
INSERT INTO pokemon_type VALUES (1, 5), (1, 8);
INSERT INTO pokemon_capacity VALUES (1, 1, 1), (1, 2, 1);