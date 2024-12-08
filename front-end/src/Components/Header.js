import React, { useEffect, useState } from 'react'
import { useNavigate } from "react-router-dom"
import Cookies from 'js-cookie';
import axios from 'axios';

export const isUserLoggedIn = () => {
  return !!Cookies.get('session-id'); // Vérifie si le cookie "session-id" existe
};

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  const handleLoginClick = () => navigate("/login");
  const handleSignupClick = () => navigate("/register");

  const handleLogoutClick = async () => {
    try {
      const sessionId = Cookies.get('session-id');
      if (sessionId) {
        // Appeler l'endpoint de déconnexion
        await axios.post('http://localhost:8083/accounts/logout', {}, { headers: { 'Session-Id': sessionId } });
        // Supprimer le cookie session-id
        Cookies.remove('session-id');

        // Si l'utilisateur est déjà sur la page d'accueil, on recharge la page
        if (window.location.pathname === '/') {
          window.location.reload();  // Recharger la page d 'accueil
        } else {
          // Sinon, rediriger vers l'accueil
          navigate('/');
        }
      }
    } catch (error) {
      console.error("Erreur lors de la déconnexion", error);
    }
  };

  useEffect(() => {
    const sessionId = Cookies.get('session-id');

    if (!sessionId) {
      setIsLoggedIn(false);
      return;
    }

    // Vérifier la validité de la session
    axios.get(`http://localhost:8083/accounts/session/validate?sessionId=${sessionId}`)
      .then(async (response) => {
        // Si la session est valide, récupérer les informations utilisateur
        const userId = response.data;
        setIsLoggedIn(true);
        const userResponse = await axios.get(`http://localhost:8083/accounts/${userId}`, { headers: { 'Session-Id': sessionId } });
        setUser(userResponse.data);
      })
      .catch((error) => {
        // Si la session est invalide, afficher un message et rediriger
        alert('Votre session a expiré, vous serez redirigé vers la page de connexion');
        Cookies.remove('session-id');
        setIsLoggedIn(false);
        navigate("/login");
      });
  }, [navigate]);

  return (
    <header className="d-flex flex-wrap align-items-center justify-content-between py-2 border-bottom px-5">
      <div className="d-flex align-items-center">
        <a href="/" className="text-decoration-none me-3">
          <img width="50" height="50" role="img" src="/logo.png" alt="Logo" />
        </a>

        <ul className="nav col-12 col-md-auto mb-2 justify-content-start mb-md-0">
          <li><a href="/" className="nav-link px-2 link-body-emphasis">Accueil</a></li>
          <li><a href="/catalogue" className="nav-link px-2 link-body-emphasis">Catalogue</a></li>
          {isLoggedIn &&
            <li><a href="/dashboard" className="nav-link px-2 link-body-emphasis">Mes réservations</a></li>
          }
        </ul>
      </div>

      <div className="col-md-3 text-end">
        {isLoggedIn ? (
          <div className="dropdown">
            <button className="btn btn-info dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              {user ? `${user.firstName} ${user.lastName}` : 'Chargement...'}
            </button>
            <ul className="dropdown-menu dropdown-menu-end">
              <li><button className="dropdown-item" onClick={handleLogoutClick}>Se déconnecter</button></li>
            </ul>
          </div>
        ) : (
          <>
            <button type="button" onClick={handleLoginClick} className="btn btn-outline-primary me-2">Connexion</button>
            <button type="button" onClick={handleSignupClick} className="btn btn-primary">S'inscrire</button>
          </>
        )}
      </div>
    </header>
  );
}

export default Header;
