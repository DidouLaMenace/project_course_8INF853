import React, { useState } from 'react';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';

function Login() {
    const loginpagestyle = {
        height: '100vh',
    };

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault(); // Empêche le rechargement de la page
        try {
            const response = await axios.post('http://localhost:8083/accounts/login', null, {
                params: { email, password },
            });

            // Enregistrement du session-id dans un cookie
            Cookies.set('session-id', response.data, { expires: 1 }); // Expiration en 1 jour

            // Enregistrement du session-id dans le localStorage
            localStorage.setItem('session-id', response.data);

            // Redirection vers la page catalogue
            navigate('/catalogue');
        } catch (error) {
            // Affiche un popup si l'authentification échoue
            alert('Identifiants incorrects. Veuillez réessayer.');
            // Réinitialisation du champ de mot de passe
            setPassword('');
        }
    };

    return (
        <>
            <Header />
                <div className="loginpage bg-light" style={loginpagestyle}>
                    <div className="contianer w-100 h-100 d-flex justify-content-center align-items-center flex-column">
                        <form
                            className="d-flex flex-column justify-content-center"
                            onSubmit={handleLogin}
                        >
                            <h2 className="text-center pb-3">Connectez-vous à PrixBanque</h2>
                            <div className="form-group mb-2">
                                <label htmlFor="exampleInputEmail1">Adresse mail</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="exampleInputEmail1"
                                    aria-describedby="emailHelp"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group mb-4">
                                <label htmlFor="exampleInputPassword1">Mot de passe</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="exampleInputPassword1"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <button type="submit" className="btn btn-primary">
                                Valider
                            </button>
                            <p className="text-center pt-3">
                                Vous n'avez de un compte ? <Link to="/register">Créez un maintenant</Link>
                            </p>
                        </form>
                    </div>
                </div>
            <Footer />
        </>
    );
}

export default Login;
