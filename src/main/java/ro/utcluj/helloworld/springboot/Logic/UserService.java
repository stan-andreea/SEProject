package ro.utcluj.helloworld.springboot.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.utcluj.helloworld.springboot.Model.Content;
import ro.utcluj.helloworld.springboot.Model.User;

@Service
@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }


}
