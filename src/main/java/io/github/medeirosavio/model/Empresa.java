package io.github.medeirosavio.model;

import java.util.List;
import java.util.Map;

public interface Empresa {

    void inserirFuncionarios();
    void removerFuncionario(String nome);
    void imprimifFuncionarios();
    void darAumento(double percentualAumento);
    Map<String, List<Funcionario>> agruparPorFuncao();
    List<Funcionario> aniversariantesDoMes(int mes);
    Funcionario funcionarioMaisVelho();
    List<Funcionario> listaFuncionariosOrdemAlfabetica();
    double totalSalarios();
    Map<Funcionario, Double> salariosEmSalariosMinimos(double salarioMinimo);



}
