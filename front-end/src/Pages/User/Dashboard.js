import React from 'react'
import HeaderUser from '../../Components/HeaderUser'
import Footer from '../../Components/Footer'

function Dashboard() {
  return (
    <>
    <HeaderUser/>
    <div className='dashbaordhero'>
        <h1 className='text-center pt-5 pb-2'>Bonjour Mr/Mme Example Example</h1>
    </div>
    <div className='dashboardsolde pt-4'>
        <h3 className='text-center mb-2'>Votre solde :</h3>
        <h3 className='text-center color-primary mb-2'>720 $</h3>
    </div>
    <div className='dashboardhistory pt-4'>
        <div className='container'>
            <div className='row'>
                <h4 className='text-center'>Listes des réservations</h4>
            </div>
            <div className='row'>
            <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nom de spéctacle</th>
      <th scope="col">Date</th>
      <th scope="col">Prix</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Spectacle 1</td>
      <td>Date 1</td>
      <td>Prix 1</td>
      <td><button className='btn btn-primary'>Modifier</button></td>
      <td><button className='btn btn-danger'>Supprimer</button></td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Spectacle 2</td>
      <td>Date 2</td>
      <td>Prix 2</td>
      <td><button className='btn btn-primary'>Modifier</button></td>
      <td><button className='btn btn-danger'>Supprimer</button></td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Spectacle n</td>
      <td>Date n</td>
      <td>Prix n</td>
      <td><button className='btn btn-primary'>Modifier</button></td>
      <td><button className='btn btn-danger'>Supprimer</button></td>
    </tr>

  </tbody>
</table>
            </div>
        </div>
    </div>
    <Footer/>
    </>
  )
}

export default Dashboard