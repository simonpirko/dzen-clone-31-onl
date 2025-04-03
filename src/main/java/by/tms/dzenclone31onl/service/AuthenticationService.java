package by.tms.dzenclone31onl.service;

import by.tms.dzenclone31onl.domain.User;
import by.tms.dzenclone31onl.dto.JwtAuthenticationResponse;
import by.tms.dzenclone31onl.dto.SignInRequest;
import by.tms.dzenclone31onl.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.ROLE_USER)
                .build();

        userService.create(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        if (userService.getUserByUsername(request.getUsername()).isEmpty())
            throw new RuntimeException("Такого пользователя не существует");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}