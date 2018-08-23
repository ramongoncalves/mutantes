package br.com.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mutants.model.Candidato;


@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
	
	    @Query("SELECT COUNT(c) FROM Candidato c WHERE c.tipo='H'")
	    Long countHumans();

	    @Query("SELECT COUNT(c) FROM Candidato c WHERE c.tipo='M'")
	    Long countMutants();
}
