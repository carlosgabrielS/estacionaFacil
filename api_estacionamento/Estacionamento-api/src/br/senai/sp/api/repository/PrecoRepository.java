package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Preco;

public interface PrecoRepository extends JpaRepository<Preco, Long>{

	@Query("select p from Preco p where p.dataFim is null")
	Preco findByVigente(); 
}
