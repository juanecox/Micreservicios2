package com.carservice.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carservice.entity.Car;
import com.carservice.repository.CarRepository;

 

@Service
public class CarServiceImp implements CarService {
	
	@Autowired
	CarRepository carRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Car> getCarAll(){
		return carRepository.findAll();		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Car> getCarById(int id) {
		return carRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Car saveCar(Car Car) {
		Car CarNuevo = carRepository.save(Car);
		return CarNuevo;
	}
	
	@Override
	@Transactional
	public  void deleteCar(int id) {
		carRepository.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Car> getByUsuario(int usuarioId) {		
		return carRepository.findByUsuarioId(usuarioId);
	}
}
