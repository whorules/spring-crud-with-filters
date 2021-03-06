package ru.korovko.crud.dto;

import lombok.Data;
import ru.korovko.crud.model.CarColor;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CarResponse {

    private UUID id;
    private String model;
    private LocalDate manufactureDate;
    private Integer maxSpeed;
    private Boolean isAutomatic;
    private CarColor color;
    private Integer weight;
}
