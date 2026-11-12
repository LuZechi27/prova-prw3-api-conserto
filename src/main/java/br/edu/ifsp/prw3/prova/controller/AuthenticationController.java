package br.edu.ifsp.prw3.prova.controller;

import br.edu.ifsp.prw3.prova.auth.AuthenticationDTO;
import br.edu.ifsp.prw3.prova.auth.User;
import br.edu.ifsp.prw3.prova.util.security.AppTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final AppTokenService tokenService;

    public AuthenticationController(AuthenticationManager manager, AppTokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.generateToken((User) Objects.requireNonNull(authentication.getPrincipal()));
        return ResponseEntity.ok(new AppTokenService.JWTTokenDTO(tokenJWT));
    }
}
