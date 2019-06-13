package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	
}
