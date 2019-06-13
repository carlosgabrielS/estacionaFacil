package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long>{
	
}
