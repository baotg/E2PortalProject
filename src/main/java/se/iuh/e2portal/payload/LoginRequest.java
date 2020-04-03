package se.iuh.e2portal.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull
    private String id;
    @NotBlank
    private String password;
}
