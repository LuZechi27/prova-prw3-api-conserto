package br.edu.ifsp.prw3.prova.mecanico;

import jakarta.validation.constraints.NotBlank;

public record MecanicoDTO(

        @NotBlank
        String nome,

        Integer anosExperiencia
) {
        public MecanicoDTO(Mecanico m) {
                this(m.getNomeMecanico(), m.getAnosExperiencia());
        }
}
