package com.leonardo.agendadorDeTarefas.infrastructure.Exceptions;

public class ResourceNotFoundExcepetion extends RuntimeException{

    public ResourceNotFoundExcepetion (String mensagem) {
        super (mensagem);
    }

    public ResourceNotFoundExcepetion(String mensagem, Throwable throwable){
        super (mensagem, throwable);
    }
}
