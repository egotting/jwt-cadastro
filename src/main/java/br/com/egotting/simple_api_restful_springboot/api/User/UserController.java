package br.com.egotting.simple_api_restful_springboot.api.User;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.api.Routes.Routes;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindEmailDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.DeleteRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import static br.com.egotting.simple_api_restful_springboot.api.Routes.Routes.MAIN_ENDPOINTS;


@RestController
@RequestMapping(MAIN_ENDPOINTS)
public class UserController {
    @Autowired
    IUserServices userServices;

    @GetMapping("/get/users")
    public ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> GetAllUsers() {
        return userServices.findAll();
    }

    @GetMapping("/get/user/{email}")
    public ResponseEntity<Result<?>> GetUniqueUser(@PathVariable @Validated FindEmailDTO data) {
        return userServices.findEmail(data);
    }

    @PutMapping("/update/user")
    public ResponseEntity<Result<?>> updateUser(@RequestBody @Validated GeneralRequestDTO data) {
        return userServices.updateEmailUser(data);
    }

    @DeleteMapping("/delete/user")
    ResponseEntity<Result<?>> DeleteUser(@RequestBody @Validated DeleteRequestDTO data) {
        return userServices.deleteUser(data);
    }
}
