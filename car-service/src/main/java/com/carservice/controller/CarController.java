package com.carservice.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carservice.entity.Car;
import com.carservice.service.CarService;
 
@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	CarService carService;
	 	
	@GetMapping 
	public ResponseEntity<List<Car>> lista() {
		List<Car> cars = carService.getCarAll();
	 	if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}		 
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> show(@PathVariable int id){
		Optional<Car> car = carService.getCarById(id);
		if (car.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(car.get());
	}
	
	@PostMapping
	public ResponseEntity<Car> save(@RequestBody Car car){	
		
		Car carNuevo = carService.saveCar(car);
		if (carNuevo == null) {
			return ResponseEntity.noContent().build();
		}		
		return ResponseEntity.ok(carNuevo);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Car> delete(@PathVariable("id") int id){
		
		carService.deleteCar(id);				
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/byuser/{id}")
	public ResponseEntity<List<Car>> Listado(@PathVariable int id){
		List<Car> cars = carService.getByUsuario(id);
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
}
