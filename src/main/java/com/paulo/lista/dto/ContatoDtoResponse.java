package com.paulo.lista.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ContatoDtoResponse {

    @JsonProperty( "id" )
    private Long id;
    @JsonProperty( "nome" )
    private String nome;
    @JsonProperty( "telefone" )
    private String telefone;
    @JsonProperty( "email" )
    private String email;

}
