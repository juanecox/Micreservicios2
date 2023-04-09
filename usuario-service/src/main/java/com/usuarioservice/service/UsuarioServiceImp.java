package com.usuarioservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.usuarioservice.entity.Usuario;
import com.usuarioservice.feignclients.BikeFeignClient;
import com.usuarioservice.feignclients.CarFeignClient;
import com.usuarioservice.model.Bike;
import com.usuarioservice.model.Car;
import com.usuarioservice.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RestTemplate restTemplate;

 	@Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;
        	
       	
	@Transactional(readOnly=true)
	public List<Usuario> getUsuarioAll(){
		return usuarioRepository.findAll();
		
	}
	@Override
	@Transactional(readOnly=true)
	public Optional<Usuario> getUsuarioById(int id) {
		return usuarioRepository.findById(id);
	}
	@Override
	@Transactional
	public Usuario saveUsuario(Usuario usuario) {
		Usuario usuarioNuevo = usuarioRepository.save(usuario);
		return usuarioNuevo;
	}
	@Override
	@Transactional
	public  void deleteUsuario(int id) {
		usuarioRepository.deleteById(id);
	}
	//leyendo miroservicios con RestTemplate
	public List<Car> getCars(int usuarioId)
	{
		return  restTemplate.getForObject("http://car-service/car/byuser/" + usuarioId, List.class);
	}
	public List<Bike> getBikes(int usuarioId)
	{		
		return restTemplate.getForObject("http://bike-service/bike/byuser/" + usuarioId, List.class);
	}
	 
    //listar llamando microservicios con FeignClient
	
	@Override
	public List<Car> listaCars(int usuarioId) {		 
		return carFeignClient.getCars2(usuarioId);
	}
	
	@Override
	public List<Bike> listaBikes(int usuarioId) {
		return bikeFeignClient.getBikes2(usuarioId);
	}
	//guardando en los microservicios con FeignClient
	public Car saveCar(int id,Car car) {
		 car.setUsuarioId(id);
		 Car carNew = carFeignClient.save(car);
		 return carNew; 
	}
	 
	public Bike saveBike(int id,Bike bike) {
		 bike.setUsuarioId(id);
		 Bike bikeNew = bikeFeignClient.save(bike);
		 return bikeNew; 
	 }
	 
	//para guardar las tablas desde el microservicio usuario FEIGN CLIENT
	//pone el id de usaurio en la url para que en el jason solo vayan marca y modelo, 
	//podria ser que no pusiera nada en la url e ir el usuarioId en el jason ,DOS MANERAS

	public Map<String, Object> getUserAndVehicles(int usuarioId) {
        Map<String, Object> result = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null) {
            result.put("Mensaje", "no existe el usuario");
            return result;
        }
        result.put("Usuario", usuario);
        List<Car> cars = carFeignClient.getCars2(usuarioId);
        if(cars.isEmpty())
            result.put("Cars", "ese usuario no tiene coches");
        else
            result.put("Cars", cars);
        List<Bike> bikes = bikeFeignClient.getBikes2(usuarioId);
        if(bikes.isEmpty())
            result.put("Bikes", "ese usaurio no tiene motos");
        else
            result.put("Bikes", bikes);
        return result;
    }
	
    
   
}
