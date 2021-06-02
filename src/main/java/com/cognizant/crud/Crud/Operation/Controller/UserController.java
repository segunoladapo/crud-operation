package com.cognizant.crud.Crud.Operation.Controller;


import com.cognizant.crud.Crud.Operation.dto.AuthenticationResponse;
import com.cognizant.crud.Crud.Operation.dto.UserDto;
import com.cognizant.crud.Crud.Operation.model.User;
import com.cognizant.crud.Crud.Operation.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    public List<UserDto> list() {
        Iterable<User> users = this.userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        Consumer<User> consumer = e -> {
            UserDto userDto = new UserDto();
            userDto.setId(e.getId());
            userDto.setEmail(e.getEmail());
            userDtoList.add(userDto);
        };
        users.iterator().forEachRemaining(consumer);
        return userDtoList;
    }

    @GetMapping("users/{id}")
    public UserDto findById(@PathVariable Long id) {
        User user = this.userRepository.findById(id).get();
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        return userDto;
    }

    @PatchMapping("users/{id}")
    public void patchUpdate(@RequestBody User userInput, @PathVariable Long id) {
        User user = this.userRepository.findById(id).get();
        user.setEmail(userInput.getEmail() != null ? userInput.getEmail() : user.getEmail());
        user.setPassword(userInput.getPassword() != null ? userInput.getPassword() : user.getPassword());
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
        if (user != null && user.getPassword().equals(user.getPassword())) {
            response.setAuthenticated(true);
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            response.setUser(userDto);
        } else {
            response.setAuthenticated(false);
        }

        return response;
    }


}
