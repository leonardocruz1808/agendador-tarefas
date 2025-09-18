package com.leonardo.agendadorDeTarefas.infrastructure.business.mapper;

import com.leonardo.agendadorDeTarefas.infrastructure.business.dto.TarefasDTO;
import com.leonardo.agendadorDeTarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

//Mapea os valores nulos e trás somente as alterações, fazendo a mesma ação do ternario: o parametro alterado ele salva
//o atributo vazio ele puxa do banco
@Mapper (componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    //MappingTarget utiliza a classe a sua frente como alvo primario de comparação, então caso a classe anterior
    //traga atributo vazio, ele preenche com a informação da referencia a sua frente
    void updateDeTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
