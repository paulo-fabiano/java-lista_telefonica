package com.paulo.lista.controller;

import com.paulo.lista.entity.Contato;
import com.paulo.lista.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/contato" )
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private Contato contato;

    @PostMapping( "/criar" )
    public ResponseEntity<Contato> criarContato(@RequestBody Contato c) {

        contato = contatoService.salvarContato(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);

    }

    @GetMapping( "/listar" )
    public ResponseEntity<List<Contato>> listarContatos() {

        List<Contato> contatos = contatoService.listarContatos();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(contatos);

    }

    @DeleteMapping( "/deletar/{id}" )
    public ResponseEntity<?> deletarContato(@PathVariable Long id) {

        contatoService.deletarContato(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("O item foi deletado com sucesso!");

    }

}
