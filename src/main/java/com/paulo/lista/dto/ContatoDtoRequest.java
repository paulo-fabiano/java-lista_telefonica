package com.paulo.lista.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ContatoDtoRequest {

    @JsonProperty( "nome" )
    private String nome;
    @JsonProperty( "telefone" )
    private String telefone;
    @JsonProperty( "email" )
    private String email;

}
