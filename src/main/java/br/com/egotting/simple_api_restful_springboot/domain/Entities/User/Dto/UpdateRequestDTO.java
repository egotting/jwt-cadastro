package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UpdateRequestDTO {

    private String newEmail;
    private String newPassword;
    @NotBlank(message = "Senha não pode estar vazio")
    private String oldPassword;
    private LocalDateTime updateAccount = LocalDateTime.now();

    public UpdateRequestDTO(String newEmail, String newPassword, String oldPassword) {
        this.newEmail = newEmail;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public LocalDateTime getUpdateAccount() {
        return updateAccount;
    }


}
