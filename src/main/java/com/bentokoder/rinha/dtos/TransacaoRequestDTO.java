package com.bentokoder.rinha.dtos;

import com.bentokoder.rinha.entidade.Tipos;
import lombok.Getter;

public record TransacaoRequestDTO(Integer valor, Tipos tipo, String descricao) {
}
