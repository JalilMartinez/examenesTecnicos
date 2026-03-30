import { useState } from 'react'
import Swal from "sweetalert2"
function TransactionsRegister() {
  const [operacion, setOperacion] = useState('')
  const [importe, setImporte] = useState('')
  const [cliente, setCliente] = useState('')

  const enviar = async () => {
    console.log("enviando ...");
    
    
    const dataToHash = `${operacion}|${parseFloat(importe)}|${cliente}`
    const hashBuffer = await crypto.subtle.digest('SHA-256', new TextEncoder().encode(dataToHash))
    const hashArray = Array.from(new Uint8Array(hashBuffer))
    const firma = hashArray.map(b => b.toString(16).padStart(2, '0')).join('')
    console.log(firma);
    const transactionData = {
      operacion,
      importe: parseFloat(importe),
      cliente,
      firma
    }
    try {
      const token  = localStorage.getItem('token')
      const response = await fetch('http://localhost:8081/processortransactionapi', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization' : `Bearer ${token}`
        },
        body: JSON.stringify(transactionData)
      })
      if (response.ok) {
        const result = await response.json()
        Swal.fire({
          icon: 'success',
          title: 'Transacción registrada',
          text: `ID de transacción: ${result.id}`,
        });
        // Reset form or show success message
        setOperacion('')
        setImporte('')
        setCliente('')
      } else {
        const errorData = await response.json();
        let mensajeMostrar = 'Ocurrió un error en la validación';
        if (errorData.errores) {
            const listaMensajes = Object.values(errorData.errores); 
            mensajeMostrar = listaMensajes.join('\n'); 
        }
        Swal.fire({
            icon: 'error',
            title: 'Datos no válidos',
            text: mensajeMostrar,
            confirmButtonText: 'Corregir'
        });
      }
    } catch (error) {
      swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Error al registrar la transacción',
      });
      console.error('Error:', error)
    }
  }

  return (
    <div className="form-container">
      <h2>Registrar operación</h2>
      <form>
        <div className="form-group">
          <input type="text" id="operacion" name="operacion" placeholder="Operación" value={operacion} onChange={(e) => setOperacion(e.target.value)} />
        </div>
        <div className="form-group">
          <input type="number" id="importe" name="importe" placeholder="Importe" value={importe} onChange={(e) => setImporte(e.target.value)} />
        </div>
        <div className="form-group">
          <input type="text" id="cliente" name="cliente" placeholder="Cliente" value={cliente} onChange={(e) => setCliente(e.target.value)} />
        </div>
        <button type="button" onClick={enviar}>Enviar</button>
      </form>
    </div>
  )
}

export default TransactionsRegister