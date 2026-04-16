import React, { useState, useEffect } from 'react';
import { DataGrid } from "@mui/x-data-grid";
import Button from "@mui/material/Button";
import Swal from "sweetalert2";
import { fetchPageTransactions, updateTransactionStatus } from '../services/transactionService';
import { useAuth0 } from "@auth0/auth0-react";

function TransactionsAdmin() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [rowCount, setRowCount] = useState(0);
  
const { getAccessTokenSilently, isAuthenticated } = useAuth0();

  const fetchTransactions = async () => {
    setLoading(true);
    const token = await getAccessTokenSilently();
    fetchPageTransactions(page,pageSize,token).then(data => {
        setTransactions(data.entityList);     // lista
        setRowCount(data.totalElements);      // total
        setLoading(false);
      })
      .catch(err => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: err.message,
        });
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchTransactions();
  }, [page, pageSize]);

  const handleCancel = async (id) => {
    const transaction = transactions.find(t => t.id === id);
    if (!transaction) return;

    try {
      const token = await getAccessTokenSilently();
      const  updatedTransaction = await updateTransactionStatus(transaction.id, transaction.referencia, 'Cancelada',token);
      setTransactions(transactions.map(t => 
        t.id === id ? { ...t, estatus: updatedTransaction.estatus } : t
      ));
      Swal.fire({
        icon: 'success',
        title: 'Transacción cancelada',
        text: `La transacción con referencia ${transaction.referencia} ha sido cancelada.`,
      });
    } catch (err) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Error al cancelar la transacción',
      });
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
          Cancelar
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
            pagination
            paginationMode="server"
            rowCount={rowCount}
            paginationModel={{ page, pageSize }} 
            onPaginationModelChange={(model) => {
              setPage(model.page);
              setPageSize(model.pageSize);
            }}
            pageSizeOptions={[5, 10, 20]}
            loading={loading}
          />
        </div>

      </div>
     
    </div>
  );
}

export default TransactionsAdmin;