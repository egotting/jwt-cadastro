package br.com.egotting.simple_api_restful_springboot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.User;
import br.com.egotting.simple_api_restful_springboot.domain.Services.UserServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> CreateUser(
            @RequestBody User user) {

        var _user = userServices.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(_user);
    }

    @GetMapping("/get-users")
    public ResponseEntity<Iterable<User>> GetAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userServices.FindAll());
    }

    @GetMapping("/get-user/{email}")
    public ResponseEntity<User> GetUniqueUser(@PathVariable @RequestBody String email) {

        if (!Param.class.isInstance(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userServices.findEmail(email));
    }

    @DeleteMapping("/delete-user/{email}")
    ResponseEntity<Void> DeleteUser(@PathVariable @RequestBody String email) {
        if (!Param.class.isInstance(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        userServices.deleteByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
