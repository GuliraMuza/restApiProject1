package peaksoft.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.dto.response.aut.AuthenticationResponse;
import peaksoft.dto.response.aut.SignInRequest;
import peaksoft.dto.response.aut.SignUpRequest;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EntityExistsException(String.format(
                    "User with email: %s already exists!", signUpRequest.getEmail()));
        }

        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "USer with email: " + signInRequest.getEmail() + " not found"
                ));

        if(signInRequest.getEmail().isBlank()){
            throw new BadCredentialsException("Email doesn't exist!");
        }

        if(!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password!");
        }

        String jwtToken=jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }
}