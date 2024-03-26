package com.sevmark.SevMark.DTO;

import java.time.LocalTime;

import com.sevmark.SevMark.model.Local;

public record AvailableTimesDTO(
    Long id,
    String day,
    String startTime,
    String endTime,
    Local local
) {

}
