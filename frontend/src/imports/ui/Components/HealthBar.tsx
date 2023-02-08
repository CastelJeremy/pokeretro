import * as React from 'react';
import './HealthBar.css';

interface IProps {
    health: number;
    healthMax: number;
}

const HealthBar: React.FC<IProps> = ({ health, healthMax }) => {
    return (
        <div className='healthBar'>
            <p>HP:</p>
            <div className='barContainer'>
                <div
                    style={{ width: (health / healthMax) * 100 + '%' }}
                    className='bar'
                ></div>
            </div>
        </div>
    );
};

export default HealthBar;
