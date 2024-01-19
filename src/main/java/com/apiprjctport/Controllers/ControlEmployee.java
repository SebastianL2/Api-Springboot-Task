package com.apiprjctport.Controllers;

import com.apiprjctport.Entities.Employee;
import com.apiprjctport.Services.ServiceEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class ControlEmployee {


    ServiceEmployee sem1 ;
    public ControlEmployee ( ServiceEmployee sem1 ){
        this.sem1= sem1;
    }
    @GetMapping("/all")
    public List<Employee> info(){
        return  this.sem1.getRepository();
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee info(@RequestBody Employee emp){
        return  this.sem1.createEmployee(emp);
    }
    @DeleteMapping("/delete")

    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        try{
            this.sem1.deleteEmployee(id);
            return new ResponseEntity<>("Empleado eliminado correctamente", HttpStatus.OK);
        }catch (EntityNotFoundException ex){
             return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee (@PathVariable Long id, @RequestBody Employee emp){
        Optional<Employee> employeeOptional = sem1.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();


            existingEmployee.setName(emp.getName());
            existingEmployee.setLastName(emp.getLastName());
            existingEmployee.setEmail(emp.getEmail());
            existingEmployee.setPassword(emp.getPassword());
            existingEmployee.setCharge(emp.getCharge());
            existingEmployee.setDepartment(emp.getDepartment());

            sem1.updateEmployee(existingEmployee);
            return new ResponseEntity<>("Empleado actualizado correctamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findEmployee (@PathVariable Long id){
        Optional<Employee> employeeOptional = sem1.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();

            return new ResponseEntity<>(existingEmployee, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
