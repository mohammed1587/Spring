package com.example.spring.controller.api;


import com.example.spring.dto.auth.AuthenticationRequest;
import com.example.spring.dto.auth.AuthenticationResponse;
import com.example.spring.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("authentication")
public interface AuthenticationApi {

  @PostMapping(Constants.AUTHENTICATION_ENDPOINT + "/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}
