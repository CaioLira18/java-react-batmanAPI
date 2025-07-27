package com.br.caio.batman.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.caio.batman.entities.Characters;
import com.br.caio.batman.repository.CharactersRepository;

@Service
public class CharactersService {

    private final CharactersRepository charactersRepository;

    @Autowired
    public CharactersService(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    public Characters insert(Characters character){
        return charactersRepository.save(character);
    }

    public List<Characters> listAll(){
        return charactersRepository.findAll();
    }

    public Optional<Characters> findById(String id){
        return charactersRepository.findById(id);
    }

     public Characters update(String id, Characters character){
        Optional<Characters> existingCharacter = charactersRepository.findById(id);
        if(existingCharacter.isPresent()){
            Characters characterToUpdate = existingCharacter.get();
            characterToUpdate.setName(character.getName());
            characterToUpdate.setNomeVerdadeiro(character.getNomeVerdadeiro());
            characterToUpdate.setAltura(character.getAltura());
            characterToUpdate.setRole(character.getRole());
            characterToUpdate.setDescription(character.getDescription());
            characterToUpdate.setFirstAppearence(character.getFirstAppearence());
            characterToUpdate.setImage(character.getImage());

            return charactersRepository.save(characterToUpdate);
        }
        return null;
    }

    public boolean deleteById(String id){
        if(charactersRepository.existsById(id)){
            charactersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}