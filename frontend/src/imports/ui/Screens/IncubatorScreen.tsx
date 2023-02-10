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
        if (action === 'Yes') {
            IncubatorService.hatch(characterId, selectedEgg).then((eggs) => {
                setEggs(eggs);
                setSelectedEgg(null);
            });
        }

        if (action === 'No') {
            setSelectedEgg(null);
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
                        ? `Hatch ${selectedEgg.pokemon.name} ?`
                        : eggs.length > 0
                        ? 'Choose an egg.'
                        : 'Empty incubator.'
                }
            />
            {selectedEgg && (
                <MenuBar
                    choices={['Yes', 'No']}
                    onSubmit={handleAction}
                />
            )}
            <p style={{ position: 'absolute', bottom: 0, left: '18px' }}>
                Weight:{' '}
                {eggs.map((a) => a.weight).reduce((a, b) => a + b, 0) / 1000} Kg
            </p>
        </React.Fragment>
    );
};

export default IncubatorScreen;
