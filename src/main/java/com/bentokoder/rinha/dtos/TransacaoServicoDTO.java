package com.bentokoder.rinha.dtos;

import com.bentokoder.rinha.entidade.Tipos;

public record TransacaoServicoDTO(Integer valor, Tipos tipo, String descricao) {
}
