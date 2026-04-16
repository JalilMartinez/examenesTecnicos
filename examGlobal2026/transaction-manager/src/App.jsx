
import './App.css'
import { Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/router/Layout.jsx';
import TransactionAdmin from './components/transactions/TransactionsAdmin.jsx';
import TransactionsRegister from './components/transactions/TransactionsRegister.jsx';
import ProtectedRoute from './components/router/ProtectedRoute.jsx';
import Login from './components/auth/Login.jsx';
import { useAuth0 } from "@auth0/auth0-react";
import { useEffect } from 'react'

function App() {
  const { isAuthenticated, isLoading, loginWithRedirect } = useAuth0();

  useEffect(() => {
    if (!isLoading && !isAuthenticated) {
      loginWithRedirect();
    }
  }, [isLoading, isAuthenticated]);

  return (
    
    <Routes>
      <Route
        path="/"
        element={
          isAuthenticated
            ? <Navigate to="/dashboard" />
            : <Login />
        }
      />
      <Route path="/dashboard" element={<ProtectedRoute><Layout /></ProtectedRoute>}>
        <Route
          index
          element={<Navigate to="register" />}
        />
        <Route path="admin" element={<TransactionAdmin />} />
        <Route path="register" element={<TransactionsRegister />} />
      </Route>
    </Routes>
  );
}

export default App
