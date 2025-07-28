import { useEffect, useState } from 'react'
import './index.css'

const App = () => {
  const [characters, setCharacters] = useState([]);
  const [locations, setLocations] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [selectedLocation, setSelectedLocation] = useState(null);

  const API_URL = "http://localhost:8080/api"
  
  const [imageIndex, setImageIndex] = useState(0);

  function handleIndex() {
  if (selectedLocation) {
    const totalImages = Object.keys(selectedLocation)
      .filter(key => key.startsWith("image"))
      .length;

    setImageIndex((prevIndex) => (prevIndex + 1) % totalImages);
  }
}


  useEffect(() => {
    fetch(`${API_URL}/characters`)
      .then(response => response.json())
      .then(data => {
        if (Array.isArray(data)) {
          setCharacters(data);
          setSelectedCharacter(data[0]); 
        } else {
          console.error('Formato inesperado para Characters:', data);
        }
      })
      .catch(error => console.error('Erro ao buscar Characters:', error));
  }, []);

  useEffect(() => {
    fetch(`${API_URL}/locations`)
      .then(response => response.json())
      .then(data => {
        if (Array.isArray(data)) {
          setLocations(data);
          setSelectedLocation(data[0]); 
        } else {
          console.error('Formato inesperado para Locations:', data);
        }
      })
      .catch(error => console.error('Erro ao buscar Locations:', error));
  }, []);

  const heroes = characters.filter(c => c.role === "HEROIS");
  const villains = characters.filter(c => c.role === "VILOES");

  return (
    <div>
      <section className='cabecalhoSection'>
        <div className="cabecalho">
          <div className="cabecalhoImage">
            <img
              src="https://res.cloudinary.com/dthgw4q5d/image/upload/v1753580299/logo_r7v3pn.png"
              alt=""
            />
          </div>
        </div>
      </section>

      <div className='rowCharacter'>
        <section className='charactersSection'>
          <div className="characters">
            <div className="characterContainer">
              <h2>Herois</h2>
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
              <h2>Viloes</h2>
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
                  <p><strong>Ocupacao:</strong> {selectedCharacter.role}</p>
                  <p><strong>Descricao:</strong> {selectedCharacter.description}</p>
                  <p><strong>Primeira Aparicao:</strong> {selectedCharacter.firstAppearence}</p>
                </div>
              </div>
            </section>
          </>
        )}
      </div>

      {selectedLocation && (
      <>
      <section className='locationSecction'>
        <div className='locationContainer'>
          <div className="locationCabecalho">
            <h1>Localizacoes</h1>
            <div className="locationsBoxContainer">
                {locations.map((locations, i) => (
                  <div
                    key={i}
                    className='locationBox'
                    onClick={() => setSelectedLocation(locations)}
                  >
                    <img src={locations.image1} alt={locations.name} />
                  </div>
                ))}
              </div>
          </div>
          <div className='locationBoxImage'>
            <div className="locationImage">
              <img src={selectedLocation[`image${imageIndex + 1}`]} alt={selectedLocation.name} />
              <button onClick={handleIndex}>
                <i className="fa-solid fa-arrow-right"></i>
              </button>
            </div>
            <div className="locationInformations">
                <h1>{selectedLocation.name}</h1>
                <p>{selectedLocation.description}</p>
            </div>
          </div>
        </div>
      </section>  
      </>        
      )}

      <section className='otherSecction'>
          <div className='NextSecction'>
            <div className="boxSecction">
              <p>Proxima Sessão</p>
            </div>
          </div>
      </section>
    </div>
  )
}

export default App;
