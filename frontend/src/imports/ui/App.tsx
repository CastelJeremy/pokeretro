import * as React from 'react';
import { Link, Route } from 'wouter';
import RegisterScreen from './Screens/RegisterScreen';
import LoginScreen from './Screens/LoginScreen';
import CharacterScreen from './Screens/CharacterScreen';
import MapScreen from './Screens/MapScreen';
import ICharacter from '../api/models/ICharacter';
import IUser from '../api/models/IUser';
import './App.css';
import TeamScreen from './Screens/TeamScreen';
import InventoryScreen from './Screens/InventoryScreen';
import WelcomeScreen from './Screens/WelcomeScreen';
import IncubatorScreen from './Screens/IncubatorScreen';
import ShopScreen from './Screens/ShopScreen';

const App: React.FC = () => {
    const [user, setUser] = React.useState<IUser>();
    const [character, setCharacter] = React.useState<ICharacter>();

    return (
        <React.Fragment>
            <Route path='/'>
                <WelcomeScreen />
            </Route>
            <Route path='/login'>
                <LoginScreen onLogin={setUser} />
            </Route>
            <Route path='/register'>
                <RegisterScreen onRegister={setUser} />
            </Route>
            <Route path='/map'>
                <MapScreen />
            </Route>
            <Route path='/character'>
                <CharacterScreen
                    userId={user ? user.id : ''}
                    character={character}
                    setCharacter={setCharacter}
                />
            </Route>
            <Route path='/shop'>
                <ShopScreen characterId={character ? character.id : '' } />
            </Route>
            <Route path='/inventory'>
                <InventoryScreen characterId={character ? character.id : ''} />
            </Route>
            <Route path='/incubator'>
                <IncubatorScreen characterId={character ? character.id : ''} />
            </Route>
            <Route path='/team'>
                <TeamScreen characterId={character ? character.id : ''} />
            </Route>
        </React.Fragment>
    );
};

export default App;
