package com.bentokoder.rinha.servicos;

import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.exceptions.ClientNotFoundException;
import com.bentokoder.rinha.repositorio.ClienteRepositorio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ClienteServicoTest {

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @InjectMocks
    private ClienteServico clienteServico;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("deve obter um cliente a partir do seu id.")
    void obterClientePeloIdCaso1(){
        Cliente clienteTest = new Cliente(1, 100000, 0);

        when(clienteRepositorio.findById(clienteTest.getId())).thenReturn(Optional.of(clienteTest));
        Cliente cliente = this.clienteServico.obterClientePeloId(clienteTest.getId());

        verify(clienteRepositorio, times(1)).findById(1);

        assertEquals(1, cliente.getId());
        assertEquals(100000, cliente.getLimite());
        assertEquals(0, cliente.getSaldo());
    }

    @Test
    @DisplayName("deve falhar ao buscar um cliente não cadastrado.")
    void obterClientePeloIdCaso2(){
        when(clienteRepositorio.findById(1)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            Cliente cliente = this.clienteServico.obterClientePeloId(1);
        });

        verify(clienteRepositorio, times(1)).findById(1);

    }
}