package com.ennio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ennio.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
