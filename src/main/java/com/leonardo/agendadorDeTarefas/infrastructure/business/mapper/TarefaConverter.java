package com.leonardo.agendadorDeTarefas.infrastructure.business.mapper;

import com.leonardo.agendadorDeTarefas.infrastructure.business.dto.TarefasDTO;
import com.leonardo.agendadorDeTarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")


    TarefasEntity paraTarefaEntity (TarefasDTO dto);

    TarefasDTO paraTarefaDTO (TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity (List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDto (List<TarefasEntity> entities);
}
