import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function AuthRequired() {
    const navigate = useNavigate();

    useEffect(() => {
        const isAuthenticated = !!localStorage.getItem('token'); // Vérifie à nouveau si connecté
        if (isAuthenticated) {
            navigate('/payment'); // Redirige directement si connecté
        }
    }, []);

    const handleLoginClick = () => {
        navigate('/auth'); // Redirige vers la page d'authentification
    };

    return (
        <div style={{ textAlign: 'center', padding: '20px' }}>
            <h2>Authentification requise</h2>
            <p>Veuillez vous connecter ou vous inscrire pour continuer.</p>
            <button className='btn btn-primary' onClick={handleLoginClick}>
                Se connecter / S'inscrire
            </button>
        </div>
    );
}

export default AuthRequired;
