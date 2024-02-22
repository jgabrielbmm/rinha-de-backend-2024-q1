package com.bentokoder.rinha.servicos;

import com.bentokoder.rinha.dtos.TransacaoRequestDTO;
import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.entidade.Tipos;
import com.bentokoder.rinha.repositorio.TransacaoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransacaoServicoTest {

    @Mock
    private ClienteServico clienteServico;

    @Mock
    private TransacaoRepositorio transacaoRepositorio;

    @InjectMocks
    private TransacaoServico transacaoServico;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("deve realizar o debito com sucesso.")
    void executarTransacaoTipoDebito(){
        Cliente cliente = new Cliente(1, 100000, 0);
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(1000, Tipos.D, "something");
        when(clienteServico.obterClientePeloId(1)).thenReturn(cliente);

        Cliente clienteAposTransacao = transacaoServico.executarTransacao(transacao, cliente.getId());

        verify(clienteServico, times(1)).obterClientePeloId(1);
        verify(clienteServico, times(1)).salvarCliente(any());
        verify(transacaoRepositorio, times(1)).save(any());

        assertEquals(-1000, clienteAposTransacao.getSaldo());
    }

}