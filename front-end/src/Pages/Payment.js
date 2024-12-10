import React, { useState, useEffect } from 'react';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';

const Payment = () => {
    const { id } = useParams(); // Récupérer l'ID de l'événement depuis l'URL
    const navigate = useNavigate();
    const [event, setEvent] = useState(null);
    const [ticketQuantity, setTicketQuantity] = useState(1); // Par défaut, 1 place
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const API_BASE_URL = 'http://localhost:8083'; // URL de la Gateway

    useEffect(() => {
        const sessionId = Cookies.get('session-id');
        if (!sessionId) {
            setIsLoggedIn(false);
            return;
        }

        const checkSession = async () => {
            try {
                const response = await axios.get(`${API_BASE_URL}/accounts/session/validate?sessionId=${sessionId}`);
                setIsLoggedIn(response.status === 200); // Si la session est valide
            } catch (err) {
                setIsLoggedIn(false); // Si une erreur se produit
            }
        };

        checkSession();
    }, []);

    // Charger les informations de l'événement
    useEffect(() => {
        const fetchEvent = async () => {
            try {
                const response = await axios.get(`${API_BASE_URL}/catalog/events/${id}`);
                setEvent(response.data);
            } catch (error) {
                console.error('Erreur lors de la récupération des informations de l\'événement :', error);
            }
        };

        fetchEvent();
    }, [id]);

    // Gérer les modifications du nombre de tickets
    const handleTicketQuantityChange = (e) => {
        const value = parseInt(e.target.value, 10);
        setTicketQuantity(value > 0 ? value : 1); // S'assurer que le nombre est au moins 1
    };

    // Gestion du paiement avec le compte
    const handlePayWithAccount = async () => {
        try {
            const totalAmount = event.ticketPrice * ticketQuantity;

            // Étape 1: Vérifier le solde via le microservice banking
            const sessionId = Cookies.get('session-id'); // Récupérer la session de l'utilisateur

            const userIdResponse = await axios.get(`${API_BASE_URL}/accounts/session/validate?sessionId=${sessionId}`);
            const userId = userIdResponse.data;

            // Étape 1 : Vérifier le solde via le microservice banking
            const balanceResponse = await axios.get(`${API_BASE_URL}/banking/balance`, {
                params: { userId },
                headers: { 'Session-Id': sessionId },
            });

            const balance = balanceResponse.data;
            console.log('Solde utilisateur :', balance);

            if (balance < totalAmount) {
                alert('Votre solde est insuffisant pour effectuer ce paiement.');
                navigate('/add-money'); // Redirige vers la page pour ajouter de l'argent
                return;
            }

            // Étape 2: Si le solde est suffisant, mettez à jour localement et affichez une alerte
            const newBalance = balance - totalAmount;

            // Mettre à jour le solde dans la base de données via le microservice banking
            await axios.put(`${API_BASE_URL}/banking/update-balance`, null, {
                params: { userId, newBalance },
                headers: { 'Session-Id': sessionId },
            });

            console.log(`Event ID: ${event.eventId}`);

            // Enregistrez la réservation dans Inventory
            await axios.post(`${API_BASE_URL}/inventory/reservations`, null, {
                params: {
                    userId: userId, // ID utilisateur
                    eventId: event.eventId, // ID de l'événement
                },
                headers: {
                    'Session-Id': sessionId, // ID de session pour authentification
                },
            });

            alert(`Paiement réussi ! Nouveau solde : ${newBalance.toFixed(2)} €`);
            navigate('/dashboard'); // Redirection vers le tableau de bord
        } catch (error) {
            console.error('Erreur lors du traitement du paiement :', error);
            alert('Une erreur est survenue lors du paiement.');
        }
    };


    if (!event) {
        return (
            <div style={{ textAlign: 'center', padding: '20px' }}>
                <p>Chargement des informations du spectacle...</p>
            </div>
        );
    }

    return (
        <>
            <Header />
            <div className="container my-5">
                <h2 className="text-center mb-4">Paiement</h2>
                <div className="row">
                    {/* Section gauche : Détails du spectacle */}
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Détails du spectacle</h5>
                                <p><strong>Nom :</strong> {event.title}</p>
                                <p><strong>Date :</strong> {new Date(event.dateTime).toLocaleString()}</p>
                                <p><strong>Prix par place :</strong> {event.ticketPrice} €</p>
                                <p><strong>Nombre de places :</strong></p>
                                <input
                                    type="number"
                                    min="1"
                                    value={ticketQuantity}
                                    onChange={handleTicketQuantityChange}
                                    className="form-control"
                                    style={{width: '80px'}}
                                />
                                <p className="mt-3"><strong>Prix total
                                    :</strong> {(event.ticketPrice * ticketQuantity).toFixed(2)} €</p>
                            </div>
                        </div>
                    </div>
                    {/* Section droite : Options de paiement */}
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Options de paiement</h5>
                                {isLoggedIn ? (
                                    <button onClick={handlePayWithAccount} className="btn btn-success w-100 mb-3">Payer avec mon
                                        compte</button>
                                ) : (
                                    <div>
                                        <p className="text-danger d-inline">Veuillez vous connecter pour payer avec
                                            votre compte.</p>
                                        <button onClick={() => navigate('/login')}
                                                className="btn btn-primary btn-sm ms-2">
                                            Se connecter
                                        </button>
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
    );
};

export default Payment;
