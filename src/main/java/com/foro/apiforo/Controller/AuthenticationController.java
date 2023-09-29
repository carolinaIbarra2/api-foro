package com.foro.apiforo.Controller;

import com.foro.apiforo.domain.user.DataUserAuthentication;
import com.foro.apiforo.domain.user.User;
import com.foro.apiforo.infra.security.DataJWTToken;
import com.foro.apiforo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DataJWTToken> authenticationUser(@RequestBody @Valid DataUserAuthentication authenticationDataUser){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDataUser.email(), authenticationDataUser.password());
        var authenticatedUser = authenticationManager.authenticate(authenticationToken);
        var JWTToken = tokenService.generatedToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JWTToken));
    }

}
