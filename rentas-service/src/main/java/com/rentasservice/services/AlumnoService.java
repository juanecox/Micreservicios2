package com.rentasservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rentasservice.entity.Alumno;

public interface AlumnoService {	
	
	public Iterable<Alumno> findAll();
	public Optional<Alumno> findById(Long id);
	public Page<Alumno> findAll(Pageable pageable);	
	public List<Alumno> findByNombreOrApellido(String term);
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);
}
