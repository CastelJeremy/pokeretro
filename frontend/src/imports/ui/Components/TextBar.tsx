import * as React from 'react';
import './TextBar.css';

interface ITextBarProps {
    content: string;
    disabled?: boolean;
    onFinish?: () => void;
}

const TextBar: React.FC<ITextBarProps> = ({ content, disabled, onFinish }) => {
    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'Enter') {
            if (onFinish) onFinish();
        }
    };

    React.useEffect(() => {
        if (!disabled) {
            window.addEventListener('keypress', handleKeyboardEvent);

            return () => {
                window.removeEventListener('keypress', handleKeyboardEvent);
            };
        }
    }, [disabled, onFinish]);

    return (
        <div className='textBar'>
            <div className='textContainer'>{content}</div>
        </div>
    );
};

export default TextBar;
