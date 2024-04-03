package com.sevmark.SevMark.DTO;

import com.sevmark.SevMark.model.Local;

import java.time.LocalTime;

public record MarkDTO(
        Long id,
        int dayOfMonth,
        String monthYear,
        String shortDay,
        String hour,
        Local local
) {
}
