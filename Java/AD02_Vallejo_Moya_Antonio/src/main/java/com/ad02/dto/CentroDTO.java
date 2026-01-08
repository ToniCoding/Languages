package com.ad02.dto;

import java.util.List;

import lombok.Data;

@Data
public class CentroDTO {
    private Long id;
    private String nombre;
    private String localidad;
    private List<Long> aulasIds;
}
