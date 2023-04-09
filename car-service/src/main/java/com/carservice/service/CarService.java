package com.carservice.service;


import java.util.List;
import java.util.Optional;

import com.carservice.entity.Car;

 
 
public interface CarService  {
	
	public List<Car> getCarAll();
	public Optional<Car> getCarById(int id);
	public Car saveCar(Car usuario) ;
	public  void deleteCar(int id);
	public List<Car>  getByUsuario(int usuarioId);
	
	
}
