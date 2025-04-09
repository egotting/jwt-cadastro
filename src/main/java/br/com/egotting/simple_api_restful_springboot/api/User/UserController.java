package br.com.egotting.simple_api_restful_springboot.api.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServices;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/v1/api")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/create/user")
    public ResponseEntity<GeneralReponseDTO<?>> CreateUser(
            @RequestBody UserRequestDTO user) {
        return userServices.saveUserDto(user);
    }

    @GetMapping("/get/users")
    public ResponseEntity<GeneralReponseDTO<?>> GetAllUsers() {
        return userServices.findAll();
    }

    @GetMapping("/get/user/{email}")
    public ResponseEntity<GeneralReponseDTO<?>> GetUniqueUser(@PathVariable String email) {
        return userServices.findEmail(email);
    }

    @PutMapping("/update/user/{email}")
    public ResponseEntity<GeneralReponseDTO<?>> updateUser(@PathVariable String email,
            @RequestBody GeneralRequestDTO user) {
        return userServices.UpdateEmailUser(email, user);
    }

    @DeleteMapping("/delete/user/{email}")
    ResponseEntity<GeneralReponseDTO<?>> DeleteUser(@PathVariable @RequestBody String email) {
        return userServices.deleteUser(email);
    }
}
