package com.br.caio.batman.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.caio.batman.entities.Characters;
import com.br.caio.batman.service.CharactersService;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {

    private final CharactersService charactersService;

    @Autowired
    public CharactersController(CharactersService charactersService) {
        this.charactersService = charactersService;
    }

    @PostMapping
    public ResponseEntity<Characters> createCharacter(@RequestBody Characters character) {
        Characters createdCharacter = charactersService.insert(character);
        return new ResponseEntity<>(createdCharacter, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Characters>> getAllCharacters() {
        List<Characters> characters = charactersService.listAll();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Characters> getCharacterById(@PathVariable String id) {
        Optional<Characters> character = charactersService.findById(id);
        if (character.isPresent()) {
            return new ResponseEntity<>(character.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Characters> updateCharacter(@PathVariable String id, @RequestBody Characters character) {
        Characters updatedCharacter = charactersService.update(id, character);
        if (updatedCharacter != null) {
            return new ResponseEntity<>(updatedCharacter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id) {
        boolean deleted = charactersService.deleteById(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}