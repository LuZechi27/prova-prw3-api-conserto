package br.edu.ifsp.prw3.prova.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
