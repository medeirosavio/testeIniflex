package io.github.medeirosavio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

import javax.persistence.MappedSuperclass;
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {

    @Column(name = "nome", columnDefinition = "VARCHAR(255)")
    private String nome;
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    private LocalDate dataNascimento;

}
