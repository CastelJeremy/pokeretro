import * as React from 'react';
import { useLocation } from 'wouter';
import IncubatorService from '../../api/IncubatorService';
import IEgg from '../../api/models/IEgg';
import IncubationList from '../Components/IncubationList';
import MenuBar from '../Components/MenuBar';
import TextBar from '../Components/TextBar';

interface IProps {
    characterId: string;
}

const IncubatorScreen: React.FC<IProps> = ({ characterId }) => {
    const [location, setLocation] = useLocation();
    const [eggs, setEggs] = React.useState<IEgg[]>([]);
    const [selectedEgg, setSelectedEgg] = React.useState<IEgg>();

    const handleAction = (action: string) => {
        if (action == 'Hatch') {
            IncubatorService.hatch(characterId, selectedEgg).then((eggs) => {
                setEggs(eggs);
                setSelectedEgg(null);
            });
        }
    };

    React.useEffect(() => {
        if (characterId) {
            IncubatorService.getAllByCharacterId(characterId).then(setEggs);
        }
    }, [characterId]);

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Escape') {
            setLocation('/map');
        }
    };

    React.useEffect(() => {
        window.addEventListener('keydown', handleKeyboardEvent);

        return () => {
            window.removeEventListener('keydown', handleKeyboardEvent);
        };
    }, []);

    return (
        <React.Fragment>
            <IncubationList
                eggs={eggs}
                disabled={!!selectedEgg}
                onSubmit={setSelectedEgg}
            />
            <TextBar
                content={
                    selectedEgg
                        ? 'What to do with ' + selectedEgg.pokemon.name + ' ?'
                        : eggs.length > 0
                        ? 'Choose an egg.'
                        : 'Empty incubator.'
                }
            />
            {selectedEgg && (
                <MenuBar choices={['Hatch']} onSubmit={handleAction} />
            )}
        </React.Fragment>
    );
};

export default IncubatorScreen;
