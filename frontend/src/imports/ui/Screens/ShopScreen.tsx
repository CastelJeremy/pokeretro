import * as React from 'react';
import { useLocation } from 'wouter';
import IEgg from '../../api/models/IEgg';
import MoneyService from '../../api/MoneyService';
import ShopService from '../../api/ShopService';
import EggsList from '../Components/EggsList';
import MenuBar from '../Components/MenuBar';
import TextBar from '../Components/TextBar';

interface IProps {
    characterId: string;
}

const ShopScreen: React.FC<IProps> = ({ characterId }) => {
    const [location, setLocation] = useLocation();
    const [money, setMoney] = React.useState<number>(0);
    const [offers, setOffers] = React.useState<IEgg[]>([]);
    const [selectedOffer, setSelectedOffer] = React.useState<IEgg>();

    React.useEffect(() => {
        MoneyService.get(characterId).then(setMoney);
    }, [characterId]);

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Escape') {
            setLocation('/map');
        }
    };

    React.useEffect(() => {
        window.addEventListener('keydown', handleKeyboardEvent);

        ShopService.refresh().then((offers) => {
            setOffers(offers.sort((a, b) => a.price - b.price));
        });

        return () => {
            window.removeEventListener('keydown', handleKeyboardEvent);
        };
    }, []);

    const handleAction = (action: string) => {
        if (action === 'Yes') {
            ShopService.buy(characterId, selectedOffer).then(() => {
                MoneyService.get(characterId).then(setMoney);
                setSelectedOffer(null);
            });
        }

        if (action === 'No') {
            setSelectedOffer(null);
        }
    };

    return (
        <React.Fragment>
            <EggsList
                eggs={offers}
                disabled={!!selectedOffer}
                onSubmit={setSelectedOffer}
            />
            <TextBar
                content={
                    selectedOffer
                        ? `Buy ${selectedOffer.pokemon.name} ?`
                        : 'Choose an offer.'
                }
            />
            {selectedOffer && (
                <MenuBar choices={['Yes', 'No']} onSubmit={handleAction} />
            )}
            <p style={{ position: 'absolute', bottom: 0, left: '18px' }}>
                Money: {money} $
            </p>
        </React.Fragment>
    );
};

export default ShopScreen;
