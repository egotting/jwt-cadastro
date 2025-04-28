package br.com.egotting.simple_api_restful_springboot.Services;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.ResponseStatus;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServicesImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServicesImpl userService;

    @Mock
    IUserRepository userRepository;


//    @Test
//    void testFindAll() {
//        // Arrange: Preparar o cenário para o teste
//        List<User.builder> userList = Arrays.asList(
//                new User.builder().email("daddaddadda@example").password("dadadad"),  // Correção aqui
//                new User.builder().email("daddaddadda@example").password("dadadad"),  // Correção aqui
//        );
//
//        // Simular o comportamento do userRepository
//        when(userRepository.findAll()).thenReturn(userList);
//
//        // Transformar a lista de User em uma lista de UserResponseDTO
//        List<UserResponseDTO> userDtoList = userList.stream()
//                .map(user -> new UserResponseDTO(user.getEmail(), user.getRoles()))
//                .collect(Collectors.toList());
//
//        // Criar o DTO esperado no retorno
//        GeneralReponseDTO<List<UserResponseDTO>> expectedResponse = new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), userDtoList);
//
//        // Ação: Chamar o método findAll() do service
//        ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> response = userService.findAll();
//
//        // Assert: Verificar se o status HTTP é OK (200)
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Assert: Verificar se o corpo da resposta contém o DTO esperado
//        assertEquals(expectedResponse, response.getBody());
//    }

}
