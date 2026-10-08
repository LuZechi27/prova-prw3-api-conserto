package br.edu.ifsp.prw3.prova.conserto;

import br.edu.ifsp.prw3.prova.mecanico.MecanicoDTO;
import br.edu.ifsp.prw3.prova.veiculo.VeiculoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ConsertoDTO(

        @NotBlank
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        String dataEntrada,

        @NotBlank
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        String dataSaida,

        @Valid
        MecanicoDTO mecanicoResponsavel,

        @Valid
        VeiculoDTO veiculo
) {}
