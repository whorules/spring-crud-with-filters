package ru.korovko.crud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.korovko.crud.model.CarColor;

import java.time.LocalDate;
import java.util.List;

@Data
public class CarSearchRequest {

    @ApiModelProperty(example = "[BMW, AUDI]")
    private List<String> models;
    @ApiModelProperty(example = "BLUE")
    private CarColor color;
    @ApiModelProperty(example = "true")
    private Boolean isAutomatic;
    @ApiModelProperty(example = "2012-03-08")
    private LocalDate startManufactureDate;
    @ApiModelProperty(example = "2015-12-31")
    private LocalDate endManufactureDate;
    @ApiModelProperty(example = "2000")
    private Integer maxWeight;
    @ApiModelProperty(example = "300")
    private Integer maxSpeed;
}
