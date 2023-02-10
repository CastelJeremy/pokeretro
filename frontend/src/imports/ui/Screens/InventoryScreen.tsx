import * as React from 'react';
import { useLocation } from 'wouter';
import EggService from '../../api/EggService';
import IncubatorService from '../../api/IncubatorService';
import IEgg from '../../api/models/IEgg';
import MoneyService from '../../api/MoneyService';
import ShopService from '../../api/ShopService';
import EggsList from '../Components/EggsList';
import MenuBar from '../Components/MenuBar';
import TextBar from '../Components/TextBar';

interface IProps {
    characterId: string;
}

const InventoryScreen: React.FC<IProps> = ({ characterId }) => {
    const [location, setLocation] = useLocation();
    const [eggs, setEggs] = React.useState<IEgg[]>([]);
    const [money, setMoney] = React.useState<number>(0);
    const [selectedEgg, setSelectedEgg] = React.useState<IEgg>();

    const reload = () => {
        EggService.getAllByCharacterId(characterId).then((eggs) =>
            setEggs(
                eggs.sort((a, b) => {
                    return a.pokemon.id - b.pokemon.id;
                })
            )
        );

        MoneyService.get(characterId).then((amount) => setMoney(amount));
    };

    const handleAction = (action: string) => {
        if (action === 'Incubate') {
            IncubatorService.place(characterId, selectedEgg).then(() => {
                EggService.delete(characterId, selectedEgg).then(() => {
                    reload();
                    setSelectedEgg(null);
                });
            });
        }

        if (action === 'Sell') {
            ShopService.sell(characterId, selectedEgg).then(() => {
                reload();
                setSelectedEgg(null);
            });
        }

        if (action === 'Set free' || action === 'Omelette ?') {
            EggService.delete(characterId, selectedEgg).then(() => {
                reload();
                setSelectedEgg(null);
            });
        }

        if (action === 'Cancel') {
            setSelectedEgg(null);
        }
    };

    React.useEffect(() => {
        if (characterId) {
            reload();
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
                        : eggs.length > 0
                        ? 'Choose an egg.'
                        : 'Empty inventory.'
                }
            />
            {selectedEgg && (
                <MenuBar
                    choices={[
                        'Incubate',
                        'Sell',
                        Math.random() > 0.99 ? 'Omelette ?' : 'Set free',
                        'Cancel',
                    ]}
                    onSubmit={handleAction}
                />
            )}
            <p style={{ position: 'absolute', bottom: 0, left: '18px' }}>
                Money: {money} $
            </p>
        </React.Fragment>
    );
};

export default InventoryScreen;
