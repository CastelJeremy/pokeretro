import IEgg from './models/IEgg';
import PokemonService from './PokemonService';
import RequestHandler from './RequestHandler';

class ShopService extends RequestHandler {
    constructor() {
        super('http://localhost:8083');
    }

    async refresh(): Promise<IEgg[]> {
        let offers: IEgg[] = [];

        const reqRefresh = await this.request('/shop/base/refresh', {
            method: 'PUT',
        });

        if (reqRefresh.status === 200) {
            const resEggs = await reqRefresh.json();

            for (const egg of resEggs) {
                offers.push({
                    id: egg.id,
                    time: egg.time,
                    weight: egg.weight,
                    price: egg.price,
                    startTime: null,
                    finished: null,
                    pokemon: await PokemonService.get(egg.pokemonId),
                });
            }

            return offers;
        }

        throw new Error();
    }

    async buy(characterId: string, offer: IEgg): Promise<void> {
        const reqBuy = await this.request('/shop/base/buy/' + characterId, {
            method: 'POST',
            body: {
                id: offer.id,
            },
        });

        if (reqBuy.status === 200) {
            return;
        }

        throw new Error();
    }

    async sell(characterId: string, egg: IEgg): Promise<void> {
        const reqBuy = await this.request('/shop/base/sell/' + characterId, {
            method: 'POST',
            body: {
                id: egg.id,
                price: egg.price,
            },
        });

        if (reqBuy.status === 200) {
            return;
        }

        throw new Error();
    }
}

export default new ShopService();
