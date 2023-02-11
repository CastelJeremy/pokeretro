import IPokemon from "./IPokemon";

interface IStat {
    attack: number;
    defense: number;
    hp: number;
    special: number;
    speed: number;
}

interface ITeammate {
    id: string;
    position: number;
    name: string;
    level: number;
    baseState: IStat;
    stat: IStat;
    currentStat: IStat;
    pokemon: IPokemon;
}

export default ITeammate;
