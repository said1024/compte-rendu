package com.said.compte_rendu.dto;

import lombok.Data;

    @Data
    public class UserResponseDto {
        private String firstName;
        private String lastName;
        private String email;
        private String jwt;
    }
