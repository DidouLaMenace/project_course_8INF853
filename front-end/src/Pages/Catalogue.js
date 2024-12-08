import React from 'react'
import Header from '../Components/Header'
import Footer from '../Components/Footer'
import { Link } from 'react-router-dom'

function Catalogue() {
  return (
    <>
      <Header />
      <div className='cataloguepage'>
        <div className='catalogueheader'>
          <h2 className='text-center pt-5'>STORE</h2>
          <p className='text-center'>TU TROUVES CE QUE TU VOUDRAIS</p>
        </div>
        <div className='store'>
          <div className='container'>
            <div className='row'>
              {/*FILTRES*/}
              <div className='col-3'>
                <div className='container d-flex flex-column justify-content-center align-items-center'>
                  <div className="input-group mt-2 mb-3">
                    <input type="text" className="form-control" placeholder="SAISIR CE QUE TU VEUX" aria-label="Recipient's username" aria-describedby="basic-addon2" />
                    <span className="input-group-text" id="basic-addon2">CHERCHER</span>
                  </div>
                  <select className="form-select mb-3" aria-label="Default select example">
                    <option selected>Sélection de la choix 1</option>
                    <option value="1">Choix 1.1</option>
                    <option value="2">Choix 1.1</option>
                    <option value="3">Choix 1.1</option>
                  </select>
                  <select className="form-select mb-3" aria-label="Default select example">
                    <option selected>Sélection de la choix 2</option>
                    <option value="1">Choix 2.1</option>
                    <option value="2">Choix 2.1</option>
                    <option value="3">Choix 2.1</option>
                  </select>
                  <div className="form-check form-switch mb-3">
                    <input className="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" />
                    <label className="form-check-label" for="flexSwitchCheckDefault">switch checkbox input 1</label>
                  </div>
                  <div className="form-check form-switch mb-3">
                    <input className="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" />
                    <label className="form-check-label" for="flexSwitchCheckDefault">switch checkbox input 1</label>
                  </div>
                  <div className="form-check form-switch mb-3">
                    <input className="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" />
                    <label className="form-check-label" for="flexSwitchCheckDefault">switch checkbox input 1</label>
                  </div>
                </div>
              </div>
              {/*STORE*/}
              <div className='col-9'>
                <div className='row w-100 d-flex justify-content-center mb-3'>
                  <div className="card col-3 mx-3">
                    <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Nom de spéctacle</h5>
                      <p className="card-text">Quelques informations à propos  le spéctacle</p>
                      <Link to="/reservation" className="btn btn-primary">Réserver</Link>
                    </div>
                  </div>
                  <div className="card col-3 mx-3">
                    <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Nom de spéctacle</h5>
                      <p className="card-text">Quelques informations à propos  le spéctacle</p>
                      <Link to="/reservation" className="btn btn-primary">Réserver</Link>
                    </div>
                  </div>
                  <div className="card col-3 mx-3">
                    <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Nom de spéctacle</h5>
                      <p className="card-text">Quelques informations à propos  le spéctacle</p>
                      <Link to="/reservation" className="btn btn-primary">Réserver</Link>
                    </div>
                  </div>
                </div>
                <div className='row w-100 d-flex justify-content-center mb-3'>
                  <div className="card col-3 mx-3">
                    <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Nom de spéctacle</h5>
                      <p className="card-text">Quelques informations à propos  le spéctacle</p>
                      <Link to="/reservation" className="btn btn-primary">Réserver</Link>
                    </div>
                  </div>
                  <div className="card col-3 mx-3">
                    <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Nom de spéctacle</h5>
                      <p className="card-text">Quelques informations à propos  le spéctacle</p>
                      <Link to="/reservation" className="btn btn-primary">Réserver</Link>
                    </div>
                  </div>
                  <div className="card col-3 mx-3">
                    <img src="https://placehold.co/600x400" className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h5 className="card-title">Nom de spéctacle</h5>
                      <p className="card-text">Quelques informations à propos  le spéctacle</p>
                      <Link to="/reservation" className="btn btn-primary">Réserver</Link>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>

  )
}

export default Catalogue