package com.usuarioservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioservice.entity.Usuario;
import com.usuarioservice.model.Bike;
import com.usuarioservice.model.Car;
import com.usuarioservice.service.UsuarioService;
 
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/saludo")
	public String ver() {
		return "Hola Mundo";
	}
	
	@GetMapping() 
	public ResponseEntity<List<Usuario>> lista() {
		List<Usuario> usuarios = usuarioService.getUsuarioAll();
	 	if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		 
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> show(@PathVariable("id") int id){
		Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
		if (usuario.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuario.get());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){	
		
		Usuario usuarioNuevo = usuarioService.saveUsuario(usuario);
		if (usuarioNuevo == null) {
			return ResponseEntity.noContent().build();
		}		
		return ResponseEntity.ok(usuarioNuevo);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable("id") int id,@RequestBody Usuario usuario){	
		Optional<Usuario> usuarioUpdate =  usuarioService.getUsuarioById(id);
		if (usuarioUpdate.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		Usuario usuarioNuevo = usuarioService.saveUsuario(usuario);
		if (usuarioNuevo == null) {
			return ResponseEntity.noContent().build();
		}		
		return ResponseEntity.ok(usuarioNuevo);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable("id") int id){
		
		usuarioService.deleteUsuario(id);				
		return ResponseEntity.noContent().build();
	}
	
	//recuperando los datos en Lista desde otros microservicios usa [RestTemplate]
	@GetMapping("cars/{usuarioId}")
	public ResponseEntity<List<Car>> listarCars(@PathVariable("usuarioId") int usuarioId){
		Optional<Usuario> user = usuarioService.getUsuarioById(usuarioId);
		if (user.isEmpty()) {
			return ResponseEntity.noContent().build();
		}		
		List<Car> cars = usuarioService.getCars(usuarioId);
		return ResponseEntity.ok(cars);
	}
	@GetMapping("bikes/{usuarioId}")
	public ResponseEntity<List<Bike>> listarBikes(@PathVariable("usuarioId") int usuarioId){
		Optional<Usuario> user = usuarioService.getUsuarioById(usuarioId);
		if (user.isEmpty()) {
			return ResponseEntity.noContent().build();
		}		
		List<Bike> bikes = usuarioService.getBikes(usuarioId);
		return ResponseEntity.ok(bikes);
	}
	//guardando car y bikes desde el microservio usuarios usa [FeignClien]
	 @PostMapping("/savecar/{usuarioId}")
	    public ResponseEntity<Car> saveCar(@PathVariable("usuarioId") int usuarioId, @RequestBody Car car) {
	        if(usuarioService.getUsuarioById(usuarioId) == null)
	            return ResponseEntity.notFound().build();
	        Car carNew = usuarioService .saveCar(usuarioId, car);
	        return ResponseEntity.ok(carNew);
	    }

	    @PostMapping("/savebike/{usuarioId}")
	    public ResponseEntity<Bike> saveBike(@PathVariable("usuarioId") int usuarioId, @RequestBody Bike bike) {
	        if(usuarioService.getUsuarioById(usuarioId) == null)
	            return ResponseEntity.notFound().build();
	        Bike bikeNew = usuarioService.saveBike(usuarioId, bike);
	        return ResponseEntity.ok(bikeNew);
	    }
	    //recuperando todos los datos desde 2 microservicios usa [FeignClien] 
	    
	    @GetMapping("/getAll/{usuarioId}")
	    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("usuarioId") int usuarioId) {
	        Map<String, Object> result = usuarioService.getUserAndVehicles(usuarioId);
	    	// Map<String, Object> result = new HashMap<>();
	    	// result.put("Mensaje", "sale error");
	        return ResponseEntity.ok(result);
	    }
	 
	    
}
