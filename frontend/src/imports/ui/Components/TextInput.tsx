import * as React from 'react';
import './TextInput.css';

interface ITextInput {
    placeholder?: string;
    label?: string;
    onSubmit?: (value: string) => void;
}

const TextInput: React.FC<ITextInput> = ({ placeholder, label, onSubmit }) => {
    const [value, _setValue] = React.useState<string>('');
    const valueRef = React.useRef(value);

    const setValue = (value: string) => {
        valueRef.current = value;
        _setValue(value);
    };

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Enter') {
            onSubmit(valueRef.current);
        }
    };

    React.useEffect(() => {
        if (onSubmit) {
            window.addEventListener('keydown', handleKeyboardEvent);

            return () => {
                window.removeEventListener('keydown', handleKeyboardEvent);
            };
        }
    }, []);

    return (
        <div className='textInputContainer'>
            {label ? <label className='textLabel'>{label}</label> : null}
            <input
                className='textInput'
                type='text'
                placeholder={placeholder}
                autoFocus={true}
                value={value}
                onChange={(ev) => {
                    setValue(ev.target.value);
                }}
            />
        </div>
    );
};

export default TextInput;
