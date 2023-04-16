package com.rentasservice.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentasservice.entity.Alumno;
import com.rentasservice.services.AlumnoService;
 
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	@Autowired
	private AlumnoService service; 
	
	@GetMapping("/hola")
	public String saludo(){
		return "Hola Carnal :)";
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable){
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}	
 	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
	
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
	
		
	private ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result, 
			@RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		Alumno entityDb = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	 
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException{
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnoDb.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}

}
