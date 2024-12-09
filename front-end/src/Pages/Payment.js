import React, { useState, useEffect } from 'react';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const Payment = () => {
    const { id } = useParams(); // Récupérer l'ID de l'événement depuis l'URL
    const navigate = useNavigate();
    const [event, setEvent] = useState(null);
    const [ticketQuantity, setTicketQuantity] = useState(1); // Par défaut, 1 place

    const API_BASE_URL = 'http://localhost:8083'; // URL de la Gateway

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
            const paymentPayload = {
                eventId: id,
                ticketQuantity,
                totalAmount: event.ticketPrice * ticketQuantity,
            };

            const response = await axios.post(`${API_BASE_URL}/payments/process`, paymentPayload, {
                withCredentials: true, // Inclure les cookies de session
            });

            if (response.status === 200) {
                alert('Paiement réussi avec votre compte !');
                navigate('/success'); // Rediriger après le paiement
            } else {
                alert('Échec du paiement.');
            }
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
                                    style={{ width: '80px' }}
                                />
                                <p className="mt-3"><strong>Prix total :</strong> {(event.ticketPrice * ticketQuantity).toFixed(2)} €</p>
                            </div>
                        </div>
                    </div>
                    {/* Section droite : Options de paiement */}
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Options de paiement</h5>
                                <button
                                    onClick={handlePayWithAccount}
                                    className="btn btn-success w-100 mb-3"
                                >
                                    Payer avec mon compte
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
};

export default Payment;
