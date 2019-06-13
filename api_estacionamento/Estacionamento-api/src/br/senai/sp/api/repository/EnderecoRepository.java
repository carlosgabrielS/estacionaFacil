package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
