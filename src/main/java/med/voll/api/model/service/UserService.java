/*package med.voll.api.model.service;

import med.voll.api.model.entities.User;
import med.voll.api.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String userName, String password){
        String encodedPassword = passwordEncoder.encode(password);
        var user = new User(userName, encodedPassword);
        userRepository.save(user);
    }



}*/
