import { useEffect, useState } from 'react'
import './index.css'

const App = () => {
  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);

  const API_URL = "http://localhost:8080/api"

  useEffect(() => {
    fetch(`${API_URL}/characters`)
      .then(response => response.json())
      .then(data => {
        if (Array.isArray(data)) {
          setCharacters(data);
          setSelectedCharacter(data[0]); // define o primeiro como padrão
        } else {
          console.error('Formato inesperado para Characters:', data);
        }
      })
      .catch(error => console.error('Erro ao buscar Characters:', error));
  }, []);

  const heroes = characters.filter(c => c.role === "HEROIS");
  const villains = characters.filter(c => c.role === "VILÕES");

  return (
    <div>
      <section className='cabecalhoSection'>
        <div className="cabecalho">
          <div className="cabecalhoImage">
            <img
              src="https://res.cloudinary.com/dthgw4q5d/image/upload/v1753553963/png-clipart-batman-logo-batman-arkham-city-batman-arkham-asylum-batman-arkham-knight-scarecrow-batman-logo-leaf-logo-Photoroom_hglxhz.png"
              alt=""
            />
          </div>
        </div>
      </section>

      <div className='rowCharacter'>
        <section className='charactersSection'>
          <div className="characters">
            <div className="characterContainer">
              <h2>Heróis</h2>
              <div className="characterBoxContainer">
                {heroes.map((character, i) => (
                  <div
                    key={i}
                    className='characterBox'
                    onClick={() => setSelectedCharacter(character)}
                  >
                    <img src={character.image} alt={character.name} />
                  </div>
                ))}
              </div>
            </div>

            <div className="characterContainer">
              <h2>Vilões</h2>
              <div className="characterBoxContainer">
                {villains.map((character, i) => (
                  <div
                    key={i}
                    className='characterBox'
                    onClick={() => setSelectedCharacter(character)}
                  >
                    <img src={character.image} alt={character.name} />
                  </div>
                ))}
              </div>
            </div>
          </div>
        </section>

        {selectedCharacter && (
          <>
            <section className='characterImage'>
              <div className="containerImage">
                <img src={selectedCharacter.image} alt={selectedCharacter.name} />
              </div>
            </section>

            <section className='biografiaCharacter'>
              <div className="containerInformations">
                <div className="logoContainer">
                  <img src="https://res.cloudinary.com/dthgw4q5d/image/upload/v1753580299/logo_r7v3pn.png" alt="" />
                </div>
                <div className="boxInformations">
                  <p><strong>Nome:</strong> {selectedCharacter.name}</p>
                  <p><strong>Nome Verdadeiro:</strong> {selectedCharacter.nomeVerdadeiro}</p>  
                  <p><strong>Altura:</strong> {selectedCharacter.altura}</p>  
                  <p><strong>Ocupação:</strong> {selectedCharacter.role}</p>
                  <p><strong>Descrição:</strong> {selectedCharacter.description}</p>
                  <p><strong>Primeira Aparição:</strong> {selectedCharacter.firstAppearence}</p>
                </div>
              </div>
            </section>
          </>
        )}
      </div>

      <section className='otherSecction'>
        <div className='container'>
          <div className='box'>
            <p>Ola</p>
          </div>
        </div>
      </section>
    </div>
  )
}

export default App;
