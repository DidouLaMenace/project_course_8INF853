import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
import { useNavigate } from 'react-router-dom';

function Reservation() {
    const navigate = useNavigate();
    const { id } = useParams(); // Récupération de l'ID de l'événement depuis l'URL
    const [event, setEvent] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const API_BASE_URL = 'http://localhost:8083'; // URL de la Gateway

    const handleReserveClick = () => {
        navigate(`/payment/${id}`, {
            state: { eventId: id }, // Ajouter l'ID de l'événement dans l'état
        });
    };

    useEffect(() => {
        const fetchEventDetails = async () => {
            try {
                const response = await fetch(`${API_BASE_URL}/catalog/events/${id}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                if (!response.ok) {
                    throw new Error('Erreur lors de la récupération des informations de l’événement.');
                }

                const data = await response.json();
                setEvent(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchEventDetails();
    }, [id]);

    if (loading) {
        return <p className="text-center py-5">Chargement des détails de l’événement...</p>;
    }

    if (error) {
        return <p className="text-center text-danger py-5">{error}</p>;
    }

    return (
        <>
            <Header />
            <div className="reservationpage pt-5">
                <div className="container">
                    <h2 className="text-center">{event.title}</h2>
                    <img src={event.imageUrls || "https://placehold.co/600x200"} className="card-img-top"
                         alt={event.title}/>
                    <p className="text-center mt-2">{event.description}</p>
                    <p className="text-center mt-2"><strong>Date :</strong> {new Date(event.dateTime).toLocaleString()}
                    </p>
                    <p className="text-center mt-2"><strong>Prix :</strong> {event.ticketPrice}$</p>
                    <div className="text-center">
                        <button onClick={handleReserveClick} className="btn btn-primary">Réserver</button>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
    );
}

export default Reservation;
