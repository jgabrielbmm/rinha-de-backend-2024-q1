package com.bentokoder.rinha.dtos;


import com.bentokoder.rinha.entidade.Transacao;

import java.util.List;

public record ExtratoViewDTO(Integer limite, Integer saldo, List<Transacao> transacoes) {
}
