package com.paulo.lista.repository;

import com.paulo.lista.entity.Contato;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ContatoRepository extends JpaRepository <Contato, Long> {

}
