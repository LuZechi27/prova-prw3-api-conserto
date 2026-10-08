package br.edu.ifsp.prw3.prova.veiculo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    private String marcaVeiculo;
    private String modeloVeiculo;
    private String anoVeiculo;
    private String corVeiculo;

    public Veiculo(VeiculoDTO dto) {
        this.marcaVeiculo = dto.marca();
        this.modeloVeiculo = dto.modelo();
        this.anoVeiculo = dto.ano();
        this.corVeiculo = dto.cor();
    }
}
