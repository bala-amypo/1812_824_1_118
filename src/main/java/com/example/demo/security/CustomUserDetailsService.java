// package com.example.demo.security;

// import com.example.demo.model.AppUser;
// import com.example.demo.repository.AppUserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.Collections;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     private AppUserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String email)
//             throws UsernameNotFoundException {

//         AppUser user = userRepository.findByUsername(email);

// if (user == null) {
//     throw new UsernameNotFoundException("User not found: " + email);
// }

//         return org.springframework.security.core.userdetails.User
//                 .builder()
//                 .username(user.getEmail())
//                 .password(user.getPassword())
//                 .authorities(Collections.emptyList())
//                 .build();
//     }`
// }
