package com.usuarioservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.usuarioservice.entity.Usuario;
import com.usuarioservice.model.Bike;
import com.usuarioservice.model.Car;

 
public interface UsuarioService  {


	public List<Usuario> getUsuarioAll();
	public Optional<Usuario> getUsuarioById(int id);
	public Usuario saveUsuario(Usuario usuario) ;
	public  void deleteUsuario(int id);
	
  //uso de RestTemplate
	public List<Car> getCars(int usuarioId);
	public List<Bike> getBikes(int usuarioId);
	
  // uso de FeignClients	
	public List<Car> listaCars(int usuarioId);
	public List<Bike> listaBikes(int usuarioId);
	
	 public Car saveCar(int id,Car car) ;	 
	 public Bike saveBike(int id,Bike bike);
	 
  // recuperando dadtos desde 2 microservicio	 
	 public Map<String, Object> getUserAndVehicles(int usuarioId);
	
}
