package com.apiprjctport.Services;

import com.apiprjctport.Entities.Employee;
import com.apiprjctport.Entities.Task;
import com.apiprjctport.Repositories.RepositoryEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee {

    private RepositoryEmployee repository;
    public ServiceEmployee(RepositoryEmployee repository){
        this.repository=repository;
    }
    public List<Employee> getRepository(){
        return this.repository.findAll();
    }
    public  Employee createEmployee ( Employee emp1){
        return this.repository.save(emp1);
    }

    public void deleteEmployee(Long id){

            repository.deleteById(id);

    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }
    public void updateEmployee(Employee emp) {
        repository.save(emp);
    }
}
