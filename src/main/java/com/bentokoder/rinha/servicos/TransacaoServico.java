package com.bentokoder.rinha.servicos;

import com.bentokoder.rinha.dtos.TransacaoRequestDTO;
import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.entidade.Tipos;
import com.bentokoder.rinha.entidade.Transacao;
import com.bentokoder.rinha.repositorio.TransacaoRepositorio;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransacaoServico {
    private ClienteServico clienteServico;
    private TransacaoRepositorio transacaoRepositorio;

    public TransacaoServico(ClienteServico clienteServico, TransacaoRepositorio transacaoRepositorio) {
        this.clienteServico = clienteServico;
        this.transacaoRepositorio = transacaoRepositorio;
    }

    public Cliente executarTransacao(TransacaoRequestDTO data, Integer id){
        Instant now = Instant.now();
        Cliente cliente = this.clienteServico.obterClientePeloId(id);
        if(data.tipo() == Tipos.D)
            debito(cliente, data.valor());
        else
            cliente.setSaldo(cliente.getSaldo() + data.valor());

        this.transacaoRepositorio.save(new Transacao(null, data.tipo(), data.descricao(), now, cliente));
        this.clienteServico.salvarCliente(cliente);
        return cliente;
    };

    private void debito(Cliente cliente, Integer valor){
        if(cliente.getSaldo() - valor < -cliente.getLimite()){
            throw new RuntimeException();
        }

        cliente.setSaldo(cliente.getSaldo() - valor);

    }
}
