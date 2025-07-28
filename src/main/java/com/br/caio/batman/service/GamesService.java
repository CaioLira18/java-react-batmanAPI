package com.br.caio.batman.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.caio.batman.entities.Games;
import com.br.caio.batman.repository.GamesRepository;

@Service
public class GamesService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public Games insert(Games games){
        return gamesRepository.save(games);
    }

    public List<Games> listAll(){
        return gamesRepository.findAll();
    }

    public Optional<Games> findById(String id){
        return gamesRepository.findById(id);
    }

     public Games update(String id, Games games){
        Optional<Games> existingGames = gamesRepository.findById(id);
        if(existingGames.isPresent()){
            Games gamesToUpdate = existingGames.get();
            gamesToUpdate.setName(games.getName());
            gamesToUpdate.setDescription(games.getDescription());
            gamesToUpdate.setPlataforms(games.getPlataforms());
            gamesToUpdate.setDateLaunch(games.getDateLaunch());
            gamesToUpdate.setImage(games.getImage());
            return gamesRepository.save(gamesToUpdate);
        }
        return null;
    }

    public boolean deleteById(String id){
        if(gamesRepository.existsById(id)){
            gamesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}