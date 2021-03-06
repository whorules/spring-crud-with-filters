package ru.korovko.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korovko.crud.dto.CreateCarRequest;
import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.dto.UpdateCarRequest;
import ru.korovko.crud.mapper.CarMapper;
import ru.korovko.crud.model.Car;
import ru.korovko.crud.repository.CarRepository;
import ru.korovko.crud.dto.CarSearchRequest;
import ru.korovko.crud.service.CarService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarResponse create(CreateCarRequest request) {
        Car car = carMapper.toCar(request);
        return carMapper.toCarResponse(carRepository.save(car));
    }

    @Override
    public CarResponse update(UUID id, UpdateCarRequest request) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No car with such id: " + id));
        carMapper.update(car, request);
        return carMapper.toCarResponse(carRepository.save(car));
    }

    @Override
    public CarResponse getById(UUID id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No car with such id: " + id));
        return carMapper.toCarResponse(car);
    }

    @Override
    public List<CarResponse> getAll() {
        return carRepository.findAll().stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        carRepository.findById(id).ifPresent(carRepository::delete);
    }

    @Override
    public List<CarResponse> find(CarSearchRequest request) {
        return carRepository.find(request);
    }
}
