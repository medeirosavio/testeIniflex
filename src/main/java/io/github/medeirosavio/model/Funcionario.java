package io.github.medeirosavio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Funcionarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private BigDecimal salario;
    @Column
    private Funcao funcao;
    @ManyToOne
    @JoinColumn(name = "id_projedata")
    private Projedata projedata;
    public void darAumento(double percentualAumento){
        BigDecimal percentualDecimal = BigDecimal.valueOf(1 + percentualAumento);
        BigDecimal novoSalario = salario.multiply(percentualDecimal);
        this.salario = novoSalario;
    }

}
