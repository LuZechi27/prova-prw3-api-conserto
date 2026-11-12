package br.edu.ifsp.prw3.prova.service;

import br.edu.ifsp.prw3.prova.conserto.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsertoService {

    private final ConsertoRepository repository;

    public ConsertoService(ConsertoRepository repository) {
        this.repository = repository;
    }

    public Conserto create(ConsertoDTO dto) {
        Conserto conserto = new Conserto(dto);
        repository.save(conserto);

        return conserto;
    }

    public Page<ConsertoDetailsDTO> findAllAtivo(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(ConsertoDetailsDTO::new);
    }

    public List<ConsertoSimplifiedDTO> findAllSimplified() {
        return repository.findAllByAtivoTrue().stream().map(ConsertoSimplifiedDTO::new).toList();
    }

    public Optional<ConsertoDetailsDTO> findById(Long id) {
        Optional<Conserto> conserto = repository.findById(id);
        return conserto.map(ConsertoDetailsDTO::new);
    }

    public ConsertoDetailsDTO update(ConsertoUpdateDTO dto, @NotNull Long id) {
        Conserto conserto = repository.getReferenceById(id);
        conserto.update(dto);
        return new ConsertoDetailsDTO(conserto);
    }

    public void delete(Long id) {
        Conserto conserto = repository.getReferenceById(id);
        conserto.delete();
    }
}
