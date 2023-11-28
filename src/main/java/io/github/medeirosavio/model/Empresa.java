package io.github.medeirosavio.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Empresa {

    void inserirFuncionarios(Funcionario funcionario);
    void removerFuncionario(Funcionario funcionario);

    List<String> imprimirFuncionarios();

    Map<Funcao, List<Funcionario>> agruparPorFuncao(Funcao funcao);
    List<Funcionario> aniversariantesDoMes(int mes);
    Funcionario funcionarioMaisVelho();
    List<Funcionario> listaFuncionariosOrdemAlfabetica();
    BigDecimal totalSalarios();
    Map<Funcionario, Double> salariosEmSalariosMinimos();



}
