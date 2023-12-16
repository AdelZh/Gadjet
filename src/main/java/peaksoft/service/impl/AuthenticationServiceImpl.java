package peaksoft.service.impl;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.UserRepo;
import peaksoft.request.SignInRequest;
import peaksoft.request.SignUpRequest;
import peaksoft.response.AuthenticationResponse;
import peaksoft.service.AuthenticationService;

import java.time.ZonedDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    @PostConstruct
    public void init(){
        User user=new User();
        user.setFirstName("kandy");
        user.setLastName("bek");
        user.setPassword("kandyBek");
        user.setEmail("kandy@gmail.com");
        user.setRole(Role.ADMIN);
        userRepo.save(user);
    }

        @Override
        public AuthenticationResponse signUp(SignUpRequest request) {
            if (userRepo.existsByEmail(request.email())){
                log.error("user with email:" + request.email() + "already exist");
                throw new EntityExistsException(
                        "user with email: " + request.email() + "already exist"
                );
            }
            User user = User.builder()
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .createdAt(ZonedDateTime.now())
                    .role(Role.USER)
                    .build();

            userRepo.save(user);
            String token = jwtService.generateToken(user);
            return AuthenticationResponse.builder().
                    token(token)
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
        }



    @Override
    public AuthenticationResponse singIn(SignInRequest request) {
        User user = userRepo.getUserByEmail(request.email()).orElseThrow(
                () -> {
                    log.error( "user with email: " + request.email() + " not found");
                    return new NotFoundException(
                            "user with email: " + request.email() + " not found");
                });

        if (request.email().isBlank()) {
            throw new BadCredentialsException("email is blank");
        }
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("wrong password");
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}