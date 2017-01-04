package io.github.vdubois.model;

import lombok.Data;

/**
 * Created by vdubois on 31/12/16.
 */
@Data
public class AuthenticationDTO {
    private String email;
    private String password;
}