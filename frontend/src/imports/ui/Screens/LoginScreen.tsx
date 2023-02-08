import * as React from 'react';
import { useLocation } from 'wouter';
import LoginForm from '../Components/LoginForm';
import IUser from '../../api/models/IUser';
import UserService from '../../api/UserService';

interface ILoginScreenProps {
    onLogin: (user: IUser) => void;
}

const LoginScreen: React.FC<ILoginScreenProps> = ({ onLogin }) => {
    const [location, setLocation] = useLocation();

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
