package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
