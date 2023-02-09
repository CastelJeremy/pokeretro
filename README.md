# PokeRetro

## Table of contents
1. [What is that repo ?](#what-is-that-repo-)
2. [Authors](#authors)
3. [Technologies used](#technologies-used)
4. [How to start](#how-to-start)
5. [License](#license)

## What is that repo ?
This repository is about a project made in the engineering school of IMT Mines Alès.
It was directed by M.FAGET Morgan.

The aim is to create a Pokemon game with a micro-service software architecture.
Some features are required :
- Players can own 6 Pokemons in their team.
- Players doesn't need to capture Pokemon, there is a shop where they can buy eggs.
- Players can sell eggs.
- Eggs can be placed in an incubator. After a delay, they will hatch.
- Hatched Pokemon needs to be predefined before hatching but Pokemon stats' have to be generated after hatching.
- If a player already own 6 Pokemon when an egg hatch, he needs to swap with a teammate.

Some additionnals features are developped :
- Players can fight Pokemon
- Players can fight Bosses

## Authors
A project requirement is to make team of 3 people :
- CASTEL Jérémy
- DARDAILLON Enzo
- DELFINO Clément

## Technologies used
The game is developped in Java with the SpringBoot framework.
It also use :
- Databases :
  - NoSQL --> MongoDB
  - SQL --> PostgreSQL
- Queue --> RabbitMQ
- RestAPI --> Spring
- Build Manager --> Maven
- Application Deployment --> Docker

## How to start
First you need to install Docker and [docker-compose](https://docs.docker.com/compose/install/) on your computer.

### Windows
Run the run.bat script from the root folder of this repository.

### Linux
Run WITH SUDO the run.sh script from the root folder of this repository.

## License
DBAD Public License

[LICENSE.MD](https://github.com/CastelJeremy/pokeretro/blob/main/LICENSE.md)
