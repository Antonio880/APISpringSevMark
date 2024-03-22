package com.sevmark.SevMark.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevmark.SevMark.services.UserRepository;
import com.sevmark.SevMark.DTO.UserDTO;
import com.sevmark.SevMark.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAllUsers(){
        return converteDadosParaDTOs(repository.findAll());
    }

    private List<UserDTO> converteDadosParaDTOs(List<User> users) {
      return users.stream()
              .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getEmail(), u.getTypeUser(), u.getPhone(), u.getPassword()))
              .collect(Collectors.toList());
    }

    private UserDTO converteDados(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getTypeUser(), user.getPhone(), user.getPassword());
    }

    public UserDTO postUser(User entity) {
        return converteDados(repository.save(entity));
    }
}
