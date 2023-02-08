import * as React from 'react';
import ITeammate from '../../api/models/ITeammate';
import HealthBar from './HealthBar';

import './TeammatesList.css';

interface IProps {
    teammates: Array<ITeammate>;
    disabled?: boolean;
    onSubmit: (index: ITeammate) => void;
}

const TeammatesList: React.FC<IProps> = ({ teammates, disabled, onSubmit }) => {
    const [length, _setLength] = React.useState<number>(teammates.length);
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
            onSubmit(teammates[selectedRef.current]);
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
        setLength(teammates.length);
    }, [teammates])

    const sortTeammates = (teammateA: ITeammate, teammateB: ITeammate) => {
        return teammateA.position - teammateB.position;
    }

    return (
        <div className='teammatesList'>
            {teammates.sort(sortTeammates).map((teammate, key) => {
                return (
                    <div
                        key={key}
                        className={
                            key == selected ? 'container selected' : 'container'
                        }
                    >
                        <img className='sprite' />
                        <div className='text'>
                            <div className='title'>
                                <p className='name'>
                                    {teammate.name.toUpperCase()}
                                </p>
                                <p className='level'>:L{teammate.level}</p>
                            </div>
                            <div className='subtitle'>
                                <HealthBar
                                    health={teammate.currentStat.hp}
                                    healthMax={teammate.stat.hp}
                                />
                                <p className='health'>
                                    {teammate.currentStat.hp} /{' '}
                                    {teammate.stat.hp}
                                </p>
                            </div>
                        </div>
                    </div>
                );
            })}
        </div>
    );
};

export default TeammatesList;
