package ru.korovko.crud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.korovko.crud.model.CarColor;

import java.time.LocalDate;

@Data
public class CreateCarRequest {

    @ApiModelProperty(example = "AUDI")
    private String model;
    @ApiModelProperty(example = "2020-05-13")
    private LocalDate manufactureDate;
    @ApiModelProperty(example = "300")
    private Integer maxSpeed;
    @ApiModelProperty(example = "true")
    private Boolean isAutomatic;
    @ApiModelProperty(example = "BLACK")
    private CarColor color;
    @ApiModelProperty(example = "1400")
    private Integer weight;
}
