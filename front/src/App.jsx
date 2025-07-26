import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

const App = () => {

  const [Characters, setCharacters] = useState([]);

  const API_URL = "http://localhost:8080/api"

  useEffect(() => {
    fetch(`${API_URL}/characters`)
      .then(response => response.json())
      .then(data => {
        if (Array.isArray(data)) setCharacters(data);
        else console.error('Formato inesperado para Characters:', data);
      })
      .catch(error => console.error('Erro ao buscar Characters:', error));
  }, []);

  return (
    <div>
      <section className='cabecalhoSection'>
        <div className="cabecalho">
          <div className="cabecalhoImage">
            <img src="https://res.cloudinary.com/dthgw4q5d/image/upload/v1753553963/png-clipart-batman-logo-batman-arkham-city-batman-arkham-asylum-batman-arkham-knight-scarecrow-batman-logo-leaf-logo-Photoroom_hglxhz.png" alt="" />
          </div>
        </div>
      </section>

      {Characters && (
      <section className='charactersSection'>
        {Characters.map((character, i) => (
          <div key={i} className="characterLine">
            <div className='characterContainer'>
              <div className="characterBoxContainer">
                {[...Array(13)].map((_, idx) => (
                  <div key={idx} className="characterBox">
                    <img src={character.image} alt="" />
                  </div>
                ))}
              </div>
            </div>
          </div>
        ))}
      </section>
    )}
    </div>
  )
}

export default App
