package se.iuh.e2portal.payload;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PasswordRequest {
	@NotBlank
	private String newPassword;

    
}
