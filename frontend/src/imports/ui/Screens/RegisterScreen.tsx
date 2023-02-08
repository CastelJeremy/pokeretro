import * as React from 'react';
import IUser from '../../api/models/IUser';

interface IRegisterScreenProps {
    onRegister: (user: IUser) => void;
}

const RegisterScreen: React.FC<IRegisterScreenProps> = ({ onRegister }) => {
    return <h1>REGISTER</h1>;
};

export default RegisterScreen;
