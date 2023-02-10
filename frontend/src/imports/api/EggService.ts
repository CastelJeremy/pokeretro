import IEgg from './models/IEgg';
import PokemonService from './PokemonService';
import RequestHandler from './RequestHandler';

class EggService extends RequestHandler {
    constructor() {
        super('http://localhost:8082');
    }

    async getAllByCharacterId(characterId: string): Promise<IEgg[]> {
        let eggs: IEgg[] = [];

        const reqEggs = await this.request('/egg/' + characterId, {
            method: 'GET',
        });

        if (reqEggs.status === 200) {
            const resEggs = await reqEggs.json();

            for (const egg of resEggs) {
                eggs.push({
                    id: egg.id,
                    time: egg.time,
                    weight: egg.weight,
                    price: egg.price,
                    startTime: null,
                    finished: null,
                    pokemon: await PokemonService.get(egg.pokemonId),
                });
            }

            return eggs;
        }

        throw new Error();
    }

    async delete(characterId: string, egg: IEgg): Promise<boolean> {
        const reqDel = await this.request('/egg/' + characterId, {
            method: 'DELETE',
            body: {
                id: egg.id,
            },
        });

        return reqDel.status === 200;
    }
}

export default new EggService();
