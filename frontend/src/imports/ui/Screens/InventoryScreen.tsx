import * as React from 'react';
import EggService from '../../api/EggService';
import IEgg from '../../api/models/IEgg';
import EggsList from '../Components/EggsList';
import MenuBar from '../Components/MenuBar';
import TextBar from '../Components/TextBar';

interface IProps {
    characterId: string;
}

const InventoryScreen: React.FC<IProps> = ({ characterId }) => {
    const [eggs, setEggs] = React.useState<IEgg[]>([]);
    const [selectedEgg, setSelectedEgg] = React.useState<IEgg>();

    const reload = () => {
        EggService.getAllByCharacterId(characterId).then((eggs) => {
            setEggs(eggs);
        });
    };

    const handleAction = (action: string) => {
        if (action === 'Break') {
            EggService.delete(characterId, selectedEgg).then(() => {
                reload();
                setSelectedEgg(null);
            });
        }
    };

    React.useEffect(() => {
        if (characterId) {
            reload();
        }
    }, [characterId]);

    return (
        <React.Fragment>
            <EggsList
                eggs={eggs}
                disabled={!!selectedEgg}
                onSubmit={(egg) => {
                    setSelectedEgg(egg);
                }}
            />
            <TextBar
                content={
                    selectedEgg
                        ? 'What to do with ' + selectedEgg.pokemon.name + ' ?'
                        : (eggs.length > 0) ? 'Choose an egg.' : 'Empty inventory.'
                }
            />
            {selectedEgg && (
                <MenuBar choices={['Sell', 'Break']} onSubmit={handleAction} />
            )}
        </React.Fragment>
    );
};

export default InventoryScreen;
