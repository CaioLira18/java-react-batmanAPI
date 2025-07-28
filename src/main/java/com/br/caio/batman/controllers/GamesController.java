package com.br.caio.batman.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.caio.batman.entities.Games;
import com.br.caio.batman.repository.GamesRepository;
import com.br.caio.batman.service.GamesService;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/games")
public class GamesController {

    private final GamesService gamesService;
    private final GamesRepository gamesRepository;

    @Autowired
    public GamesController(GamesService gamesService, GamesRepository gamesRepository) {
        this.gamesService = gamesService;
        this.gamesRepository = gamesRepository;
    }

    @PostMapping
    public ResponseEntity<Games> createGames(@RequestBody Games games) {
        Games createdLocations = gamesService.insert(games);
        return new ResponseEntity<>(createdLocations, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> saveAll(@RequestBody List<Games> games) {
        gamesRepository.saveAll(games);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Games>> getAllGames() {
        List<Games> games = gamesService.listAll();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> getGameById(@PathVariable String id) {
        Optional<Games> games = gamesService.findById(id);
        return games
            .map(loc -> new ResponseEntity<>(loc, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Games> updateGames(@PathVariable String id, @RequestBody Games games) {
        Games updateGame = gamesService.update(id, games);
        if (updateGame != null) {
            return new ResponseEntity<>(updateGame, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        boolean deleted = gamesService.deleteById(id);
        return deleted
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
