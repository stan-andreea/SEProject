package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.helloworld.springboot.Model.User;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public static User getUserByUserName(String username) {
        //TODO

        return new User();
    }
}
