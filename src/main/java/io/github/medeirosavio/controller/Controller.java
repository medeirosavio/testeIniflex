package io.github.medeirosavio.controller;

import io.github.medeirosavio.dto.ProjedataDTO;
import io.github.medeirosavio.dto.FuncionarioDTO;
import io.github.medeirosavio.model.Funcao;
import io.github.medeirosavio.model.Funcionario;
import io.github.medeirosavio.service.ProjedataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/testeIniflex")
public class Controller {

    @Autowired
    private ProjedataService projedataService;

    @PostMapping("/cadastrar/empresa")
    public ResponseEntity<String> cadastrarEmpresa(@RequestBody ProjedataDTO projedataDTO) {
        projedataService.cadastrarEmpresa(projedataDTO);
        return new ResponseEntity<>("Empresa cadastrada com sucesso", HttpStatus.CREATED);
    }

    @PostMapping("/cadastrar/funcionario")
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @RequestParam Long idEmpresa) {
        projedataService.cadastrarFuncionario(funcionarioDTO,idEmpresa);
        return new ResponseEntity<>("Funcionario cadastrado com sucesso", HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{nome}")
    public void removerFuncionarioPorNome(@PathVariable String nome, @RequestParam Long idEmpresa) {
        projedataService.removerFuncionarioPorNome(nome,idEmpresa);
    }

    @GetMapping("/funcionarios/{idEmpresa}")
    public ResponseEntity<List<Funcionario>> obterFuncionariosPorEmpresa(@PathVariable Long idEmpresa) {
        projedataService.obterFuncionariosPorEmpresa(idEmpresa);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/aumentarSalarios/{idEmpresa}")
    public ResponseEntity<String> aumentarSalarios(@RequestParam Long idEmpresa, @RequestParam double percentualAumento) {
        projedataService.darAumentoParaFuncionarios(idEmpresa, percentualAumento);
        return ResponseEntity.ok("Aumento aplicado com sucesso!");
    }

    @GetMapping("/funcionariosPorFuncao")
    public ResponseEntity<Map<Funcao, List<Funcionario>>> obterFuncionariosPorFuncao
            (@RequestParam Long idEmpresa, @RequestParam Funcao funcao) {
        Map<Funcao, List<Funcionario>> funcionariosPorFuncao = projedataService.obterFuncionariosPorFuncao(idEmpresa, funcao);
        return ResponseEntity.ok(funcionariosPorFuncao);
    }

    @GetMapping("/{idEmpresa}/aniversariantes/{mes}")
    public List<Funcionario> obterAniversariantesDoMes(
            @PathVariable Long idEmpresa,
            @PathVariable int mes
    ) {
        return projedataService.obterAniversariantesDoMes(idEmpresa, mes);
    }

    @GetMapping("/funcionarioMaisVelho/{idEmpresa}")
    public Funcionario obterFuncionarioMaisVelho(@PathVariable Long idEmpresa) {
        return projedataService.obterFuncionarioMaisVelho(idEmpresa);
    }

    @GetMapping
    public List<Funcionario> listarFuncionariosOrdemAlfabetica(@PathVariable Long idEmpresa) {
        return projedataService.listarFuncionariosOrdemAlfabetica(idEmpresa);
    }

    @GetMapping("/totalSalarios/{idEmpresa}")
    public BigDecimal calcularTotalSalarios(@PathVariable Long idEmpresa) {
        return projedataService.calcularTotalSalarios(idEmpresa);
    }

    @GetMapping("/imprimirQuantidadeSalarios/{idEmpresa}")
    public Map<Funcionario, Double> imprimirQuantidadeSalarios(@PathVariable Long idEmpresa) {
        return projedataService.calcularQuantidadeSalarios(idEmpresa);
    }
}
