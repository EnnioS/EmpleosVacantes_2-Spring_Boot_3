package com.ennio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ennio.model.Solicitud;

public interface ISolicitudesService {
	void guardar(Solicitud solicitud);
	void eliminar(Integer idSolicitud);
	List<Solicitud> buscarTodas();
	Solicitud buscarPorId(Integer idSolicitud);
	Page<Solicitud> buscarTodas(Pageable page);
}
