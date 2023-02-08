import * as React from 'react';
import { useLocation } from 'wouter';
import ITeammate from '../../api/models/ITeammate';
import TeammateService from '../../api/TeammateService';
import TeammatesList from '../Components/TeammatesList';
import TextBar from '../Components/TextBar';

interface ITeamScreenProps {
    characterId: string;
}

const TeamScreen: React.FC<ITeamScreenProps> = ({ characterId }) => {
    const [location, setLocation] = useLocation();
    const [teammates, setTeammates] = React.useState<Array<ITeammate>>([]);
    const [selectedTeammate, setSelectedTeammate] = React.useState<ITeammate>();
    const [disabled, setDisabled] = React.useState<boolean>();

    const swap = (teammateOne: ITeammate, teammateTwo: ITeammate) => {
        TeammateService.swap(characterId, teammateOne.id, teammateTwo.id).then(
            (teammates) => {
                setTeammates(teammates);
            }
        );
    };

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Escape') {
            setLocation('/map');
        }
    };

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
                onSubmit={(teammate) => {
                    if (selectedTeammate) {
                        if (selectedTeammate.id != teammate.id) {
                            swap(selectedTeammate, teammate);
                            setSelectedTeammate(null);
                        }
                    } else {
                        setSelectedTeammate(teammate);
                    }
                }}
            />
            {selectedTeammate ? (
                <TextBar
                    content={'Swap ' + selectedTeammate.name + ' with...'}
                />
            ) : (
                <TextBar content='Choose a POKÉMON.' />
            )}
        </React.Fragment>
    );
};

export default TeamScreen;
