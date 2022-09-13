package com.restaurant.stoke.stoke.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurant.stoke.stoke.entities.Stoke;
import com.restaurant.stoke.stoke.repository.StokeRepository;
import com.restaurant.stoke.stoke.services.exceptions.DatabaseException;
import com.restaurant.stoke.stoke.services.exceptions.ResourceNotFoundException;

@Service
public class StokeService {
    
    @Autowired
    private StokeRepository repository;

    public Stoke saveStoke(Stoke obj){
        return repository.save(obj);
    }

    public List<Stoke> getAllStoke(){
        List<Stoke> obj = repository.findAll();
        return obj;
    }

    public Stoke getByIdStoke(Long id){
        Optional<Stoke> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Stoke updateStoke(Long id, Stoke obj){
        Stoke entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }
    private void updateData(Stoke entity, Stoke obj) {
        Instant instant = Instant.now();
        entity.setAmount(obj.getAmount());
        entity.setValidity(obj.getValidity());
        entity.setUpdDate(instant);
    }

    public void deleteStoke(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
        
    }

}
