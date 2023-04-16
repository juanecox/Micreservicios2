package com.rentasservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentasservice.entity.Alumno;
import com.rentasservice.repository.AlumnoRepository;
 
@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return alumnoRepository.findByNombreOrApellido(term);
	}
	
	@Override
	public Iterable<Alumno> findAll() {		
		return alumnoRepository.findAll();
	}	

	@Override
	@Transactional(readOnly = true)
	public Page<Alumno> findAll(Pageable pageable) {
		return alumnoRepository.findAll(pageable);
	}
  
	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return alumnoRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return  alumnoRepository.save(alumno) ;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		alumnoRepository.deleteById(id);
	}
	
 

}
