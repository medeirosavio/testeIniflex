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
@Table(name = "Projedata")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projedata implements Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cnpj;
    @Column
    private String email;
    @Column
    private String telefone;
    @OneToMany(mappedBy = "projedata", cascade = CascadeType.ALL, orphanRemoval = true)
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
    public List<String> imprimirFuncionarios() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat formatoNumerico = new DecimalFormat("#,##0.00");

        List<String> resultado = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            StringBuilder linha = new StringBuilder();
            linha.append("Nome: ").append(funcionario.getNome()).append("\n");
            linha.append("Data de Nascimento: ").append(funcionario.getDataNascimento().format(formatoData)).append("\n");
            linha.append("Função: ").append(funcionario.getFuncao()).append("\n");
            linha.append("Salário: ").append(formatoNumerico.format(funcionario.getSalario())).append("\n\n");

            resultado.add(linha.toString());
        }
        return resultado;
    }

    @Override
    public Map<Funcao, List<Funcionario>> agruparPorFuncao(Funcao funcao) {
        Map<Funcao, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
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
