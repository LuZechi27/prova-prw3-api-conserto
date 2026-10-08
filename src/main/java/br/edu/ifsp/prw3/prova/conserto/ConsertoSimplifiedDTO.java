package br.edu.ifsp.prw3.prova.conserto;

public record ConsertoSimplifiedDTO(
        Long id,
        String dataEntrada,
        String dataSaida,
        String nomeMecanico,
        String marcaVeiculo,
        String modeloVeiculo
) {

    public ConsertoSimplifiedDTO(Conserto c) {
        this(c.getId(), c.getDataEntrada(), c.getDataSaida(),
                c.getMecanicoResponsavel().getNomeMecanico(),
                c.getVeiculo().getMarcaVeiculo(),
                c.getVeiculo().getModeloVeiculo()
        );
    }
}
