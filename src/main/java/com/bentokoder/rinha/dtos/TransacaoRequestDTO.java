package com.bentokoder.rinha.dtos;

import com.bentokoder.rinha.entidade.Tipos;

public record TransacaoRequestDTO(Integer valor, char tipo, String descricao) {
}
