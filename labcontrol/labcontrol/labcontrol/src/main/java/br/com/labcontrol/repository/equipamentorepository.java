package br.com.labcontrol.repository;

import br.com.labcontrol.model.equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface equipamentorepository
        extends JpaRepository<equipamento, Long> {

    boolean existsByNumeroPatrimonio(String numeroPatrimonio);

    boolean existsByNumeroPatrimonioAndIdNot(
            String numeroPatrimonio, Long id);
}