import * as React from 'react';
import { useLocation } from 'wouter';
import LoginForm from '../Components/LoginForm';
import IUser from '../../api/models/IUser';
import UserService from '../../api/UserService';
import TextBar from '../Components/TextBar';

interface IProps {
    onRegister: (user: IUser) => void;
}

const RegisterScreen: React.FC<IProps> = ({ onRegister }) => {
    const [location, setLocation] = useLocation();
    const [error, setError] = React.useState<string>();

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

    const handleRegister = async (username: string, password: string) => {
        setError('');
        if (username.length < 5)
            setError('Username must be at least 5 characters long.');
        if (password.length < 9)
            setError('Password must be at least 9 characters long.');

        if (!error) {
            try {
                const token = await UserService.register(username, password);
                const user = await UserService.getByUsername(username);
                user.token = token;

                onRegister(user);
                setLocation('/character');
            } catch (e) {}
        }
    };

    return (
        <React.Fragment>
            <LoginForm onSubmit={handleRegister} />
            {error && <TextBar content={error} />}
        </React.Fragment>
    );
};

export default RegisterScreen;
