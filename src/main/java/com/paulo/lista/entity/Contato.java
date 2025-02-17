package com.paulo.lista.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "contatos" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contato {

    /*
        Entidade principal da API
     */

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private Long id;
    @Column( name = "nome", nullable = false )
    private String nome;
    @Column( name = "telefone", nullable = false )
    private String telefone;
    @Column( name = "email", nullable = false )
    private String email;

    /*
        Esperamos que todos os clientes possuam todos os atributos, para isso adicionamos "nullable = false",
        então quando o Hibernate for criar o banco automaticamente ou inserir algum dado ele sempre irá esperar que
        o atributo venha com algum valor.
     */

}
