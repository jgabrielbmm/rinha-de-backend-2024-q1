package com.bentokoder.rinha.servicos;

import com.bentokoder.rinha.dtos.TransacaoRequestDTO;
import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.entidade.Tipos;
import com.bentokoder.rinha.repositorio.TransacaoRepositorio;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

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
    void executarTransacaoTipoDebitoCaso1(){
        Cliente cliente = new Cliente(1, 100000, 0);
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(1000, Tipos.D, "something");
        when(clienteServico.obterClientePeloId(1)).thenReturn(cliente);

        Cliente clienteAposTransacao = transacaoServico.executarTransacao(transacao, cliente.getId());

        verify(clienteServico, times(1)).obterClientePeloId(1);
        verify(clienteServico, times(1)).salvarCliente(any());
        verify(transacaoRepositorio, times(1)).save(any());

        assertEquals(-1000, clienteAposTransacao.getSaldo());
    }

    @Test
    @DisplayName("deve falhar a tentativa de debito devido a falta de limite.")
    void executarTransacaoTipoDebitoCaso2(){
        Cliente cliente = new Cliente(1, 1000, 0);
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(1001, Tipos.D, "something");
        when(clienteServico.obterClientePeloId(1)).thenReturn(cliente);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Cliente clienteAposTransacao = transacaoServico.executarTransacao(transacao, cliente.getId());
        });

        verify(clienteServico, times(1)).obterClientePeloId(1);
        verify(clienteServico, times(0)).salvarCliente(any());
        verify(transacaoRepositorio, times(0)).save(any());

    }

    @Test
    @DisplayName("deve falhar ao tentar realizar um transacao para um cliente não cadastrado.")
    void executarTransacaoTipoDebitoCaso3(){
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(1001, Tipos.D, "something");

        Exception exception = assertThrows(Exception.class, () -> {
            Cliente clienteAposTransacao = transacaoServico.executarTransacao(transacao, 1);
        });

        verify(clienteServico, times(1)).obterClientePeloId(1);
        verify(clienteServico, times(0)).salvarCliente(any());
        verify(transacaoRepositorio, times(0)).save(any());

    }

    @Test
    @DisplayName("deve realizar um credito com sucesso.")
    void executarTransacaoTipoCredito(){
        Cliente cliente = new Cliente(1, 100000, 0);
        TransacaoRequestDTO transacao = new TransacaoRequestDTO(1000, Tipos.C, "something");
        when(clienteServico.obterClientePeloId(1)).thenReturn(cliente);

        Cliente clienteAposTransacao = transacaoServico.executarTransacao(transacao, cliente.getId());

        verify(clienteServico, times(1)).obterClientePeloId(1);
        verify(clienteServico, times(1)).salvarCliente(any());
        verify(transacaoRepositorio, times(1)).save(any());

        assertEquals(1000, clienteAposTransacao.getSaldo());
        assertEquals(100000, clienteAposTransacao.getLimite());
    }


}