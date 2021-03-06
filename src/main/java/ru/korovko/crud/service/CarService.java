package ru.korovko.crud.service;

import ru.korovko.crud.dto.CreateCarRequest;
import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.dto.UpdateCarRequest;
import ru.korovko.crud.dto.CarSearchRequest;

import java.util.List;
import java.util.UUID;

public interface CarService {

    CarResponse create(CreateCarRequest request);

    CarResponse update(UUID id, UpdateCarRequest request);

    CarResponse getById(UUID id);

    List<CarResponse> getAll();

    void deleteById(UUID id);

    List<CarResponse> find(CarSearchRequest request);
}
