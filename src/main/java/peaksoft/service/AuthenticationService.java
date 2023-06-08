package peaksoft.service;

import peaksoft.dto.response.aut.AuthenticationResponse;
import peaksoft.dto.response.aut.SignInRequest;
import peaksoft.dto.response.aut.SignUpRequest;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);
}
