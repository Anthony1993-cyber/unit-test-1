package co.develhope.testController.controller;

import co.develhope.testController.entities.User;
import co.develhope.testController.repositories.UserRepository;
import co.develhope.testController.services.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // create
    @PostMapping("")
    public @ResponseBody
    User create(@RequestBody User user){
        return userRepository.save(user);
    }

    // read all
    @GetMapping("/")
    public @ResponseBody
    List<User> getAll(){
        return userRepository.findAll();
    }

    // read just one
    @GetMapping("/{id}")
    public @ResponseBody  User getSingle(@PathVariable long id){
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    // update the id of a student
    @PutMapping("/{id}")
    public @ResponseBody User update(@PathVariable long id, @RequestBody  @NotNull User user){
        user.setId(id);
        return userRepository.save(user);
    }

    // update the isWorking column of a student
    @PutMapping("/{id}/work")
    public @ResponseBody User setStudentIsWorking(@PathVariable long id, @RequestParam("working") boolean working){
        return userService.setUserIsWorkingStatus(id, working);
    }

    // delete a student
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userRepository.deleteById(id);
    }


}