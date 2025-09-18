package com.leonardo.agendadorDeTarefas.infrastructure.business;

import com.leonardo.agendadorDeTarefas.infrastructure.Enums.StatusNotificationEnum;
import com.leonardo.agendadorDeTarefas.infrastructure.Exceptions.ResourceNotFoundExcepetion;
import com.leonardo.agendadorDeTarefas.infrastructure.business.dto.TarefasDTO;
import com.leonardo.agendadorDeTarefas.infrastructure.business.mapper.TarefaConverter;
import com.leonardo.agendadorDeTarefas.infrastructure.business.mapper.TarefaUpdateConverter;
import com.leonardo.agendadorDeTarefas.infrastructure.entity.TarefasEntity;
import com.leonardo.agendadorDeTarefas.infrastructure.repository.TarefasRepository;
import com.leonardo.agendadorDeTarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        //extraindo o token através do usuario
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        dto.setDataAlteracao(LocalDateTime.now());
        dto.setStatusNotificationEnum(StatusNotificationEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasPorPeirido(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDto(
                tarefasRepository.findBydataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefasEntity> listaDeTarefas = tarefasRepository.findByemailUsuario(email);

        return tarefaConverter.paraListaTarefasDto(listaDeTarefas);
    }

    public void deletaTarefaPorID(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundExcepetion e) {
            throw new ResourceNotFoundExcepetion("Não foi possível deletar a tarefa, id não encontrado" +
                    id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificationEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcepetion
                    ("Tarefa não encontrada" + id));
            entity.setStatusNotificationEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundExcepetion e) {
            throw new ResourceNotFoundExcepetion("Erro ao alterar status da tarefa" + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcepetion
                    ("Tarefa não encontrada" + id));
            tarefaUpdateConverter.updateDeTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundExcepetion e){
            throw new ResourceNotFoundExcepetion("Erro ao atualizar a tarefa" + e.getCause());
        }
    }
}
