package com.sevmark.SevMark.services;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevmark.SevMark.DTO.LocalDTO;
import com.sevmark.SevMark.model.Local;
import com.sevmark.SevMark.model.User;
import com.sevmark.SevMark.repository.LocalRepository;
import com.sevmark.SevMark.repository.UserRepository;

@Service
public class LocalService {

    @Autowired
    private LocalRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<LocalDTO> getAllLocals(){
        return converteDadosParaDTOs(repository.findAll());
    }

    public LocalDTO getLocalById(Long id) {
        Optional<Local> localOptional = repository.findById(id);
        if (localOptional.isPresent()) {
            return converteDados(localOptional.get());
        } else {
            // Aqui você pode lidar com a situação em que o local com o ID fornecido não é encontrado
            // Pode lançar uma exceção ou retornar um DTO vazio, por exemplo
            return new LocalDTO(-1L, "Local não encontrado", 0.0, "", "", null);
        }
    }

    private List<LocalDTO> converteDadosParaDTOs(List<Local> locals) {
      return locals.stream()
              .map(l -> new LocalDTO(l.getId(), l.getLocationName(), l.getPrice(), l.getDescription(), l.getObs(), l.getUser()))
              .collect(Collectors.toList());
    }

    private LocalDTO converteDados(Local local){
        return new LocalDTO(local.getId(), local.getLocationName(), local.getPrice(), local.getDescription(), local.getObs(), local.getUser());
    }

    public LocalDTO postLocal(Local entity) {
        User user = userRepository.findById(entity.getUser().getId()).orElse(null);
        if (user != null) {
            entity.setUser(user);
            return converteDados(repository.save(entity));
        }else{
            return null;
        }
        
    }

    public LocalDTO updateLocal(Long id, Local entity) {
        // Verifica se o local com o ID fornecido existe
        Optional<Local> localOptional = repository.findById(id);
        User user = userRepository.findById(entity.getUser().getId()).orElse(null);
        if (localOptional.isPresent() && user != null) {
            // Atualiza os dados do local existente com os dados fornecidos
            entity.setUser(user);
            Local existingLocal = localOptional.get();
            existingLocal.setLocationName(entity.getLocationName());
            existingLocal.setPrice(entity.getPrice());
            existingLocal.setDescription(entity.getDescription());
            existingLocal.setObs(entity.getObs());
            existingLocal.setUser(entity.getUser());
            // Salva e retorna o local atualizado
            return converteDados(repository.save(existingLocal));
        } else {
            // Aqui você pode lidar com a situação em que o local com o ID fornecido não é encontrado
            // Pode lançar uma exceção ou retornar null, por exemplo
            return new LocalDTO(-1L, "Local não encontrado", 0.0, "", "", null);
        }
    }

    public void deleteLocal(Long id) {
        repository.deleteById(id);
    }
    
}
