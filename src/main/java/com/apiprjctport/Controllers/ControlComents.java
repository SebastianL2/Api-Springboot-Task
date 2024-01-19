package com.apiprjctport.Controllers;

import com.apiprjctport.Entities.Coments;
import com.apiprjctport.Services.ServiceComents;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coments")
public class ControlComents {


    ServiceComents coment1 ;
    public ControlComents(ServiceComents coment1 ){
        this.coment1= coment1;
    }

    @GetMapping("/all")
    public List<Coments> info(){
        return  this.coment1.getRepository();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Coments info(@RequestBody Coments emp){
        return  this.coment1.createComents(emp);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComents(@RequestParam Long id) {
        try{
            this.coment1.deleteComents(id);
            return new ResponseEntity<>("Comentario eliminado correctamente", HttpStatus.OK);
        }catch (EntityNotFoundException ex){
             return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")

    public ResponseEntity<String> updateComents (@PathVariable Long id, @RequestBody Coments emp){
        Optional<Coments> ComentsOptional = coment1.findById(id);
        if (ComentsOptional.isPresent()) {
            Coments existingComents = ComentsOptional.get();


            existingComents.setText(emp.getText());
            existingComents.setDate(emp.getDate());


            coment1.updateComents(existingComents);
            return new ResponseEntity<>("Empleado actualizado correctamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findComents (@PathVariable Long id){
        Optional<Coments> ComentsOptional = coment1.findById(id);
        if (ComentsOptional.isPresent()) {
            Coments existingComents = ComentsOptional.get();

            return new ResponseEntity<>(existingComents, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
