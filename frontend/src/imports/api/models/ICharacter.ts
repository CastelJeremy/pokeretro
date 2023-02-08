enum CharacterGender {
    'Boy',
    'Girl',
}

interface ICharacter {
    id: string;
    userId: string;
    name: string;
    gender: CharacterGender;
    starter: number;
}

export default ICharacter;
export { CharacterGender };
