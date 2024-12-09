import React, { useState } from 'react';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';

function Register() {
    const registerpagestyle = {
        height: '100vh',
    };

    const [email, setEmail] = useState('');
    const [password1, setPassword1] = useState('');
    const [password2, setPassword2] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();

        // Validation des champs côté client
        if (!email || !password1 || !password2 || !firstName || !lastName) {
            alert('Veuillez remplir tous les champs.');
            return;
        }

        if (password1 !== password2) {
            alert('Les mots de passe ne correspondent pas.');
            return;
        }

        try {
            // Appel à l'API pour créer un compte
            const registerResponse = await axios.post(
                'http://localhost:8083/accounts/register',
                null,
                {
                    params: {
                        email,
                        password: password1,
                        firstName,
                        lastName,
                    },
                }
            );

            // Récupération des informations du compte
            const { email: createdEmail } = registerResponse.data;

            // Appel à l'API pour s'authentifier après la création
            const loginResponse = await axios.post(
                'http://localhost:8083/accounts/login',
                null,
                {
                    params: {
                        email: createdEmail,
                        password: password1,
                    },
                }
            );

            // Enregistrement du session-id dans un cookie
            Cookies.set('session-id', loginResponse.data, { expires: 1 });

            // Enregistrement du session-id dans le localStorage
            localStorage.setItem('session-id', loginResponse.data);

            // Redirection vers la page catalogue
            navigate('/catalogue');
        } catch (error) {
            // Gestion des erreurs
            const errorMessage =
                error.response?.data?.message || 'Erreur lors de la création du compte.';
            alert(errorMessage);
        }
    };

    return (
        <>
            <Header />
                <div className="registerpage bg-light" style={registerpagestyle}>
                    <div className="container w-100 h-100 d-flex justify-content-center align-items-center flex-column">
                        <form
                            className="d-flex flex-column justify-content-center"
                            onSubmit={handleRegister}
                        >

                            <h2 className="text-center pb-3">S'inscrire sur PrixBanque</h2>

                            <div className="form-group mb-2">
                                <label htmlFor="firstName">Prénom</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="firstName"
                                    value={firstName}
                                    onChange={(e) => setFirstName(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group mb-2">
                                <label htmlFor="lastName">Nom</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="lastName"
                                    value={lastName}
                                    onChange={(e) => setLastName(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group mb-2">
                                <label htmlFor="email">Adresse mail</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group mb-2">
                                <label htmlFor="password1">Entrez un mot de passe</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="password1"
                                    value={password1}
                                    onChange={(e) => setPassword1(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group mb-2">
                                <label htmlFor="password2">Entrez à nouveau le mot de passe</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="password2"
                                    value={password2}
                                    onChange={(e) => setPassword2(e.target.value)}
                                    required
                                />
                            </div>

                            <button type="submit" className="btn btn-primary mt-2">
                                Valider
                            </button>
                            <p className="text-center pt-3">
                                Vous avez déjà un compte ? <Link to="/login">Connectez vous</Link>
                            </p>
                        </form>
                    </div>
                </div>
            <Footer />
        </>
    );
}

export default Register;
