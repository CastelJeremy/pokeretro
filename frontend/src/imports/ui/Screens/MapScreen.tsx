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
                choices={['Arena', 'PokeCenter', 'Inventory', 'Team', 'Shop']}
                onSubmit={(choice) => {
                    if (choice === 'Inventory') setLocation('/inventory');
                    if (choice === 'Team') setLocation('/team');
                }}
            />
        </React.Fragment>
    );
};

export default MapScreen;
