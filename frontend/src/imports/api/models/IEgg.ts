import IPokemon from './IPokemon';

interface IEgg {
    id: number;
    time: number;
    weight: number;
    price: number;
    startTime: number;
    finished: boolean;
    pokemon: IPokemon;
}

export default IEgg;
