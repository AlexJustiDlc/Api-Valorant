package com.valorant.api.security.service;

import com.valorant.api.security.jwt.JwtProvider;
import com.valorant.api.security.model.RoleUser;
import com.valorant.api.security.model.User;
import com.valorant.api.security.model.UserDetail;
import com.valorant.api.security.model.dto.JwtDto;
import com.valorant.api.security.model.dto.LoginDto;
import com.valorant.api.security.model.dto.UserDto;
import com.valorant.api.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        return UserDetail.build(user);
    }

    public List<User> getAll(){
        return (List<User>) repository.findAll();
    }

    public User getByUsername(String Username){
        return repository.findByUsername(Username);
    }

    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

    public void create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }


    public String newUser(UserDto userDto){
        User user = new User();
        Set<RoleUser> roles = new HashSet<>();
        RoleUser role = new RoleUser();
        role.setId(2);
        roles.add(role);

        user.setNames(userDto.getNames());
        user.setSurnames(userDto.getSurnames());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRoles(roles);

        this.create(user);
        return "User \""+user.getUsername()+"\" Created.";
    }

    public JwtDto Login(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtDto(token);
    }

    public JwtDto refresh(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }
}
