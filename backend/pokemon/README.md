# Backend-Pokemon

Everything you need to get general informations on pokemons.

## Build

**Make sure you are running java 17**

```
$ javac -version
javac 17.0.5

$ java -version
openjdk version "17.0.5" 2022-10-18
OpenJDK Runtime Environment (build 17.0.5+8-Ubuntu-2ubuntu122.04)
OpenJDK 64-Bit Server VM (build 17.0.5+8-Ubuntu-2ubuntu122.04, mixed mode, sharing)
```

If you meet the requirements build the Micro-Service with mvnw :

```
./mvnw package
```

## Run

Fire up the Micro-Service with docker-compose. The database and the app will setup on their own.

```
GET  http://localhost:8085/pokemons
GET  http://localhost:8085/pokemons/eggable
GET  http://localhost:8085/pokemons/:id
POST http://localhost:8085/pokemons/:id/generate
```