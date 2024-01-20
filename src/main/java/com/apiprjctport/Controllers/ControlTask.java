package com.apiprjctport.Controllers;

import com.apiprjctport.Entities.*;
import com.apiprjctport.Entities.Task;
import com.apiprjctport.Services.ServiceEmployee;
import com.apiprjctport.Services.ServiceProject;
import com.apiprjctport.Services.ServiceTask;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/task")
public class ControlTask {

    @Autowired
    private final ServiceTask task1;
    @Autowired
    private ServiceProject serviceProject;
    @Autowired
    public ControlTask( ServiceTask task1) {

        this.task1 = task1;
    }



    @GetMapping("/all")
    public List<Task> info(){
        return  this.task1.getRepository();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Task info(@RequestBody Task emp){
        return  this.task1.createTask(emp);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam Long id) {
        try{
            this.task1.deleteTask(id);
            return new ResponseEntity<>("Tarea eliminado correctamente", HttpStatus.OK);
        }catch (EntityNotFoundException ex){
             return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask (@PathVariable Long id, @RequestBody Task emp){
        Optional<Task> TaskOptional = task1.findById(id);
        if (TaskOptional.isPresent()) {
            Task existingTask = TaskOptional.get();


            existingTask.setTitle(emp.getTitle());
            existingTask.setDesc(emp.getDesc());
            existingTask.setComents(emp.getComents());
            existingTask.setPriority(emp.getPriority());
            existingTask.setFinished(emp.getFinished());

            task1.updateTask(existingTask);
            return new ResponseEntity<>("Tarea actualizada correctamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Tarea no encontrada", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findTask (@PathVariable Long id){
        Optional<Task> TaskOptional = task1.findById(id);
        if (TaskOptional.isPresent()) {
            Task existingTask = TaskOptional.get();

            return new ResponseEntity<>(existingTask, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("Tarea no encontrada", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add/employee/{id}")
    public ResponseEntity<Task> addCommnentToProject(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Optional<Task> optionalProject = task1.findById(id);

        if (optionalProject.isPresent()) {
            Task task = optionalProject.get();
            List<Employee> employees = task.getEmployeeList();
            employees.add(newEmployee);
            task1.createTask(task);

            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
