
import './App.css'
import { Routes, Route } from 'react-router-dom';
import Layout from './components/router/Layout.jsx';
import TransactionAdmin from './components/transactions/TransactionsAdmin.jsx';
import TransactionsRegister from './components/transactions/TransactionsRegister.jsx';
import ProtectedRoute from './components/router/ProtectedRoute.jsx';
import Login from './components/auth/Login.jsx';


function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/dashboard" element={<ProtectedRoute><Layout /></ProtectedRoute>}>
        <Route path="admin" element={<TransactionAdmin />} />
        <Route path="register" element={<TransactionsRegister />} />
      </Route>
    </Routes>
  );
}

export default App
