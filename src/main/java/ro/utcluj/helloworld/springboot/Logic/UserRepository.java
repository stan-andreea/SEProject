package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.helloworld.springboot.Model.Content;
import ro.utcluj.helloworld.springboot.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
