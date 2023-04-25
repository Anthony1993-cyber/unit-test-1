package co.develhope.testController.services;

import co.develhope.testController.entities.User;
import co.develhope.testController.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User setUserIsWorkingStatus(Long id, boolean isWorking){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) return null;
        user.get().setWorking(isWorking);
        return userRepository.save(user.get());
    }


}
