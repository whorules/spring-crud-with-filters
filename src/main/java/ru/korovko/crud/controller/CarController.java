package ru.korovko.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korovko.crud.dto.CreateCarRequest;
import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.dto.UpdateCarRequest;
import ru.korovko.crud.dto.CarSearchRequest;
import ru.korovko.crud.service.CarService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public CarResponse create(@RequestBody CreateCarRequest request) {
        return carService.create(request);
    }

    @PutMapping("/{id}")
    public CarResponse update(@PathVariable UUID id, @RequestBody UpdateCarRequest request) {
        return carService.update(id, request);
    }

    @GetMapping("/{id}")
    public CarResponse getById(@PathVariable UUID id) {
        return carService.getById(id);
    }

    @GetMapping
    public List<CarResponse> getAll() {
        return carService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        carService.deleteById(id);
    }

    @PostMapping("/list")
    public List<CarResponse> find(@RequestBody CarSearchRequest request) {
        return carService.find(request);
    }
}
