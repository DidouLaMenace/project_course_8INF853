import React from 'react';
import { useNavigate } from 'react-router-dom';

function EventCard({ event }) {
    const navigate = useNavigate();

    const handleClick = () => {
        // Naviguer vers la page de réservation avec l'ID de l'événement
        navigate(`/reservation/${event.eventId}`);
    };

    // Définition du style pour la description avec un max de 2 lignes
    const descriptionStyle = {
        display: '-webkit-box',
        WebkitLineClamp: 2,  // Limite à 2 lignes
        WebkitBoxOrient: 'vertical',
        overflow: 'hidden',
        textOverflow: 'ellipsis',
    };

    // Style pour la carte, avec Flexbox pour garantir une hauteur constante
    const cardBodyStyle = {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',  // Espace entre le titre, la description et le bouton
        height: '100%',  // Permet à la carte d'étirer toute la hauteur disponible
    };

    const imageUrl = event.imageUrls && event.imageUrls.trim() !== "" ? event.imageUrls : "https://placehold.co/600x400";

    return (
        <div className="card d-flex shadow-sm" style={{ height: '100%' }}>
            <img
                src={imageUrl}
                className="card-img-top"
                alt={event.title}
            />
            <div className="card-body" style={cardBodyStyle}>
                <h5 className="card-title">{event.title}</h5>
                <p className="card-text" style={descriptionStyle}>
                    {event.description || 'Aucune description disponible'}
                </p>
                <button onClick={handleClick} className="btn btn-primary mt-auto">
                    Réserver
                </button>
            </div>
        </div>
    );
}

export default EventCard;
