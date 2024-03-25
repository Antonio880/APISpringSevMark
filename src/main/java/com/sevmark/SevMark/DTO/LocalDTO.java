package com.sevmark.SevMark.DTO;

import com.sevmark.SevMark.model.User;

public record LocalDTO(
    Long id,
    String locationName,
    Double price,
    String description,
    String obs,
    User user
) {
}
