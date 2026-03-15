
import './App.css'
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login.jsx';
import Layout from './components/Layout.jsx';
import TransactionAdmin from './components/TransactionsAdmin.jsx';
import TransactionsRegister from './components/TransactionsRegister.jsx';
import ProtectedRoute from './components/ProtectedRoute.jsx';


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
