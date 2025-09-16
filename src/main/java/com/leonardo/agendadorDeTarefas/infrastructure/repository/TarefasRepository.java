package com.leonardo.agendadorDeTarefas.infrastructure.repository;

import com.leonardo.agendadorDeTarefas.infrastructure.business.dto.TarefasDTO;
import com.leonardo.agendadorDeTarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository <TarefasEntity, String> {

    List<TarefasEntity> findBydataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefasEntity> findByemailUsuario (String email);
}

