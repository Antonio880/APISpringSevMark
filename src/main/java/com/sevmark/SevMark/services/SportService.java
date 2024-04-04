package com.sevmark.SevMark.services;

import com.sevmark.SevMark.DTO.SportDTO;
import com.sevmark.SevMark.model.Local;
import com.sevmark.SevMark.model.Sport;
import com.sevmark.SevMark.repository.LocalRepository;
import com.sevmark.SevMark.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SportService {

    @Autowired
    private SportRepository repository;

    @Autowired
    private LocalRepository localRepository;
    public List<SportDTO> getAllSports() {
        return repository.findAll().stream().map(sport -> EntifyDTOMapper.convertToDTO(sport, SportDTO.class)).collect(Collectors.toList());
    }

    public SportDTO createSport(Sport sport) {
        Local local = localRepository.findById(sport.getLocal().getId()).orElse(null);
        if (local != null){
            sport.setLocal(local);
            return convertToDTO(repository.save(sport));
        }else{
            return null;
        }
    }

    private SportDTO convertToDTO(Sport sport) {
        return new SportDTO(sport.getId(), sport.getName(), sport.getLocal());
    }

    public void deleteSport(Long id) {
        repository.deleteById(id);
    }
}
