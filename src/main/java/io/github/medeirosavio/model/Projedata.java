package io.github.medeirosavio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Funcionarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projedata implements Empresa {

    @Id
    private Long id;
    @Column
    private String cnpj;
    @Column
    private String email;
    @Column
    private String telefone;
    @OneToMany(mappedBy = "projedata")
    private List<Funcionario> funcionarios = new ArrayList<>();


    @Override
    public void inserirFuncionarios() {

    }

    @Override
    public void removerFuncionario(String nome) {

    }

    @Override
    public void imprimifFuncionarios() {

    }

    @Override
    public void darAumento(double percentualAumento) {

    }

    @Override
    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return null;
    }

    @Override
    public List<Funcionario> aniversariantesDoMes(int mes) {
        return null;
    }

    @Override
    public Funcionario funcionarioMaisVelho() {
        return null;
    }

    @Override
    public List<Funcionario> listaFuncionariosOrdemAlfabetica() {
        return null;
    }

    @Override
    public double totalSalarios() {
        return 0;
    }

    @Override
    public Map<Funcionario, Double> salariosEmSalariosMinimos(double salarioMinimo) {
        return null;
    }
}
