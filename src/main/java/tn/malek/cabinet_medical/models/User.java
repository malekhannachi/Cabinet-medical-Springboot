package tn.malek.cabinet_medical.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "firstname", nullable = false)
    public String firstname;

    @Column(name = "lastname", nullable = false)
    public String lastname;

    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "role")
    public String role = "patient";

    @Column(name = "accountState")
    public Boolean accountState = true;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    public List<Ordonnance> ordonnance;

}