package io.github.medeirosavio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {

    @Column
    private String nome;
    @Column
    private LocalDate dataNascimento;

}
