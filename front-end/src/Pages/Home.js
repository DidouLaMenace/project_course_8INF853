import React from 'react'
import Footer from '../Components/Footer'
import Header from '../Components/Header'
import '../App.css'
import { useNavigate } from 'react-router-dom'


function Home() {
    const navigate = useNavigate();

    const handleSeeEventsClick = () => {navigate("/catalogue")}

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
            {/*LES DERNIERS SPECTACELS BEGIN*/}
            <div className='latestshows py-5'>
                <div className='container'>
                    <div className='row'>
                        <div className='col'>
                            <h5>Les derniers spéctacles</h5>
                        </div>
                        <div className='col text-end'>
                            <a href='/catalogue'>Voir tout</a>
                        </div>
                    </div>
                    <div className='row pt-3'>
                        <div className='col-sm'>
                            <div className="card">
                                <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Nom de spéctacle</h5>
                                    <p className="card-text">Quelques informations à propos  le spéctacle</p>
                                    <a href="#" className="btn btn-primary">Réserver</a>
                                </div>
                            </div>
                        </div>
                        <div className='col-sm'>
                            <div className="card">
                                <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Nom de spéctacle</h5>
                                    <p className="card-text">Quelques informations à propos  le spéctacle</p>
                                    <a href="#" className="btn btn-primary">Réserver</a>
                                </div>
                            </div>
                        </div>
                        <div className='col-sm'>
                            <div className="card">
                                <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Nom de spéctacle</h5>
                                    <p className="card-text">Quelques informations à propos  le spéctacle</p>
                                    <a href="#" className="btn btn-primary">Réserver</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/*LES DERNIERS SPECTACELS BEGIN*/}
            <Footer />
        </div>
    )
}

export default Home