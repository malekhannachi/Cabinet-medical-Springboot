package tn.malek.cabinet_medical.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ordonnances")
public class Ordonnance {

        @Id
        @GeneratedValue
        public Integer id;
        @Column(name = "name",unique = true,nullable = false)
        public  String name;

        @ManyToOne
        @JoinColumn(name = "idUser")
        @JsonIgnoreProperties("ordonnance")
        public User user;
}
