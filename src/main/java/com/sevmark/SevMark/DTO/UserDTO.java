package com.sevmark.SevMark.DTO;

public record UserDTO(
    Long id,
    String email,
    String username,
    String password,
    String typeUser,
    String phone
) {
}
