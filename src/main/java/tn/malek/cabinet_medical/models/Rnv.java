package tn.malek.cabinet_medical.models;


import javax.persistence.*;

@Entity
@Table(name = "rnvs")
public class Rnv {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "date", nullable = false)
    public String time;

    @Column(name = "description", nullable = false)
    public String description;
}
