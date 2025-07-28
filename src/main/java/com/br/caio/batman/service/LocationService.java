package com.br.caio.batman.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.caio.batman.entities.Locations;
import com.br.caio.batman.repository.LocationRepository;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Locations insert(Locations character){
        return locationRepository.save(character);
    }

    public List<Locations> listAll(){
        return locationRepository.findAll();
    }

    public Optional<Locations> findById(String id){
        return locationRepository.findById(id);
    }

     public Locations update(String id, Locations character){
        Optional<Locations> existingLocation = locationRepository.findById(id);
        if(existingLocation.isPresent()){
            Locations locationToUpdate = existingLocation.get();
            locationToUpdate.setName(character.getName());
            locationToUpdate.setDescription(character.getDescription());
            locationToUpdate.setImage(character.getImage());
            return locationRepository.save(locationToUpdate);
        }
        return null;
    }

    public boolean deleteById(String id){
        if(locationRepository.existsById(id)){
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}