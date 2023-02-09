import * as React from 'react';
import { useLocation } from 'wouter';
import ITeammate from '../../api/models/ITeammate';
import TeammateService from '../../api/TeammateService';
import MenuBar from '../Components/MenuBar';
import TeammatesList from '../Components/TeammatesList';
import TextBar from '../Components/TextBar';

interface ITeamScreenProps {
    characterId: string;
}

const TeamScreen: React.FC<ITeamScreenProps> = ({ characterId }) => {
    const [location, setLocation] = useLocation();
    const [teammates, setTeammates] = React.useState<Array<ITeammate>>([]);
    const [selectedTeammate, setSelectedTeammate] = React.useState<ITeammate>();
    const [swapTeammate, setSwapTeammate] = React.useState<ITeammate>();

    const handleAction = (action: string) => {
        if (action === 'Swap') {
            setSwapTeammate(selectedTeammate);
            setSelectedTeammate(null);
        }

        if (action === 'Release') {
            TeammateService.delete(characterId, selectedTeammate).then(setTeammates);
            setSelectedTeammate(null);
        }
    };

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Escape') {
            setLocation('/map');
        }
    };

    React.useEffect(() => {
        if (swapTeammate && selectedTeammate) {
            TeammateService.swap(characterId, swapTeammate.id, selectedTeammate.id).then(setTeammates);
            setSwapTeammate(null);
            setSelectedTeammate(null);
        }
    }, [selectedTeammate]);

    React.useEffect(() => {
        if (characterId) {
            TeammateService.getAllByCharacterId(characterId).then(
                (teammates) => {
                    setTeammates(teammates);
                }
            );
        }
    }, [characterId]);

    React.useEffect(() => {
        window.addEventListener('keydown', handleKeyboardEvent);

        return () => {
            window.removeEventListener('keydown', handleKeyboardEvent);
        };
    }, []);

    return (
        <React.Fragment>
            <TeammatesList
                teammates={teammates}
                disabled={!!selectedTeammate}
                onSubmit={setSelectedTeammate}
            />
            <TextBar
                content={
                    swapTeammate
                        ? 'Swap ' + swapTeammate.name + ' with...'
                        : 'Choose a POKÉMON.'
                }
            />
            {selectedTeammate && (
                <MenuBar
                    choices={['Stats', 'Swap', 'Release']}
                    onSubmit={handleAction}
                />
            )}
        </React.Fragment>
    );
};

export default TeamScreen;
