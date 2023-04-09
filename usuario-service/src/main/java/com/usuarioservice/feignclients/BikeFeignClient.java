package com.usuarioservice.feignclients;

 

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuarioservice.model.Bike;

@FeignClient(name="bike-service" ,url="http://localhost:8003")
@RequestMapping("/bike")
public interface BikeFeignClient {
	
	@PostMapping()
	public Bike save(@RequestBody Bike bike);
	
	/*lista ya se hizo FeignClient vs restTemplate*/
	@GetMapping("/byuser/{usuarioId}")
	public List<Bike> getBikes2(@PathVariable("usuarioId") int usuarioId);
}
