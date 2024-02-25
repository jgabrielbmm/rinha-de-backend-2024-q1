package com.bentokoder.rinha.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TransacaoRequestDTO(
        @NotNull
        Integer valor,
        @NotNull
        Character tipo,
        @NotNull
        @Size(min = 1, max = 10, message = "Descrição deve conter no mínimo 1 e no máximo 10 caracteres")
        String descricao) {
}
