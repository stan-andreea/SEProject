package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.utcluj.helloworld.springboot.Model.Privilege;
import ro.utcluj.helloworld.springboot.Model.Role;

public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    @Query("SELECT u FROM Privilege u WHERE u.name = ?1")
    Privilege findByName(String name);
}
