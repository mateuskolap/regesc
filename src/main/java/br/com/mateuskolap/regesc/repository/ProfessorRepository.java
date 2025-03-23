package br.com.mateuskolap.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mateuskolap.regesc.orm.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
