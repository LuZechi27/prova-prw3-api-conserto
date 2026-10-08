package br.edu.ifsp.prw3.prova.conserto;

import jakarta.validation.constraints.NotNull;

public record ConsertoUpdateDTO(

        // tirei o id daqui para seguir o padrão PUT path/entidade/id
        // nos slides o id é pelo corpo
        // id recebe pela requisicao e não pelo corp""o

        String dataSaida,
        String nomeMecanico,
        Integer anosExperiencia
) {
}
