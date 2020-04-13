package library.avenir.test.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignInDto {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
