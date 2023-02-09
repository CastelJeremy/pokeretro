import IEgg from './models/IEgg';
import PokemonService from './PokemonService';
import RequestHandler from './RequestHandler';

class EggService extends RequestHandler {
    constructor() {
        super('http://localhost:8082');
    }

    async getAllByCharacterId(characterId: string): Promise<IEgg[]> {
        let eggs: IEgg[] = [];

        const reqEggs = await this.request('/egg?idTrainer=' + characterId, {
            method: 'GET',
        });

        if (reqEggs.status === 200) {
            const resEggs = await reqEggs.json();

            for (const egg of resEggs.eggs) {
                eggs.push({
                    id: egg.id,
                    time: egg.time,
                    weight: egg.weight,
                    pokemon: await PokemonService.get(egg.idPokemon),
                });
            }

            return eggs;
        }

        throw new Error();
    }

    async delete(characterId: string, egg: IEgg): Promise<boolean> {
        const reqDel = await this.request('/egg?idTrainer=' + characterId, {
            method: 'DELETE',
            body: {
                id: egg.id,
            },
        });

        return reqDel.status === 200;
    }
}

export default new EggService();
