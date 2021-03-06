package ru.korovko.crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.dto.CreateCarRequest;
import ru.korovko.crud.dto.UpdateCarRequest;
import ru.korovko.crud.model.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toCar(CreateCarRequest request);

    CreateCarRequest toCarDto(Car car);

    CarResponse toCarResponse(Car car);

    void update(@MappingTarget Car car, UpdateCarRequest dto);
}
