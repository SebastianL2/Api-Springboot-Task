package com.apiprjctport.Services;


import com.apiprjctport.Entities.Project;
import com.apiprjctport.Repositories.RepositoryProject;
import com.apiprjctport.Repositories.RepositoryProject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProject {

    private RepositoryProject repository;
    public ServiceProject(RepositoryProject repository){
        this.repository=repository;
    }
    public List<Project> getRepository(){
        return this.repository.findAll();
    }
    public  Project createProject ( Project emp1){
        return this.repository.save(emp1);
    }

    public void deleteProject(Long id){
        if (this.repository.existsById(id)) {
            repository.deleteById(id);
        } else {

            throw new EntityNotFoundException("El empleado con ID " +id + " no existe.");
        }
    }

    public Optional<Project> findById(Long id) {
        return repository.findById(id);
    }
    public void updateProject(Project emp) {
        repository.save(emp);
    }
}
