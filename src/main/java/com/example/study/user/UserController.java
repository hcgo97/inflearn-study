package com.example.study.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;

    }

    @GetMapping("/users")
    public List<User> selectAllUsers(){
        return service.findAll();

    }


    @GetMapping("/users/{id}")
    public User selectUser(@PathVariable int id){
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id) );
        }
        return user;

    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saveUser = service.save(user);

        //현재요청되어진 request값을 사용한다
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id) );
        }

    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){

        User currentUser = service.findOne(id); // 먼저 해당유저를 찾는다.

        if(currentUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id) );
        }

        currentUser.setName(user.getName());
        currentUser.setJoinDate(user.getJoinDate());

        service.updateUser(currentUser);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);

    }


}
