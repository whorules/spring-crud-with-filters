package ru.korovko.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korovko.crud.model.Car;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID>, CarSearchRepository {
}
