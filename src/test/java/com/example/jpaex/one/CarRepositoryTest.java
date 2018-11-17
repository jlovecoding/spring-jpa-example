package com.example.jpaex.one;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldGetAllCars() {
        //Given
        Car car1 = Car.builder().carId("1234ABC").model("polo").build();
        Car car2 = Car.builder().carId("5678DEF").model("a3").build();
        Stream.of(car1, car2).forEach(testEntityManager::persistAndFlush);

        //When
        List<Car> cars = carRepository.findAll();

        //Then
        assertThat(cars).hasSize(2);
        assertThat(cars).containsExactlyInAnyOrder(car1, car2);
    }

    @Test
    public void shouldCreateCar() {
        //Given
        Car newCar = Car.builder().carId("1234").model("oneModel").build();

        //When
        Car created = carRepository.save(newCar);

        //Then
        assertThat(created.getCarId()).isEqualTo(newCar.getCarId());
        assertThat(created.getModel()).isEqualTo(newCar.getModel());
    }

    @Test
    public void shouldFindById() {
        //Given
        Car newCar = Car.builder().carId("1234").model("oneModel").build();
        carRepository.save(newCar);

        //When
        Optional<Car> found = carRepository.findById(newCar.getCarId());

        //Then
        assertThat(found).isPresent();
        assertThat(found.get()).isEqualTo(newCar);
    }

    @Test
    public void shouldUpdateCar() {
        //Given
        Car newCar = Car.builder().carId("1234").model("oneModel").build();
        carRepository.save(newCar);
        Car toUpdate = Car.builder().carId("1234").model("otherModel").build();

        //When
        Car updated = carRepository.save(toUpdate);

        //Then
        assertThat(updated.getModel()).isEqualTo(toUpdate.getModel());
        assertThat(carRepository.findAll()).hasSize(1);
        Optional<Car> found = carRepository.findById(newCar.getCarId());
        assertThat(found.isPresent());
        assertThat(found.get()).isEqualTo(toUpdate);
    }
}