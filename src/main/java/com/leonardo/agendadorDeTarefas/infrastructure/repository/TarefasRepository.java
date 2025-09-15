package com.leonardo.agendadorDeTarefas.infrastructure.repository;

import com.leonardo.agendadorDeTarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository <TarefasEntity, String> {

}
