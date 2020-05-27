package com.utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.modelo.Instrumento;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long>{
	
}
