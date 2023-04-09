package com.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carservice.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository< Car, Integer> {

    List<Car> findByUsuarioId(int usuarioId);
}
