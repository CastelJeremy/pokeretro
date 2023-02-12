import * as React from 'react';
import { useLocation } from 'wouter';
import LoginForm from '../Components/LoginForm';
import IUser from '../../api/models/IUser';
import UserService from '../../api/UserService';

interface IProps {
    onLogin: (user: IUser) => void;
}

const LoginScreen: React.FC<IProps> = ({ onLogin }) => {
    const [location, setLocation] = useLocation();

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Escape') {
            setLocation('/');
        }
    };

    React.useEffect(() => {
        window.addEventListener('keydown', handleKeyboardEvent);

        return () => {
            window.removeEventListener('keydown', handleKeyboardEvent);
        };
    }, []);

    const handleLogin = async (username: string, password: string) => {
        try {
            const token = await UserService.login(username, password);
            const user = await UserService.getByUsername(username);
            user.token = token;

            onLogin(user);
            setLocation('/character');
        } catch (e) {}
    };

    return <LoginForm onSubmit={handleLogin} />;
};

export default LoginScreen;
