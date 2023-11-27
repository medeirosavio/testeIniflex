package io.github.medeirosavio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.medeirosavio.model.Funcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuncionarioDTO {
    @NotBlank
    private String nome;
    @NotNull
    @Past
    private LocalDate dataNascimento;
    @NotNull
    private BigDecimal salario;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Funcao funcao;

}
