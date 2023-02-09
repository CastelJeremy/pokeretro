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

const App: React.FC = () => {
    const [user, setUser] = React.useState<IUser>();
    const [character, setCharacter] = React.useState<ICharacter>();

    return (
        <React.Fragment>
            <Route path='/fds'>
                <Link href='/login'>
                    <a>LOGIN</a>
                </Link>
                <Link href='/register'>
                    <a>REGISTER</a>
                </Link>
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
            <Route path='/'>
                <CharacterScreen
                    userId='9b3a947e-9e03-490d-91a3-9a9a67084c8e'
                    character={character}
                    setCharacter={setCharacter}
                />
            </Route>
            <Route path='/inventory'>
                <InventoryScreen characterId='7d91df4c-ae08-4722-b85e-0b45bec1b639'/>
            </Route>
            <Route path='/team'>
                <TeamScreen characterId='7d91df4c-ae08-4722-b85e-0b45bec1b639' />
            </Route>
        </React.Fragment>
    );
};

export default App;
