package br.edu.ifsp.prw3.prova.controller;

import br.edu.ifsp.prw3.prova.conserto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("conserto")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsertoDetailsDTO> create(@RequestBody @Valid ConsertoDTO dto, UriComponentsBuilder uriBuilder) {
        Conserto conserto = new Conserto(dto);
        repository.save(conserto);

        URI uri = uriBuilder.path("/conserto/{id}").buildAndExpand(conserto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ConsertoDetailsDTO(conserto));
    }

    @GetMapping
    public ResponseEntity<Page<ConsertoDetailsDTO>> getAll(Pageable pageable) {
        Page<ConsertoDetailsDTO> page = repository.findAllByAtivoTrue(pageable).map(ConsertoDetailsDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("simplificado")
    public ResponseEntity<List<ConsertoSimplifiedDTO>> getSimplified() {
        List<ConsertoSimplifiedDTO> response = repository.findAllByAtivoTrue()
                .stream().map(ConsertoSimplifiedDTO::new).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsertoDetailsDTO> getConsertoById(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            Conserto conserto = consertoOptional.get();
            return ResponseEntity.ok(new ConsertoDetailsDTO(conserto));
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid ConsertoUpdateDTO dto, @PathVariable @NotNull Long id) {
        if (idIsNotValid(id)) return ResponseEntity.notFound().build();

        if (dto.dataSaida() == null && dto.nomeMecanico() == null && dto.anosExperiencia() == null)
            return ResponseEntity.ok(new GenericResponseDTO(
                    "Nenhuma alteração feira, verifique se o corpo da requisição segue o padrão: { dataSaida: x, nomeMecanico: x, anosExperiencia: x } "
            ));

        Conserto conserto = repository.getReferenceById(id);
        conserto.update(dto);

        return ResponseEntity.ok(new ConsertoDetailsDTO(conserto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ConsertoDetailsDTO> delete(@PathVariable Long id) {
        if (idIsNotValid(id)) return ResponseEntity.notFound().build();

        Conserto conserto = repository.getReferenceById(id);
        conserto.delete();
        return ResponseEntity.noContent().build();
    }

    private boolean idIsNotValid(Long id) {
        return !repository.existsById(id);
    }
}
