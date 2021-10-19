package tn.malek.cabinet_medical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.cabinet_medical.models.Ordonnance;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Integer> {
}
