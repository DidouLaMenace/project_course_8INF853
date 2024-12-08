import React from 'react'
import Footer from '../Components/Footer'
import Header from '../Components/Header'

function Home() {
    const herosectionstyle = {
        height: '60vh',
        backgroundColor: 'black'
    }
    return (
        <div>
            <Header />
            {/*HERO SECTION BEGIN*/}
            <div classNameName='herosection' style={herosectionstyle}>
                <div className='container w-100 h-100 d-flex justify-content-center align-items-center'>
                    <h2 className='text-center'>ESPACE PUBLICITE</h2>
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
                            <a href='#'>Voir tout</a>
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