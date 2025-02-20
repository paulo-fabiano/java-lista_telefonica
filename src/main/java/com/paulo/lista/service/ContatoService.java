package com.paulo.lista.service;

import com.paulo.lista.dto.ContatoDtoRequest;
import com.paulo.lista.dto.ContatoDtoResponse;
import com.paulo.lista.entity.Contato;
import com.paulo.lista.exception.UsuarioNaoExiste;
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

    public Contato atualizarContato(Long id, Contato contato) throws UsuarioNaoExiste {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if(!contatoOptional.isPresent()) {
            throw new UsuarioNaoExiste("Não existe usuário com esse ID.");
        }
        Contato c = contatoOptional.get();
        if (contato.getNome() != null) {
            c.setNome(contato.getNome());
        }
        if (contato.getTelefone() != null) {
            c.setTelefone(contato.getTelefone());
        }
        if (contato.getEmail() != null) {
            c.setTelefone(contato.getTelefone());
        }
        return contatoRepository.save(contato);
    }

    public String deletarContato(Long id) throws UsuarioNaoExiste {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (!contatoOptional.isPresent()) {
            throw new UsuarioNaoExiste("Não existe usuário com esse ID.");
        }
        contatoRepository.delete(contatoOptional.get());
        return "Contato deletado com sucesso!";
    }

}
