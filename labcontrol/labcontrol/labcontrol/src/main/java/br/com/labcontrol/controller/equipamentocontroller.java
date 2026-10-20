package br.com.labcontrol.controller;

import br.com.labcontrol.model.equipamento;
import br.com.labcontrol.service.equipamentoservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class equipamentocontroller {

    private final equipamentoservice service;

    public equipamentocontroller(equipamentoservice service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<equipamento> cadastrar(
            @Valid @RequestBody equipamento equipamento) {

        equipamento novo = service.cadastrar(equipamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<equipamento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<equipamento> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<equipamento> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody equipamento equipamento) {

        return ResponseEntity.ok(service.atualizar(id, equipamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}