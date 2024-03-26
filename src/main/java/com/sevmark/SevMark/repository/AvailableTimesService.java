package com.sevmark.SevMark.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevmark.SevMark.DTO.AvailableTimesDTO;
import com.sevmark.SevMark.model.AvailableTimes;
import com.sevmark.SevMark.model.Local;
import com.sevmark.SevMark.services.AvailableTimesRepository;
import com.sevmark.SevMark.services.LocalRepository;

@Service
public class AvailableTimesService {

    @Autowired
    private AvailableTimesRepository repository;

    @Autowired
    private LocalRepository localRepository;

    public List<AvailableTimesDTO> getAllAvailableTimes(){
        return converteDadosParaDTOs(repository.findAll());
    }

    public AvailableTimesDTO getAvailableTimesById(Long id){
        Optional<AvailableTimes> availableTimesOptional = repository.findById(id);

        if(availableTimesOptional.isPresent()){
            return converteDados(availableTimesOptional.get());
        }else{
            return new AvailableTimesDTO(-1L, "Horários não encontrados", null, null, null);
        }
    }

    public AvailableTimesDTO postAvailableTimes(AvailableTimes entity){
        System.out.println(entity);
        Local local = localRepository.findById(entity.getLocal().getId()).orElse(null);
        if(local != null){
            entity.setLocal(local);
            
            return converteDados(repository.save(entity));
        }
        else{
            return null;
        }

    }

    public AvailableTimesDTO updateAvailableTimes(Long id, AvailableTimes entity){
        Optional<AvailableTimes> availableTimesOptional = repository.findById(id);
        Local local = localRepository.findById(entity.getLocal().getId()).orElse(null);
        if (availableTimesOptional.isPresent() && local != null) {
            entity.setLocal(local);
            AvailableTimes existingAvailableTime = availableTimesOptional.get();
            existingAvailableTime.setDay(entity.getDay());
            existingAvailableTime.setStartTime(entity.getStartTime());
            existingAvailableTime.setEndTime(entity.getEndTime());
            existingAvailableTime.setLocal(entity.getLocal());

            return converteDados(existingAvailableTime);
        }else{
            return new AvailableTimesDTO(-1L, "Horários não encontrados", null, null, null);
        }
    }

    public void deleteAvailableTimes(Long id) {
        repository.deleteById(id);
    }

    private List<AvailableTimesDTO> converteDadosParaDTOs(List<AvailableTimes> availableTimes) {
      return availableTimes.stream()
              .map(l -> new AvailableTimesDTO(l.getId(), l.getDay(), l.getStartTimeFormatted(), l.getEndTimeFormatted(), l.getLocal()))
              .collect(Collectors.toList());
    }

    private AvailableTimesDTO converteDados(AvailableTimes availableTimes){
        return new AvailableTimesDTO(availableTimes.getId(), availableTimes.getDay(), availableTimes.getStartTimeFormatted(), availableTimes.getEndTimeFormatted(), availableTimes.getLocal());
    }
}
