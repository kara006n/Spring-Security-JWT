package io.javabrains.springsecurityjwt.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


//testing comment 1
//testing commit 2
//creating userdetailservice class to load object of userDetails
//commit 2 in master
//commit 1 by feature 2
//commit 2 by feature 2
//commit again by master
//commit 2 by master

@Component
public class MyUserDetailsService implements UserDetailsService {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return new User("foo", "foo", new ArrayList<>());
      }
}
