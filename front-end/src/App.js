import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './Pages/Home';
import Login from './Pages/Login';
import Register from './Pages/Register';
import Catalogue from './Pages/Catalogue';
import Reservation from './Pages/Reservation';
import Dashboard from './Pages/User/Dashboard';
import Payment from './Pages/Payment';
import AddMoney from "./Pages/User/AddMoney";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/catalogue' element={<Catalogue />} />
        <Route path='/reservation/:id' element={<Reservation />} />
        <Route path='/dashboard' element={<Dashboard />} />
        <Route path="/payment/:id" element={<Payment />} />
        <Route path="/add-money" element={<AddMoney />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
