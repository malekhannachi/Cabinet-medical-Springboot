package tn.malek.cabinet_medical.controllers;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.malek.cabinet_medical.models.User;
import tn.malek.cabinet_medical.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.password = this.bCryptPasswordEncoder.encode(user.password);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> categories = this.userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id) {
        this.userRepository.deleteById(id);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "user deleted");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Optional<User>> getCategoryById(@PathVariable Integer id) {
        // try {
        Optional<User> user = this.userRepository.findById(id);//.get();

        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        else
            return ResponseEntity.status(HttpStatus.OK).body(user);


    }



    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {

        HashMap<String, Object> response = new HashMap<>();

        User userFromDB = userRepository.findByEmail(user.email);

        if (userFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(user.password, userFromDB.password);

            if (!compare) {
                response.put("message", "user not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

}
