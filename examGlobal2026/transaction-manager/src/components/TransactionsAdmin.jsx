import React, { useState, useEffect } from 'react';
import { DataGrid } from "@mui/x-data-grid";
import Button from "@mui/material/Button";

function TransactionsAdmin() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8081/processor-transaction-api/getAllTransactions')
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al cargar los datos');
        }
        return response.json();
      })
      .then(data => {
        setTransactions(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []);


  const handleCancel = async (id) => {
    const transaction = transactions.find(t => t.id === id);
    if (!transaction) return;

    try {
      const response = await fetch(`http://localhost:8081/processor-transaction-api/updateEstatusTransaction`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          id: transaction.id,
          referencia: transaction.referencia,
          estatus: 'Cancelada' 
        }),
      });
      if (!response.ok) {
        throw new Error('Error al cancelar la transacción');
      }
      const updatedTransaction = await response.json();
      // Actualizar el estatus en la lista
      setTransactions(transactions.map(t => 
        t.id === id ? { ...t, estatus: updatedTransaction.estatus } : t
      ));
    } catch (err) {
      alert('Error al cancelar: ' + err.message);
    }
  };


  if (loading) {
    return <div>Cargando...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  const columns = [
    { field: "id", headerName: "ID", flex: 0.5 },
    { field: "estatus", headerName: "Estatus", flex: 1 },
    { field: "referencia", headerName: "Referencia", flex: 1 },
    { field: "importe", headerName: "Importe", flex: 0.7 },
    {
      field: "acciones",
      headerName: "Acciones",
      flex: 0.8,
      renderCell: (params) => (
        <Button
          variant="contained"
          color="error"
          onClick={() => handleCancel(params.row.id)}
        >
          Cancel
        </Button>
      ),
    },
  ];

  return (
    <div>
       
      <h2>Administrar Transacciones</h2>
      <div>
        <div style={{ height: 500, width: "100%" }}>
          <DataGrid
            rows={transactions}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5, 10, 20]}
            pagination
            resizable
          />
        </div>

      </div>
     
    </div>
  );
}

export default TransactionsAdmin;