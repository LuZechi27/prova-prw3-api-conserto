package br.edu.ifsp.prw3.prova.controller;

import br.edu.ifsp.prw3.prova.conserto.*;
import br.edu.ifsp.prw3.prova.service.ConsertoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    private final ConsertoService service;

    public ConsertoController(ConsertoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ConsertoDetailsDTO> create(@RequestBody @Valid ConsertoDTO dto, UriComponentsBuilder uriBuilder) {
        Conserto conserto = service.create(dto);
        URI uri = uriBuilder.path("/conserto/{id}").buildAndExpand(conserto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ConsertoDetailsDTO(conserto));
    }

    @GetMapping
    public ResponseEntity<Page<ConsertoDetailsDTO>> getAll(Pageable pageable) {
        Page<ConsertoDetailsDTO> page = service.findAllAtivo(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("simplificado")
    public ResponseEntity<List<ConsertoSimplifiedDTO>> getSimplified() {
        List<ConsertoSimplifiedDTO> response = service.findAllSimplified();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsertoDetailsDTO> getConsertoById(@PathVariable Long id) {
        Optional<ConsertoDetailsDTO> conserto = service.findById(id);
        return conserto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid ConsertoUpdateDTO dto, @PathVariable @NotNull Long id) {
        if (dto.dataSaida() == null && dto.nomeMecanico() == null && dto.anosExperiencia() == null)
            return ResponseEntity.ok(new GenericResponseDTO(
                    "Nenhuma alteração feira, verifique se o corpo da requisição segue o padrão: { dataSaida: x, nomeMecanico: x, anosExperiencia: x } "
            ));
        ConsertoDetailsDTO conserto = service.update(dto, id);
        return ResponseEntity.ok(conserto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ConsertoDetailsDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
