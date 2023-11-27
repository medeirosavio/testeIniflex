package io.github.medeirosavio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public void inserirFuncionarios(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    @Override
    public void removerFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

    @Override
    public void imprimirFuncionarios() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat formatoNumerico = new DecimalFormat("#,##0.00");

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatoData));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("Salário: " + formatoNumerico.format(funcionario.getSalario()));
            System.out.println(); // Pular linha entre os funcionários
        }
    }

    @Override
    public Map<Funcao, List<Funcionario>> agruparPorFuncao() {
        Map<Funcao, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            Funcao funcao = funcionario.getFuncao();
            funcionariosPorFuncao.computeIfAbsent(funcao, k -> new ArrayList<>()).add(funcionario);
        }

        return funcionariosPorFuncao;
    }


    @Override
    public List<Funcionario> aniversariantesDoMes(int mes) {
        List<Funcionario> aniversariantes = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            LocalDate dataNascimento = funcionario.getDataNascimento();
            if (dataNascimento != null && dataNascimento.getMonthValue() == mes) {
                aniversariantes.add(funcionario);
            }
        }

        return aniversariantes;
    }

    @Override
    public Funcionario funcionarioMaisVelho() {
        Funcionario maisVelho = funcionarios.get(0);

        for (Funcionario funcionario : funcionarios) {
            LocalDate dataNascimentoAtual = funcionario.getDataNascimento();
            LocalDate dataNascimentoMaisVelho = maisVelho.getDataNascimento();

            if (dataNascimentoAtual.isBefore(dataNascimentoMaisVelho)) {
                maisVelho = funcionario;
            }
        }
        return maisVelho;
    }

    @Override
    public List<Funcionario> listaFuncionariosOrdemAlfabetica() {
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);

        Collections.sort(funcionariosOrdenados, Comparator.comparing(Funcionario::getNome));

        return funcionariosOrdenados;
    }

    @Override
    public BigDecimal totalSalarios() {
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        return totalSalarios;
    }

    @Override
    public Map<Funcionario, Double> salariosEmSalariosMinimos() {
        BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salario = funcionario.getSalario();
            BigDecimal salariosMinimos = salario.divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }
        return null;
    }
}
