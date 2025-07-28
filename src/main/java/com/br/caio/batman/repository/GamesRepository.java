package com.br.caio.batman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.caio.batman.entities.Games;

@Repository
public interface GamesRepository extends JpaRepository<Games, String> {
    
}