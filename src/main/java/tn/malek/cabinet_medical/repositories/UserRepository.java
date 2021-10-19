package tn.malek.cabinet_medical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.cabinet_medical.models.User;

public interface UserRepository  extends JpaRepository<User,Integer> {

        User findByEmail(String email);
    User findByRole(String Role);

}
