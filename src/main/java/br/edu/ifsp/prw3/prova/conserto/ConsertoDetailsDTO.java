package br.edu.ifsp.prw3.prova.conserto;

import br.edu.ifsp.prw3.prova.mecanico.MecanicoDTO;
import br.edu.ifsp.prw3.prova.veiculo.VeiculoDTO;

public record ConsertoDetailsDTO(
    Long id,
    String dataEntrada,
    String dataSaida,
    MecanicoDTO mecanicoResponsavel,
    VeiculoDTO veiculo
) {
    public ConsertoDetailsDTO(Conserto c) {
        this(c.getId(), c.getDataEntrada(), c.getDataSaida(),
                new MecanicoDTO(c.getMecanicoResponsavel()),
                new VeiculoDTO(c.getVeiculo())
        );
    }
}
