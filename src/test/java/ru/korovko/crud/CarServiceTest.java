package ru.korovko.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korovko.crud.dto.CarResponse;
import ru.korovko.crud.dto.CreateCarRequest;
import ru.korovko.crud.dto.UpdateCarRequest;
import ru.korovko.crud.mapper.CarMapper;
import ru.korovko.crud.model.Car;
import ru.korovko.crud.model.CarColor;
import ru.korovko.crud.repository.CarRepository;
import ru.korovko.crud.service.CarService;
import ru.korovko.crud.service.impl.CarServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

	private static final UUID CAR_ID = UUID.randomUUID();

    @Mock
    private CarRepository carRepository;
    private final CarMapper carMapper = Mappers.getMapper(CarMapper.class);
    private CarService carService;
    private CreateCarRequest createCarRequest;
    private Car car;

    @BeforeEach
    void init() {
		car = new Car()
				.setId(CAR_ID)
				.setColor(CarColor.BLACK)
				.setModel("AUDI")
				.setIsAutomatic(true)
				.setMaxSpeed(300)
				.setWeight(2000);
		createCarRequest = new CreateCarRequest()
				.setColor(CarColor.BLACK)
				.setModel("AUDI")
				.setIsAutomatic(true)
				.setMaxSpeed(300)
				.setWeight(2000);
        carService = new CarServiceImpl(carRepository, carMapper);
    }

    @Test
    void createCarTest() {
        when(carRepository.save(any())).thenReturn(car);
        CarResponse carResponse = carService.create(createCarRequest);
		assertEquals("AUDI", carResponse.getModel());
		assertEquals(2000, carResponse.getWeight());
		assertEquals(300, carResponse.getMaxSpeed());
    }

    @Test
	void updateCarTest() {
		when(carRepository.findById(CAR_ID)).thenReturn(Optional.of(car));
		when(carRepository.save(any())).thenReturn(car.setWeight(1900));
		UpdateCarRequest updateCarRequest = new UpdateCarRequest().setWeight(1900);
		CarResponse response = carService.update(CAR_ID, updateCarRequest);
		assertEquals(1900, response.getWeight());
	}

	@Test
	void getByIdTest() {
		when(carRepository.findById(CAR_ID)).thenReturn(Optional.of(car));
		CarResponse byId = carService.getById(CAR_ID);
		assertEquals(2000, byId.getWeight());
		assertEquals(CAR_ID, byId.getId());
	}

	@Test
	void getAllTest() {
		UUID newCarId = UUID.randomUUID();
		Car newCar = new Car()
				.setId(newCarId)
				.setManufactureDate(LocalDate.of(2020, 5, 23))
				.setModel("FERRARI")
				.setMaxSpeed(360);
		List<Car> cars = List.of(car, newCar);
		when(carRepository.findAll()).thenReturn(cars);
		List<CarResponse> all = carService.getAll();
		assertEquals(CAR_ID, all.get(0).getId());
		assertEquals(newCarId, all.get(1).getId());
	}

	@Test
	void deleteCarTest() {
		when(carRepository.findById(CAR_ID)).thenReturn(Optional.of(car));
    	carService.deleteById(CAR_ID);
    	verify(carRepository, times(1)).delete(car);
	}
}
