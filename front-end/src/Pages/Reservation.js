import React from 'react'
import Header from '../Components/Header'
import Footer from '../Components/Footer'

function Reservation() {
  return (
    <>
    <Header/>
    <div className='reservationpage pt-5'>
        <div className='container'>
            <h2 className='text-center'>Nom de spéctacle</h2>
            <img src="https://placehold.co/600x200" className="card-img-top" alt="..." />
            <p className='text-center mt-2'>Quelques informations a propos le spéctacle</p>
            <p className='text-center mt-2'>Date : 21/01/2025 </p>
            <p className='text-center mt-2'>Prix : 250$ </p>
            <div className='text-center'>
            <button className='btn btn-primary'>Réserver</button>
            </div>
        </div>
    </div>
    <Footer/>
    </>
  )
}

export default Reservation