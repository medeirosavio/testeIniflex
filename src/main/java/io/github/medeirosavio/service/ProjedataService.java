package io.github.medeirosavio.service;

import io.github.medeirosavio.dto.ProjedataDTO;
import io.github.medeirosavio.dto.FuncionarioDTO;
import io.github.medeirosavio.model.Funcionario;
import io.github.medeirosavio.model.Projedata;
import io.github.medeirosavio.repository.FuncionarioRepository;
import io.github.medeirosavio.repository.ProjedataRepository;
import io.github.medeirosavio.util.CnpjValidator;
import io.github.medeirosavio.util.DateValidator;
import io.github.medeirosavio.util.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjedataService {

    @Autowired
    private ProjedataRepository projedataRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void cadastrarEmpresa(ProjedataDTO projedataDTO) {
        try {
            Projedata projedata = converterDTOparaEntidade(projedataDTO);
            projedataRepository.save(projedata);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro de integridade de dados ao cadastrar a empresa", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro interno ao processar a solicitação", e);
        }
    }

    private Projedata converterDTOparaEntidade(ProjedataDTO projedataDTO) {
        Projedata projedata = new Projedata();
        validarCnpj(projedataDTO.getCnpj());
        projedata.setCnpj(projedataDTO.getCnpj());
        projedata.setEmail(projedataDTO.getEmail());
        projedata.setTelefone(projedataDTO.getTelefone());
        return projedata;
    }

    public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO, Long idEmpresa) {
        try {
            Funcionario funcionario = converterDTOparaEntidade(funcionarioDTO,idEmpresa);
            funcionarioRepository.save(funcionario);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro de integridade de dados ao cadastrar o funcionario", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro interno ao processar a solicitação", e);
        }
    }

    private Funcionario converterDTOparaEntidade(FuncionarioDTO funcionarioDTO, Long idEmpresa) {
        validarId(idEmpresa);
        validarAdulto(funcionarioDTO.getDataNascimento());
        Funcionario funcionario = new Funcionario();
        Projedata projedata = projedataRepository.getById(idEmpresa);
        funcionario.setProjedata(projedata);
        projedata.getFuncionarios().add(funcionario);
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setFuncao(funcionarioDTO.getFuncao());
        funcionario.setSalario(funcionarioDTO.getSalario());
        funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
        return funcionario;
    }

    private void validarCnpj(String cnpj) {
        if (!CnpjValidator.isValid(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
        }
    }

    private void validarId(Long id) {
        if (!IdValidator.isIdValid(id)) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    }

    private void validarAdulto(LocalDate dataNascimento){
        if (!DateValidator.isAdult(dataNascimento)){
            throw new IllegalArgumentException("Apenas maior de idade pode trabalhar");
        }
    }


}
