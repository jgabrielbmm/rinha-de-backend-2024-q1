package com.bentokoder.rinha.controllers;

import com.bentokoder.rinha.dtos.ExtratoViewDTO;
import com.bentokoder.rinha.dtos.TransacaoRequestDTO;
import com.bentokoder.rinha.dtos.TransacaoResponseDTO;
import com.bentokoder.rinha.dtos.TransacaoServicoDTO;
import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.entidade.Tipos;
import com.bentokoder.rinha.entidade.Transacao;
import com.bentokoder.rinha.servicos.ClienteServico;
import com.bentokoder.rinha.servicos.TransacaoServico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private TransacaoServico transacaoServico;
    public ClienteController(TransacaoServico transacaoServico) {
        this.transacaoServico = transacaoServico;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<TransacaoResponseDTO> transacao(@Valid @RequestBody TransacaoRequestDTO data, @PathVariable Integer id){
        Tipos tipo = Tipos.obterValorDoTipo(data.tipo());
        TransacaoServicoDTO transacaoServicoDTO = new TransacaoServicoDTO(data.valor(), tipo, data.descricao());
        Cliente cliente = this.transacaoServico.executarTransacao(transacaoServicoDTO, id);
        TransacaoResponseDTO responseDTO = new TransacaoResponseDTO(cliente.getLimite(), cliente.getSaldo());
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<ExtratoViewDTO> extrato(@PathVariable Integer id){
        ExtratoViewDTO extrato = this.transacaoServico.extrato(id);
        return ResponseEntity.ok().body(extrato);
    }

}
