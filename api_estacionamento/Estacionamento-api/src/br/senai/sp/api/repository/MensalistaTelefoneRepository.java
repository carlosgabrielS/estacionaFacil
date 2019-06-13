package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.MensalistaTelefone;

public interface MensalistaTelefoneRepository extends JpaRepository<MensalistaTelefone, Long> {
	
}
