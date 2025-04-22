package br.com.egotting.simple_api_restful_springboot.api.User;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.DeleteRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServices;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Result<?>> CreateUser(
            @RequestBody @Validated UserRequestDTO data) {
        return userServices.saveUserDto(data);
    }

    @GetMapping("/get/users")
    public ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> GetAllUsers() {
        return userServices.findAll();
    }

    @GetMapping("/get/user/{email}")
    public ResponseEntity<Result<?>> GetUniqueUser(@PathVariable String email) {
        return userServices.findEmail(email);
    }

    @PutMapping("/update/user/{email}")
    public ResponseEntity<Result<?>> updateUser(@PathVariable String email,
                                                @RequestBody GeneralRequestDTO data) {
        return userServices.UpdateEmailUser(email, data);
    }

    @DeleteMapping("/delete/user")
    ResponseEntity<Result<?>> DeleteUser(@RequestBody @Valid DeleteRequestDTO data) {
        return userServices.deleteUser(data);
    }
}
