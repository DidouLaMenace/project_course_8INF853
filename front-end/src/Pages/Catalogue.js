import React, { useState, useEffect } from 'react';
import axios from 'axios';
import EventCard from '../Components/EventCard';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
const CataloguePage = () => {
  const [searchText, setSearchText] = useState('');
  const [categoryId, setCategoryId] = useState('');
  const [categories, setCategories] = useState([]);
  const [events, setEvents] = useState([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);

  // Charger les catégories au démarrage
  useEffect(() => {
    axios.get('http://localhost:8083/catalog/categories')
      .then(response => {
        setCategories(response.data);
      })
      .catch(error => console.error("Erreur lors de la récupération des catégories", error));
  }, []);

  // Charger les événements avec les filtres
  const loadEvents = (resetPage = false) => {
    setLoading(true);
    const currentPage = resetPage ? 0 : page;
    axios.get('http://localhost:8083/catalog/events', {
      params: {
        categoryId: categoryId || undefined,
        searchText: searchText || undefined,
        page: currentPage,
        size: 6
      }
    })
      .then(response => {
        const fetchedEvents = response.data.content;
        if (resetPage) {
          setEvents(fetchedEvents);
        } else {
          setEvents(prevEvents => [...prevEvents, ...fetchedEvents]);
        }
        setHasMore(fetchedEvents.length === 6); // Si on a exactement 6 événements, on peut en charger plus
        setPage(currentPage + 1);
      })
      .catch(error => console.error("Erreur lors de la récupération des événements", error))
      .finally(() => setLoading(false));
  };

  // Effacer les événements et charger de nouveau si on change les filtres
  useEffect(() => {
    loadEvents(true);
  }, [searchText, categoryId]);

  // Handler pour la recherche
  const handleSearchChange = (e) => {
    setSearchText(e.target.value);
  };

  // Handler pour le changement de catégorie
  const handleCategoryChange = (e) => {
    setCategoryId(e.target.value);
  };

  // Handler pour charger plus d'événements
  const handleLoadMore = () => {
    loadEvents();
  };

  return (
    <>
      <Header />
      <div className="cataloguepage">
        <div className="catalogueheader">
          <h2 className="text-center py-5">Catalogue des événements</h2>
        </div>
        <div className="container mb-5">
          <div className="row">
            {/* FILTRES */}
            <div className="col-3 container">
              <h4 className="mb-3">Filtrer les résultats</h4>

              <div className="d-flex flex-column form-group mb-2">
                <label htmlFor="searchTextInput">Chercher un mot-clé</label>
                <input
                  type="text"
                  className="form-control"
                  id="searchTextInput"
                  value={searchText}
                  onChange={handleSearchChange}
                  placeholder="Entrez un mot-clé"
                />
              </div>

              <div className="d-flex flex-column form-group mb-2">
                <label htmlFor="categorySelect">Sélectionner une catégorie</label>
                <select
                  className="form-select"
                  id="categorySelect"
                  value={categoryId}
                  onChange={handleCategoryChange}
                >
                  <option value="">Aucune catégorie sélectionnée</option>
                  {categories.map((category) => (
                    <option key={category.categoryId} value={category.categoryId}>
                      {category.name}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            {/* EVENTS */}
            <div className="col-9">
              <div className="row w-100 d-flex justify-content-center mb-3">
                {events.length > 0 ? (
                  events.map((event) => (
                    <div className="col-4 mb-4" key={event.event_id}>
                      <EventCard event={event} />
                    </div>
                  ))
                ) : (
                  <p className="text-center">Aucun événement trouvé.</p>
                )}
              </div>

              {/* CHARGER PLUS D'ÉLÉMENTS */}
              {hasMore && (
                <div className="d-flex justify-content-center w-100 mt-4">
                  <button onClick={handleLoadMore} className="btn btn-secondary px-3" disabled={loading}>
                    {loading ? 'Chargement...' : 'Charger plus d\'éléments'}
                  </button>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default CataloguePage;
