package com.bentokoder.rinha.repositorio;

import com.bentokoder.rinha.entidade.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepositorio extends JpaRepository<Transacao, Integer> {
    @Query("SELECT t FROM transacoes t WHERE t.cliente.id = :clienteId ORDER BY t.realizadaEm DESC LIMIT 10")
    List<Transacao> obterListaDeTransacaoPorId(Integer clienteId);
}
