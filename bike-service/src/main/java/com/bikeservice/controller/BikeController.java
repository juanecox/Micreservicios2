package com.bikeservice.controller;


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

import com.bikeservice.entity.Bike;
import com.bikeservice.service.BikeService;

 
@RestController
@RequestMapping("/bike")
public class BikeController {
	@Autowired
	BikeService bikeService;
	
	@GetMapping 
	public ResponseEntity<List<Bike>> lista() {
		List<Bike> bikes = bikeService.getBikeAll();
	 	if (bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		 
		return ResponseEntity.ok(bikes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bike> show(@PathVariable int id){
		Optional<Bike> bike = bikeService.getBikeById(id);
		if (bike.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bike.get());
	}
	
	@PostMapping
	public ResponseEntity<Bike> save(@RequestBody Bike bike){	
		
		Bike bikeNuevo = bikeService.saveBike(bike);
		if (bikeNuevo == null) {
			return ResponseEntity.noContent().build();
		}		
		return ResponseEntity.ok(bikeNuevo);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Bike> delete(@PathVariable("id") int id){
		
		bikeService.deleteBike(id);				
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/byuser/{id}")
	public ResponseEntity<List<Bike>> Listado(@PathVariable int id){
		List<Bike> bikes = bikeService.getByUsuario(id);
		if (bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bikes);
	}
}
