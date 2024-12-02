import React, { useState } from 'react';

const AuthPage = () => {
    const [isLogin, setIsLogin] = useState(true); // Gère l'état : Connexion ou Inscription
    const [formData, setFormData] = useState({
        firstName: '',
        lastName : '',
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
    
        const endpoint = isLogin ? "/auth/auth/login" : "/accounts/create";
        const payload = isLogin
            ? {
                  email: formData.email,
                  password: formData.password,
              }
            : {
                  email: formData.email,
                  password: formData.password,
                  firstName: formData.firstName,
                  lastName: formData.lastName,
              };
    
        try {
            const requestOptions = {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams(payload),
            };
    
            const response = await fetch(`${BASE_URL}${endpoint}`, requestOptions);
    
            if (!response.ok) {
                // Récupérer le message d'erreur (texte ou JSON)
                const errorMessage = await response.text();
                throw new Error(errorMessage || "Une erreur est survenue.");
            }
    
            // Déterminez si la réponse est JSON ou texte
            const contentType = response.headers.get("Content-Type");
            const data = contentType && contentType.includes("application/json")
                ? await response.json()
                : await response.text();
    
            alert(isLogin ? "Connexion réussie !" : "Inscription réussie !");
            console.log("Réponse serveur :", data);
    
            // Réinitialisez les champs du formulaire après la soumission
            setFormData({ firstName: '', lastName: '', email: '', password: '' });
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
                    <>
                        <div style={{ marginBottom: '10px' }}>
                            <label>
                                Prénom:
                                <input
                                    type="text"
                                    name="firstName"
                                    value={formData.firstName}
                                    onChange={handleInputChange}
                                    required
                                    style={{ width: '100%', padding: '8px', marginTop: '5px' }}
                                />
                            </label>
                        </div>
                        <div style={{ marginBottom: '10px' }}>
                            <label>
                                Nom:
                                <input
                                    type="text"
                                    name="lastName"
                                    value={formData.lastName}
                                    onChange={handleInputChange}
                                    required
                                    style={{ width: '100%', padding: '8px', marginTop: '5px' }}
                                />
                            </label>

                        </div>
                    </>
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
