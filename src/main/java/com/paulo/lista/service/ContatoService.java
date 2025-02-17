package com.paulo.lista.service;

import com.paulo.lista.dto.ContatoDtoRequest;
import com.paulo.lista.dto.ContatoDtoResponse;
import com.paulo.lista.entity.Contato;
import com.paulo.lista.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    /*
        A API tem funções que criar, listam, atualizam e apagam os contatos da lista.
     */

    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoDtoResponse tranformaEmContatoDtoResponse(Contato contato) {

        ContatoDtoResponse contatoDtoResponse = new ContatoDtoResponse(
                contato.getId(),
                contato.getNome(),
                contato.getTelefone(),
                contato.getTelefone()
        );

        return contatoDtoResponse;

    }

    public Contato tranformaEmContato(ContatoDtoRequest contatoDtoRequest) {

        Contato contato = Contato.builder()
                .nome(contatoDtoRequest.getNome())
                .telefone(contatoDtoRequest.getTelefone())
                .email(contatoDtoRequest.getEmail())
                .build();

        return contato;

    };


    public Contato salvarContato(Contato contato) {
        try {
            return contatoRepository.save(contato);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    public Contato atualizarContato(Long id, Contato contato) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if(!contatoOptional.isPresent()) {
            throw new RuntimeException();
        }
        Contato c = contatoOptional.get();
        return contatoRepository.save(contato);
        /*
            Enquanto estudo uma forma de atualizar somente o dado recebido, irei ficar salvando toda a entidade.
            Por que se só vier o nome eu ainda não sei como alterar somente um atributo.
         */
    }

    public String deletarContato(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (!contatoOptional.isPresent()) {
            return "Não foi possível localizar o contato pelo Id";
        }
        contatoRepository.delete(contatoOptional.get());
        return "Contato deletado com sucesso!";
    }

}
