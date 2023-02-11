import * as React from 'react';
import sprite1Animated from '../../../1.gif';
import sprite2Animated from '../../../2.gif';
import sprite3Animated from '../../../3.gif';
import sprite4Animated from '../../../4.gif';
import sprite5Animated from '../../../5.gif';
import sprite6Animated from '../../../6.gif';
import sprite7Animated from '../../../7.gif';
import sprite8Animated from '../../../8.gif';
import sprite1 from '../../../1.jpg';
import sprite2 from '../../../2.jpg';
import sprite3 from '../../../3.jpg';
import sprite4 from '../../../4.jpg';
import sprite5 from '../../../5.jpg';
import sprite6 from '../../../6.jpg';
import sprite7 from '../../../7.jpg';
import sprite8 from '../../../8.jpg';

interface IProps {
    pokemonId: number;
    className: string;
    animate: boolean;
}

const Sprite: React.FC<IProps> = ({ className, pokemonId, animate = true }) => {
    const [srcIdle, setSrcIdle] = React.useState<string>();
    const [srcAnim, setSrcAnim] = React.useState<string>();

    React.useEffect(() => {
        if (pokemonId >= 1) setSrcAnim(sprite1Animated);
        if (pokemonId >= 20) setSrcAnim(sprite2Animated);
        if (pokemonId >= 40) setSrcAnim(sprite3Animated);
        if (pokemonId >= 50) setSrcAnim(sprite4Animated);
        if (pokemonId >= 70) setSrcAnim(sprite5Animated);
        if (pokemonId >= 90) setSrcAnim(sprite6Animated);
        if (pokemonId >= 120) setSrcAnim(sprite7Animated);
        if (pokemonId >= 140) setSrcAnim(sprite8Animated);
        if (pokemonId >= 1) setSrcIdle(sprite1);
        if (pokemonId >= 20) setSrcIdle(sprite2);
        if (pokemonId >= 40) setSrcIdle(sprite3);
        if (pokemonId >= 50) setSrcIdle(sprite4);
        if (pokemonId >= 70) setSrcIdle(sprite5);
        if (pokemonId >= 90) setSrcIdle(sprite6);
        if (pokemonId >= 120) setSrcIdle(sprite7);
        if (pokemonId >= 140) setSrcIdle(sprite8);
    }, [pokemonId]);

    return (
        <React.Fragment>
            {animate ? (
                <img
                    style={{ imageRendering: 'pixelated' }}
                    className={className}
                    src={srcAnim}
                />
            ) : (
                <img
                    style={{ imageRendering: 'pixelated' }}
                    className={className}
                    src={srcIdle}
                />
            )}
        </React.Fragment>
    );
};

export default Sprite;
