import * as React from 'react';
import { useLocation } from 'wouter';
import MenuBar from '../Components/MenuBar';
import TextBar from '../Components/TextBar';

const MapScreen: React.FC = () => {
    const [location, setLocation] = useLocation();

    return (
        <React.Fragment>
            <TextBar content='Where do you want to go ?' />
            <MenuBar
                choices={[
                    'Shop',
                    'Inventory',
                    'Incubator',
                    'Team',
                    'Arena',
                    'PokeCenter',
                ]}
                onSubmit={(choice) => {
                    if (choice === 'Shop') setLocation('/shop');
                    if (choice === 'Inventory') setLocation('/inventory');
                    if (choice === 'Incubator') setLocation('/incubator');
                    if (choice === 'Team') setLocation('/team');
                }}
            />
        </React.Fragment>
    );
};

export default MapScreen;
