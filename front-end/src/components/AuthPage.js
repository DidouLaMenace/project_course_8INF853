import React, { useState } from 'react';

const AuthPage = () => {
    const [isLogin, setIsLogin] = useState(true); // Gère l'état : Connexion ou Inscription
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (isLogin) {
            alert(`Connexion réussie pour l'utilisateur ${formData.email}`);
        } else {
            alert(`Inscription réussie pour l'utilisateur ${formData.username}`);
        }
        setFormData({ username: '', email: '', password: '' });
    };

    return (
        <div style={{ maxWidth: '400px', margin: 'auto', padding: '20px', textAlign: 'center' }}>
            <h1>{isLogin ? 'Connexion' : 'Inscription'}</h1>
            <form onSubmit={handleSubmit}>
                {!isLogin && (
                    <div style={{ marginBottom: '10px' }}>
                        <label>
                            Nom d'utilisateur:
                            <input
                                type="text"
                                name="username"
                                value={formData.username}
                                onChange={handleInputChange}
                                required
                                style={{ width: '100%', padding: '8px', marginTop: '5px' }}
                            />
                        </label>
                    </div>
                )}
                <div style={{ marginBottom: '10px' }}>
                    <label>
                        {isLogin ? 'Email:' : 'Email:'}
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleInputChange}
                            required
                            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
                        />
                    </label>
                </div>
                <div style={{ marginBottom: '10px' }}>
                    <label>
                        Mot de passe:
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleInputChange}
                            required
                            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
                        />
                    </label>
                </div>
                <button
                    type="submit"
                    style={{
                        width: '100%',
                        padding: '10px',
                        backgroundColor: '#4CAF50',
                        color: 'white',
                        border: 'none',
                        cursor: 'pointer'
                    }}
                >
                    {isLogin ? 'Se connecter' : 'S\'inscrire'}
                </button>
            </form>
            <button
                onClick={() => setIsLogin(!isLogin)}
                style={{
                    marginTop: '20px',
                    backgroundColor: 'transparent',
                    color: '#007BFF',
                    border: 'none',
                    cursor: 'pointer'
                }}
            >
                {isLogin ? 'Créer un compte' : 'J\'ai déjà un compte'}
            </button>
        </div>
    );
};

export default AuthPage;
