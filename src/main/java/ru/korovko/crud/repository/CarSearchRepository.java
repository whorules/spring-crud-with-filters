package ru.korovko.crud.repository;

import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.dto.CarSearchRequest;

import java.util.List;

public interface CarSearchRepository {

    List<CarResponse> find(CarSearchRequest request);
}
