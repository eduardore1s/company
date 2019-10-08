package com.hotmart.api.company.services.security;

import com.hotmart.api.company.model.entity.User;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()){
            return userOptional.get();
        }

        throw new ResourceNotFoundException(null, "Dados inválidos.");
    }
}
