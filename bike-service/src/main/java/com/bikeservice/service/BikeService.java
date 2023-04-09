package com.bikeservice.service;

import java.util.List;
import java.util.Optional;

import com.bikeservice.entity.Bike;
 

public interface BikeService  {
	
	public List<Bike> getBikeAll();
	public Optional<Bike> getBikeById(int id);
	public Bike saveBike(Bike usuario) ;
	public  void deleteBike(int id);
	public List<Bike>  getByUsuario(int usuarioId);
	
	
}
