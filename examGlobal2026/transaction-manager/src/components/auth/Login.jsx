import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Swal from "sweetalert2";
import { useAuth0 } from "@auth0/auth0-react";

function Login() {
  const [userName, setUser] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
            
      const response = await fetch('http://localhost:8080/auth/authenticateUser', { 
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userName, psw: password }),
      });
      const data = await response.json();
      if (!response.ok) {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: data.message,
        });
        throw new Error('Credenciales incorrectas');
      }

      
      if (!data) {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Credenciales incorrectas',
        });
        throw new Error('Credenciales incorrectass');
      }
      localStorage.setItem('authenticated', 'true');
      localStorage.setItem('token',data.token)
      navigate('/dashboard/register');
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const { loginWithRedirect } = useAuth0();

  return (
    <div className="form-container">
      <h2>Login</h2>
      <form>
        <div className="form-group">
          <label>Usuario:</label>
          <input
            type="text"
            value={userName}
            onChange={(e) => setUser(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Contraseña:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="button" disabled={loading} onClick={() => loginWithRedirect()}>
          {loading ? 'Cargando...' : 'Iniciar Sesión'}
        </button>
        
      </form>
    </div>
  );
}

export default Login;