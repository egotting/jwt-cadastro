package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

public class UpdateRequestDTO {

    @Email
    private String newEmail;
    @Size(min = 8)
    @IPasswordValidator
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

    @Override
    public int hashCode() {
        return Objects.hash(newEmail, newPassword, updateAccount);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRequestDTO that = (UpdateRequestDTO) o;
        return Objects.equals(newEmail, that.newEmail) && Objects.equals(newPassword, that.newPassword) && Objects.equals(updateAccount, that.updateAccount);
    }


}
