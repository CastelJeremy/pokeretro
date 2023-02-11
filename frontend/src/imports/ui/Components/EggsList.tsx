import * as React from 'react';
import IEgg from '../../api/models/IEgg';

import './EggsList.css';
import Sprite from './Sprite';

interface IProps {
    eggs: IEgg[];
    disabled?: boolean;
    onSubmit: (egg: IEgg) => void;
}

const EggsList: React.FC<IProps> = ({ eggs, disabled, onSubmit }) => {
    const [length, _setLength] = React.useState<number>(eggs.length);
    const [selected, _setSelected] = React.useState<number>(0);
    const [pressEnter, _setPressEnter] = React.useState<boolean>(false);
    const lengthRef = React.useRef(length);
    const selectedRef = React.useRef(selected);
    const pressEnterRef = React.useRef(pressEnter);

    const setLength = (value: number) => {
        lengthRef.current = value;
        _setLength(value);
    };

    const setSelected = (value: number) => {
        selectedRef.current = value;
        _setSelected(value);
    };

    const setPressEnter = (value: boolean) => {
        pressEnterRef.current = value;
        _setPressEnter(value);
    };

    const handleKeyboardEvent = (ev: KeyboardEvent) => {
        if (ev.key == 'ArrowDown') {
            setSelected((selectedRef.current + 1) % lengthRef.current);
        } else if (ev.key == 'ArrowUp') {
            if (selectedRef.current == 0) {
                setSelected(lengthRef.current - 1);
            } else {
                setSelected(selectedRef.current - 1);
            }
        } else if (ev.key == 'Enter') {
            setPressEnter(true);
        }
    };

    React.useEffect(() => {
        if (pressEnter) {
            onSubmit(eggs[selectedRef.current]);
            setPressEnter(false);
        }
    }, [pressEnter]);

    React.useEffect(() => {
        if (!disabled) window.addEventListener('keydown', handleKeyboardEvent);

        return () => {
            window.removeEventListener('keydown', handleKeyboardEvent);
        };
    }, [disabled]);

    React.useEffect(() => {
        setLength(eggs.length);

        if (selectedRef.current > eggs.length - 1) {
            setSelected(eggs.length - 1);
        }
    }, [eggs]);

    return (
        <div className='EggsList'>
            {eggs.map((egg, key) => {
                return (
                    <div
                        key={key}
                        className={
                            key == selected ? 'container selected' : 'container'
                        }
                    >
                        <Sprite className='sprite' pokemonId={egg.pokemon.id} animate={key == selected} />
                        <div className='text'>
                            <div className='title'>
                                <p className='name'>
                                    #{egg.pokemon.id}{' '}
                                    {egg.pokemon.name.toUpperCase()}
                                </p>
                            </div>
                            <div className='subtitle'>
                                <p className='weight'>{egg.weight} g</p>
                                <p className='price'>{egg.price} $</p>
                                <p className='time'>{egg.time} sec.</p>
                            </div>
                        </div>
                    </div>
                );
            })}
        </div>
    );
};

export default EggsList;
