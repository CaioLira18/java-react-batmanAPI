package com.br.caio.batman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.caio.batman.entities.Characters;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, String> {
    
}