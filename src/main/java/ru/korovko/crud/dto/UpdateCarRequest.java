package ru.korovko.crud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.korovko.crud.model.CarColor;

@Data
@Accessors(chain = true)
public class UpdateCarRequest {

    @ApiModelProperty(example = "AUDI")
    private String model;
    @ApiModelProperty(example = "300")
    private Integer maxSpeed;
    @ApiModelProperty(example = "true")
    private Boolean isAutomatic;
    @ApiModelProperty(example = "BLACK")
    private CarColor color;
    @ApiModelProperty(example = "1400")
    private Integer weight;
}
