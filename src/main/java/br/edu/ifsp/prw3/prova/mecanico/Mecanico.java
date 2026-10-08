package br.edu.ifsp.prw3.prova.mecanico;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {

    private String nomeMecanico;
    private Integer anosExperiencia;

    public Mecanico(MecanicoDTO dto) {
        this.nomeMecanico = dto.nome();
        this.anosExperiencia = dto.anosExperiencia();
    }

    public void update(String nomeMecanico, Integer anosExperiencia) {
        if(nomeMecanico != null) this.nomeMecanico = nomeMecanico;
        if(anosExperiencia != null) this.anosExperiencia = anosExperiencia;
    }
}
