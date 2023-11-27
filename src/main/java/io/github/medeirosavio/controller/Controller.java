package io.github.medeirosavio.controller;

import io.github.medeirosavio.dto.EmpresaDTO;
import io.github.medeirosavio.dto.FuncionarioDTO;
import io.github.medeirosavio.service.EmpresaService;
import io.github.medeirosavio.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testeIniflex")
public class Controller {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/cadastrar/empresa")
    public ResponseEntity<String> cadastrarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        empresaService.cadastrarEmpresa(empresaDTO);
        return new ResponseEntity<>("Empresa cadastrada com sucesso", HttpStatus.CREATED);
    }

    @PostMapping("/cadastrar/funcionario")
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        empresaService.cadastrarFuncionario(funcionarioDTO);
        return new ResponseEntity<>("Funcionário cadastrado com sucesso", HttpStatus.CREATED);
    }

    


}
