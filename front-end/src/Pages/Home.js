import React, { useEffect, useState } from 'react';
import Footer from '../Components/Footer'
import Header from '../Components/Header'
import '../App.css'
import { useNavigate } from 'react-router-dom'


function Home() {
    const navigate = useNavigate();

    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const API_BASE_URL = 'http://localhost:8083'; // URL de la Gateway

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const response = await fetch(`${API_BASE_URL}/catalog/events`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                if (!response.ok) {
                    throw new Error('Erreur lors de la récupération des événements.');
                }

                const data = await response.json();
                setEvents(data.slice(0, 3)); // Limiter à 3 événements
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchEvents();
    }, []);

    const handleSeeEventsClick = () => {
        navigate('/catalogue');
    };

    if (loading) {
        return <p>Chargement des derniers spectacles...</p>;
    }

    if (error) {
        return <p style={{ color: 'red' }}>{error}</p>;
    }

    return (
        <div>
            <Header />
            {/*HERO SECTION BEGIN*/}
            <div className="px-4 py-5 text-center border-bottom hero-bg">
                <h2 className="fw-bold mt-5">Votre billetterie en ligne</h2>
                <div className="col-lg-6 mx-auto mb-5">
                    <p className="lead mb-4 fw-medium">Avec Prixbanque, trouvez vos places de concerts, de festivals, de pièces de théâtres et bien d'autres au meilleur prix. Simplifiez vos réservations et profitez d’une expérience inoubliable. Trouvez votre prochaine aventure dès aujourd’hui !</p>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
                        <button type="button" onClick={handleSeeEventsClick} className="btn btn-primary btn-lg px-4 me-sm-3">Voir tous les événements</button>
                    </div>
                </div>
            </div>
            {/*HERO SECTION FINISH*/}
            {/*LES DERNIERS SPECTACELS BEGIN*/}
            <div className="latestshows py-5">
                <div className="container">
                    <div className="row">
                        <div className="col">
                            <h5>Les derniers spéctacles</h5>
                        </div>
                        <div className="col text-end">
                            <a href="/catalogue">Voir tout</a>
                        </div>
                    </div>
                    <div className="row pt-3">
                        {events.map((event) => (
                            <div className="col-sm" key={event.eventId}>
                                <div className="card">
                                    <img
                                        src={event.imageUrls || 'https://placehold.co/600x400'}
                                        className="card-img-top"
                                        alt={event.title}
                                    />
                                    <div className="card-body">
                                        <h5 className="card-title">{event.title}</h5>
                                        <p className="card-text">{event.description}</p>
                                        <p>
                                            <strong>Date :</strong>{' '}
                                            {new Date(event.dateTime).toLocaleString('fr-FR')}
                                        </p>
                                        <p>
                                            <strong>Lieu :</strong> {event.location}
                                        </p>
                                        <a href="#" className="btn btn-primary">
                                            Réserver
                                        </a>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            {/*LES DERNIERS SPECTACELS BEGIN*/}
            <Footer/>
        </div>
    )
}

export default Home