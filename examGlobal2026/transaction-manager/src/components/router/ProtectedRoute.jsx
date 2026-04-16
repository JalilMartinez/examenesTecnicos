import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth0 } from "@auth0/auth0-react";

function ProtectedRoute({ children }) {
  const isAuthenticatedManual = localStorage.getItem('authenticated') === 'true';
  const { isAuthenticated, isLoading } = useAuth0();

  return isAuthenticated || isAuthenticatedManual ? children : <Navigate to="/" />;
}

export default ProtectedRoute;