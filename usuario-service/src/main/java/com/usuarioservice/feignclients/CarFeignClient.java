package com.usuarioservice.feignclients;

 
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

 
import com.usuarioservice.model.Car;

@FeignClient(name="car-service")
@RequestMapping("/car")
public interface CarFeignClient {
	
	@PostMapping()
	public Car save(@RequestBody Car car);
	
	/*lista con FeignClient vs para comparar restTemplate*/
	@GetMapping("/byuser/{usuarioId}")
	public List<Car> getCars2(@PathVariable("usuarioId") int usuarioId);
}