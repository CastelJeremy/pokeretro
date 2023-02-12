import ICharacter, { CharacterGender } from './models/ICharacter';
import RequestHandler from './RequestHandler';

class CharacterService extends RequestHandler {
    constructor() {
        super('http://localhost:8081');
    }

    async create(character: ICharacter): Promise<ICharacter> {
        let gender = 'boy';

        if (character.gender == CharacterGender.Girl) gender = 'girl';

        const reqCharacter = await this.request('/trainer', {
            method: 'POST',
            body: {
                userId: character.userId,
                name: character.name,
                gender: gender,
                starter: character.starter,
            },
        });

        if (reqCharacter.status === 200) {
            const resCharacter = await reqCharacter.json();

            return resCharacter;
        }

        throw new Error();
    }

    async getAllByUserId(userId: string): Promise<ICharacter[]> {
        let characters: ICharacter[] = [];

        const reqCharacters = await this.request('/trainer/user/' + userId, {
            method: 'GET',
        });

        if (reqCharacters.status === 200) {
            const resCharacters = await reqCharacters.json();

            for (const character of resCharacters) {
                characters.push({
                    id: character.id,
                    userId: userId,
                    name: character.name,
                    gender: character.gender,
                    starter: character.starter,
                });
            }

            return characters;
        }

        throw new Error();
    }
}

export default new CharacterService();
