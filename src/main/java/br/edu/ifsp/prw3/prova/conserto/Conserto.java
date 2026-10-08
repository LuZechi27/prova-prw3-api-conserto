package br.edu.ifsp.prw3.prova.conserto;

import br.edu.ifsp.prw3.prova.mecanico.Mecanico;
import br.edu.ifsp.prw3.prova.veiculo.Veiculo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "consertos")
@Entity(name = "Conserto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conserto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataEntrada;
    private String dataSaida;

    @Embedded
    private Mecanico mecanicoResponsavel;

    @Embedded
    private Veiculo veiculo;

    private Boolean ativo;

    public Conserto(ConsertoDTO dto) {
        this.ativo = true;
        this.dataEntrada = dto.dataEntrada();
        this.dataSaida = dto.dataSaida();
        this.mecanicoResponsavel = new Mecanico(dto.mecanicoResponsavel());
        this.veiculo = new Veiculo(dto.veiculo());
    }

    public void delete() {
        this.ativo = false;
    }

    public void update(ConsertoUpdateDTO dto) {
        if (dto.dataSaida() != null) this.dataSaida = dto.dataSaida();
        if (dto.nomeMecanico() != null || dto.anosExperiencia() != null)
            this.mecanicoResponsavel.update(dto.nomeMecanico(), dto.anosExperiencia());
    }
}
