package com.example.online_banking.rest.model;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    public Page(List<T> data) {
        this.data = data;
    }

    private List<T> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
}
