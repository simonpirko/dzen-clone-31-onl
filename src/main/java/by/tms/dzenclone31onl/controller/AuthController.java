package by.tms.dzenclone31onl.controller;

import by.tms.dzenclone31onl.dto.JwtAuthenticationResponse;
import by.tms.dzenclone31onl.dto.SignInRequest;
import by.tms.dzenclone31onl.dto.SignUpRequest;
import by.tms.dzenclone31onl.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/registration")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
      return authenticationService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
