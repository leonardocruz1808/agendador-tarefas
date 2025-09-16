package com.leonardo.agendadorDeTarefas.infrastructure.business.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {


    private String email;
    private String senha;



}
