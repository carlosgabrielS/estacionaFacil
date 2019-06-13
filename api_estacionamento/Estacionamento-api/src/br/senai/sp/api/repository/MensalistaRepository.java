package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Mensalista;

public interface MensalistaRepository extends JpaRepository<Mensalista, Long>{

}
