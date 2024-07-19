import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './ButtonStyle.css';
import useAuth from '../../customHook/useAuth'; // Import the custom hook

function Button({ isLoginButton, isLoginToSeeButton }) {
    const [isPressed, setIsPressed] = useState(false);
    //const [isLoggedIn, setLoggedIn] = useState(false);
    const [isHovered, setIsHovered] = useState(false);

    const navigate = useNavigate(); // Hook for navigating

    
    const { isLoggedIn, decoded } = useAuth(); // Use the custom hook

    const handlePress = () => {
        setIsPressed(true);
        console.log('Div pressed!');

        if (!isLoggedIn) {
            // Navigate to the /login page
            navigate('/login');
        }
    };

    const handleRelease = () => {
        setIsPressed(false);
    };

    const handleMouseOver = () => {
        console.log('Hovered!');
        setIsHovered(true);
    };

    const handleMouseOut = () => {
        console.log('Not hovered!');
        setIsHovered(false);
    };

    const buttonStyleLogin = {
        cursor: 'pointer',
        color: isHovered ? '#000000' : '#e61e43',
        '&:hover': {
            color: '#000000',
        }
    };


    const buttonStyleLoginToSee = {
        cursor: 'pointer',
        color: '#1668e3',
        borderWidth: '1px',
        borderColor: '#818494',
        borderStyle: 'solid',
        borderRadius: '2500rem',
        fontWeight: '700',
        marginTop: '8%',
        display: 'inline-block',
        fontSize: '0.75rem',
        textAlign: 'center',
        width: '70%'
    };

    return (
        <>
            {isLoginButton && (
                <div
                    className="button_container"
                    onClick={handlePress}
                    onMouseUp={handleRelease}
                    onMouseLeave={handleRelease}
                    onMouseOver={handleMouseOver}
                    onMouseOut={handleMouseOut}
                    style={buttonStyleLogin}
                >
                    {isLoggedIn ? <p className="button_text2">Merhaba, {decoded.name}</p> : <p className="button_text1">Giriş Yap</p>}
                </div>
            )}

            {isLoginToSeeButton && (
                <div
                    className="button_container"
                    onClick={handlePress}
                    onMouseUp={handleRelease}
                    onMouseLeave={handleRelease}
                    onMouseOver={handleMouseOver}
                    onMouseOut={handleMouseOut}
                    style={buttonStyleLoginToSee}
                >
                    <p>Üye Fiyatı İçin Giriş Yapın</p>
                </div>
            )}
        </>
    );
}

export default Button;
