package com.leonardo.agendadorDeTarefas.infrastructure.business;

import com.leonardo.agendadorDeTarefas.infrastructure.Enums.StatusNotificationEnum;
import com.leonardo.agendadorDeTarefas.infrastructure.business.dto.TarefasDTO;
import com.leonardo.agendadorDeTarefas.infrastructure.business.mapper.TarefaConverter;
import com.leonardo.agendadorDeTarefas.infrastructure.entity.TarefasEntity;
import com.leonardo.agendadorDeTarefas.infrastructure.repository.TarefasRepository;
import com.leonardo.agendadorDeTarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa (String token, TarefasDTO dto){
        //extraindo o token através do usuario
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        dto.setDataAlteracao(LocalDateTime.now());
        dto.setStatusNotificationEnum(StatusNotificationEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasPorPeirido (LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefasDto(
                tarefasRepository.findBydataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail (String token){
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefasEntity> listaDeTarefas = tarefasRepository.findByemailUsuario(email);

        return tarefaConverter.paraListaTarefasDto(listaDeTarefas);
    }
}
