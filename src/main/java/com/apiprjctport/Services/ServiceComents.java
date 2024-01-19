package com.apiprjctport.Services;

import com.apiprjctport.Entities.Coments;
import com.apiprjctport.Entities.Coments;
import com.apiprjctport.Repositories.RepositoryComents;
import com.apiprjctport.Repositories.RepositoryComents;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceComents {

    private RepositoryComents repository;
    public ServiceComents(RepositoryComents repository){
        this.repository=repository;
    }
    public List<Coments> getRepository(){
        return this.repository.findAll();
    }
    public  Coments createComents ( Coments emp1){
        return this.repository.save(emp1);
    }

    public void deleteComents(Long id){
        if (this.repository.existsById(id)) {
            repository.deleteById(id);
        } else {

            throw new EntityNotFoundException("El empleado con ID " +id + " no existe.");
        }
    }

    public Optional<Coments> findById(Long id) {
        return repository.findById(id);
    }
    public void updateComents(Coments emp) {
        repository.save(emp);
    }
}
