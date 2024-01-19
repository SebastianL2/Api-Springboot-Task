package com.apiprjctport.Controllers;

import com.apiprjctport.Entities.Coments;
import com.apiprjctport.Entities.Project;
import com.apiprjctport.Entities.Project;
import com.apiprjctport.Entities.Task;
import com.apiprjctport.Services.ServiceProject;
import com.apiprjctport.Services.ServiceProject;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/project")
public class ControlProject {


    ServiceProject servic1 ;
    public ControlProject(ServiceProject servic1 ){
        this.servic1= servic1;
    }
    @GetMapping("/all")
    public List<Project> info() {
        List<Project> projects = servic1.getRepository();

        // Forzar la carga de tareas para cada proyecto
        for (Project project : projects) {
            Hibernate.initialize(project.getTasks());
        }

        return projects;
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Project info(@RequestBody Project emp){
        return  this.servic1.createProject(emp);
    }
    @DeleteMapping("/delete")

    public ResponseEntity<String> deleteProject(@RequestParam Long id) {
        try{
            this.servic1.deleteProject(id);
            return new ResponseEntity<>("Project eliminado correctamente", HttpStatus.OK);
        }catch (EntityNotFoundException ex){
             return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject (@PathVariable Long id, @RequestBody Project emp){
        Optional<Project> ProjectOptional = servic1.findById(id);
        if (ProjectOptional.isPresent()) {
            Project existingProject = ProjectOptional.get();


            existingProject.setName(emp.getName());
            existingProject.setDepartment(emp.getDepartment());
            existingProject.setDescription(emp.getDescription());

            servic1.updateProject(existingProject);
            return new ResponseEntity<>("Project actualizado correctamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Project no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add/task/{id}")
    public ResponseEntity<Project> addTaskToProject(@PathVariable Long id, @RequestBody Task newTask) {
        Optional<Project> optionalProject = servic1.findById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            List<Task> tasks = project.getTasks();

            // Agregar la nueva tarea a la lista de tareas del proyecto
            tasks.add(newTask);

            // Actualizar el proyecto en la base de datos
            servic1.createProject(project);

            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add/comment/{id}")
    public ResponseEntity<Project> addCommnentToProject(@PathVariable Long id, @RequestBody Coments newComment) {
        Optional<Project> optionalProject = servic1.findById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            List<Coments> coments = project.getComentsList();

            // Agregar la nueva tarea a la lista de tareas del proyecto
            coments.add(newComment);

            // Actualizar el proyecto en la base de datos
            servic1.createProject(project);

            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
