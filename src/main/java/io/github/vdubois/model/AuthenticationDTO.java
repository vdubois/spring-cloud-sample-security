package io.github.vdubois.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by vdubois on 31/12/16.
 */
@Data
public class AuthenticationDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}