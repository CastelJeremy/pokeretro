import * as React from 'react';
import './MenuBar.css';

interface IMenuBarProps {
    choices: string[];
    onSubmit: (choice: string) => void;
}

const MenuBar: React.FC<IMenuBarProps> = ({ choices, onSubmit }) => {
    const [selected, _setSelected] = React.useState<number>(0);
    const selectedRef = React.useRef(selected);

    const setSelected = (value: number) => {
        selectedRef.current = value;
        _setSelected(value);
    };

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'ArrowDown') {
            setSelected((selectedRef.current + 1) % choices.length);
        } else if (ev.key == 'ArrowUp') {
            if (selectedRef.current == 0) {
                setSelected(choices.length - 1);
            } else {
                setSelected(selectedRef.current - 1);
            }
        } else if (ev.key == 'Enter') {
            onSubmit(choices[selectedRef.current]);
        }
    };

    React.useEffect(() => {
        window.addEventListener('keydown', handleKeyboardEvent);
        return () => {
            window.removeEventListener('keydown', handleKeyboardEvent);
        };
    }, []);

    return (
        <div className='menuBar'>
            <div className='choicesContainer'>
                <ol className='choicesList'>
                    {choices.map((choice, key) => {
                        if (key == selected) {
                            return (
                                <li key={key} className='selected'>
                                    {choice}
                                </li>
                            );
                        } else {
                            return <li key={key}>{choice}</li>;
                        }
                    })}
                </ol>
            </div>
        </div>
    );
};

export default MenuBar;
