import React from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { useAuth0 } from "@auth0/auth0-react";

function Layout() {
   const { logout } = useAuth0();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout({
          logoutParams: {
            returnTo: window.location.origin
          }
        });
    localStorage.removeItem('authenticated');
    navigate('/');
  };

  return (
    <div>
      <nav className="navbar">
        <Link to="/dashboard/admin" style={{ background: 'none', border: 'none', color: 'white', fontSize: '16px', cursor: 'pointer', padding: '10px 20px', borderRadius: '4px', transition: 'background-color 0.3s' }}>Administrar Transacciones</Link>
        <Link to="/dashboard/register" style={{ background: 'none', border: 'none', color: 'white', fontSize: '16px', cursor: 'pointer', padding: '10px 20px', borderRadius: '4px', transition: 'background-color 0.3s' }}>Registrar Transacción</Link>
        <button onClick={handleLogout}>Cerrar Sesión</button>
      </nav>
      <main style={{ padding: '20px' }}>
        <Outlet />
      </main>
    </div>
  );
}

export default Layout;