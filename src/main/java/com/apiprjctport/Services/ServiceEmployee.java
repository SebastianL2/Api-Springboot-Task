package com.apiprjctport.Services;

import com.apiprjctport.Entities.Employee;
import com.apiprjctport.Entities.Task;
import com.apiprjctport.Repositories.RepositoryEmployee;
import com.apiprjctport.Repositories.RepositoryTask;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceEmployee {
    @Autowired
    private RepositoryEmployee repository;

    @Autowired
    private RepositoryTask repositoryTask;

    public List<Employee> getRepository(){
        return repository.findAll();
    }

    public List<Employee> getEmployeeDetails() {

            return repository.findAll();

    }

    public Employee createEmployee(Employee emp1){
        return repository.save(emp1);
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

    public Employee assignProjectToEmployee(Long employee_id, Long task_id) {

        Employee employee = repository.findById(employee_id).orElse(null);
        Task task = repositoryTask.findById(task_id).orElse(null);

        if (employee != null && task != null) {
            List<Task> assignedTasks = employee.getAssignedTask();
            assignedTasks.add(task);
            employee.setAssignedTask(assignedTasks);
            return repository.save(employee);
        } else {
            // Manejo de errores o retorno específico si el empleado o la tarea no se encuentran
            return null;
        }
    }

}
