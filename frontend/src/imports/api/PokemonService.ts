import IPokemon from './models/IPokemon';
import RequestHandler from './RequestHandler';

class PokemonService extends RequestHandler {
    constructor() {
        super('http://localhost:8085');
    }

    async get(id: number): Promise<IPokemon> {
        const reqPokemon = await this.request('/pokemons/' + id, {
            method: 'GET',
        });

        if (reqPokemon.status === 200) {
            const resPokemon = await reqPokemon.json();

            return {
                id: resPokemon.id,
                name: resPokemon.name,
            };
        }

        throw new Error();
    }
}

export default new PokemonService();
