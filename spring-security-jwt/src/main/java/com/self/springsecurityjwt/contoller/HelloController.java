package com.self.springsecurityjwt.contoller;


import com.self.springsecurityjwt.model.AuthenticationRequest;
import com.self.springsecurityjwt.model.AuthenticationResponse;
import com.self.springsecurityjwt.service.MyUserDetailsService;
import com.self.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Autowired
  JwtUtil jwtUtil;

  @GetMapping("/hello")
  public String helloMessage() {
    return "Hello";
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
    @RequestBody AuthenticationRequest authenticationRequest
    ) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                                                               authenticationRequest.getPassword()));
    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
