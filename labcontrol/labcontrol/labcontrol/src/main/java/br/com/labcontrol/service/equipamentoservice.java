package br.com.labcontrol.service;

import br.com.labcontrol.model.equipamento;
import br.com.labcontrol.repository.equipamentorepository;
import org.springframework.stereotype.Service;
import br.com.labcontrol.exception.equipamentonaoencontradoexception;

import java.util.List;

@Service
public class equipamentoservice {

    private final equipamentorepository repository;

    public equipamentoservice(equipamentorepository repository) {
        this.repository = repository;
    }

    public equipamento cadastrar(equipamento equipamento) {
        if (repository.existsByNumeroPatrimonio(
                equipamento.getNumeroPatrimonio())) {
            throw new IllegalArgumentException(
                    "Já existe um equipamento com esse patrimônio.");
        }

        return repository.save(equipamento);
    }

    public List<equipamento> listarTodos() {
        return repository.findAll();
    }

    public equipamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Equipamento não encontrado: " + id));
    }

    public equipamento atualizar(Long id, equipamento dados) {
        equipamento equipamento = buscarPorId(id);

        if (repository.existsByNumeroPatrimonioAndIdNot(
                dados.getNumeroPatrimonio(), id)) {
            throw new IllegalArgumentException(
                    "Já existe outro equipamento com esse patrimônio.");
        }

        equipamento.setNome(dados.getNome());
        equipamento.setNumeroPatrimonio(
                dados.getNumeroPatrimonio());
        equipamento.setCategoria(dados.getCategoria());
        equipamento.setQuantidade(dados.getQuantidade());

        return repository.save(equipamento);
    }

    public void excluir(Long id) {
        equipamento equipamento = buscarPorId(id);
        repository.delete(equipamento);
    }
}