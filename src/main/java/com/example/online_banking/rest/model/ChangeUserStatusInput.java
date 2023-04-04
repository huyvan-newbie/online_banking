package com.example.online_banking.rest.model;

import lombok.Data;

@Data
public class ChangeUserStatusInput {
    private Long id;
    private Integer status;
}
