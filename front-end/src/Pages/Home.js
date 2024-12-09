import React, { useEffect, useState } from 'react'
import Footer from '../Components/Footer'
import Header from '../Components/Header'
import '../App.css'
import { useNavigate } from 'react-router-dom'
import EventCard from '../Components/EventCard';

function Home() {
    const [events, setEvents] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Effectuer la requête pour récupérer les prochains événements
        fetch('http://localhost:8083/catalog/events?page=0&size=3')
            .then(response => response.json())
            .then(data => {
                // Mettre à jour le state avec les événements récupérés
                setEvents(data.content);
            })
            .catch(error => console.error('Erreur lors de la récupération des événements :', error));
    }, []);

    const handleSeeEventsClick = () => {
        navigate("/catalogue")
    }

    return (
        <div>
            <Header />
            {/*HERO SECTION BEGIN*/}
            <div className="px-4 py-5 text-center border-bottom hero-bg">
                <h2 className="fw-bold mt-5">Votre boutique de tickets d'évenements en ligne</h2>
                <div className="col-lg-6 mx-auto mb-5">
                    <p className="lead mb-4 fw-medium">Avec Prixbanque, trouvez vos places de concerts, de festivals, d'expositions et bien d'autres au meilleur prix. Simplifiez vos réservations et profitez d’une expérience inoubliable. Trouvez votre prochaine aventure dès aujourd’hui !</p>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
                        <button type="button" onClick={handleSeeEventsClick} className="btn btn-primary btn-lg px-4 me-sm-3">Voir tous les évenements</button>
                    </div>
                </div>
            </div>
            {/*HERO SECTION FINISH*/}

            {/*LES DERNIERS SPECTACLES BEGIN*/}
            <div className='latestshows py-5'>
                <div className='container'>
                    <div className='row'>
                        <div className='col'>
                            <h5>Les évenements à venir</h5>
                        </div>
                        <div className='col text-end'>
                            <a href='/catalogue'>Voir tout</a>
                        </div>
                    </div>
                    <div className='row pt-3'>
                        {events.length > 0 ? (
                            events.map(event => (
                                <div className='col-sm' key={event.event_id}>
                                    <EventCard event={event} />
                                </div>
                            ))
                        ) : (
                            <div className="col-sm">
                                <p>Chargement des événements...</p>
                            </div>
                        )}
                    </div>
                </div>
            </div>
            {/*LES DERNIERS SPECTACLES FINISH*/}

            <Footer />
        </div>
    )
}

export default Home
