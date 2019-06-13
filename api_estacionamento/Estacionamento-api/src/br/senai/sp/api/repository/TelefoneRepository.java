package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
