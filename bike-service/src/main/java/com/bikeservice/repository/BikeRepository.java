package com.bikeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeservice.entity.Bike;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository< Bike, Integer> {

    List<Bike> findByUsuarioId(int usuarioId);
}
