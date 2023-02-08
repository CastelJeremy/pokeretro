import * as React from 'react';
import { useLocation } from 'wouter';
import CharacterService from '../../api/CharacterService';
import ICharacter, { CharacterGender } from '../../api/models/ICharacter';
import MenuBar from '../Components/MenuBar';
import TextBar from '../Components/TextBar';
import TextInput from '../Components/TextInput';

interface ICharacterScreenProps {
    userId: string;
    character: ICharacter;
    setCharacter: (character: ICharacter) => void;
}

const CharacterScreen: React.FC<ICharacterScreenProps> = ({
    userId,
    character,
    setCharacter,
}) => {
    const [location, setLocation] = useLocation();
    const [gender, setGender] = React.useState<CharacterGender>();
    const [name, setName] = React.useState<string>();
    const [starter, setStarter] = React.useState<string>();

    const handleCharacterCreation = async () => {
        let starterId = 1;

        if (starter == 'Charmander') {
            starterId = 4;
        }

        if (starter == 'Squirtle') {
            starterId = 7;
        }

        const character = await CharacterService.create({
            id: null,
            userId: userId,
            name: name,
            gender: gender,
            starter: starterId,
        });

        setCharacter(character);
    };

    React.useEffect(() => {
        if (starter) handleCharacterCreation();
    }, [starter]);

    React.useEffect(() => {
        CharacterService.getAllByUserId(userId).then((characters) => {
            setCharacter(characters[0]);
        });
    }, [userId]);

    if (character) {
        return (
            <React.Fragment>
                <h1>
                    characterSprite
                    <br />
                    {character.gender}
                </h1>
                <TextBar
                    content={'Welcome back ' + character.name + ' !'}
                    onFinish={() => setLocation('/map')}
                />
            </React.Fragment>
        );
    } else if (name) {
        return (
            <React.Fragment>
                <h1>starterSprite</h1>
                <TextBar content='Pick your POKÉMON.' disabled />
                <MenuBar
                    choices={['Bulbazaur', 'Charmander', 'Squirtle']}
                    onSubmit={setStarter}
                />
            </React.Fragment>
        );
    } else if (gender !== undefined)
        return (
            <React.Fragment>
                <TextInput placeholder='...' label='Name' onSubmit={setName} />
            </React.Fragment>
        );
    else
        return (
            <React.Fragment>
                <h1>Gender sprite</h1>
                <TextBar content='Who are you ?' disabled />
                <MenuBar
                    choices={['Boy', 'Girl']}
                    onSubmit={(value: string) => {
                        if (value === 'Boy') setGender(CharacterGender.Boy);
                        if (value === 'Girl') setGender(CharacterGender.Girl);
                    }}
                />
            </React.Fragment>
        );
};

export default CharacterScreen;
