package com.cognizant.crud.Crud.Operation.Controller;


import com.cognizant.crud.Crud.Operation.dto.AuthenticationResponse;
import com.cognizant.crud.Crud.Operation.model.User;
import com.cognizant.crud.Crud.Operation.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("users")
    public User create(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("users")
    public Iterable<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }

    @PatchMapping("users/{id}")
    public void patchUpdate(@RequestBody User userInput, @PathVariable Long id) {
        User user = this.userRepository.findById(id).get();
        user.setEmail(userInput.getEmail());
        user.setPassword(userInput.getPassword());
        this.userRepository.save(user);
    }

    @DeleteMapping("users/{id}")
    public void delete(@PathVariable Long id) {
        User user = this.userRepository.findById(id).get();
        this.userRepository.delete(user);
    }

    @PostMapping("users/authenticate")
    public AuthenticationResponse authenticate(@RequestBody User userInput) {

        User user = this.userRepository.findByEmail(userInput.getEmail());
        AuthenticationResponse response = new AuthenticationResponse();
        if (user.getPassword().equals(user.getPassword())) {
            response.setAuthenticated(true);
        }
        response.setUser(user);
        return response;
    }


}
