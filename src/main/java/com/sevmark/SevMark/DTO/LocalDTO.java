package com.sevmark.SevMark.DTO;

public record LocalDTO(
    Long id,
    String locationName,
    Double price,
    String description,
    String obs
) {
}
