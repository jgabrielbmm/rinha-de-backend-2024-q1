package com.bentokoder.rinha.servicos;

import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.exceptions.ClientNotFoundException;
import com.bentokoder.rinha.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ClienteServico {

    private ClienteRepositorio clienteRepositorio;

    public ClienteServico(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public Cliente obterClientePeloId(Integer id){
        return this.clienteRepositorio.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public void salvarCliente(Cliente cliente){

        this.clienteRepositorio.save(cliente);
    }
}
