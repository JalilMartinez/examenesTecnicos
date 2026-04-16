import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom';
import { Auth0Provider } from "@auth0/auth0-react";
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Auth0Provider
      domain="dev-xjsdws42csm43zql.us.auth0.com"
      clientId="GmZeDHVeOmqqhx04wRPEIMUqQJ3Vnjc6"
      authorizationParams={{ 
        redirect_uri: window.location.origin ,
        audience: "https://transaccionsApi"
      }}
    >
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Auth0Provider>
  </StrictMode>,
)
