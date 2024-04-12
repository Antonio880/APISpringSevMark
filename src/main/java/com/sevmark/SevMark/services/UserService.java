package com.sevmark.SevMark.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sevmark.SevMark.repository.UserRepository;
import com.sevmark.SevMark.DTO.UserDTO;
import com.sevmark.SevMark.model.User;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        UserDetails usuario = repository.findByEmail(email);
        System.out.println(usuario);
        return usuario;
    }

    public List<UserDTO> getAllUsers(){
        return convertDataToDTOs(repository.findAll());
    }

    public UserDTO getUserById(Long id) {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            return convertData(user);
        } else {
            return null;
        }
    }

    public UserDTO postUser(User entity) {
        return convertData(repository.save(entity));
    }

    public UserDTO updateUser(Long id, User entity) {
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser != null) {
            entity.setId(id); // Ensure the ID is set correctly
            return convertData(repository.save(entity));
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    private List<UserDTO> convertDataToDTOs(List<User> users) {
        return users.stream()
                .map(this::convertData)
                .collect(Collectors.toList());
    }

    private UserDTO convertData(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getTypeUser(), user.getPhone());
    }
}
