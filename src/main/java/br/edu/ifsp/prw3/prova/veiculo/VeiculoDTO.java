package br.edu.ifsp.prw3.prova.veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VeiculoDTO(

        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        @NotBlank
        @Pattern(regexp = "\\d{4}")
        String ano,

        String cor
) {
        public VeiculoDTO(Veiculo v) {
                this(v.getMarcaVeiculo(), v.getModeloVeiculo(),
                        v.getAnoVeiculo(),
                        v.getCorVeiculo()
                );
        }
}
