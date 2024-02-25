package com.bentokoder.rinha.controllers;

import com.bentokoder.rinha.dtos.TransacaoRequestDTO;
import com.bentokoder.rinha.dtos.TransacaoServicoDTO;
import com.bentokoder.rinha.dtos.TransacaoResponseDTO;
import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.entidade.Tipos;
import com.bentokoder.rinha.servicos.TransacaoServico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class TransacaoController {
    private TransacaoServico servico;
    public TransacaoController(TransacaoServico servico) {
        this.servico = servico;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<TransacaoResponseDTO> transacao(@Valid @RequestBody TransacaoRequestDTO data, @PathVariable Integer id){
        Tipos tipo = Tipos.obterValorDoTipo(data.tipo());
        TransacaoServicoDTO transacaoServicoDTO = new TransacaoServicoDTO(data.valor(), tipo, data.descricao());
        Cliente cliente = this.servico.executarTransacao(transacaoServicoDTO, id);
        TransacaoResponseDTO responseDTO = new TransacaoResponseDTO(cliente.getLimite(), cliente.getSaldo());
        return ResponseEntity.ok().body(responseDTO);
    }

}
