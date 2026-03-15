import React, { useState, useEffect } from 'react';

function TransactionsAdmin() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(5);
  const [sortField, setSortField] = useState('id');
  const [sortDirection, setSortDirection] = useState('asc');

  useEffect(() => {
    // Reemplaza con la URL correcta de la API
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

  const sortedTransactions = [...transactions].sort((a, b) => {
    let field = sortField;
    if (sortField === 'venta') field = 'importe';
    let aVal = a[field];
    let bVal = b[field];
    if (field === 'id' || field === 'importe') {
      aVal = Number(aVal);
      bVal = Number(bVal);
    }
    if (sortDirection === 'asc') {
      return aVal > bVal ? 1 : -1;
    } else {
      return aVal < bVal ? 1 : -1;
    }
  });

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = sortedTransactions.slice(indexOfFirstItem, indexOfLastItem);
  const totalPages = Math.ceil(sortedTransactions.length / itemsPerPage);

  const handleCancel = async (id) => {
    const transaction = transactions.find(t => t.id === id);
    if (!transaction) return;

    try {
      // Reemplaza con la URL correcta para cancelar
      const response = await fetch(`http://localhost:8081/processor-transaction-api/updateEstatusTransaction`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          id: transaction.id,
          referencia: transaction.referencia,
          estatus: 'Cancelada' // O el estatus correcto para cancelada
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

  const handleItemsPerPageChange = (e) => {
    setItemsPerPage(Number(e.target.value));
    setCurrentPage(1);
  };

  if (loading) {
    return <div>Cargando...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h2>Administrar Transacciones</h2>
      <div style={{ marginBottom: '20px' }}>
        <label>Registros por página: </label>
        <select value={itemsPerPage} onChange={handleItemsPerPageChange}>
          <option value={5}>5</option>
          <option value={10}>10</option>
          <option value={20}>20</option>
        </select>
      </div>
      <table border="1" style={{ width: '100%', textAlign: 'left' }}>
        <thead>
          <tr>
            <th>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                <span>ID</span>
                <select
                  onChange={(e) => {
                    if (e.target.value) {
                      setSortField('id');
                      setSortDirection(e.target.value);
                    }
                  }}
                  value={sortField === 'id' ? sortDirection : ''}
                  style={{ marginLeft: '5px' }}
                >
                  <option value="">-</option>
                  <option value="asc">↑</option>
                  <option value="desc">↓</option>
                </select>
              </div>
            </th>
            <th>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                <span>Estatus</span>
                <select
                  onChange={(e) => {
                    if (e.target.value) {
                      setSortField('estatus');
                      setSortDirection(e.target.value);
                    }
                  }}
                  value={sortField === 'estatus' ? sortDirection : ''}
                  style={{ marginLeft: '5px' }}
                >
                  <option value="">-</option>
                  <option value="asc">↑</option>
                  <option value="desc">↓</option>
                </select>
              </div>
            </th>
            <th>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                <span>Referencia</span>
                <select
                  onChange={(e) => {
                    if (e.target.value) {
                      setSortField('referencia');
                      setSortDirection(e.target.value);
                    }
                  }}
                  value={sortField === 'referencia' ? sortDirection : ''}
                  style={{ marginLeft: '5px' }}
                >
                  <option value="">-</option>
                  <option value="asc">↑</option>
                  <option value="desc">↓</option>
                </select>
              </div>
            </th>
            <th>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                <span>Importe</span>
                <select
                  onChange={(e) => {
                    if (e.target.value) {
                      setSortField('venta');
                      setSortDirection(e.target.value);
                    }
                  }}
                  value={sortField === 'venta' ? sortDirection : ''}
                  style={{ marginLeft: '5px' }}
                >
                  <option value="">-</option>
                  <option value="asc">↑</option>
                  <option value="desc">↓</option>
                </select>
              </div>
            </th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {currentItems.map(item => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.estatus}</td>
              <td>{item.referencia}</td>
              <td>{item.importe}</td>
              <td>
                <button onClick={() => handleCancel(item.id)}>Cancelar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div style={{ marginTop: '20px' }}>
        <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 1}>
          Anterior
        </button>
        <span style={{ margin: '0 10px' }}>Página {currentPage} de {totalPages}</span>
        <button onClick={() => setCurrentPage(currentPage + 1)} disabled={currentPage === totalPages}>
          Siguiente
        </button>
      </div>
    </div>
  );
}

export default TransactionsAdmin;