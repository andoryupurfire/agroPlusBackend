package com.agro.agroplus.service;


import com.agro.agroplus.entity.Agricultor;
import com.agro.agroplus.repository.AgricultorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgricultorService implements UserDetailsService {

    private final AgricultorRepository agricultorRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException{

        Agricultor agricultor = agricultorRepository
                .findByUsername(username)
                .orElseThrow(()->
                        new UsernameNotFoundException(
                                "Usuario no encontrado: " +  username) );

        return User.builder()
                .username(agricultor.getUsername())
                .password(agricultor.getPassword())
                .roles("AGRICULTOR")
                .build();
    }
}
