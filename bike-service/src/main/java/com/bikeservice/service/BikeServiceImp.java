package com.bikeservice.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikeservice.entity.Bike;
import com.bikeservice.repository.BikeRepository;

 
 

@Service
public class BikeServiceImp implements BikeService {
	
	@Autowired
	BikeRepository bikeRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Bike> getBikeAll(){
		return bikeRepository.findAll();		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Bike> getBikeById(int id) {
		return bikeRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Bike saveBike(Bike Bike) {
		Bike BikeNuevo = bikeRepository.save(Bike);
		return BikeNuevo;
	}
	
	@Override
	@Transactional
	public  void deleteBike(int id) {
		bikeRepository.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Bike> getByUsuario(int usuarioId) {		
		return bikeRepository.findByUsuarioId(usuarioId);
	}
}
