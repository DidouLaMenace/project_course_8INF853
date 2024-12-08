import React from 'react'
import { Link } from 'react-router-dom'

function Register() {
    const registerpagestyle = {
        height: '100vh'
    }
    return (
        <div className='registerpage' style={registerpagestyle}>
            <div className='contianer w-100 h-100 d-flex justify-content-center align-items-center flex-column'>
                <form>
                    <h2 className='text-center pb-3'>Project Name</h2>
                    <div className="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
                        <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div className="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password" />
                    </div>
                    <div className="form-group">
                        <label for="exampleInputPassword1">Repeat Password</label>
                        <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password" />
                    </div>
                    <div className="form-check">
                        <input type="checkbox" className="form-check-input" id="exampleCheck1" />
                        <label className="form-check-label" for="exampleCheck1">Check me out</label>
                    </div>
                    <Link to='/dashboard'>
                        <div className='text-center pt-2'><button type="submit" className="btn btn-primary">Register</button></div>
                    </Link >
                    <p className='text-center pt-3'>Vous avez un compte ? <Link to='/login'>Connectez vous</Link></p>
                </form>
            </div>
        </div>
    )
}

export default Register