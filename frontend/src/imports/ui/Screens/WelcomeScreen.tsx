import * as React from 'react';
import { useLocation } from 'wouter';
import MenuBar from '../Components/MenuBar';

const WelcomeScreen: React.FC = () => {
    const [location, setLocation] = useLocation();

    const handleSubmit = (action: string) => {
        if (action == 'LOGIN') setLocation('/login');
        if (action == 'REGISTER') setLocation('/register');
    };
    return (
        <React.Fragment>
            <h1>Welcome !</h1>
            <MenuBar choices={['LOGIN', 'REGISTER']} onSubmit={handleSubmit} />
        </React.Fragment>
    );
};

export default WelcomeScreen;
