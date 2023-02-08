import * as React from 'react';
import TextInput from './TextInput';
import './LoginForm.css';

import logo from '../../../logo.png';

interface ILoginFormProps {
    onSubmit: (username: string, password: string) => void;
}

const LoginForm: React.FC<ILoginFormProps> = ({ onSubmit }) => {
    const [username, setUsername] = React.useState<string>('');
    const [password, setPassword] = React.useState<string>('');

    const handleSubmit = (event: any) => {
        event.preventDefault();
        onSubmit(username, password);
    };

    return (
        <form className='loginForm' onSubmit={handleSubmit} action=''>
            <img src={logo} />
            <input
                onChange={(e) => setUsername(e.target.value)}
                value={username}
                placeholder='USERNAME'
                type='text'
            />
            <input
                onChange={(e) => setPassword(e.target.value)}
                value={password}
                placeholder='PASSWORD'
                type='password'
            />
            <button onClick={handleSubmit} type='submit'>
                PRESS ENTER
            </button>
        </form>
    );
};

export default LoginForm;
