package com.apiprjctport.Services;

import com.apiprjctport.Entities.Task;
import com.apiprjctport.Entities.Task;
import com.apiprjctport.Repositories.RepositoryTask;
import com.apiprjctport.Repositories.RepositoryTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceTask {
    Task tsk1;

    private RepositoryTask repository;
    public ServiceTask(RepositoryTask repository){
        this.repository=repository;
    }
    public List<Task> getRepository(){
        return this.repository.findAll();
    }
    public  Task createTask ( Task tsk){
        return this.repository.save(tsk);
    }

    public void deleteTask(Long id){

        repository.deleteById(id);

    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }
    public void updateTask(Task emp) {
        repository.save(emp);
    }



}
