package io.javabrains.springsecurityjwt;

import io.javabrains.springsecurityjwt.filter.JwtAuthFilter;
import io.javabrains.springsecurityjwt.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigurer  {


      @Autowired
      private MyUserDetailsService userDetailsService; // Replace with your actual service name

      @Autowired
      private JwtAuthFilter jwtAuthFilter;

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()// Disable CSRF for simplicity (consider enabling in production)
                    .authorizeRequests()
                    .requestMatchers("/authenticate").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .userDetailsService(userDetailsService)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
      }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance(); // Configure password encoding
      }

//      @Bean
//      public AuthenticationProvider authenticationProvider(){
//            DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//            authenticationProvider.setUserDetailsService(userDetailsService);
//            authenticationProvider.setPasswordEncoder(passwordEncoder());
//            return authenticationProvider;
//      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
      }



}
