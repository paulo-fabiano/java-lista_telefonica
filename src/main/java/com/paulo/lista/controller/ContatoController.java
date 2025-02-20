package com.paulo.lista.controller;

import com.paulo.lista.dto.ContatoDtoRequest;
import com.paulo.lista.dto.ContatoDtoResponse;
import com.paulo.lista.entity.Contato;
import com.paulo.lista.error.ErrorMessage;
import com.paulo.lista.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "/contato" )
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    private Contato contato;

    @PostMapping( "/criar" )
    public ResponseEntity<?> criarContato(@RequestBody ContatoDtoRequest contatoDtoRequest) {
        try {
            Contato dto = contatoService.salvarContato(contatoService.tranformaEmContato(contatoDtoRequest));
            return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.tranformaEmContatoDtoResponse(dto));
        }
        catch (Exception e) {
            ErrorMessage error = new ErrorMessage (
                    HttpStatus.CONFLICT.value(),
                    "Não foi possível criar o usuário: Error: "+ e.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @GetMapping( "/listar" )
    public ResponseEntity<?> listarContatos() {
        try {
            List<Contato> listaContatos = contatoService.listarContatos();
            List<ContatoDtoResponse> dtoList = new ArrayList<>();
            for (Contato c : listaContatos) {
                ContatoDtoResponse dto = new ContatoDtoResponse(
                        c.getId(),
                        c.getNome(),
                        c.getTelefone(),
                        c.getEmail()
                );
                dtoList.add(dto);
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(dtoList);
        }
        catch (Exception e) {
            ErrorMessage error = new ErrorMessage(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error: " + e.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping( "/atualizar" )
    public ResponseEntity<?> atualizarContato(@PathVariable Long id, @RequestBody ContatoDtoRequest contatoDtoRequest) {
        try {
            Contato dto = contatoService.atualizarContato(id, contatoService.tranformaEmContato(contatoDtoRequest));
            return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.tranformaEmContatoDtoResponse(dto));
        }
        catch (Exception e) {
            ErrorMessage error = new ErrorMessage(
                    HttpStatus.BAD_REQUEST.value(),
                    "Erro ao atualizar o contato. Error: " + e.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping( "/deletar/{id}" )
    public ResponseEntity<?> deletarContato(@PathVariable Long id) {
        try {
            contatoService.deletarContato(id);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("O item foi deletado com sucesso!");
        }
        catch (Exception e) {
            ErrorMessage error = new ErrorMessage(
                    HttpStatus.NO_CONTENT.value(),
                    "Error ao apagar contato. Error: " + e.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
        }
    }

}
