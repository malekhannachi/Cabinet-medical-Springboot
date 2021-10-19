package tn.malek.cabinet_medical.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.malek.cabinet_medical.models.Rnv;
import tn.malek.cabinet_medical.repositories.RnvRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("rnv")
public class RnvController {

    private RnvRepository rnvRepository;
    @Autowired
    public  RnvController(RnvRepository rnvRepository){
        this.rnvRepository = rnvRepository ;
    }

    @GetMapping(path="all")
    public ResponseEntity<List<Rnv>> getAllRnvs()
    {
        List<Rnv>rnvs=this.rnvRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rnvs);
    }

    @GetMapping(path = "one/{id}")
    public ResponseEntity<Optional<Rnv>> getRnvById(@PathVariable Integer id) {
        // try {
        Optional<Rnv> rnv = this.rnvRepository.findById(id);//.get();

        if (rnv.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnv);
        else
            return ResponseEntity.status(HttpStatus.OK).body(rnv);


    }
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Integer id) {
        this.rnvRepository.deleteById(id);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "rnv deleted");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "add")
    public ResponseEntity<Rnv> addRnv(@RequestBody Rnv rnv){

        System.out.println(rnv.time);
        System.out.println(rnv.description);

        Rnv savedRnv = this.rnvRepository.save(rnv);

       return ResponseEntity.status(HttpStatus.CREATED).body(savedRnv);
    }





}
