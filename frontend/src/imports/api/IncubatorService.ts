import IEgg from './models/IEgg';
import PokemonService from './PokemonService';
import RequestHandler from './RequestHandler';

class IncubatorService extends RequestHandler {
    constructor() {
        super('http://localhost:8084');
    }

    async getAllByCharacterId(characterId: string): Promise<IEgg[]> {
        let eggs: IEgg[] = [];

        const reqEggs = await this.request('/incubator/' + characterId, {
            method: 'GET',
        });

        if (reqEggs.status === 200) {
            const resEggs = await reqEggs.json();

            for (const egg of resEggs) {
                eggs.push({
                    id: egg.id,
                    time: egg.time,
                    weight: egg.weight,
                    price: null,
                    startTime: egg.incubationStartTime,
                    finished: egg.incubationFinished,
                    pokemon: await PokemonService.get(egg.pokemonId),
                });
            }

            return eggs;
        }

        throw new Error();
    }

    async place(characterId: string, egg: IEgg): Promise<void> {
        const reqPlace = await this.request('/incubator/place/' + characterId, {
            method: 'POST',
            body: {
                id: egg.id,
                time: egg.time,
                weight: egg.weight,
                pokemonId: egg.pokemon.id,
            },
        });

        if (reqPlace.status === 200) {
            return;
        }

        throw new Error();
    }

    async hatch(characterId: string, egg: IEgg): Promise<IEgg[]> {
        let eggs: IEgg[] = [];

        const reqEggs = await this.request('/incubator/hatch/' + characterId, {
            method: 'POST',
            body: {
                id: egg.id,
            },
        });

        if (reqEggs.status === 200) {
            const resEggs = await reqEggs.json();

            for (const egg of resEggs) {
                eggs.push({
                    id: egg.id,
                    time: egg.time,
                    weight: egg.weight,
                    price: null,
                    startTime: egg.incubationStartTime,
                    finished: egg.incubationFinished,
                    pokemon: await PokemonService.get(egg.pokemonId),
                });
            }

            return eggs;
        }

        throw new Error();
    }
}

export default new IncubatorService();
