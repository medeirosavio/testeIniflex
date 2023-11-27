package io.github.medeirosavio.repository;

import io.github.medeirosavio.model.Projedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjedataRepository extends JpaRepository<Projedata,Long> {
}
