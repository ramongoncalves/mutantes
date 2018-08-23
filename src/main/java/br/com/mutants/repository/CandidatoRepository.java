package br.com.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mutants.model.Candidato;


@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> { }
