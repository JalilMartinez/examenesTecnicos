

const BASE_URL = 'http://localhost:8080/processortransactionapi';
const GET_TRANSACTIONS = 'getAllTransactions'
const UPDATE_STATUS = 'updateEstatusTransaction';

export const fetchPageTransactions = async (page, pageSize, token) => {
    const response = await fetch(`${BASE_URL}/${GET_TRANSACTIONS}?page=${page}&size=${pageSize}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      }});
    const data = await response.json(); // 👈 SIEMPRE lee el body

    if (!response.ok) {
      throw new Error(data.message || 'Error en la petición');
    }

    return data;
};

export const updateTransactionStatus = async (transactionId, referencia, status, token) => {
  const response = await fetch(`${BASE_URL}/${UPDATE_STATUS}`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    },
    body: JSON.stringify({
      id: transactionId,
      referencia: referencia,
      estatus: status 
    }),
  });

  if (!response.ok) {
    throw new Error('No se pudo actualizar el estatus de la transacción');
  }

  return await response.json();
};