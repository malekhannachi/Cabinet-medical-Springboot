package tn.malek.cabinet_medical.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.malek.cabinet_medical.models.Ordonnance;
import tn.malek.cabinet_medical.repositories.OrdonnanceRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("ordonnances")
public class OrdonnanceController {

private  OrdonnanceRepository ordonnanceRepository;
    @Autowired
public  OrdonnanceController(OrdonnanceRepository ordonnanceRepository){
        this.ordonnanceRepository = ordonnanceRepository ;
    }
    @GetMapping(path="all")
    public ResponseEntity<List<Ordonnance>> getAllCategories()
    {
        List<Ordonnance>categories=this.ordonnanceRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping(path = "add")
    public ResponseEntity<Ordonnance> addCategory(@RequestBody Ordonnance ordonnance){

        System.out.println(ordonnance.name);

        Ordonnance savedOrdonnance = this.ordonnanceRepository.save(ordonnance);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrdonnance);
    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Optional<Ordonnance>> getCategoryById(@PathVariable Integer id) {
        // try {
        Optional<Ordonnance> ordonnance = this.ordonnanceRepository.findById(id);//.get();

        if (ordonnance.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ordonnance);
        else
            return ResponseEntity.status(HttpStatus.OK).body(ordonnance);


    }




    @PatchMapping(path = "update")
    public ResponseEntity<Ordonnance>updateCategory(@RequestBody Ordonnance ordonnance)
    {
        try {
            ordonnanceRepository.findById(ordonnance.id);

            Ordonnance updateOrdonnance =this.ordonnanceRepository.save(ordonnance);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateOrdonnance);
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Ordonnance());
        }
    }



    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Integer id) {
        this.ordonnanceRepository.deleteById(id);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Ordonnace deleted");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




}
