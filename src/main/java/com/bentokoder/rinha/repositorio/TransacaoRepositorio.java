package com.bentokoder.rinha.repositorio;

import com.bentokoder.rinha.entidade.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepositorio extends JpaRepository<Transacao, Integer> {
}
