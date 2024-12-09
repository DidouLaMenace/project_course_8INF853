import React, { useEffect, useState } from 'react';
import Header from '../Components/Header';
import Footer from '../Components/Footer';

const Catalogue = () => {
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
          const errorMessage = await response.text();
          throw new Error(errorMessage || 'Erreur lors de la récupération des événements.');
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

  if (loading) {
    return <p>Chargement des événements...</p>;
  }

  if (error) {
    return <p style={{ color: 'red' }}>{error}</p>;
  }

  return (
      <>
        <Header />
        <div style={{ padding: '20px' }}>
          <h1>Catalogue des événements</h1>
          <div
              style={{
                display: 'flex',
                flexWrap: 'wrap',
                justifyContent: 'space-between',
                gap: '10px',
              }}
          >
            {events.map((event) => (
                <div
                    key={event.eventId}
                    style={{
                      border: '1px solid #ccc',
                      borderRadius: '10px',
                      padding: '10px',
                      width: '30%',
                    }}
                >
                  {event.imageUrls && (
                      <img
                          src={event.imageUrls}
                          alt={event.title}
                          style={{ width: '100%', borderRadius: '5px' }}
                      />
                  )}
                  <h2>{event.title}</h2>
                  <p>{event.description}</p>
                    <p>
                        <strong>Date :</strong> {new Date(event.dateTime).toLocaleString('fr-FR')}
                    </p>

                    <p>
                        <strong>Lieu :</strong> {event.location}
                    </p>
                  <button
                      style={{
                        marginTop: '10px',
                        padding: '8px 12px',
                        backgroundColor: '#4CAF50',
                        color: '#fff',
                        border: 'none',
                        cursor: 'pointer',
                      }}
                      onClick={() => alert(`Billet pour ${event.title} réservé !`)}
                  >
                    Réserver
                  </button>
                </div>
            ))}
          </div>
        </div>
        <Footer />
      </>
  );
};

export default Catalogue;
