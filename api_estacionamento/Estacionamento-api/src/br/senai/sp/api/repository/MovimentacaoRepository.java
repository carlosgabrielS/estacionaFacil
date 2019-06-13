package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
	
	@Query("select mov from Movimentacao mov where mov.placa = ?1")
	Movimentacao findByPlaca(String placa);
	
	@Query("select est from Movimentacao est where est.horaSaida is null")
	List<Movimentacao> findByEstacionados();
}
