package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.helloworld.springboot.Model.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content,Integer> {

}
