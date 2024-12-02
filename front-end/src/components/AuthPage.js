import React, { useState } from 'react';

const AuthPage = () => {
    const [isLogin, setIsLogin] = useState(true); // Gère l'état : Connexion ou Inscription
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: ''
    });

    const BASE_URL = "http://localhost:8083"; // URL de la Gateway

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const endpoint = isLogin ? "/auth/login" : "/auth/register";
        const payload = isLogin
            ? { email: formData.email, password: formData.password }
            : { username: formData.username, email: formData.email, password: formData.password };

        try {
            const response = await fetch(`${BASE_URL}${endpoint}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error("Une erreur est survenue. Veuillez réessayer.");
            }

            const data = await response.json();
            alert(isLogin ? "Connexion réussie !" : "Inscription réussie !");
            console.log("Réponse serveur :", data);

            setFormData({ username: '', email: '', password: '' });
        } catch (error) {
            console.error("Erreur :", error.message);
            alert(error.message);
        }
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
                        Email:
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
