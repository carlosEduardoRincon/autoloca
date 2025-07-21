import './App.css';

import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [veiculos, setVeiculos] = useState([]);

  useEffect(() => {
    axios.get("/veiculo?page=1&size=20")
        .then(res => setVeiculos(res.data))
        .catch(err => console.error(err));
  }, []);

  return (
      <div style={{ padding: 20 }}>
        <h1>Lista de veiculos</h1>
          <ul>
              {veiculos.map(v => (
                  <li key={v.id}>
                      <strong>Placa:</strong> {v.placa} <br />
                      <strong>Modelo:</strong> {v.modelo} <br />
                      <strong>Ano:</strong> {v.ano} <br />
                      <strong>Valor Diária:</strong> {v.valorDiaria}
                  </li>
              ))}
          </ul>
      </div>
  );
}

export default App;

