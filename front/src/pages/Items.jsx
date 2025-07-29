import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const Items = () => {
  const { id } = useParams(); 
  const [game, setGame] = useState(null); 
  const API_URL = "http://localhost:8080/api";

  useEffect(() => {
    console.log("useEffect disparado");

    fetch(`${API_URL}/games`)
      .then(response => response.json())
      .then(data => {
        console.log("Dados recebidos da API:", data);
        const found = data.find(g => g.id === id);
        console.log("Jogo encontrado:", found);
        setGame(found);
      })
      .catch(err => console.error("Erro ao buscar dados:", err));
  }, [id]);

  console.log("Game data:", game);
  console.log("ID from URL:", id);

  if (!game) {
    return (
      <div className="loading">
        <div className="loadingText">
          <p>Carregando...</p>
        </div>
      </div>
    );
  }

  return (
    <div> 
      <div className="gameContainer">
        <div className="gameInformation">
          <div className="imageGame">
            <img src={game.image} alt={game.name} />
          </div>
          <h1>{game.name}</h1>
          <p>Descricao: {game.description}</p>
          <p>Data de Lancamento: {game.dateLaunch}</p>
          <p>Plataformas: {game.plataforms}</p>
        </div>
      </div>
    </div>
  );
};

export default Items;
