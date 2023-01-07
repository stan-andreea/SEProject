package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.utcluj.helloworld.springboot.Model.Role;
import ro.utcluj.helloworld.springboot.Model.User;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT u FROM Role u WHERE u.name = ?1")
    Role findByName(String name);
}
