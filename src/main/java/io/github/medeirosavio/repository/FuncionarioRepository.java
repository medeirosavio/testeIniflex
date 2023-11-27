package io.github.medeirosavio.repository;

import io.github.medeirosavio.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}
