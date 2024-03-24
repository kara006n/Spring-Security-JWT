package io.javabrains.springsecurityjwt;

import io.javabrains.springsecurityjwt.dto.AuthRequest;
import io.javabrains.springsecurityjwt.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

      @Autowired
      private AuthenticationManager authenticationManager;

      @Autowired
      private JwtService jwtService;


      @RequestMapping({"/hello"})
      public String hello(){
            return "Hello World";
      }


      @PostMapping("/authenticate")
      public String authenticateAndGetToken(@RequestBody AuthRequest req){

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            if(authenticate.isAuthenticated()){
                  return jwtService.generateToken(req.getUsername());
            }
            else{
                  throw new UsernameNotFoundException("invalid user request");
            }

      }



}
