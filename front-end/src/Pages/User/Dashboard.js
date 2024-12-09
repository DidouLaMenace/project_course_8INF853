import React, { useEffect, useState } from 'react';
import Header from '../../Components/Header';
import Footer from '../../Components/Footer';
import axios from 'axios';
import Cookies from 'js-cookie';

function Dashboard() {
    const [userId, setUserId] = useState(null);
    const [userName, setUserName] = useState(''); // Pour afficher le nom de l'utilisateur
    const [balance, setBalance] = useState(null);
    const [reservations, setReservations] = useState([]);
    const [error, setError] = useState(null);

    const API_BASE_URL = 'http://localhost:8083'; // URL de la Gateway

    // Récupérer l'ID utilisateur
    useEffect(() => {
        const sessionId = Cookies.get('session-id');
        if (!sessionId) {
            setError('Utilisateur non connecté');
            return;
        }

        const fetchUserId = async () => {
            try {
                const response = await axios.get(`${API_BASE_URL}/accounts/session/validate?sessionId=${sessionId}`);
                setUserId(response.data);
            } catch (err) {
                console.error('Erreur lors de la récupération de l\'ID utilisateur :', err);
                setError('Erreur de session');
            }
        };

        fetchUserId();
    }, []);

    // Récupérer le solde
    useEffect(() => {
        if (!userId) return;

        const fetchBalance = async () => {
            try {
                const response = await axios.get(`${API_BASE_URL}/bank/balance`, {
                    params: { userId },
                });
                setBalance(response.data.balance);
            } catch (err) {
                console.error('Erreur lors de la récupération du solde :', err);
                setBalance(null);
            }
        };

        fetchBalance();
    }, [userId]);

    // Récupérer les réservations
    useEffect(() => {
        //if (!userId) return;
        const sessionId = Cookies.get('session-id');
        if (!sessionId) {
            setError('Utilisateur non connecté');
            return;
        }

        const fetchUserData = async () => {
            try {
                // Valider la session pour obtenir l'ID utilisateur
                const sessionResponse = await axios.get(`${API_BASE_URL}/accounts/session/validate?sessionId=${sessionId}`);
                const userId = sessionResponse.data;
                setUserId(userId);
            } catch (err) {
                console.error('Erreur lors de la récupération des informations utilisateur :', err);
                setError('Erreur de session');
            }
        };
        fetchUserData();

        const fetchUserName = async () => {
            try {
                const userResponse = await axios.get(`${API_BASE_URL}/accounts/${userId}/firstname`);
                setUserName(userResponse.data); // Récupère directement le prénom
            } catch (error) {
                console.error("Erreur lors de la récupération du prénom de l'utilisateur :", error);
                setUserName("Utilisateur"); // Valeur par défaut en cas d'erreur
            }
        };

        fetchUserName();

        const fetchReservations = async () => {
            try {
                const response = await axios.get(`${API_BASE_URL}/inventory/user/${userId}`, {
                    params: { userId },
                });
                setReservations(response.data);
            } catch (err) {
                console.error('Erreur lors de la récupération des réservations :', err);
                setReservations([]);
            }
        };

        fetchReservations();
    }, [userId]);

    return (
        <>
            <Header />
            <div className='dashbaordhero'>
                <h1 className='text-center pt-5 pb-2'>
                    Bonjour {userName || 'Utilisateur'} {/* Affiche le nom de l'utilisateur */}
                </h1>
                <h5 className='text-center'>Votre ID : {userId || 'Chargement...'}</h5>
            </div>
            <div className='dashboardsolde pt-4'>
                <h3 className='text-center mb-2'>Votre solde :</h3>
                <h3 className='text-center color-primary mb-2'>
                    {balance !== null ? `${balance} €` : 'Chargement...'}
                </h3>
            </div>
            <div className='dashboardhistory pt-4'>
                <div className='container'>
                    <div className='row'>
                        <h4 className='text-center'>Listes des réservations</h4>
                    </div>
                    <div className='row'>
                        <table className='table'>
                            <thead>
                            <tr>
                                <th scope='col'>#</th>
                                <th scope='col'>Nom de spéctacle</th>
                                <th scope='col'>Date</th>
                                <th scope='col'>Prix</th>
                                <th scope='col'>Modifier</th>
                                <th scope='col'>Supprimer</th>
                            </tr>
                            </thead>
                            <tbody>
                            {reservations.length > 0 ? (
                                reservations.map((reservation, index) => (
                                    <tr key={reservation.id}>
                                        <th scope='row'>{index + 1}</th>
                                        <td>{reservation.eventName}</td>
                                        <td>{new Date(reservation.eventDate).toLocaleDateString()}</td>
                                        <td>{reservation.price} €</td>
                                        <td>
                                            <button className='btn btn-primary'>Modifier</button>
                                        </td>
                                        <td>
                                            <button className='btn btn-danger'>Supprimer</button>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan='6' className='text-center'>
                                        Aucun réservation trouvée.
                                    </td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Dashboard;
