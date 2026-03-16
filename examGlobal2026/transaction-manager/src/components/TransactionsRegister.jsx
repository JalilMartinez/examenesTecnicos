import { useState } from 'react'

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
      const response = await fetch('http://localhost:8081/processor-transaction-api', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(transactionData)
      })
      if (response.ok) {
        const result = await response.json()
        console.log('Transaction processed:', result)
        // Reset form or show success message
        setOperacion('')
        setImporte('')
        setCliente('')
      } else {
        console.error('Error processing transaction')
      }
    } catch (error) {
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