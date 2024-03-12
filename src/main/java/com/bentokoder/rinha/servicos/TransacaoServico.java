package com.bentokoder.rinha.servicos;

import com.bentokoder.rinha.dtos.ExtratoViewDTO;
import com.bentokoder.rinha.dtos.TransacaoServicoDTO;
import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.entidade.Tipos;
import com.bentokoder.rinha.entidade.Transacao;
import com.bentokoder.rinha.exceptions.ClientNotFoundException;
import com.bentokoder.rinha.exceptions.InsufficientFundsException;
import com.bentokoder.rinha.repositorio.TransacaoRepositorio;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoServico {
    private ClienteServico clienteServico;
    private TransacaoRepositorio transacaoRepositorio;

    public TransacaoServico(ClienteServico clienteServico, TransacaoRepositorio transacaoRepositorio) {
        this.clienteServico = clienteServico;
        this.transacaoRepositorio = transacaoRepositorio;
    }

    public Cliente executarTransacao(TransacaoServicoDTO data, Integer id){
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


    public ExtratoViewDTO extrato(Integer id){
        Cliente cliente = this.clienteServico.obterClientePeloId(id);
        List<Transacao> transacoes = this.transacaoRepositorio.obterListaDeTransacaoPorId(id);
        return new ExtratoViewDTO(cliente.getLimite(), cliente.getSaldo(), transacoes);
    }

    private void debito(Cliente cliente, Integer valor){
        if(cliente.getSaldo() - valor < -cliente.getLimite()){
            throw new InsufficientFundsException();
        }
        cliente.setSaldo(cliente.getSaldo() - valor);
    }
}
