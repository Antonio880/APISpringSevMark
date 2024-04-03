package com.sevmark.SevMark.services;

import com.sevmark.SevMark.DTO.MarkDTO;
import com.sevmark.SevMark.model.Local;
import com.sevmark.SevMark.model.Mark;
import com.sevmark.SevMark.repository.LocalRepository;
import com.sevmark.SevMark.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@Service

public class MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private LocalRepository localRepository;

    public List<MarkDTO> getAllMarks() {
        return markRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MarkDTO getMarkById(Long id) {
        Optional<Mark> markOptional = markRepository.findById(id);
        return markOptional.map(this::convertToDTO).orElse(null);
    }

    public MarkDTO createMark(Mark mark) {
        Local local = localRepository.findById(mark.getLocal().getId()).orElse(null);
        if (local != null){
            mark.setLocal(local);
            System.out.println(local);
            return convertToDTO(markRepository.save(mark));
        }else{
            return null;
        }
    }

    public MarkDTO updateMark(Long id, Mark updatedMark) {
        Optional<Mark> markOptional = markRepository.findById(id);
        if (markOptional.isPresent()) {
            Mark existingMark = markOptional.get();
            existingMark.setDayOfMonth(updatedMark.getDayOfMonth());
            existingMark.setMonthYear(updatedMark.getMonthYear());
            existingMark.setShortDay(updatedMark.getShortDay());
            existingMark.setHour(updatedMark.getHour());
            existingMark.setLocal(updatedMark.getLocal());
            return convertToDTO(markRepository.save(existingMark));
        } else {
            return null;
        }
    }

    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }

    private MarkDTO convertToDTO(Mark mark) {
        return new MarkDTO(mark.getId(), mark.getDayOfMonth(), mark.getMonthYear(), mark.getShortDay(),
                mark.getHour().toString(), mark.getLocal());
    }
}