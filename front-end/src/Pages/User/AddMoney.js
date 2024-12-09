import React, { useState } from 'react';
import Header from '../../Components/Header';
import Footer from '../../Components/Footer';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';

function AddMoney() {
    const [amount, setAmount] = useState('');
    const [cardNumber, setCardNumber] = useState('');
    const [cardHolder, setCardHolder] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [cvv, setCvv] = useState('');
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();
    const [error, setError] = useState(null);
    const [balance, setBalance] = useState(null);


    const API_BASE_URL = 'http://localhost:8083'; // URL de la Gateway

    // Gestion de l'écriture fluide et des validations pour les champs
    const handleCardNumberChange = (e) => {
        const input = e.target.value.replace(/\D/g, ''); // Enlever tout sauf les chiffres
        if (input.length <= 16) {
            setCardNumber(input.replace(/(.{4})/g, '$1 ').trim()); // Ajouter un espace tous les 4 chiffres
            if (input.length < 16) {
                setError('Le numéro de carte doit contenir exactement 16 chiffres.');
            } else {
                setError(null);
            }
        }
    };

    const handleExpiryDateChange = (e) => {
        const input = e.target.value.replace(/\D/g, ''); // Enlever tout sauf les chiffres
        if (input.length <= 4) {
            setExpiryDate(input.replace(/(\d{2})/, '$1/')); // Ajouter un slash après le mois
        }
    };

    const handleExpiryDateValidation = () => {
        const [month, year] = expiryDate.split('/');
        const today = new Date();
        const expirationDate = new Date(`20${year}`, month - 1); // Mois indexé à 0
        if (!month || !year || expirationDate <= today) {
            setError('La date d\'expiration doit être valide et supérieure à aujourd\'hui.');
        } else {
            setError(null);
        }
    };

    const handleCvvChange = (e) => {
        const input = e.target.value.replace(/\D/g, ''); // Enlever tout sauf les chiffres
        if (input.length <= 3) {
            setCvv(input);
            if (input.length < 3) {
                setError('Le CVV doit contenir exactement 3 chiffres.');
            } else {
                setError(null);
            }
        }
    };

    const handleAddMoney = async () => {
        if (error) return; // Ne pas soumettre si une erreur est présente

        try {
            const sessionId = Cookies.get('session-id');
            if (!sessionId) {
                alert('Veuillez vous connecter.');
                navigate('/login');
                return;
            }

            if (amount <= 0) {
                setMessage("Le montant doit être supérieur à 0.");
                return;
            }

            // Récupérer l'ID utilisateur à partir de la session
            const userIdResponse = await axios.get(`${API_BASE_URL}/accounts/session/validate?sessionId=${sessionId}`);
            const userId = userIdResponse.data;

            // Récupérer le solde actuel
            const currentBalanceResponse = await axios.get(`${API_BASE_URL}/banking/balance`, {
                params: { userId },
            });
            const currentBalance = currentBalanceResponse.data;

            // Calculer le nouveau solde
            const newBalance = parseFloat(currentBalance) + parseFloat(amount);

            // Envoyer la mise à jour du solde au backend
            const updateResponse = await axios.put(
                `${API_BASE_URL}/banking/update-balance`,
                null,
                {
                    params: { userId, newBalance },
                    headers: { 'Session-Id': sessionId },
                }
            );

            if (updateResponse.status === 200) {
                setMessage('Votre compte a été rechargé avec succès.');
                setBalance(newBalance); // Mettre à jour localement le solde
                setAmount(''); // Réinitialiser les champs
                setCardNumber('');
                setCardHolder('');
                setExpiryDate('');
                setCvv('');
            } else {
                setMessage('Une erreur est survenue lors de la mise à jour de votre solde.');
            }
        } catch (err) {
            console.error('Erreur lors de la mise à jour du solde :', err);
            setMessage('Une erreur est survenue. Veuillez réessayer.');
        }
    };

    return (
        <>
            <Header />
            <div className="container my-5">
                <h2 className="text-center mb-4">Ajouter de l'argent</h2>
                <div className="card mx-auto" style={{ maxWidth: '500px' }}>
                    <div className="card-body">
                        <form>
                            <div className="mb-3">
                                <label htmlFor="amount" className="form-label">Montant (en €)</label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="amount"
                                    value={amount}
                                    onChange={(e) => {
                                        const value = parseFloat(e.target.value);
                                        if (value >= 0 || isNaN(value)) {
                                            setAmount(value);
                                            setError(null);
                                        } else {
                                            setError("Le montant ne peut pas être négatif.");
                                        }
                                    }}
                                    required
                                    min="0"
                                />
                            </div>
                            <h5 className="mt-4">Informations bancaires</h5>
                            <div className="mb-3">
                                <label htmlFor="cardNumber" className="form-label">Numéro de carte</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="cardNumber"
                                    value={cardNumber}
                                    onChange={handleCardNumberChange}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="cardHolder" className="form-label">Nom sur la carte</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="cardHolder"
                                    value={cardHolder}
                                    onChange={(e) => setCardHolder(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="row">
                                <div className="col-md-6">
                                    <label htmlFor="expiryDate" className="form-label">Date d'expiration</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="expiryDate"
                                        placeholder="MM/AA"
                                        value={expiryDate}
                                        onChange={handleExpiryDateChange}
                                        onBlur={handleExpiryDateValidation}
                                        required
                                    />
                                </div>
                                <div className="col-md-6">
                                    <label htmlFor="cvv" className="form-label">CVV</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="cvv"
                                        value={cvv}
                                        onChange={handleCvvChange}
                                        required
                                    />
                                </div>
                            </div>
                            <button type="button" onClick={handleAddMoney} className="btn btn-primary w-100 mt-4">
                                Ajouter
                            </button>
                        </form>
                        {error && <p className="mt-3 text-center text-danger">{error}</p>}
                        {message && <p className="mt-3 text-center text-success">{message}</p>}
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default AddMoney;
