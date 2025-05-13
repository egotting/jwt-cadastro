package br.com.egotting.simple_api_restful_springboot.api.User;

import br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindAllDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindEmailDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServicesImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResponseStatus.ResponseStatusDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UpdateRequestDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServicesImpl userServices;

    public UserController(UserServicesImpl userServices) {
        this.userServices = userServices;
    }


    @GetMapping("/listar")
    public ResponseEntity<ResponseStatusDTO<List<FindAllDTO>>> GetAllUsers() {
        return userServices.findAll();
    }

    @GetMapping("/encontrar/{email}")
    public ResponseEntity<Result<?>> GetUniqueUser(@PathVariable String email, FindEmailDTO data) {
        return userServices.findEmail(email, data);
    }

    @PutMapping("/atualizar/{email}")
    public ResponseEntity<Result<?>> updateUser(@PathVariable String email, @RequestBody @Validated UpdateRequestDTO data) {
        return userServices.updateEmailUser(email, data);
    }

    @DeleteMapping("/deletar/{email}")
    ResponseEntity<Result<?>> DeleteUser(@PathVariable String email) {
        return userServices.deleteUser(email);
    }
}
