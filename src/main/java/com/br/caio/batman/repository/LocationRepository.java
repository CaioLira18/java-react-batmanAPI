package com.br.caio.batman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.caio.batman.entities.Locations;

@Repository
public interface LocationRepository extends JpaRepository<Locations, String> {
    
}